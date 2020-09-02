package com.yunusen.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yunusen.springcloud.entites.CommonResult;
import com.yunusen.springcloud.entites.Payment;
import com.yunusen.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/25 20:51
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    /*
        @SentinelResource(value = "fallback")//没有配置
        @SentinelResource(value = "fallback", fallback = "handlerFallback")//fallback只负责业务异常
        @SentinelResource(value = "fallback", blockHandler = "blockHandler")// blockHandler只负责sentinel控制台配置异常
        @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
     */
    @RequestMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler", exceptionsToIgnore = IllegalAccessException.class)
    public CommonResult<Payment> fallback(@PathVariable Long id) throws IllegalAccessException {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4){
            throw new IllegalAccessException("IllegalAccessException, 非法参数异常");
        }else if(result.getData() == null) {
            throw new NullPointerException("NullPointerException, 该id无对应记录，空指针异常");
        }
        return result;
    }

    public CommonResult<Payment> handlerFallback(@PathVariable Long id, Throwable throwable){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(444, "handlerException兜底异常，内容：" + throwable.getMessage(), payment);
    }

    public CommonResult<Payment> blockHandler(@PathVariable Long id, BlockException ex){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(445, "blockHandler-sentinel限流，内容：" + ex.getMessage(), payment);
    }

    // openFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }
}
