package com.zjc.drivingschool.db.response;


import com.zjc.drivingschool.db.model.AppResponse;

/**
 * 收藏教练列表响应
 *
 * @author LJ
 * @date 2016年7月21日
 */
public class TeacherLocal extends AppResponse {

    /**
     * latitude : 30.50975684751174
     * longitude : 114.4459663012655
     * tid : fccffbf2624942ad99ade86daa47f195
     */

    private double latitude;
    private double longitude;
    private String tid;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
