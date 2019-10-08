package com.mphasis.timetracker.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.mphasis.timetracker.dao.TimeEntryDao;
import com.sun.scenario.effect.impl.prism.PrCropPeer;

public class TimeEntryDaoImpl implements TimeEntryDao
{
	DataSource dataSource;
	java.sql.Timestamp stTimestamp=null;
	java.sql.Timestamp endTimestamp=null;

	public DataSource getDataSource()
	{
			return this.dataSource;
	}

	public void setDataSource(DataSource dataSource)
	{
			this.dataSource = dataSource;
	}
	
	@Override
	public String projectname(HttpSession session,String projName) throws SQLException {

		String query = "select distinct a.project_name from project a,emp_proj b,employee c where a.project_id = b.Project_id and b.emp_id = c.emp_id and c.emp_id = ? and c.emp_active = ? ";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, (String) session.getAttribute("id"));
		pstmt.setString(2, "Y");
		//System.out.println("project Name :"+projName);
		ResultSet resultSet = pstmt.executeQuery();
		if (resultSet.next()) {
			return resultSet.getString(1);
		} else {
			System.out.println("NULL VALUE");
			return null;
		} // return null;
	}

	@Override
	public List<String> processName(HttpSession session,String projName) throws SQLException {
		String query=("select distinct a.LCM_NAME from LCM a,plan_activity b,process c,project d where b.emp_id = ? and b.plan_name=c.pro_name and c.lcm_id=a.lcm_id and b.proj_id=d.project_id and d.project_name = ? ");
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, (String) session.getAttribute("id"));
		pstmt.setString(2, projName);
		ResultSet resultSet = pstmt.executeQuery();
		List<String> list=new ArrayList<String>();
		while (resultSet.next()) {
//			System.out.println("Dao"+resultSet.getString(1));
			list.add( resultSet.getString(1));
		}
		return list;
	}

	@Override
	public List<String> wrkRequest(HttpSession session,String processName,String stDate,String endDate) throws SQLException {
		String query="select distinct work_request_name from plan_activity where emp_id = ? and Activity_Status='In-Progress'  and Activity_start_date <= ? and Activity_end_date >= ? and Baseline='Y' and proj_id IN (SELECT project_id FROM emp_proj WHERE emp_id = ?) and lcm_name = ?";
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
		Date parsedDate = dateFormat.parse(stDate);
		stTimestamp = new java.sql.Timestamp(parsedDate.getTime());
		//System.out.println("Timestamp "+ timestamp);
		
		Date parsedDate1 = dateFormat.parse(endDate);
		endTimestamp = new java.sql.Timestamp(parsedDate1.getTime());
		//System.out.println("Timestamp "+ timestamp1);
		
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, (String) session.getAttribute("id"));
		pstmt.setTimestamp(2, endTimestamp);
		pstmt.setTimestamp(3, stTimestamp);
		pstmt.setString(4, (String) session.getAttribute("id"));
		pstmt.setString(5, processName);
		ResultSet resultSet = pstmt.executeQuery();
		List<String> list=new ArrayList<String>();
		while (resultSet.next()) {
		//	System.out.println("Dao"+resultSet.getString(1));
			list.add( resultSet.getString(1));
		}
		return list;
	}

	@Override
	public List<String> activity(HttpSession session, String request)
			throws SQLException {
		//System.out.println("inside activity dao");
		String query="select distinct activity_name from plan_activity where emp_id = ? and Activity_Status='In-Progress'  and Activity_start_date <= ? and Activity_end_date >= ? and Baseline='Y' and work_request_name = ? and proj_id IN (SELECT project_id FROM emp_proj WHERE emp_id = ?) order by activity_name";
		//System.out.println(stTimestamp+""+endTimestamp);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, (String) session.getAttribute("id"));
		pstmt.setTimestamp(2, endTimestamp);
		pstmt.setTimestamp(3, stTimestamp);
		pstmt.setString(4, request);
		pstmt.setString(5, (String) session.getAttribute("id"));
		ResultSet resultSet = pstmt.executeQuery();
		List<String> list=new ArrayList<String>();
		while (resultSet.next()) {
			//System.out.println("activity Dao"+resultSet.getString(1));
			list.add( resultSet.getString(1));
		}
		//System.out.println(list);
		return list;
	}

	@Override
	public List<String> wrkUnit(String activity, String processName) throws SQLException {
		String query=("select distinct wu_name from work_unit_details where Activity_name = ? and lcm_name = ? ");
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, activity);
		pstmt.setString(2, processName);
		ResultSet resultSet = pstmt.executeQuery();
		List<String> list=new ArrayList<String>();
		while (resultSet.next()) {
			System.out.println("Dao"+resultSet.getString(1));
			list.add( resultSet.getString(1));
		}
		return list;
	}

	
}
