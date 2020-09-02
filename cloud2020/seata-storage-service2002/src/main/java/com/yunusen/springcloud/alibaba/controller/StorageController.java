package com.yunusen.springcloud.alibaba.controller;

import com.yunusen.springcloud.alibaba.domain.CommonResult;
import com.yunusen.springcloud.alibaba.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/29 14:06
 */
@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/storage/decrease")
    public CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        storageService.decrease(productId, count);
        return new CommonResult(200, "扣减库存成功");
    }

}
