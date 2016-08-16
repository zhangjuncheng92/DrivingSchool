package com.zjc.drivingschool.db.model;


/**
 * 支付接口附带参数
 * 
 * @author LJ
 * @date 2016年8月3日
 */
public class OptionalData
{
	private String uid;// 充值用户的ID(必传)

	private String loginname;// 充值用户的登录名(必传)

	private String note;// 操作说明

	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

	public String getLoginname()
	{
		return loginname;
	}

	public void setLoginname(String loginname)
	{
		this.loginname = loginname;
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
