package com.demo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Leave {
	private Integer id;
	private String username;
	private String realname;
	private String classes;
	private String dept;
	private String duration;
	private Date startTime;
	private Date endTime;
	private String reason;	
	private String checkname;
	private String state;
	private Integer frequency;
	private String auditTeacher;
	private String auditLeader;
	private String auditMaster;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCheckname() {
		return checkname;
	}
	public void setCheckname(String checkname) {
		this.checkname = checkname;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	public String getAuditTeacher() {
		return auditTeacher;
	}
	public void setAuditTeacher(String auditTeacher) {
		this.auditTeacher = auditTeacher;
	}
	public String getAuditLeader() {
		return auditLeader;
	}
	public void setAuditLeader(String auditLeader) {
		this.auditLeader = auditLeader;
	}
	public String getAuditMaster() {
		return auditMaster;
	}
	public void setAuditMaster(String auditMaster) {
		this.auditMaster = auditMaster;
	}
	@Override
	public String toString() {
		return "Leave [id=" + id + ", username=" + username + ", realname=" + realname + ", classes=" + classes
				+ ", dept=" + dept + ", duration=" + duration + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", reason=" + reason + ", checkname=" + checkname + ", state=" + state + ", frequency=" + frequency
				+ ", auditTeacher=" + auditTeacher + ", auditLeader=" + auditLeader + ", auditMaster=" + auditMaster
				+ "]";
	}
	
}
