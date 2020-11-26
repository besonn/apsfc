package com.hpe.pojo;
/**
 * 
* @Name: MenuInfo
* @Description: 
* @author yukinoo
* @date 2019年9月5日
*
 */
public class MenuInfo {
	private String name;	/// 菜品名称
	private String typename;	/// 菜品类别
	private String burden;	/// 原材料
	private String brief;	/// 简介
	private String price;	/// 单价
	private String sums;	/// 数量
	private String price1;	/// 折后单价
	private String sums1;	/// 优惠数量
	private String imgpath;	/// 图片路径
	private int id;	/// ID
	
	public MenuInfo() {
		super();
	}
	public MenuInfo(String name, String typename, String burden, String brief, String price, String sums,
			String price1, String sums1, String imgpath,int id) {
		super();
		this.id = id;
		this.name = name;
		this.typename = typename;
		this.burden = burden;
		this.brief = brief;
		this.price = price;
		this.sums = sums;
		this.price1 = price1;
		this.sums1 = sums1;
		this.imgpath = imgpath;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getBurden() {
		return burden;
	}
	public void setBurden(String burden) {
		this.burden = burden;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSums() {
		return sums;
	}
	public void setSums(String sums) {
		this.sums = sums;
	}
	public String getPrice1() {
		return price1;
	}
	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	public String getSums1() {
		return sums1;
	}
	public void setSums1(String sums1) {
		this.sums1 = sums1;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	
}
