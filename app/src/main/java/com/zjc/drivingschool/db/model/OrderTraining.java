package com.zjc.drivingschool.db.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderTraining
{
	private String id;// 订单id

	private String orderid;// 订单编号

	private String licenseid;// 驾照类型ID

	private String licensename;// 驾照类型名称

	private String subjectid;// 项目类型ID

	private String subjectname;// 项目类型名称

	private String carsid;// 车型ID

	private String carsname;// 车型名称

	private Boolean isvip;// 是否VIP

	private String pid;// 价格id

	private String state;// 订单状态 1.预订成功 2.已支付 3.申请退订 4.已退订 5.消费中 6.已消费 7.待评价 8.已完成 9.已取消

	private BigDecimal price;// 单价

	private Integer number;// 数量

	private BigDecimal total;// 总价

	private BigDecimal payment;// 支付金额

	private BigDecimal discount;// 优惠金额

	private String vid;// 代金券ID（多个用','分割）

	private String vname;// 优惠券名称（忽略）

	private String uname;// 下单人昵称

	private String uid;// 下单用户

	private Date ordertime;// 下单时间

	private Double longitude;// 发起位置经度

	private Double latitude;// 发起位置纬度

	private String contactsname;// 联系人姓名

	private String contactsphone;// 联系人电话

	private Date starttime;// 任务开始时间

	private Date endtime;// 任务结束时间

	private String sid;// 驾校ID

	private String sname;// 驾校名称

	private String tid;// 教练ID

	private String tname;// 教练名称

	private Date assigntime;// 分配时间

	private String assignuser;// 分配人

	private Date unsubtime;// 申请退订时间

	private Date refundtime;// 退订时间

	private String refunduser;// 处理退订user

	private Date canceltime;// 订单取消时间

	private Boolean delflag;// 删除标记

	private String note1;// 备用字段1

	private String note2;// 备用字段2

	private String note3;// 备用字段3

	private Date creatdate;// 创建时间

	private String creatuser;// 创建用户

	private Date lastupdatedate;// 上次修改时间

	private String lastupdateuser;// 上次修改用户

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id == null ? null : id.trim();
	}

	public String getOrderid()
	{
		return orderid;
	}

	public void setOrderid(String orderid)
	{
		this.orderid = orderid == null ? null : orderid.trim();
	}

	public String getLicenseid()
	{
		return licenseid;
	}

	public void setLicenseid(String licenseid)
	{
		this.licenseid = licenseid == null ? null : licenseid.trim();
	}

	public String getLicensename()
	{
		return licensename;
	}

	public void setLicensename(String licensename)
	{
		this.licensename = licensename == null ? null : licensename.trim();
	}

	public String getSubjectid()
	{
		return subjectid;
	}

	public void setSubjectid(String subjectid)
	{
		this.subjectid = subjectid == null ? null : subjectid.trim();
	}

	public String getSubjectname()
	{
		return subjectname;
	}

	public void setSubjectname(String subjectname)
	{
		this.subjectname = subjectname == null ? null : subjectname.trim();
	}

	public String getCarsid()
	{
		return carsid;
	}

	public void setCarsid(String carsid)
	{
		this.carsid = carsid == null ? null : carsid.trim();
	}

	public String getCarsname()
	{
		return carsname;
	}

	public void setCarsname(String carsname)
	{
		this.carsname = carsname == null ? null : carsname.trim();
	}

	public Boolean getIsvip()
	{
		return isvip;
	}

	public void setIsvip(Boolean isvip)
	{
		this.isvip = isvip;
	}

	public String getPid()
	{
		return pid;
	}

	public void setPid(String pid)
	{
		this.pid = pid == null ? null : pid.trim();
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state == null ? null : state.trim();
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public Integer getNumber()
	{
		return number;
	}

	public void setNumber(Integer number)
	{
		this.number = number;
	}

	public BigDecimal getTotal()
	{
		return total;
	}

	public void setTotal(BigDecimal total)
	{
		this.total = total;
	}

	public BigDecimal getPayment()
	{
		return payment;
	}

	public void setPayment(BigDecimal payment)
	{
		this.payment = payment;
	}

	public BigDecimal getDiscount()
	{
		return discount;
	}

	public void setDiscount(BigDecimal discount)
	{
		this.discount = discount;
	}

	public String getVid()
	{
		return vid;
	}

	public void setVid(String vid)
	{
		this.vid = vid == null ? null : vid.trim();
	}

	public String getVname()
	{
		return vname;
	}

	public void setVname(String vname)
	{
		this.vname = vname == null ? null : vname.trim();
	}

	public String getUname()
	{
		return uname;
	}

	public void setUname(String uname)
	{
		this.uname = uname == null ? null : uname.trim();
	}

	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid == null ? null : uid.trim();
	}

	public Date getOrdertime()
	{
		return ordertime;
	}

	public void setOrdertime(Date ordertime)
	{
		this.ordertime = ordertime;
	}

	public Double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
	}

	public Double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	public String getContactsname()
	{
		return contactsname;
	}

	public void setContactsname(String contactsname)
	{
		this.contactsname = contactsname == null ? null : contactsname.trim();
	}

	public String getContactsphone()
	{
		return contactsphone;
	}

	public void setContactsphone(String contactsphone)
	{
		this.contactsphone = contactsphone == null ? null : contactsphone.trim();
	}

	public Date getStarttime()
	{
		return starttime;
	}

	public void setStarttime(Date starttime)
	{
		this.starttime = starttime;
	}

	public Date getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Date endtime)
	{
		this.endtime = endtime;
	}

	public String getSid()
	{
		return sid;
	}

	public void setSid(String sid)
	{
		this.sid = sid == null ? null : sid.trim();
	}

	public String getSname()
	{
		return sname;
	}

	public void setSname(String sname)
	{
		this.sname = sname == null ? null : sname.trim();
	}

	public String getTid()
	{
		return tid;
	}

	public void setTid(String tid)
	{
		this.tid = tid == null ? null : tid.trim();
	}

	public String getTname()
	{
		return tname;
	}

	public void setTname(String tname)
	{
		this.tname = tname == null ? null : tname.trim();
	}

	public Date getAssigntime()
	{
		return assigntime;
	}

	public void setAssigntime(Date assigntime)
	{
		this.assigntime = assigntime;
	}

	public String getAssignuser()
	{
		return assignuser;
	}

	public void setAssignuser(String assignuser)
	{
		this.assignuser = assignuser == null ? null : assignuser.trim();
	}

	public Date getUnsubtime()
	{
		return unsubtime;
	}

	public void setUnsubtime(Date unsubtime)
	{
		this.unsubtime = unsubtime;
	}

	public Date getRefundtime()
	{
		return refundtime;
	}

	public void setRefundtime(Date refundtime)
	{
		this.refundtime = refundtime;
	}

	public String getRefunduser()
	{
		return refunduser;
	}

	public void setRefunduser(String refunduser)
	{
		this.refunduser = refunduser == null ? null : refunduser.trim();
	}

	public Date getCanceltime()
	{
		return canceltime;
	}

	public void setCanceltime(Date canceltime)
	{
		this.canceltime = canceltime;
	}

	public Boolean getDelflag()
	{
		return delflag;
	}

	public void setDelflag(Boolean delflag)
	{
		this.delflag = delflag;
	}

	public String getNote1()
	{
		return note1;
	}

	public void setNote1(String note1)
	{
		this.note1 = note1 == null ? null : note1.trim();
	}

	public String getNote2()
	{
		return note2;
	}

	public void setNote2(String note2)
	{
		this.note2 = note2 == null ? null : note2.trim();
	}

	public String getNote3()
	{
		return note3;
	}

	public void setNote3(String note3)
	{
		this.note3 = note3 == null ? null : note3.trim();
	}

	public Date getCreatdate()
	{
		return creatdate;
	}

	public void setCreatdate(Date creatdate)
	{
		this.creatdate = creatdate;
	}

	public String getCreatuser()
	{
		return creatuser;
	}

	public void setCreatuser(String creatuser)
	{
		this.creatuser = creatuser == null ? null : creatuser.trim();
	}

	public Date getLastupdatedate()
	{
		return lastupdatedate;
	}

	public void setLastupdatedate(Date lastupdatedate)
	{
		this.lastupdatedate = lastupdatedate;
	}

	public String getLastupdateuser()
	{
		return lastupdateuser;
	}

	public void setLastupdateuser(String lastupdateuser)
	{
		this.lastupdateuser = lastupdateuser == null ? null : lastupdateuser.trim();
	}
}