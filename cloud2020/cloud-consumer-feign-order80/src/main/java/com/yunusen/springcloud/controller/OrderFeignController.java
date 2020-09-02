package com.yunusen.springcloud.controller;

import com.yunusen.springcloud.entites.CommonResult;
import com.yunusen.springcloud.entites.Payment;
import com.yunusen.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/8 18:18
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){

        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        // openFeign-ribbon, 客户端一般默认等待一秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
