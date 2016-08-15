package com.zjc.drivingschool.db.model;

import java.io.Serializable;

public class UserCode implements Serializable{

	//
	private Integer id; 

	//
	private String phone; 

	//
	private String code; 

	//
	private Integer type; 

	//
	private Integer status; 

	//
	private String sendTime;

	public Integer  getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String  getPhone(){
		return this.phone;
	}

	public void setPhone(String phone){
		this.phone = phone == null ? null : phone.trim();
	}

	public String  getCode(){
		return this.code;
	}

	public void setCode(String code){
		this.code = code == null ? null : code.trim();
	}

	public Integer  getType(){
		return this.type;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer  getStatus(){
		return this.status;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public String  getSendTime(){
		return this.sendTime;
	}

	public void setSendTime(String sendTime){
		this.sendTime=sendTime;
	}
}
