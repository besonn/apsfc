package com.hpe.pojo;
/**
 * 
* @Name: TypesPo
* @Description: 菜品类别
* @author yukinoo
* @date 2019年9月4日
*
 */
public class TypePo {
	private int id; /// ID
	private String name;	/// 类别名称
	public TypePo(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public TypePo(String name) {
		super();
		this.name = name;
	}
	public TypePo() {
		super();
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
	
}
