package com.demo.entity;

public class Audit {
	private String classes;
	private String checkname;
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getCheckname() {
		return checkname;
	}
	public void setCheckname(String checkname) {
		this.checkname = checkname;
	}
	@Override
	public String toString() {
		return "Audit [classes=" + classes + ", checkname=" + checkname + "]";
	}
	
}
