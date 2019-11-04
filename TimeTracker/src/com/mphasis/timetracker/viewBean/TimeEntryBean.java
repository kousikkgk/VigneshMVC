package com.mphasis.timetracker.viewBean;

public class TimeEntryBean 
{
	private int timeidtext;
	private int timeidhidden;
	private String projectName;
	private String processName;
	private String requestName;
	private String activityName;
	private String wrkunitName;
	private double monEffort;
	private double tueEffort;
	private double wedEffort;
	private double thuEffort;
	private double friEffort;
	private double satEffort;
	private double sunEffort;
	
	public int getTimeidtext() {
		return timeidtext;
	}
	public void setTimeidtext(int timeidtext) {
		this.timeidtext = timeidtext;
	}
	public int getTimeidhidden() {
		return timeidhidden;
	}
	public void setTimeidhidden(int timeidhidden) {
		this.timeidhidden = timeidhidden;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getWrkunitName() {
		return wrkunitName;
	}
	public void setWrkunitName(String wrkunitName) {
		this.wrkunitName = wrkunitName;
	}
	public double getMonEffort() {
		return monEffort;
	}
	public void setMonEffort(double monEffort) {
		this.monEffort = monEffort;
	}
	public double getTueEffort() {
		return tueEffort;
	}
	public void setTueEffort(double tueEffort) {
		this.tueEffort = tueEffort;
	}
	public double getWedEffort() {
		return wedEffort;
	}
	public void setWedEffort(double wedEffort) {
		this.wedEffort = wedEffort;
	}
	public double getThuEffort() {
		return thuEffort;
	}
	public void setThuEffort(double thuEffort) {
		this.thuEffort = thuEffort;
	}
	public double getFriEffort() {
		return friEffort;
	}
	public void setFriEffort(double friEffort) {
		this.friEffort = friEffort;
	}
	public double getSatEffort() {
		return satEffort;
	}
	public void setSatEffort(double satEffort) {
		this.satEffort = satEffort;
	}
	public double getSunEffort() {
		return sunEffort;
	}
	public void setSunEffort(double sunEffort) {
		this.sunEffort = sunEffort;
	}
	@Override
	public String toString() {
		return "TimeEntryBean [timeidtext=" + timeidtext + ", timeidhidden=" + timeidhidden + ", projectName="
				+ projectName + ", processName=" + processName + ", requestName=" + requestName + ", activityName="
				+ activityName + ", wrkunitName=" + wrkunitName + ", monEffort=" + monEffort + ", tueEffort="
				+ tueEffort + ", wedEffort=" + wedEffort + ", thuEffort=" + thuEffort + ", friEffort=" + friEffort
				+ ", satEffort=" + satEffort + ", sunEffort=" + sunEffort + "]";
	}
	
		
}
