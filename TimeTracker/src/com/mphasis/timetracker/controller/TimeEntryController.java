package com.mphasis.timetracker.controller;

import java.sql.SQLException;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mphasis.timetracker.delegate.TimeEntryDelegate;
import com.mphasis.timetracker.viewBean.LoginBean;
import com.mphasis.timetracker.viewBean.TimeBean;
import com.mphasis.timetracker.viewBean.TimeEntryBean;

@Controller
public class TimeEntryController {
	@Autowired
	private TimeEntryDelegate timeEntryDelegate;
	java.sql.Timestamp stTimestamp, endTimestamp = null;

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response,TimeEntryBean timeEntryBean,Model model) {
		System.out.println("view2");
		ModelAndView model1 = new ModelAndView("welcome");
		model1.addObject("timeEntryBean", timeEntryBean);
		//model.a
		//model.addObject("timeEntryBean", timeEntryBean);
		model1.addObject("loggedInUser", session.getAttribute("empName") + " [" + session.getAttribute("id") + " ]");
		return model1;
	}

	@RequestMapping("/project")
	@ResponseBody
	public String getPrjectName(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// String prosResp=request.getParameter("get_member");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		JSONArray jArray = new JSONArray();
		List<String> projdbList;
		HashMap<String, String> map = null;
		try {
			projdbList = timeEntryDelegate.projectname(session);
			System.out.println(projdbList);
			for (String str : projdbList) {
				map = new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jArray.toString();
	}

	@RequestMapping("/process")
	@ResponseBody
	public String getProcessName(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String prosResp = request.getParameter("get_member");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		JSONArray jArray = new JSONArray();
		List<String> projdbList;
		try {
			projdbList = timeEntryDelegate.processName(session, prosResp);
			System.out.println(projdbList);
			for (String str : projdbList) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jArray.toString();
	}

	@RequestMapping("/request")
	@ResponseBody
	public String getRequestsName(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String processName = request.getParameter("procsName");
		String stDate = request.getParameter("stDate");
		String endDate = request.getParameter("endDate");
		session.setAttribute("stDate", stDate);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		JSONArray jArray = new JSONArray();

		List<String> projdbList;
		try {
			projdbList = timeEntryDelegate.wrkRequest(session, processName, stDate, endDate);
			System.out.println(projdbList);
			for (String str : projdbList) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jArray.toString();
	}

	@RequestMapping("/activity")
	@ResponseBody
	public String getActivityName(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String reqName = request.getParameter("reqName");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		JSONArray jArray = new JSONArray();
		List<String> projdbList;
		try {
			projdbList = timeEntryDelegate.activity(session, reqName);
			System.out.println(projdbList);
			for (String str : projdbList) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jArray.toString();
	}

	@RequestMapping("/wrkunit")
	@ResponseBody
	public String getWorkunit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String activityName = request.getParameter("activity");
		String processName = request.getParameter("process");
		System.out.println(activityName + ">>>>" + processName);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		JSONArray jArray = new JSONArray();
		List<String> projdbList;
		try {
			projdbList = timeEntryDelegate.wrkUnit(activityName, processName);
			System.out.println(projdbList);
			for (String str : projdbList) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("name", str);
				jArray.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jArray.toString();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String getformData(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model1, @ModelAttribute("timeEntryBean") TimeEntryBean timeEntryBean,
			@ModelAttribute("loginBean") LoginBean loginBean,Model saveModel,RedirectAttributes redir) {
		// model.addObject("loggedInUser", session.getAttribute("empName")+"
		// ["+session.getAttribute("id")+" ]");
		model1.addAttribute("loggedInUser", session.getAttribute("empName") + " [" + session.getAttribute("id") + " ]");
		System.out.println(request.getParameter("json"));
		ModelAndView model = new ModelAndView("welcome");
		Date parsedDate = null;
		System.out.println();
		int empId = Integer.parseInt((String) session.getAttribute("id"));
		String empName = (String) session.getAttribute("empName");
		String wrName = timeEntryBean.getWrkReqName();
		String lcmName = timeEntryBean.getProcessName();
		String process = "Monitor System Operations";
		String activity = timeEntryBean.getActivity();
		String activityDesc = "Monitor System Operations - Monitor Application";
		String wrkUnit = timeEntryBean.getWrkUnit();
		String WrkUntType = "Usecase";
		String remarks = "remarks";
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
			parsedDate = dateFormat.parse((String) session.getAttribute("stDate"));
		} catch (Exception e) {

		}
		stTimestamp = new java.sql.Timestamp(parsedDate.getTime());
		double mon = timeEntryBean.getMonEffort();
		double tue = timeEntryBean.getTueEffort();
		double wed = timeEntryBean.getWedEffort();
		double thu = timeEntryBean.getThuEffort();
		double fri = timeEntryBean.getFriEffort();
		double sat = timeEntryBean.getSatEffort();
		double sun = timeEntryBean.getSunEffort();
		String flag1, flag2, flag3, flag4, flag5, flag6, flag7, updtFlag;
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

		List<TimeBean> impl = null;
		try {
			timeEntryDelegate.insertDB(empId, empName, wrName, lcmName, process, activity, activityDesc, wrkUnit,
					WrkUntType, remarks, stTimestamp, mon, tue, wed, thu, fri, sat, sun, flag1, flag2, flag3, flag4,
					flag5, flag6, flag7, updtFlag);
			impl=timeEntryDelegate.viewDB(empId, stTimestamp);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println(impl);
		//redir.addAttribute("model", impl);
		redir.addFlashAttribute("model", impl);
		//saveModel.addAttribute("model", impl);
		//model.addObject("model", impl);
		return "redirect:/welcome";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String getElement(HttpServletRequest request, HttpServletResponse response, HttpSession session,
		TimeEntryBean timeEntryBean,RedirectAttributes redir) {
		System.out.println("Enter View Method in TimeEntryController Class");
		// ModelAndView model = new ModelAndView("login");
		String stWeek = request.getParameter("stweek");
		System.out.println(stWeek);
		int empId = Integer.parseInt((String) session.getAttribute("id"));
		Date parsedDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		List<TimeBean> viewData = null;
		try {
			parsedDate = dateFormat.parse(stWeek);
			stTimestamp = new java.sql.Timestamp(parsedDate.getTime());
			viewData = timeEntryDelegate.viewDB(empId, stTimestamp);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("VIEW " + viewData);
		//ModelAndView model=new ModelAndView("welcome", "model", viewData);
		redir.addAttribute("model", viewData);
		//model.addAttribute("model", viewData);
		//System.out.println("View Model: "+model);
		return "welcome";

	}
}
