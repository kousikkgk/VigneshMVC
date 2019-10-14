package com.mphasis.timetracker.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mphasis.timetracker.delegate.TimeEntryDelegate;
import com.mphasis.timetracker.viewBean.LoginBean;
import com.mphasis.timetracker.viewBean.TimeBean;
import com.mphasis.timetracker.viewBean.TimeBeanImpl;
import com.mphasis.timetracker.viewBean.TimeEntryBean;

@Controller
public class TimeEntryController {
	@Autowired
	private TimeEntryDelegate timeEntryDelegate;
	java.sql.Timestamp stTimestamp,endTimestamp=null;
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpSession session,HttpServletRequest request, HttpServletResponse response,
			TimeEntryBean timeEntryBean) {
		System.out.println("view2");
		ModelAndView model = new ModelAndView("welcome");
		// LoginBean loginBean = new LoginBean();
		model.addObject("loggedInUser", session.getAttribute("empName")+" ["+session.getAttribute("id")+" ]");
		return model;
	}
	@RequestMapping("/project")
	@ResponseBody public String getPrjectName(HttpServletRequest request,HttpServletResponse response,HttpSession session) 
	{
		//String prosResp=request.getParameter("get_member");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");	
		JSONArray jArray=new JSONArray();
		List<String> projdbList;
		try {
			projdbList = timeEntryDelegate.projectname(session);
			System.out.println(projdbList);
			for(String str:projdbList) 
			{
				HashMap<String, String>  map=new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jArray.toString();
	}
	@RequestMapping("/process")
	@ResponseBody public String getProcessName(HttpServletRequest request,HttpServletResponse response,HttpSession session) 
	{
		String prosResp=request.getParameter("get_member");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");	
		JSONArray jArray=new JSONArray();
		List<String> projdbList;
		try {
			projdbList = timeEntryDelegate.processName(session, prosResp);
			System.out.println(projdbList);
			for(String str:projdbList) 
			{
				HashMap<String, String>  map=new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jArray.toString();
	}
	@RequestMapping("/request")
	@ResponseBody public String getRequestsName(HttpServletRequest request,HttpServletResponse response,HttpSession session) 
	{
		String processName=request.getParameter("procsName");
		String stDate=request.getParameter("stDate");
		String endDate=request.getParameter("endDate");
		session.setAttribute("stDate", stDate);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");	
		JSONArray jArray=new JSONArray();
		
		List<String> projdbList;
		try {
			projdbList = timeEntryDelegate.wrkRequest(session, processName, stDate, endDate);
			System.out.println(projdbList);
			for(String str:projdbList) 
			{
				HashMap<String, String>  map=new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jArray.toString();
	}
	@RequestMapping("/activity")
	@ResponseBody public String getActivityName(HttpServletRequest request,HttpServletResponse response,HttpSession session) 
	{
		String reqName=request.getParameter("reqName");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");	
		JSONArray jArray=new JSONArray();
		List<String> projdbList;
		try {
			projdbList = timeEntryDelegate.activity(session,reqName);
			System.out.println(projdbList);
			for(String str:projdbList) 
			{
				HashMap<String, String>  map=new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jArray.toString();
	}
	@RequestMapping("/wrkunit")
	@ResponseBody public String getWorkunit(HttpServletRequest request,HttpServletResponse response,HttpSession session) 
	{
		String activityName=request.getParameter("activity");
		String processName=request.getParameter("process");
		System.out.println(activityName+">>>>"+processName);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");	
		JSONArray jArray=new JSONArray();
		List<String> projdbList;
		try {
			projdbList = timeEntryDelegate.wrkUnit(activityName, processName);
			System.out.println(projdbList);
			for(String str:projdbList) 
			{
				HashMap<String, String>  map=new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jArray.toString();
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody 
	public ModelAndView getformData(HttpServletRequest request,HttpServletResponse response,HttpSession session,
			@ModelAttribute("timeEntryBean") TimeEntryBean timeEntryBean,@ModelAttribute("loginBean") LoginBean loginBean) 
	{
		System.out.println(request.getParameter("json"));
		ModelAndView model = new ModelAndView("welcome");
		String projName=timeEntryBean.getProjectname();
		model.addObject("projectName", timeEntryBean.getProjectname());
		Date parsedDate=null;
		System.out.println();
//		try {
//			parsedDate = dateFormat.parse(request.getParameter("startDate"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		java.sql.Timestamp stTimestamp = new java.sql.Timestamp(parsedDate.getTime());
		//model.addObject("startDate", stTimestamp);
		//model.addObject("endDate", timeEntryBean.getEndDate());
		int empId=Integer.parseInt((String)session.getAttribute("id"));
		String empName=(String)session.getAttribute("empName");
		String wrName=timeEntryBean.getWrkReqName();
        String lcmName=timeEntryBean.getProcessName();
        String process="Monitor System Operations";
        String activity=timeEntryBean.getActivity();
        String activityDesc="Monitor System Operations - Monitor Application";
        String wrkUnit=timeEntryBean.getWrkUnit();
        String WrkUntType="Usecase";
        String remarks="remarks";
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
		parsedDate = dateFormat.parse((String) session.getAttribute("stDate"));
		//session.removeAttribute("stDate");
		}catch(Exception e) 
		{
			
		}
		stTimestamp = new java.sql.Timestamp(parsedDate.getTime());
      //  java.sql.Timestamp stweek=timeEntryBean.getStartDate();
       // java.sql.Timestamp endweek=timeEntryBean.getEndDate();
        double mon=timeEntryBean.getMonEffort();
        double tue=timeEntryBean.getTueEffort();
        double wed=timeEntryBean.getWedEffort();
        double thu=timeEntryBean.getThuEffort();
        double fri=timeEntryBean.getFriEffort();
        double sat=timeEntryBean.getSatEffort();
        double sun=timeEntryBean.getSunEffort();
		String flag1,flag2,flag3,flag4,flag5,flag6,flag7,updtFlag;
		if (mon==0.0)
			flag1= "N";
		else
			flag1= "Y";
		if (tue==0.0)
			flag2= "N";
		else
			flag2= "Y";
		
		if (wed==0.0)
			flag3= "N";
		else
			flag3= "Y";
		
		if (thu==0.0)
			flag4= "N";
		else
			flag4= "Y";
		
		if (fri==0.0)
			flag5= "N";
		else
			flag5= "Y";
		
		if (sat==0.0)
			flag6= "N";
		else
			flag6= "Y";
		
		if (sun==0.0)
			flag7= "N";
		else
			flag7= "Y";
    	updtFlag="Y";
    	
    	// Map<String, String> map = new HashMap<String,String>();  
    	// list.add(bean.setProjectname(timeEntryBean.getProcessName()));
    	//TimeBeanImpl imp1=new TimeBeanImpl();
    	
    	List<TimeBean> impl = null;//=imp1.getTimebeanimpl();
    	//imp1.setTimebeanimpl(impl);
    	
    	try {
			impl=timeEntryDelegate.insertDB(empId, empName, wrName, lcmName, process, activity, activityDesc, wrkUnit,WrkUntType , remarks, stTimestamp,mon, tue, wed, thu, fri, sat, sun, flag1, flag2, flag3, flag4, flag5, flag6, flag7, updtFlag);
    	} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	System.out.println(impl);
//    	for (Map.Entry<String, String> entry : map.entrySet()) {
		    //System.out.println(entry.getKey() + " = " + entry.getValue());
		    
//		    switch(entry.getKey()) 
//		    {
//		    	case "projname":
//		    		bean.setProjectname(entry.getValue());
//		    		break;
//		    	case "process":
//		    		bean.setProcessname(entry.getValue());
//		    		break;
//		    	case "request":
//			    	bean.setRequestname(entry.getValue());	
//			    	break;
//		    	case "activity":
//		    		bean.setActivityname(entry.getValue());
//		    		break;
//		    	case "wkunit":
//		    		bean.setWorkunitname(entry.getValue());
//		    		break;
//		    	case "mon":
//		    		bean.setMon(Double.parseDouble(entry.getValue()));
//		    		break;
//		    	case "tue":
//		    		bean.setTue(Double.parseDouble(entry.getValue()));
//		    		break;
//		    	case "wed":
//		    		bean.setWed(Double.parseDouble(entry.getValue()));
//		    		break;
//		    	case "thu":
//		    		bean.setThu(Double.parseDouble(entry.getValue()));
//		    		break;
//		    	case "fri":
//		    		bean.setFri(Double.parseDouble(entry.getValue()));
//		    		break;
//		    	case "sat":
//		    		bean.setSat(Double.parseDouble(entry.getValue()));
//		    		break;
//		    	case "sun":
//		    		bean.setSun(Double.parseDouble(entry.getValue()));
//		    		break;
//		    		
//		    }
//		    bean.setProjectname(projName);
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
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		   // System.out.println("model"+map);
		    model.addObject("model", impl);
		//System.out.println(imp1.getTimebeanimpl());
		return model;
		//return model;
		//return empName;
	}
}
	
