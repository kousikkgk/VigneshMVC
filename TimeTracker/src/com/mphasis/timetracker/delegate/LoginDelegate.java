package com.mphasis.timetracker.delegate;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.mphasis.timetracker.service.UserService;

public class LoginDelegate
{
		private UserService userService;

		public UserService getUserService()
		{
				return this.userService;
		}

		public void setUserService(UserService userService)
		{
				this.userService = userService;
		}

		public boolean isValidUser(String username, String password) throws SQLException
    {
		    return userService.isValidUser(username, password);
    }
		
		public String projName(HttpSession session) throws SQLException
		{
			return userService.projName(session);
			
		}
}
