package com.zjc.drivingschool.db.model;

import java.io.Serializable;

public class Account implements Serializable{

	//主键
	private int id; 

	//账户账号
	private String accountNo; 

	//账户类型（1.平台账户 2.医院账户 3.用户账户）
	private int userType; 

	//账户关联用户或者医生主键
	private int userId; 

	//内部账号余额
	private Double total; 

	//内部账户冻结金额
	private Double totalFrozen; 

	//支付宝支付账号
	private String alipayAccount; 

	//微信支付账号
	private String wechatAccount; 

	//银联账号
	private String unionpayAccount; 

	public int  getId(){
		return this.id;
	}

	public void setId(int id){
		this.id=id;
	}

	public String  getAccountNo(){
		return this.accountNo;
	}

	public void setAccountNo(String accountNo){
		this.accountNo = accountNo == null ? null : accountNo.trim();
	}

	public int  getUserType(){
		return this.userType;
	}

	public void setUserType(int userType){
		this.userType=userType;
	}

	public int  getUserId(){
		return this.userId;
	}

	public void setUserId(int userId){
		this.userId=userId;
	}

	public Double  getTotal(){
		return this.total;
	}

	public void setTotal(Double total){
		this.total=total;
	}

	public Double  getTotalFrozen(){
		return this.totalFrozen;
	}

	public void setTotalFrozen(Double totalFrozen){
		this.totalFrozen=totalFrozen;
	}

	public String  getAlipayAccount(){
		return this.alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount){
		this.alipayAccount = alipayAccount == null ? null : alipayAccount.trim();
	}

	public String  getWechatAccount(){
		return this.wechatAccount;
	}

	public void setWechatAccount(String wechatAccount){
		this.wechatAccount = wechatAccount == null ? null : wechatAccount.trim();
	}

	public String  getUnionpayAccount(){
		return this.unionpayAccount;
	}

	public void setUnionpayAccount(String unionpayAccount){
		this.unionpayAccount = unionpayAccount == null ? null : unionpayAccount.trim();
	}
}
