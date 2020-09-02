package com.yunusen.springcloud.alibaba.controller;

import com.yunusen.springcloud.alibaba.domain.CommonResult;
import com.yunusen.springcloud.alibaba.domain.Order;
import com.yunusen.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/29 3:02
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }

}

