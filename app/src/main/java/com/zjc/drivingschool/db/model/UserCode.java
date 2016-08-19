package com.zjc.drivingschool.db.model;

import java.io.Serializable;

public class UserCode implements Serializable{

	/**
	 * phone : 13296677400
	 * valcode : 209914
	 */

	private String phone;
	private String valcode;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getValcode() {
		return valcode;
	}

	public void setValcode(String valcode) {
		this.valcode = valcode;
	}
}
