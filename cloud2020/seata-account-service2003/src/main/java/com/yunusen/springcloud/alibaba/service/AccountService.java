package com.yunusen.springcloud.alibaba.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/29 14:38
 */
public interface AccountService {

    /*
        扣减余额
     */
    void decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
