package com.zjc.drivingschool.db.model;

/**
 * 消息列表Item
 * 
 * @author LJ
 * @date 2016年7月21日
 */
public class MessageItem
{	
	private String mid;// 消息id
	
	private String title;// 消息标题
	
	private Boolean isread;// 是否已读，1.已读  0.未读
	
	private String creatdate;// 发送时间

	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		this.mid = mid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Boolean getIsread()
	{
		return isread;
	}

	public void setIsread(Boolean isread)
	{
		this.isread = isread;
	}

	public String getCreatdate()
	{
		return creatdate;
	}

	public void setCreatdate(String creatdate)
	{
		this.creatdate = creatdate;
	}
	
}
