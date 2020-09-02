package com.yunusen.springcloud.alibaba.controller;

import com.yunusen.springcloud.alibaba.domain.CommonResult;
import com.yunusen.springcloud.alibaba.service.AccountService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/29 14:47
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){

        accountService.decrease(userId, money);
        return new CommonResult(200, "扣减账户余额成功");
    }
}
