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
    private double balance;// 余额

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
