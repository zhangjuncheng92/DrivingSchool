package com.zjc.drivingschool.db.response;

import com.zjc.drivingschool.db.model.AppResponse;

import java.math.BigDecimal;

/**
 * 订单详情响应
 *
 * @author LJ
 * @date 2016年7月21日
 */
public class OrderDetailResponse extends AppResponse {
    private String orid;// 订单id

    private String orderid;// 订单编号

    private String subjectid;// 项目类型ID

    private String subjectname;// 项目类型名称

    private String carsid;// 车型ID

    private String carsname;// 车型名称

    private Boolean isvip;// 是否VIP

    private String state;// 订单状态 1.预订成功 2.已支付 3.申请退订 4.已退订 5.消费中 6.已消费 7.待评价 8.已完成 9.已取消

    private String pid;// 价格id

    private double price;// 单价

    private int number;// 数量

    private double total;// 总价

    private double payment;// 支付金额

    private double discount;// 优惠金额

    private String uname;// 下单人昵称

    private String uid;// 下单用户

    private String ordertime;// 下单时间

    private Double longitude;// 发起位置经度

    private Double latitude;// 发起位置纬度

    private String contactsname;// 联系人姓名

    private String contactsphone;// 联系人电话

    private String starttime;// 任务开始时间

    private String endtime;// 任务结束时间

    private String sid;// 驾校ID

    private String sname;// 驾校名称

    private String tid;// 教练ID

    private String tname;// 教练名称

    private String assigntime;// 分配时间

    private String assignuser;// 分配人

    private String unsubtime;// 申请退订时间

    private String refundtime;// 退订时间

    private String refunduser;// 处理退订user

    private String canceltime;// 订单取消时间

    //private List<Voucher> vouchers;// 代金券列表

    public String getOrderid() {
        return orderid;
    }

    public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getCarsid() {
        return carsid;
    }

    public void setCarsid(String carsid) {
        this.carsid = carsid;
    }

    public String getCarsname() {
        return carsname;
    }

    public void setCarsname(String carsname) {
        this.carsname = carsname;
    }

    public Boolean getIsvip() {
        return isvip;
    }

    public void setIsvip(Boolean isvip) {
        this.isvip = isvip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getContactsname() {
        return contactsname;
    }

    public void setContactsname(String contactsname) {
        this.contactsname = contactsname;
    }

    public String getContactsphone() {
        return contactsphone;
    }

    public void setContactsphone(String contactsphone) {
        this.contactsphone = contactsphone;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getAssignuser() {
        return assignuser;
    }

    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    public String getRefunduser() {
        return refunduser;
    }

    public void setRefunduser(String refunduser) {
        this.refunduser = refunduser;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getAssigntime() {
        return assigntime;
    }

    public void setAssigntime(String assigntime) {
        this.assigntime = assigntime;
    }

    public String getUnsubtime() {
        return unsubtime;
    }

    public void setUnsubtime(String unsubtime) {
        this.unsubtime = unsubtime;
    }

    public String getRefundtime() {
        return refundtime;
    }

    public void setRefundtime(String refundtime) {
        this.refundtime = refundtime;
    }

    public String getCanceltime() {
        return canceltime;
    }

    public void setCanceltime(String canceltime) {
        this.canceltime = canceltime;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

}
