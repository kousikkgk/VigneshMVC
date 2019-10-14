package com.mphasis.timetracker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mphasis.timetracker.delegate.LoginDelegate;
import com.mphasis.timetracker.delegate.TimeEntryDelegate;
import com.mphasis.timetracker.viewBean.LoginBean;
import com.mphasis.timetracker.viewBean.TimeEntryBean;

@Controller
public class LoginController {
	@Autowired
	private LoginDelegate loginDelegate;

	@Autowired
	private TimeEntryDelegate timeEntryDelegate;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			TimeEntryBean timeEntryBean) {
		System.out.println("view1");
		ModelAndView model = new ModelAndView("login");
		// LoginBean loginBean = new LoginBean();
		model.addObject("loginBean", loginBean);
		model.addObject("timeEntryBean", timeEntryBean);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("loginBean") LoginBean loginBean,
			@ModelAttribute("timeEntryBean") TimeEntryBean timeEntryBean) {
		ModelAndView model = null;
		HttpSession session = request.getSession();
		try {
			boolean isValidUser = loginDelegate.isValidUser(session,loginBean.getUsername(), loginBean.getPassword());
			
			session.setAttribute("id", loginBean.getUsername());
			
			if (isValidUser) {
				System.out.println("User Login Successful");
//				request.setAttribute("loggedInUser", session.getAttribute("empName")+" [ "+loginBean.getUsername()+" ]");
				System.out.println(timeEntryBean.getProjectname());
				model = new ModelAndView("welcome");
				
			} else {
				model = new ModelAndView("login");
				request.setAttribute("message", "Invalid credentials!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
//	@ModelAttribute("projName")
//	public List<String> projNameList(HttpSession session,@ModelAttribute("timeEntryBean") TimeEntryBean timeEntryBean)
//	{
//		List<String> projList=new ArrayList<String>();
//		try {
//			projList.add(timeEntryDelegate.projectname(session, "hi"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return projList;
//	}

}
