package com.yunusen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/19 16:48
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderMain83 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain83.class, args);
    }
}
