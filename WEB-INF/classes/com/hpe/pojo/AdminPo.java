package com.hpe.pojo;
/**
 * 
 * @author yukinoo
 *
 */
public class AdminPo {
	private int id;  /// 账户id
	private String name;   /// 账号
	private String pwd;   /// 密码
	private String authority;	/// 限权
	public AdminPo(int id, String name, String pwd, String authority) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.authority = authority;
	}
	public AdminPo(int id, String name, String pwd) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}
	public AdminPo() {
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
