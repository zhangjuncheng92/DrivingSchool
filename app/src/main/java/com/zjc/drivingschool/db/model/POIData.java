package com.zjc.drivingschool.db.model;

/**
 * POI数据实体
 * @author LJ
 * @date 2016年8月3日
 */
public class POIData
{
	private String uid;// 数据id

	private String geotable_id;// lbs数据表ID

	private String title;// poi名称

	private String address;// poi地址

	private String province;// poi所属省

	private String city;// poi所属城市

	private String district;// poi所属区

	private int coord_type;// 坐标系定义,3代表百度经纬度坐标系统 4代表百度墨卡托系统

	private Long[] location;// 经纬度

	private String tags;// poi的标签

	private int distance;// 距离，单位为米

	private int weight;// 权重

	private String pushid;// 推送ID

	private String schoolid;// 驾校ID

	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

	public String getGeotable_id()
	{
		return geotable_id;
	}

	public void setGeotable_id(String geotable_id)
	{
		this.geotable_id = geotable_id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getDistrict()
	{
		return district;
	}

	public void setDistrict(String district)
	{
		this.district = district;
	}

	public int getCoord_type()
	{
		return coord_type;
	}

	public void setCoord_type(int coord_type)
	{
		this.coord_type = coord_type;
	}

	public Long[] getLocation()
	{
		return location;
	}

	public void setLocation(Long[] location)
	{
		this.location = location;
	}

	public String getTags()
	{
		return tags;
	}

	public void setTags(String tags)
	{
		this.tags = tags;
	}

	public int getDistance()
	{
		return distance;
	}

	public void setDistance(int distance)
	{
		this.distance = distance;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public String getPushid()
	{
		return pushid;
	}

	public void setPushid(String pushid)
	{
		this.pushid = pushid;
	}

	public String getSchoolid()
	{
		return schoolid;
	}

	public void setSchoolid(String schoolid)
	{
		this.schoolid = schoolid;
	}

}
