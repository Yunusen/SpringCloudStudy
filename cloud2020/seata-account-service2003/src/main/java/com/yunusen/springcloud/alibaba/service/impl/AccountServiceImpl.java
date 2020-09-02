package com.yunusen.springcloud.alibaba.service.impl;


import com.yunusen.springcloud.alibaba.dao.AccountDao;
import com.yunusen.springcloud.alibaba.service.AccountService;
import io.micrometer.core.instrument.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/29 14:40
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("-->扣减余额开始");
//        try{
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        accountDao.decrease(userId, money);
        log.info("-->扣减余额结束");
    }
}
