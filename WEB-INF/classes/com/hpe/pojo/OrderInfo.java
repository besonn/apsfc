package com.hpe.pojo;
/**
 * 
* @Name: OrderInfo
* @Description: order.jsp窗口类
* @author yukinoo
* @date 2019年9月5日
*
 */
public class OrderInfo {
	private int userid; 	/// ID
	private String realname;	/// 用户真实姓名
	private String phone;	/// 用户电话（联系方式）
	private String address;	/// 用户地址
	private String name;	/// 菜品名称
	private String menusum;	/// 订购数量	
	private String price;	/// 菜品单价
	private String times;	/// 订购时间
	private String delivery;	/// 配送方式
	public OrderInfo(int userid, String realname, String phone, String address, String name, String menusum, String price,
			String times, String delivery) {
		super();
		this.userid = userid;
		this.realname = realname;
		this.phone = phone;
		this.address = address;
		this.name = name;
		this.menusum = menusum;
		this.price = price;
		this.times = times;
		this.delivery = delivery;
	}
	public OrderInfo(String realname, String phone, String address, String name, String menusum, String price,
			String times, String delivery) {
		super();
		this.realname = realname;
		this.phone = phone;
		this.address = address;
		this.name = name;
		this.menusum = menusum;
		this.price = price;
		this.times = times;
		this.delivery = delivery;
	}
	public OrderInfo() {
		super();
	}
	public int getUserid() {
		return userid;
	}
	public void setId(int userid) {
		this.userid = userid;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMenusum() {
		return menusum;
	}
	public void setMenusum(String menusum) {
		this.menusum = menusum;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
}
