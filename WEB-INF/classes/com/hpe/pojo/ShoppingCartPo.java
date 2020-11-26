package com.hpe.pojo;
/**
 * 
* @Name: ShoppingCartPo
* @Description: 餐车实体类
* @author yukinoo
* @date 2019年9月9日
*
 */
public class ShoppingCartPo {
	private String name;
	private String price;
	private int sum;
	private int menuid;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getMenuid() {
		return menuid;
	}
	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}
	public ShoppingCartPo() {
		super();
	}
	public ShoppingCartPo(String name, String price, int sum, int menuid) {
		super();
		this.name = name;
		this.price = price;
		this.sum = sum;
		this.menuid = menuid;
	}
	
}
