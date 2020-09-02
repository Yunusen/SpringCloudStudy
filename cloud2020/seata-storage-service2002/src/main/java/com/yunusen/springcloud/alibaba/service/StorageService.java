package com.yunusen.springcloud.alibaba.service;

import org.apache.ibatis.annotations.Param;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/29 14:01
 */
public interface StorageService {
    /*
        扣减库存
     */
    void decrease(Long productId, Integer count);
}
