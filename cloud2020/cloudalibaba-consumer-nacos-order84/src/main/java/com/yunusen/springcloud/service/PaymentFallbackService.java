package com.yunusen.springcloud.service;

import com.yunusen.springcloud.entites.CommonResult;
import com.yunusen.springcloud.entites.Payment;
import org.springframework.stereotype.Component;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/28 18:24
 */
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级返回，--PaymentFallbackService", new Payment(id, "errorSerial"));
    }
}
