package com.hpe.pojo;
/**
 * 
* @Name: RankInfo
* @Description: 排行榜窗口类
* @author yukinoo
* @date 2019年9月6日
*
 */
public class RankInfo {
	private int menuid;
	private String menuname;
	private double sum;
	public RankInfo(int menuid, String menuname, double sum) {
		super();
		this.menuid = menuid;
		this.menuname = menuname;
		this.sum = sum;
	}
	public RankInfo(String menuname, double sum) {
		super();
		this.menuname = menuname;
		this.sum = sum;
	}
	public RankInfo() {
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
	
}
