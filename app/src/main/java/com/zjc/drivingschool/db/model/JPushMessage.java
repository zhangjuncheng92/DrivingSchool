package com.zjc.drivingschool.db.model;

/**
 * JPush消息内容体
 * 
 * @author LJ
 * @date 2016年7月21日
 */
public class JPushMessage
{
	
	private String msg_content;// 消息内容
	
	private OrderItem extras;// 附加订单参数

	public String getMsg_content()
	{
		return msg_content;
	}

	public void setMsg_content(String msg_content)
	{
		this.msg_content = msg_content;
	}

	public OrderItem getExtras()
	{
		return extras;
	}

	public void setExtras(OrderItem extras)
	{
		this.extras = extras;
	}
	
}
