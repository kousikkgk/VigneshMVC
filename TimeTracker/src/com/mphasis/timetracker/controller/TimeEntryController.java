package com.mphasis.timetracker.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mphasis.timetracker.delegate.TimeEntryDelegate;
import com.mphasis.timetracker.viewBean.TimeEntryBean;

@Controller
public class TimeEntryController {
	@Autowired
	private TimeEntryDelegate timeEntryDelegate;
	java.sql.Timestamp stTimestamp,endTimestamp=null;
	
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
	@RequestMapping(value="/save",params="save",method=RequestMethod.POST)
	@ResponseBody public String getformData(HttpServletRequest request,HttpServletResponse response,HttpSession session,
			@ModelAttribute("timeEntryBean") TimeEntryBean timeEntryBean) 
	{
		String projName=timeEntryBean.getProjectname();
		System.out.println(projName);
//		String prosResp=request.getParameter("get_member");
//		response.setContentType("application/json");
//		response.setCharacterEncoding("utf-8");	
//		JSONArray jArray=new JSONArray();
//		List<String> projdbList;
//		try {
//			projdbList = timeEntryDelegate.processName(session, prosResp);
//			System.out.println(projdbList);
//			for(String str:projdbList) 
//			{
//				HashMap<String, String>  map=new HashMap<String, String>();
//				map.put("name", str);
//				jArray.add(map);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return jArray.toString();
		return projName;
	}
	@RequestMapping(value="/timesave",params="submit",method=RequestMethod.POST)
	@ResponseBody public String getformSubmit(HttpServletRequest request,HttpServletResponse response,HttpSession session,
			@ModelAttribute("timeEntryBean") TimeEntryBean timeEntryBean) 
	{
		String projName=timeEntryBean.getProjectname();
		System.out.println(projName);
//		String prosResp=request.getParameter("get_member");
//		response.setContentType("application/json");
//		response.setCharacterEncoding("utf-8");	
//		JSONArray jArray=new JSONArray();
//		List<String> projdbList;
//		try {
//			projdbList = timeEntryDelegate.processName(session, prosResp);
//			System.out.println(projdbList);
//			for(String str:projdbList) 
//			{
//				HashMap<String, String>  map=new HashMap<String, String>();
//				map.put("name", str);
//				jArray.add(map);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return jArray.toString();
		return projName;
	}
}
	
