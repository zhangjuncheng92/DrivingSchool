package com.zjc.drivingschool.db.model;

/**
 * 学车项目类型
 * 
 * @author LJ
 * @date 2016年7月21日
 */
public class ProductSubject
{	
	private String sid;// 项目类型ID

	private String subjectname;// 项目类型名称

	public String getSid()
	{
		return sid;
	}

	public void setSid(String sid)
	{
		this.sid = sid;
	}

	public String getSubjectname()
	{
		return subjectname;
	}

	public void setSubjectname(String subjectname)
	{
		this.subjectname = subjectname;
	}
	
}
