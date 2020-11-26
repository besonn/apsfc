package com.hpe.pojo;
/**
 * 
* @Name: NoticePo
* @Description: 公告
* @author yukinoo
* @date 2019年9月5日
*
 */
public class NoticePo {
	private int id;
	private String name;
	private String content;
	private String times;
	public NoticePo(int id, String name, String content, String times) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.times = times;
	}
	public NoticePo(String name, String content, String times) {
		super();
		this.name = name;
		this.content = content;
		this.times = times;
	}
	public NoticePo() {
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	
}
