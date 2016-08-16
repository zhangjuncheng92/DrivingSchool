package com.zjc.drivingschool.db.model;

import java.math.BigDecimal;

/**
 * 学车价格
 * 
 * @author LJ
 * @date 2016年7月21日
 */
public class ProductPrice
{	
	private String pid;// 普通价格
	
	private BigDecimal price;// 普通价格

	private BigDecimal vipprice;// VIP价格
	
	public String getPid()
	{
		return pid;
	}

	public void setPid(String pid)
	{
		this.pid = pid;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public BigDecimal getVipprice()
	{
		return vipprice;
	}

	public void setVipprice(BigDecimal vipprice)
	{
		this.vipprice = vipprice;
	}
	
}
