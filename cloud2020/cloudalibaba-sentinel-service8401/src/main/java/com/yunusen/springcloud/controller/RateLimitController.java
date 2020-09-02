package com.yunusen.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yunusen.springcloud.entites.CommonResult;
import com.yunusen.springcloud.entites.Payment;
import com.yunusen.springcloud.myHandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/25 15:36
 */
@RestController
public class RateLimitController {

    @GetMapping(value = "/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200, "按资源名限流测试OK", new Payment(2020L,"serial001"));
    }

    public CommonResult handleException(BlockException e){
        return new CommonResult(444, e.getClass().getCanonicalName()+ "\t 服务不可用");
    }

    @GetMapping(value = "/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200, "按URL限流测试OK", new Payment(2020L, "serial002"));
    }

    @GetMapping(value = "/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200, "按客户自定义测试OK", new Payment(2020L, "serial003"));
    }
}
