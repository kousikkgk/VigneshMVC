package com.mphasis.timetracker.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.mphasis.timetracker.dao.TimeEntryDao;
import com.mphasis.timetracker.service.TimeEntryService;
import com.mphasis.timetracker.viewBean.TimeBean;

public class TimeEntryServiceImpl implements TimeEntryService {
	
	private TimeEntryDao timeEntryDao;
	
	
	public TimeEntryDao getTimeEntryDao() {
		return this.timeEntryDao;
	}

	public void setTimeEntryDao(TimeEntryDao timeEntryDao) {
		this.timeEntryDao = timeEntryDao;
	}

	@Override
	public List<String> projectname(HttpSession session) throws SQLException {
		return timeEntryDao.projectname(session);
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

	@Override
	public List<TimeBean> insertDB(int empId, String empName, String wrName, String lcmName, String process,
			String activity, String activityDesc, String wkUnit, String wkUnitType, String remarks, java.sql.Timestamp stweek,double mon,
			double tue, double wed, double thu, double fri, double sat, double sun, String flag1, String flag2,
			String flag3, String flag4, String flag5, String flag6, String flag7, String updtFlag) throws SQLException {
		// TODO Auto-generated method stub
		return timeEntryDao.insertDB(empId, empName, wrName, lcmName, process, activity, activityDesc, wkUnit, wkUnitType, remarks,stweek, mon, tue, wed, thu, fri, sat, sun, flag1, flag2, flag3, flag4, flag5, flag6, flag7, updtFlag);
	}

	@Override
	public List<TimeBean> updateDB(int timeid,int empId, String empName, String wrName, String lcmName, String process,
			String activity, String activityDesc, String wkUnit, String wkUnitType, String remarks, java.sql.Timestamp stweek,double mon,
			double tue, double wed, double thu, double fri, double sat, double sun, String flag1, String flag2,
			String flag3, String flag4, String flag5, String flag6, String flag7, String updtFlag) throws SQLException {
		// TODO Auto-generated method stub
		return timeEntryDao.updateDB(timeid,empId, empName, wrName, lcmName, process, activity, activityDesc, wkUnit, wkUnitType, remarks,stweek, mon, tue, wed, thu, fri, sat, sun, flag1, flag2, flag3, flag4, flag5, flag6, flag7, updtFlag);
	}
	@Override
	public List<TimeBean> viewDB(int empId, Timestamp stweek) {
		return timeEntryDao.viewDB(empId, stweek);
	}

	@Override
	public List<TimeBean> editDB(int timeid, int empId) {
		// TODO Auto-generated method stub
		return timeEntryDao.editDB(timeid, empId);
	}

	@Override
	public int deleteDB(int timeid) {
		// TODO Auto-generated method stub
		return timeEntryDao.deleteDB(timeid);
	}

}
