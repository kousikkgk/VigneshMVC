package com.mphasis.timetracker.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.mphasis.timetracker.viewBean.TimeBean;

public interface TimeEntryService {
	public List<String> projectname(HttpSession session) throws SQLException;
	public List<String> processName(HttpSession session,String projName) throws SQLException;
	public List<String> wrkRequest(HttpSession session,String processName,String stDate,String endDate) throws SQLException;
	public List<String> activity(HttpSession session,String request) throws SQLException;
	public List<String> wrkUnit(String activity,String processName) throws SQLException;
	public List<TimeBean> insertDB(int empId,String empName,String wrName,String lcmName,String process,String activity,String activityDesc,String wkUnit,String wkUnitType,String remarks,java.sql.Timestamp stweek, double mon,double tue,double wed,double thu,double fri,double sat,double sun,String flag1,String flag2,String flag3,String flag4,String flag5,String flag6,String flag7,String updtFlag) throws SQLException;
	public List<TimeBean> updateDB(int timeid,int empId,String empName,String wrName,String lcmName,String process,String activity,String activityDesc,String wkUnit,String wkUnitType,String remarks,java.sql.Timestamp stweek, double mon,double tue,double wed,double thu,double fri,double sat,double sun,String flag1,String flag2,String flag3,String flag4,String flag5,String flag6,String flag7,String updtFlag) throws SQLException;
	public List<TimeBean> viewDB(int empId,java.sql.Timestamp stweek);
	public List<TimeBean> editDB(int timeid,int empId);
	public int deleteDB(int timeid);
}
