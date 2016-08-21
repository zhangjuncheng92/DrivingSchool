package com.zjc.drivingschool.db.response;

import com.zjc.drivingschool.db.model.AppResponse;
import com.zjc.drivingschool.db.model.OrderItem;

import java.util.List;

/**
 * 订单列表响应
 *
 * @author LJ
 * @date 2016年7月21日
 */
public class OrderListResponse extends AppResponse {
    private List<OrderItem> orderitems;// 订单列表对象

    public List<OrderItem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List<OrderItem> orderitems) {
        this.orderitems = orderitems;
    }

}
