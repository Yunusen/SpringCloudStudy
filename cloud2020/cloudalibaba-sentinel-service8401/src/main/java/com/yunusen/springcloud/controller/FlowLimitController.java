package com.yunusen.springcloud.controller;

import java.util.concurrent.TimeUnit;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yunusen.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/20 11:35
 */
@RestController
@Slf4j
public class FlowLimitController {

//    @Autowired
//    private OrderService orderService;

    @GetMapping("/testA")
    public String testA(){
//        orderService.getOrder();
//        try{
//            TimeUnit.MILLISECONDS.sleep(800);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        return "---testA";
    }

    @GetMapping("/testB")
    public String testB(){
//        orderService.getOrder();//鏈路调用
        //log.info(Thread.currentThread().getName()+ "\t" + "/testB");
        return "---testB";
    }

    @GetMapping("/testD")
    public String testD(){
//        try{
//            TimeUnit.SECONDS.sleep(1);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        log.info("testD 测试RT");

        int i = 1/0;
        log.info("testD 测试异常比例");
        return "---testD";
    }

    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){
        return "testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException ex){
        return "deal_testHotKey";
    }
}
