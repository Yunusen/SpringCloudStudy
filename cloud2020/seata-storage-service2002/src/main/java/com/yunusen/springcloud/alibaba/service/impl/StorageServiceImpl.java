package com.yunusen.springcloud.alibaba.service.impl;

import com.yunusen.springcloud.alibaba.dao.StorageDao;
import com.yunusen.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/29 14:03
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("-->storage-service扣减库存开始");
        storageDao.decrease(productId, count);
        log.info("-->storage-service扣减库存结束");

    }
}
