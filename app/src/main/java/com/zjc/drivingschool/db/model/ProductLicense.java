package com.zjc.drivingschool.db.model;

/**
 * 学车驾照类型名称
 * 
 * @author LJ
 * @date 2016年7月21日
 */
public class ProductLicense
{	
	private String lid;// 驾照类型ID

	private String licensename;// 驾照类型名称

	public String getLid()
	{
		return lid;
	}

	public void setLid(String lid)
	{
		this.lid = lid;
	}

	public String getLicensename()
	{
		return licensename;
	}

	public void setLicensename(String licensename)
	{
		this.licensename = licensename;
	}
	
}
