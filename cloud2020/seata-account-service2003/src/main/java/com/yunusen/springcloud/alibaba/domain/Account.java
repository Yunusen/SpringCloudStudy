package com.yunusen.springcloud.alibaba.domain;

import java.math.BigDecimal;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/29 14:28
 */
public class Account {

    private Long id;
    /*
        用户id
     */
    private Long userId;
    /*
        总额度
     */
    private BigDecimal total;
    /*
        已用额度
     */
    private BigDecimal used;
    /*
        剩余额度
     */
    private BigDecimal residue;
}
