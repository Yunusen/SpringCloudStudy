package com.yunusen.springcloud.service;

import com.yunusen.springcloud.entites.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/7/23 9:13
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
