package com.mphasis.timetracker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
//			TimeEntryBean timeEntryBean) {
//		System.out.println("view1");
//		ModelAndView model = new ModelAndView("login");
//		// LoginBean loginBean = new LoginBean();
//		model.addObject("loginBean", loginBean);
//		model.addObject("timeEntryBean", timeEntryBean);
//		return model;
//	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String displayLogin(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String executeLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model,
			LoginBean loginBean,
			// @ModelAttribute("loginBean") LoginBean loginBean,
			@ModelAttribute("timeEntryBean") TimeEntryBean timeEntryBean) {
		System.out.println("Login controller...");
		// ModelAndView model = null;
		//HttpSession session = request.getSession();
		try {

			model.addAttribute("userName", loginBean.getUsername());
			model.addAttribute("passWord", loginBean.getPassword());
			System.out.println(model);
			session.setAttribute("id", loginBean.getUsername());

			boolean isValidUser = loginDelegate.isValidUser(session, loginBean.getUsername(), loginBean.getPassword());

			session.setAttribute("id", loginBean.getUsername());

			if (isValidUser) {
				System.out.println("User Login Successful"); 
				model.addAttribute("loggedInUser",session.getAttribute("empName") + " [ " + loginBean.getUsername() + " ]");
//				request.setAttribute("loggedInUser",
//						session.getAttribute("empName") + " [ " + loginBean.getUsername() + " ]");
				System.out.println(timeEntryBean.getProjectname());
				// model = new ModelAndView("welcome");
				return "welcome";

			} else {
				return "login";
			}
			// model = new ModelAndView("login"); request.setAttribute("message", "Invalid
			// credentials!!"); }

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "welcome";
	}

}
