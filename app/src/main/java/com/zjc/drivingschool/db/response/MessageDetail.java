package com.zjc.drivingschool.db.response;

import com.zjc.drivingschool.db.model.AppResponse;

/**
 * 消息列表响应
 *
 * @author LJ
 * @date 2016年7月21日
 */
public class MessageDetail extends AppResponse {


    /**
     * context : 您的订单[SJ160830187510000002]已由为民驾校接单,该驾校正在为你指派教练，请稍等！
     * creatdate : 2016-08-31 09:44:10
     * isread : true
     * mid : 9df6c66f82e1429ab8ff9748d4a36fc5
     * title : 订单接单通知
     */

    private String context;
    private String creatdate;
    private boolean isread;
    private String mid;
    private String title;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(String creatdate) {
        this.creatdate = creatdate;
    }

    public boolean isIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
