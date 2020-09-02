package com.yunusen.springcloud.controller;

import com.yunusen.springcloud.entites.CommonResult;
import com.yunusen.springcloud.entites.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/25 20:28
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L, new Payment(1L, "1111"));
        hashMap.put(2L, new Payment(2L, "2222"));
        hashMap.put(3L, new Payment(3L, "3333"));
    }


    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult(200, "from mysql, serverPort: " + serverPort, payment);
        return result;
    }
}
