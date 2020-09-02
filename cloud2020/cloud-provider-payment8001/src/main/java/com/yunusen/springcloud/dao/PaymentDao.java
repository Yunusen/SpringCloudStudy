package com.yunusen.springcloud.dao;

import com.yunusen.springcloud.entites.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/7/23 8:44
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
