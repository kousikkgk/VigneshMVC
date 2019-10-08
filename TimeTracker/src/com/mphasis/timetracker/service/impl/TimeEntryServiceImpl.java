package com.mphasis.timetracker.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.mphasis.timetracker.dao.TimeEntryDao;
import com.mphasis.timetracker.service.TimeEntryService;

public class TimeEntryServiceImpl implements TimeEntryService {
	
	private TimeEntryDao timeEntryDao;
	
	
	public TimeEntryDao getTimeEntryDao() {
		return this.timeEntryDao;
	}

	public void setTimeEntryDao(TimeEntryDao timeEntryDao) {
		this.timeEntryDao = timeEntryDao;
	}

	@Override
	public String projectname(HttpSession session,String projName) throws SQLException {
		return timeEntryDao.projectname(session, projName);
	}

	@Override
	public List<String> processName(HttpSession session, String projName) throws SQLException {
		return timeEntryDao.processName(session, projName);
	}

	@Override
	public List<String> wrkRequest(HttpSession session, String processName, String stDate, String endDate)
			throws SQLException {
		return timeEntryDao.wrkRequest(session, processName, stDate, endDate);
	}

	@Override
	public List<String> activity(HttpSession session,String request)
			throws SQLException {
		return timeEntryDao.activity(session, request);
	}

	@Override
	public List<String> wrkUnit(String activity, String processName) throws SQLException {
		return timeEntryDao.wrkUnit(activity, processName);
	}



}
