package com.zjc.drivingschool.db.model;

import java.math.BigDecimal;

/**
 * 订单列表Item
 *
 * @author LJ
 * @date 2016年7月21日
 */
public class OrderItem
{
	private String orid;// 订单id

	private String orderid;// 订单编号

	private String title;// 订单标题

	private String uid;// 预约人ID

	private String uname;// 预约人

	private String state;// 订单状态 1.预订成功 2.已支付 3.申请退订 4.已退订 5.消费中 6.已消费 7.待评价 8.已完成 9.已取消

	private double total;// 总价

	private String ordertime;// 下单时间

	public String getOrid()
	{
		return orid;
	}

	public void setOrid(String orid)
	{
		this.orid = orid;
	}

	public String getOrderid()
	{
		return orderid;
	}

	public void setOrderid(String orderid)
	{
		this.orderid = orderid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public double getTotal()
	{
		return total;
	}

	public void setTotal(double total)
	{
		this.total = total;
	}

	public String getOrdertime()
	{
		return ordertime;
	}

	public void setOrdertime(String ordertime)
	{
		this.ordertime = ordertime;
	}

	public String getUname()
	{
		return uname;
	}

	public void setUname(String uname)
	{
		this.uname = uname;
	}

	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

}
