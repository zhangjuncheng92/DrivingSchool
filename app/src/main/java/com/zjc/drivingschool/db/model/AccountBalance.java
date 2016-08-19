package com.zjc.drivingschool.db.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 学员账户余额响应
 *
 * @author LJ
 * @date 2016年7月21日
 */
public class AccountBalance implements Serializable {
    private int balance;// 余额

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
