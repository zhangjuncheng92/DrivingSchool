package com.zjc.drivingschool.db.response;

import com.zjc.drivingschool.db.model.AppResponse;

import java.util.List;

/**
 * 优惠卷
 *
 * @author LJ
 * @date 2016年7月21日
 */
public class Coupon extends AppResponse {


    /**
     * amount : 20
     * createoid : 42164b4381024eb1b6d6b9c0836aaa59
     * endtime : 2017-08-18 22:25:14
     * id : eb3ab92cd84049da99685915acd9d50d
     * isoverdue : false
     * isused : false
     * limitamount : 200
     * starttime : 2016-08-23 22:25:14
     * type : 1
     * uid : 42164b4381024eb1b6d6b9c0836aaa59
     * usedoid :
     * usedtime :
     * vname : 充值代金券
     */

    private List<VouchersEntity> vouchers;

    public List<VouchersEntity> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<VouchersEntity> vouchers) {
        this.vouchers = vouchers;
    }

    public static class VouchersEntity {
        private double amount;
        private String createoid;
        private String endtime;
        private String id;
        private boolean isoverdue;
        private boolean isused;
        private double limitamount;
        private String starttime;
        private int type;
        private String uid;
        private String usedoid;
        private String usedtime;
        private String vname;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getCreateoid() {
            return createoid;
        }

        public void setCreateoid(String createoid) {
            this.createoid = createoid;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsoverdue() {
            return isoverdue;
        }

        public void setIsoverdue(boolean isoverdue) {
            this.isoverdue = isoverdue;
        }

        public boolean isIsused() {
            return isused;
        }

        public void setIsused(boolean isused) {
            this.isused = isused;
        }

        public double getLimitamount() {
            return limitamount;
        }

        public void setLimitamount(double limitamount) {
            this.limitamount = limitamount;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsedoid() {
            return usedoid;
        }

        public void setUsedoid(String usedoid) {
            this.usedoid = usedoid;
        }

        public String getUsedtime() {
            return usedtime;
        }

        public void setUsedtime(String usedtime) {
            this.usedtime = usedtime;
        }

        public String getVname() {
            return vname;
        }

        public void setVname(String vname) {
            this.vname = vname;
        }
    }
}
