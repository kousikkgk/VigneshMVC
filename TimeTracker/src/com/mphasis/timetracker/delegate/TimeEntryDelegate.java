package com.mphasis.timetracker.delegate;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.mphasis.timetracker.service.TimeEntryService;

public class TimeEntryDelegate {
	private TimeEntryService timeEntryService;
	
	public TimeEntryService getTimeEntryService() {
		return this.timeEntryService;
	}
	public void setTimeEntryService(TimeEntryService timeEntryService) {
		this.timeEntryService = timeEntryService;
	}
	public String projectname(HttpSession session,String projName) throws SQLException
	{
		return timeEntryService.projectname(session,projName);
		
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
}
