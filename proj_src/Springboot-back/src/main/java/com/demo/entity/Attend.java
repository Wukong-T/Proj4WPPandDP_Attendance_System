package com.demo.entity;

public class Attend {
	private String username;//用户名
	private String realname;//姓名
	private String classes; //组号
	private String dept;//部门
	private Integer early;//早退
	private Integer late;//迟到
	private Integer vacation;//请假
	private Integer truancy; //旷工
	private Integer score; //考勤成绩
	private String propose;// 提出疑义
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Integer getEarly() {
		return early;
	}
	public void setEarly(Integer early) {
		this.early = early;
	}
	public Integer getLate() {
		return late;
	}
	public void setLate(Integer late) {
		this.late = late;
	}
	public Integer getVacation() {
		return vacation;
	}
	public void setVacation(Integer vacation) {
		this.vacation = vacation;
	}
	public Integer getTruancy() {
		return truancy;
	}
	public void setTruancy(Integer truancy) {
		this.truancy = truancy;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getPropose() {
		return propose;
	}
	public void setPropose(String propose) {
		this.propose = propose;
	}
	
	@Override
	public String toString() {
		return "Attend [username=" + username + ", realname=" + realname + ", classes=" + classes + ", dept=" + dept
				+ ", early=" + early + ", late=" + late + ", vacation=" + vacation + ", truancy=" + truancy + ", score="
				+ score + ", propose=" + propose + "]";
	}
	
}
