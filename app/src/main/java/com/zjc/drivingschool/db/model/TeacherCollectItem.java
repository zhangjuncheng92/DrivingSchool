package com.zjc.drivingschool.db.model;

import java.io.Serializable;

/**
 * 收藏教练Item
 * 
 * @author LJ
 * @date 2016年7月21日
 */
public class TeacherCollectItem implements Serializable {
	private String tcid;// 教练收藏id
	
	private String tid;// 教练id

	private String teachername;// 教练名称
	
	private String photo;// 教练头像
	
	private int stars;// 评级1-5
	
	private String schoolname;// 所属驾校名称
	
	private boolean gender;// 性别
	
	private String phone;// 联系电话

	public String getTcid()
	{
		return tcid;
	}

	public void setTcid(String tcid)
	{
		this.tcid = tcid;
	}

	public String getTid()
	{
		return tid;
	}

	public void setTid(String tid)
	{
		this.tid = tid;
	}

	public String getTeachername()
	{
		return teachername;
	}

	public void setTeachername(String teachername)
	{
		this.teachername = teachername;
	}

	public String getPhoto()
	{
		return photo;
	}

	public void setPhoto(String photo)
	{
		this.photo = photo;
	}

	public int getStars()
	{
		return stars;
	}

	public void setStars(int stars)
	{
		this.stars = stars;
	}

	public String getSchoolname()
	{
		return schoolname;
	}

	public void setSchoolname(String schoolname)
	{
		this.schoolname = schoolname;
	}

	public boolean getGender()
	{
		return gender;
	}

	public void setGender(boolean gender)
	{
		this.gender = gender;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
}
