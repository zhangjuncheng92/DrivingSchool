package com.zjc.drivingschool.db.model;

/**
 * JPush推送的目标对象
 * 
 * @author LJ
 * @date 2016年7月21日
 */
public class JPushTarget
{	
	private String[] registration_id;// 这里仅使用注册ID

	public String[] getRegistration_id()
	{
		return registration_id;
	}

	public void setRegistration_id(String[] registration_id)
	{
		this.registration_id = registration_id;
	}
	
}
