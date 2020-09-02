package com.yunusen.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/29 3:07
 */
@Configuration
@MapperScan({"com.yunusen.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
