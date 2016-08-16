package com.zjc.drivingschool.db.model;

/**
 * 学车车型
 * 
 * @author LJ
 * @date 2016年7月21日
 */
public class ProductCars
{	
	private String cid;// 车型ID

	private String carsname;// 车型名称

	public String getCid()
	{
		return cid;
	}

	public void setCid(String cid)
	{
		this.cid = cid;
	}

	public String getCarsname()
	{
		return carsname;
	}

	public void setCarsname(String carsname)
	{
		this.carsname = carsname;
	}
	
}
