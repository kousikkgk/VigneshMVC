package com.mphasis.timetracker.delegate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.naming.java.javaURLContextFactory;

import com.mphasis.timetracker.service.TimeEntryService;
import com.mphasis.timetracker.viewBean.TimeBean;

public class TimeEntryDelegate {
	private TimeEntryService timeEntryService;
	
	public TimeEntryService getTimeEntryService() {
		return this.timeEntryService;
	}
	public void setTimeEntryService(TimeEntryService timeEntryService) {
		this.timeEntryService = timeEntryService;
	}
	public List<String> projectname(HttpSession session) throws SQLException
	{
		return timeEntryService.projectname(session);
		
	}
	public List<String> processName(HttpSession session, String projName) throws SQLException
	{
		return timeEntryService.processName(session,projName);
		
	}
	public List<String> wrkRequest(HttpSession session,String processName,String stDate,String endDate) throws SQLException
	{
		return timeEntryService.wrkRequest(session,processName,stDate,endDate);
		
	}
	public List<String> activity(HttpSession session,String request) throws SQLException
	{
		return timeEntryService.activity(session,request);
		
	}
	public List<String> wrkUnit(String activity,String processName) throws SQLException
	{
		return timeEntryService.wrkUnit(activity,processName);
		
	}
	public List<TimeBean> insertDB(int empId,String empName,String wrName,String lcmName,String process,String activity,String activityDesc,String wkUnit,String wkUnitType,String remarks,java.sql.Timestamp stweek,double mon,double tue,double wed,double thu,double fri,double sat,double sun,String flag1,String flag2,String flag3,String flag4,String flag5,String flag6,String flag7,String updtFlag) throws SQLException 
	{
		return timeEntryService.insertDB(empId, empName, wrName, lcmName, process, activity, activityDesc, wkUnit, wkUnitType, remarks, stweek,mon, tue, wed, thu, fri, sat, sun, flag1, flag2, flag3, flag4, flag5, flag6, flag7, updtFlag);
	}
	public List<TimeBean> viewDB(int empId,java.sql.Timestamp stweek) throws SQLException
	{
		return timeEntryService.viewDB(empId, stweek);
		
	}
}
