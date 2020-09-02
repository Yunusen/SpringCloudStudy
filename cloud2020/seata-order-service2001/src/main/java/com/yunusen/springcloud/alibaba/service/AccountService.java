package com.yunusen.springcloud.alibaba.service;

import com.yunusen.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/29 2:38
 */
@FeignClient(value = "seata-account-service")
public interface AccountService {

    @GetMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);

}
