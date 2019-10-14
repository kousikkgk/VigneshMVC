package com.mphasis.timetracker.viewBean;

import java.util.ArrayList;
import java.util.List;

public class TimeBean 
{
	private String projectname;
	private String processname;
	private String requestname;
	private String activityname;
	private String workunitname;
	private double mon;
	private double tue;
	private double wed;
	private double thu;
	private double fri;
	private double sat;
	private double sun;
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getProcessname() {
		return processname;
	}
	public void setProcessname(String processname) {
		this.processname = processname;
	}
	public String getRequestname() {
		return requestname;
	}
	public void setRequestname(String requestname) {
		this.requestname = requestname;
	}
	public String getActivityname() {
		return activityname;
	}
	public void setActivityname(String activiyname) {
		this.activityname = activiyname;
	}
	public String getWorkunitname() {
		return workunitname;
	}
	public void setWorkunitname(String workunitname) {
		this.workunitname = workunitname;
	}
	public double getMon() {
		return mon;
	}
	public void setMon(double mon) {
		this.mon = mon;
	}
	public double getTue() {
		return tue;
	}
	public void setTue(double tue) {
		this.tue = tue;
	}
	public double getWed() {
		return wed;
	}
	public void setWed(double wed) {
		this.wed = wed;
	}
	public double getThu() {
		return thu;
	}
	public void setThu(double thu) {
		this.thu = thu;
	}
	public double getFri() {
		return fri;
	}
	public void setFri(double fri) {
		this.fri = fri;
	}
	public double getSat() {
		return sat;
	}
	public void setSat(double sat) {
		this.sat = sat;
	}
	public double getSun() {
		return sun;
	}
	public void setSun(double sun) {
		this.sun = sun;
	}
	public TimeBean(String projectname, String processname, String requestname, String activityname,
			String workunitname, double mon, double tue, double wed, double thu, double fri, double sat, double sun) {
		super();
		this.projectname = projectname;
		this.processname = processname;
		this.requestname = requestname;
		this.activityname = activityname;
		this.workunitname = workunitname;
		this.mon = mon;
		this.tue = tue;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
		this.sat = sat;
		this.sun = sun;
	}
	
}
