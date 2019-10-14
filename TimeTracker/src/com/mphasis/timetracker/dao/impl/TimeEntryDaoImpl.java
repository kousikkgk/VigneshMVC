package com.mphasis.timetracker.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.mphasis.timetracker.dao.TimeEntryDao;
import com.mphasis.timetracker.viewBean.TimeBean;
import com.mphasis.timetracker.viewBean.TimeBeanImpl;

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
	public List<String> projectname(HttpSession session) throws SQLException {

		String query = "select distinct a.project_name from project a,emp_proj b,employee c where a.project_id = b.Project_id and b.emp_id = c.emp_id and c.emp_id = ? and c.emp_active = ? ";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, (String) session.getAttribute("id"));
		pstmt.setString(2, "Y");
		ResultSet resultSet = pstmt.executeQuery();
		List<String> list=new ArrayList<String>();
		while (resultSet.next()) {
//			System.out.println("Dao"+resultSet.getString(1));
			list.add( resultSet.getString(1));
		}
		return list;
		
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

	@Override
	public List<TimeBean> insertDB(int empId, String empName, String wrName, String lcmName, String process,
			String activity, String activityDesc, String wkUnit, String wkUnitType, String remarks,java.sql.Timestamp stweek,
			double mon, double tue, double wed, double thu, double fri, double sat, double sun, String flag1,
			String flag2, String flag3, String flag4, String flag5, String flag6, String flag7, String updtFlag)
			throws SQLException {
		//TimeBean bean=new TimeBean();
		String projName = null;
		String projnamequery = "select distinct a.project_name from project a,emp_proj b,employee c where a.project_id = b.Project_id and b.emp_id = c.emp_id and c.emp_id = ? and c.emp_active = ? ";
		
		PreparedStatement prjnamest = dataSource.getConnection().prepareStatement(projnamequery);
		prjnamest.setInt(1, empId);
		prjnamest.setString(2, "Y");
		ResultSet rs1=prjnamest.executeQuery();
		while(rs1.next()) {
			projName=rs1.getString(1);
		}
		
		String projIdQuery="SELECT distinct project_id FROM emp_proj WHERE emp_id = ?";
		PreparedStatement stst = dataSource.getConnection().prepareStatement(projIdQuery);
		stst.setInt(1, empId);
		ResultSet rs=stst.executeQuery();
		int projId=0;
		while(rs.next()) {
			projId=rs.getInt(1);
		}
		String query="insert into timeentries (project_id, emp_id,emp_name, wr_name, lcm_name, process, activity, activity_desc, work_unit, work_unit_type, remarks, start_week, mon, tue, wed, thu, fri, sat, sun, flag1, flag2, flag3, flag4, flag5, flag6, flag7, update_flag) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement st = dataSource.getConnection().prepareStatement(query);
		st.setInt(1, projId);
        st.setInt(2, empId);
        st.setString(3, empName);
        st.setString(4, wrName);
        st.setString(5, lcmName);
        st.setString(6, process);
        st.setString(7, activity);
        st.setString(8, activityDesc);
        st.setString(9, wkUnit);
        st.setString(10, wkUnitType);
        st.setString(11, remarks);
        st.setTimestamp(12, stweek);
        //st.setDate(12, (java.sql.Date)session.getAttribute("stDate"));
        st.setDouble(13, mon);
        st.setDouble(14,tue);
        st.setDouble(15,wed);
        st.setDouble(16,thu);
        st.setDouble(17,fri);
        st.setDouble(18,sat);
        st.setDouble(19,sun);
        if (mon==0.0)
			st.setString(20, "N");
		else
			st.setString(20, "Y");
    	if (tue==0.0)
			st.setString(21, "N");
		else
			st.setString(21, "Y");
    	if (wed==0.0)
			st.setString(22, "N");
		else
			st.setString(22, "Y");
    	if (thu==0.0)
			st.setString(23, "N");
		else
			st.setString(23, "Y");
    	if (fri==0.0)
			st.setString(24, "N");
		else
			st.setString(24, "Y");
    	if (sat==0.0)
			st.setString(25, "N");
		else
			st.setString(25, "Y");
    	if (sun==0.0)
			st.setString(26, "N");
		else
			st.setString(26, "Y");
    	st.setString(27, "Y");
    	st.executeUpdate();
    	String insQuery="select * from timeentries where emp_id = ? and start_week = ? order by time_id desc;";
		PreparedStatement stmt = dataSource.getConnection().prepareStatement(insQuery);
		stmt.setInt(1, empId);
		stmt.setTimestamp(2, stweek);
		ResultSet resultSet = stmt.executeQuery();
		//List<String> list=new ArrayList<String>();
		HashMap<String,String> map=null;
		List<TimeBean> bean1 = new ArrayList<TimeBean>();
		TimeBeanImpl impl=new TimeBeanImpl();
		while (resultSet.next()) {
			map=new HashMap<String,String>();
//			System.out.println("Dao"+resultSet.getString(1));
			//list.add( resultSet.getString("wr_name"));
			bean1.add(new TimeBean(projName, resultSet.getString("lcm_name"), resultSet.getString("wr_name"), activity, resultSet.getString("work_unit"), Double.parseDouble(resultSet.getString("mon")), Double.parseDouble(resultSet.getString("tue")), Double.parseDouble(resultSet.getString("wed")), Double.parseDouble(resultSet.getString("thu")), Double.parseDouble(resultSet.getString("fri")), Double.parseDouble(resultSet.getString("sat")), Double.parseDouble(resultSet.getString("sun"))));
//			bean.setProjectname(projName);
//			bean.setProcessname(resultSet.getString("lcm_name"));
//			bean.setRequestname(resultSet.getString("wr_name"));
//			bean.setActivityname(activity);
//			bean.setWorkunitname(resultSet.getString("work_unit"));
//			bean.setMon(Double.parseDouble(resultSet.getString("mon")));
//			bean.setTue(Double.parseDouble(resultSet.getString("tue")));
//			bean.setWed(Double.parseDouble(resultSet.getString("wed")));
//			bean.setThu(Double.parseDouble(resultSet.getString("thu")));
//			bean.setFri(Double.parseDouble(resultSet.getString("fri")));
//			bean.setSat(Double.parseDouble(resultSet.getString("sat")));
//			bean.setSun(Double.parseDouble(resultSet.getString("sun")));
			impl.setTimebeanimpl(bean1);
		}
//		for(TimeBean str:impl.getTimebeanimpl()) {
//		System.out.println(str.getActivityname());
//		}
		return impl.getTimebeanimpl();
	}
	
}
