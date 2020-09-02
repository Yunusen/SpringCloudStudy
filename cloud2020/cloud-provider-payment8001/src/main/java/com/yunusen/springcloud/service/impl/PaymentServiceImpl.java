package com.yunusen.springcloud.service.impl;

import com.yunusen.springcloud.dao.PaymentDao;
import com.yunusen.springcloud.entites.Payment;
import com.yunusen.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/7/23 9:14
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
