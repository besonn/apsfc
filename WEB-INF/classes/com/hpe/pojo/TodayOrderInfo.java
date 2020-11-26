package com.hpe.pojo;
/**
 * 
* @Name: TodayOrderInfo
* @Description:	销售统计类
* @author yukinoo
* @date 2019年9月9日
*
 */
public class TodayOrderInfo {
	private int menuid;
	private String menuname;
	private double sum;
	private String price;
	public TodayOrderInfo(int menuid, String menuname, double sum, String price) {
		super();
		this.menuid = menuid;
		this.menuname = menuname;
		this.sum = sum;
		this.price = price;
	}
	public TodayOrderInfo(String menuname, double sum, String price) {
		super();
		this.menuname = menuname;
		this.sum = sum;
		this.price = price;
	}
	public TodayOrderInfo() {
		super();
	}
	public int getMenuid() {
		return menuid;
	}
	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
