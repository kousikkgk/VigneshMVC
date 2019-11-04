package com.mphasis.timetracker.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mphasis.timetracker.delegate.TimeEntryDelegate;
import com.mphasis.timetracker.viewBean.TimeBean;
import com.mphasis.timetracker.viewBean.TimeEntryBean;

@Controller
public class TimeEntryController
{	
	@Autowired
	private TimeEntryDelegate timeEntryDelegate;
	java.sql.Timestamp stTimestamp, endTimestamp = null;
	
	@RequestMapping("/welcome")
	public ModelAndView displayLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			TimeEntryBean timeEntryBean, Model model) {
		ModelAndView model1 = new ModelAndView("welcome");
//		model1.addObject("timeEntryBean", timeEntryBean);
		model1.addObject("loggedInUser", session.getAttribute("empName") + " [" + session.getAttribute("id") + " ]");
		return model1;
	}
	
	@RequestMapping("/logout")
	public String logOut(HttpSession session) 
	{
		session.invalidate();
		return "redirect:/"; 
	}
	
	@RequestMapping("/editrow")
	@ResponseBody
	public String editRow(@RequestParam("timeiddata") int timeId,HttpSession session) {
		JSONArray jsonArray=null;
		int empId=0;
		List<TimeBean> listEdit=null;

		empId = Integer.parseInt((String) session.getAttribute("id"));
		jsonArray = new JSONArray();
		
		try {
			listEdit = timeEntryDelegate.editDB(timeId, empId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(TimeBean str:listEdit) 
		{
			HashMap<Object,Object> map=new HashMap<Object, Object>();
			map.put("projectname", str.getProjectname());
			map.put("processname", str.getProcessname());
			map.put("requestname", str.getRequestname());
			map.put("activity", str.getActivityname());
			map.put("workunit", str.getWorkunitname());
			map.put("mon", str.getMon());
			map.put("tue", str.getTue());
			map.put("wed", str.getWed());
			map.put("thu", str.getThu());
			map.put("fri", str.getFri());
			map.put("sat", str.getSat());
			map.put("sun", str.getSun());
			map.put("edit", str.getTimeid());
			map.put("del", str.getTimeid());
			map.put("timeid", str.getTimeid());
			jsonArray.add(map);
		}
		return	jsonArray.toString();
	}
	
	@RequestMapping("/deleterow")
	public String deleteRow(@RequestParam("timeiddata") int timeId,HttpServletResponse response,HttpSession session) 
	{
		try {
			timeEntryDelegate.deleteDB(timeId);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/viewdbdata";
	}
	@RequestMapping("/viewdbdata")
	@ResponseBody
	public String viewDBData(HttpServletResponse response,HttpSession session) {
		JSONArray jsonArray=null;
		int empId=0;
		Date parsedDate = null;
		List<TimeBean> viewData = null;
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		jsonArray = new JSONArray();
		
		//Convert String Date into Sql TimeStamp
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
			parsedDate = dateFormat.parse((String) session.getAttribute("stDate"));
			System.out.println(parsedDate);
		} catch (Exception e) {

		}
		stTimestamp = new java.sql.Timestamp(parsedDate.getTime());
		try {
			empId = Integer.parseInt((String) session.getAttribute("id"));
			System.out.println(empId+"--"+stTimestamp);
				viewData = timeEntryDelegate.viewDB(empId, stTimestamp);
				if(viewData!=null) {
					for (TimeBean str : viewData) {
						HashMap<Object, Object> map = new HashMap<>();
						map.put("projectname", str.getProjectname());
						map.put("processname", str.getProcessname());
						map.put("requestname", str.getRequestname());
						map.put("activity", str.getActivityname());
						map.put("workunit", str.getWorkunitname());
						map.put("mon", str.getMon());
						map.put("tue", str.getTue());
						map.put("wed", str.getWed());
						map.put("thu", str.getThu());
						map.put("fri", str.getFri());
						map.put("sat", str.getSat());
						map.put("sun", str.getSun());
						map.put("edit", str.getTimeid());
						map.put("del", str.getTimeid());
						map.put("timeid", str.getTimeid());
						jsonArray.add(map);
					}
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return jsonArray.toString();
	}
	@RequestMapping("/save")
	public String saveFormData(TimeEntryBean timeEntryBean,HttpSession session)
	{
		Date parsedDate = null;
		int timeidhidden,timeId,empId;
		String empName,wrName,lcmName,process,activity,activityDesc,wrkUnit,wrkUntType,remarks;
		String flag1, flag2, flag3, flag4, flag5, flag6, flag7, updtFlag;
		double mon,tue,wed,thu,fri,sat,sun;
		SimpleDateFormat dateFormat;
		List<TimeBean> impl = null;
		timeId=timeEntryBean.getTimeidtext();
		timeidhidden=timeEntryBean.getTimeidhidden();
		System.out.println("TIME-ID(Text): "+timeId);
		System.out.println("TIME-ID(Hidden): "+timeidhidden);
		
		empId = Integer.parseInt((String) session.getAttribute("id"));
		empName = (String) session.getAttribute("empName");
		wrName = timeEntryBean.getActivityName();
		lcmName = timeEntryBean.getProcessName();
		process = "Monitor System Operations";
		activity = timeEntryBean.getActivityName();
		activityDesc = "Monitor System Operations - Monitor Application";
		wrkUnit = timeEntryBean.getWrkunitName();
		wrkUntType = "Usecase";
		remarks = "remarks";
		
		dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		System.out.println(session.getAttribute("stDate"));
		try {
			parsedDate = dateFormat.parse((String) session.getAttribute("stDate"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		stTimestamp = new java.sql.Timestamp(parsedDate.getTime());
		
		mon = timeEntryBean.getMonEffort();
		tue = timeEntryBean.getTueEffort();
		wed = timeEntryBean.getWedEffort();
		thu = timeEntryBean.getThuEffort();
		fri = timeEntryBean.getFriEffort();
		sat = timeEntryBean.getSatEffort();
		sun = timeEntryBean.getSunEffort();
		
		if (mon == 0.0)
			flag1 = "N";
		else
			flag1 = "Y";
		if (tue == 0.0)
			flag2 = "N";
		else
			flag2 = "Y";

		if (wed == 0.0)
			flag3 = "N";
		else
			flag3 = "Y";

		if (thu == 0.0)
			flag4 = "N";
		else
			flag4 = "Y";

		if (fri == 0.0)
			flag5 = "N";
		else
			flag5 = "Y";

		if (sat == 0.0)
			flag6 = "N";
		else
			flag6 = "Y";

		if (sun == 0.0)
			flag7 = "N";
		else
			flag7 = "Y";
		updtFlag = "Y";
		
		if (timeidhidden==0) {
			System.out.println("inside save method");
			try {
				timeEntryDelegate.insertDB(empId, empName, wrName, lcmName, process, activity, activityDesc, wrkUnit,
						wrkUntType, remarks, stTimestamp, mon, tue, wed, thu, fri, sat, sun, flag1, flag2, flag3, flag4,
						flag5, flag6, flag7, updtFlag);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else {
			System.out.println("inside update method");
			try {
				timeEntryDelegate.updateDB(timeidhidden,empId, empName, wrName, lcmName, process, activity, activityDesc, wrkUnit,
						wrkUntType, remarks, stTimestamp, mon, tue, wed, thu, fri, sat, sun, flag1, flag2, flag3, flag4,
						flag5, flag6, flag7, updtFlag);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return "redirect:/welcome";
	}
	
	@RequestMapping("/dateview")
	public String dateView(@RequestParam("stweek") String stDate,HttpSession session,HttpServletResponse response) {
		
		//if(session.getAttribute("stDate")!=null) {
			session.removeAttribute("stDate");
		//}else {
			session.setAttribute("stDate", stDate);
		//}
		return "redirect:/viewdbdata";
	}	
}