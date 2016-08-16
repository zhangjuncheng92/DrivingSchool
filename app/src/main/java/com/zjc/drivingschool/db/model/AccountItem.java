package com.zjc.drivingschool.db.model;

/**
 * 账户列表Item
 * 
 * @author LJ
 * @date 2016年7月21日
 */
public class AccountItem
{	
	private String accid;// 主键ID

	private String uid;// 用户ID

	private int type;// 账户操作类型：1.充值，-1.消费

	private int otype;// 操作类型: 0.充值  1.练车  2.报名  3.报考

	private String orid;// 订单ID

	private Double totalfee;// 金额

	private String timestamp;// 操作时间

	private String note;// 备注

	public String getAccid()
	{
		return accid;
	}

	public void setAccid(String accid)
	{
		this.accid = accid;
	}

	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public int getOtype()
	{
		return otype;
	}

	public void setOtype(int otype)
	{
		this.otype = otype;
	}

	public String getOrid()
	{
		return orid;
	}

	public void setOrid(String orid)
	{
		this.orid = orid;
	}

	public Double getTotalfee()
	{
		return totalfee;
	}

	public void setTotalfee(Double totalfee)
	{
		this.totalfee = totalfee;
	}

	public String getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(String timestamp)
	{
		this.timestamp = timestamp;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

}
