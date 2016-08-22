package com.zjc.drivingschool.db.response;

import com.zjc.drivingschool.db.model.AppResponse;
import com.zjc.drivingschool.db.model.TeacherCollectItem;

import java.util.List;

/**
 * 收藏教练列表响应
 *
 * @author LJ
 * @date 2016年7月21日
 */
public class TeacherCollectListResponse extends AppResponse {
    private List<TeacherCollectItem> tcitems;// 收藏教练对象

    public List<TeacherCollectItem> getTcitems() {
        return tcitems;
    }

    public void setTcitems(List<TeacherCollectItem> tcitems) {
        this.tcitems = tcitems;
    }

}
