package com.demo.entity;

import java.util.Date;

public class Notice {
	private Integer id;
	private String realname;
	private String title;
	private String content;
	private Date date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRealname() {
		return realname;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", realname=" + realname + ", title=" + title + ", content=" + content + ", date="
				+ date + "]";
	}


	

	
	
}
