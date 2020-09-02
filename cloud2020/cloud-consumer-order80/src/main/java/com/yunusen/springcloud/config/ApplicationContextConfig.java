package com.yunusen.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/5 16:40
 */
@Configuration
public class ApplicationContextConfig {

    /**
     * @LoadBalanced 负载均衡
     * @return
     */
    @Bean
    //@LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
