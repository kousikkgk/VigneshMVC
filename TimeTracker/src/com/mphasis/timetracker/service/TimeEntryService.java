package com.mphasis.timetracker.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

public interface TimeEntryService {
	public String projectname(HttpSession session,String projName) throws SQLException;
	public List<String> processName(HttpSession session,String projName) throws SQLException;
	public List<String> wrkRequest(HttpSession session,String processName,String stDate,String endDate) throws SQLException;
	public List<String> activity(HttpSession session,String request) throws SQLException;
	public List<String> wrkUnit(String activity,String processName) throws SQLException;
}
