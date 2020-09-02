package com.yunusen.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yunusen.springcloud.entites.CommonResult;
import com.yunusen.springcloud.entites.Payment;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/25 15:55
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException1(BlockException e){
        return new CommonResult(4444,"按客户自定义的global handlerException-1");
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(4444,"按客户自定义的global handlerException-2");
    }
}
