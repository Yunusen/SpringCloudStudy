package com.yunusen.springcloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/24 16:41
 */
@Service
@Slf4j
public class OrderService {

    @SentinelResource("getOrder")
    public String getOrder(){
        log.info("==order==");
        return "order";
    }
}
