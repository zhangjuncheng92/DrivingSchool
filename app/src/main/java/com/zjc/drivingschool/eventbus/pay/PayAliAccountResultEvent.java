package com.zjc.drivingschool.eventbus.pay;

/**
 * Created by Administrator on 2015/7/14.
 */
public class PayAliAccountResultEvent {
    String result;

    public PayAliAccountResultEvent(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
