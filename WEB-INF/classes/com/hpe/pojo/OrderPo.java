package com.hpe.pojo;
/**
 * 
* @Name: OrdersPo
* @Description: 订单
* @author yukinoo
* @date 2019年9月4日
*
 */
public class OrderPo {
	private int id;	/// ID
	private String userid;	/// 用户ID
	private String menuid;	/// 菜品ID
	private String menusum;	/// 订购数量
	private String times;	/// 时间
	private String delivery;/// 状态
	public OrderPo(int id, String userid, String menuid, String menusum, String times, String delivery) {
		super();
		this.id = id;
		this.userid = userid;
		this.menuid = menuid;
		this.menusum = menusum;
		this.times = times;
		this.delivery = delivery;
	}
	public OrderPo(String userid, String menuid, String menusum, String times, String delivery) {
		super();
		this.userid = userid;
		this.menuid = menuid;
		this.menusum = menusum;
		this.times = times;
		this.delivery = delivery;
	}
	public OrderPo() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getMenusum() {
		return menusum;
	}
	public void setMenusum(String menusum) {
		this.menusum = menusum;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	@Override
	public String toString() {
		return "OrderPo [id=" + id + ", userid=" + userid + ", menuid=" + menuid + ", menusum=" + menusum + ", times="
				+ times + ", delivery=" + delivery + "]";
	}
	
}
