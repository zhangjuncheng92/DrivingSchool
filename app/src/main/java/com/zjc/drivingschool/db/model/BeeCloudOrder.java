package com.zjc.drivingschool.db.model;


import org.json.JSONObject;

/**
 * BeeCloud订单实体
 * 
 * @author LJ
 * @date 2016年7月21日
 */
public class BeeCloudOrder
{	
	private String id;// 订单记录的唯一标识，可用于查询单笔记录

	private String bill_no;// 订单编号
	
	private int total_fee;// 订单金额，单位为分
	
	private String trade_no;// 渠道交易号， 当支付成功时有值
	
	private String channel;// 渠道类型 WX、ALI、UN、JD、YEE、KUAIQIAN、PAYPAL、BD
	
	private String sub_channel;// 子渠道类型
	
	private String title;// 订单标题
	
	private Long create_time;// 订单创建时间, 毫秒时间戳, 13位
	
	private Boolean spay_result;// 订单是否成功
	
	private Boolean revert_result;// 订单是否已经撤销
	
	private Boolean refund_result;// 订单是否已经退款
	
	private JSONObject optional; //附加数据,用户自定义的参数，将会在webhook通知中原样返回，该字段是JSON格式的字符串 {"key1":"value1","key2":"value2",...}
	
	private JSONObject message_detail; //渠道详细信息， 当need_detail传入true时返回

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getBill_no()
	{
		return bill_no;
	}

	public void setBill_no(String bill_no)
	{
		this.bill_no = bill_no;
	}

	public int getTotal_fee()
	{
		return total_fee;
	}

	public void setTotal_fee(int total_fee)
	{
		this.total_fee = total_fee;
	}

	public String getTrade_no()
	{
		return trade_no;
	}

	public void setTrade_no(String trade_no)
	{
		this.trade_no = trade_no;
	}

	public String getChannel()
	{
		return channel;
	}

	public void setChannel(String channel)
	{
		this.channel = channel;
	}

	public String getSub_channel()
	{
		return sub_channel;
	}

	public void setSub_channel(String sub_channel)
	{
		this.sub_channel = sub_channel;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Long getCreate_time()
	{
		return create_time;
	}

	public void setCreate_time(Long create_time)
	{
		this.create_time = create_time;
	}

	public Boolean getSpay_result()
	{
		return spay_result;
	}

	public void setSpay_result(Boolean spay_result)
	{
		this.spay_result = spay_result;
	}

	public Boolean getRevert_result()
	{
		return revert_result;
	}

	public void setRevert_result(Boolean revert_result)
	{
		this.revert_result = revert_result;
	}

	public Boolean getRefund_result()
	{
		return refund_result;
	}

	public void setRefund_result(Boolean refund_result)
	{
		this.refund_result = refund_result;
	}

	public JSONObject getOptional()
	{
		return optional;
	}

	public void setOptional(JSONObject optional)
	{
		this.optional = optional;
	}

	public JSONObject getMessage_detail()
	{
		return message_detail;
	}

	public void setMessage_detail(JSONObject message_detail)
	{
		this.message_detail = message_detail;
	}
	
}
