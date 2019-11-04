package com.mphasis.timetracker.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mphasis.timetracker.delegate.TimeEntryDelegate;

@Controller
public class PageProcessingController {
	
	@Autowired
	private TimeEntryDelegate timeEntryDelegate;
	
	@RequestMapping("/project")
	@ResponseBody
	public String getProjectName(HttpServletResponse response,HttpSession session) {
		JSONArray jArray=null;
		List<String> projdbList=null;
		HashMap<String, String> map = null;
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		jArray=new JSONArray();
		try {
			projdbList = timeEntryDelegate.projectname(session);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(projdbList!=null) {
			for (String str : projdbList) {
				map = new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		}
		return jArray.toString();
	}
	
	@RequestMapping("/process")
	@ResponseBody
	public String getProcessName(@RequestParam("get_member1")String projSel,HttpSession session,HttpServletResponse response) {
		
		JSONArray jArray=null;
		List<String> processDbList=null;
		HashMap<String, String> map=null; 
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		jArray = new JSONArray();
		
		try {
			processDbList = timeEntryDelegate.processName(session, projSel);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(processDbList!=null) {
			for (String str : processDbList) {
				map = new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		}
		return jArray.toString();
	}
	
	@RequestMapping("/request")
	@ResponseBody
	public String getRequestName(@RequestParam("get_member1")String processSel,@RequestParam("get_member2")String stDate,@RequestParam("get_member3")String endDate,HttpSession session,HttpServletResponse response) {
		JSONArray jArray=null;
		List<String> requestDbList=null;
		HashMap<String, String> map=null; 
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		jArray = new JSONArray();
		
		try {
			requestDbList = timeEntryDelegate.wrkRequest(session, processSel, stDate, endDate);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(requestDbList!=null) {
			for (String str : requestDbList) {
				map = new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		}
		return jArray.toString();
	}
	
	@RequestMapping("/activity")
	@ResponseBody
	public String getActivityName(@RequestParam("get_member1")String requestSel,@RequestParam("get_member2")String stDate,@RequestParam("get_member3")String endDate,HttpSession session,HttpServletResponse response) {
		JSONArray jArray=null;
		List<String> activityDbList=null;
		HashMap<String, String> map=null; 
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		jArray = new JSONArray();
		
		try {
			activityDbList = timeEntryDelegate.activity(session, requestSel);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(activityDbList!=null) {
			for (String str : activityDbList) {
				map = new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		}
		return jArray.toString();
	}
	
	@RequestMapping("/wrkunit")
	@ResponseBody
	public String getWrkunitName(@RequestParam("get_member1")String activitySel,@RequestParam("get_member2")String processSel,HttpSession session,HttpServletResponse response) {
		JSONArray jArray=null;
		List<String> wrkunitDbList=null;
		HashMap<String, String> map=null; 
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		jArray = new JSONArray();
		
		try {
			wrkunitDbList = timeEntryDelegate.wrkUnit(activitySel, processSel);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(wrkunitDbList!=null) {
			for (String str : wrkunitDbList) {
				map = new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		}
		return jArray.toString();
	}
}
