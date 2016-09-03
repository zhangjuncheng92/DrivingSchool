package com.zjc.drivingschool.eventbus;


import com.zjc.drivingschool.db.response.Coupon;

/**
 * Created by Administrator on 2015/7/14.
 */
public class StudyCouponChooseEvent {
    private Coupon.VouchersEntity vouchersEntity;

    public StudyCouponChooseEvent(Coupon.VouchersEntity vouchersEntity) {
        this.vouchersEntity = vouchersEntity;
    }

    public Coupon.VouchersEntity getVouchersEntity() {
        return vouchersEntity;
    }
}
