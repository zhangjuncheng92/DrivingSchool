package com.zjc.drivingschool.db.response;

import com.zjc.drivingschool.db.model.AppResponse;
import com.zjc.drivingschool.db.model.ProductCars;
import com.zjc.drivingschool.db.model.ProductPrice;
import com.zjc.drivingschool.db.model.ProductSubject;

import java.util.List;

/**
 * 学员产品响应
 * 
 * @author LJ
 * @date 2016年7月21日
 */
public class UserProductResponse extends AppResponse
{
	private ProductPrice productprice;// 价格

	private List<ProductCars> cars;// 车型

	private List<ProductSubject> subject;// 科目

	public ProductPrice getProductprice()
	{
		return productprice;
	}

	public void setProductprice(ProductPrice productprice)
	{
		this.productprice = productprice;
	}

	public List<ProductCars> getCars()
	{
		return cars;
	}

	public void setCars(List<ProductCars> cars)
	{
		this.cars = cars;
	}

	public List<ProductSubject> getSubject()
	{
		return subject;
	}

	public void setSubject(List<ProductSubject> subject)
	{
		this.subject = subject;
	}

}
