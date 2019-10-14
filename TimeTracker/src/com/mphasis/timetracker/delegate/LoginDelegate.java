package com.mphasis.timetracker.delegate;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.mphasis.timetracker.service.UserService;

public class LoginDelegate
{
		private UserService userService;

		public UserService getUserService()
		{
			System.out.println("Delegate");
				return this.userService;
		}

		public void setUserService(UserService userService)
		{
				this.userService = userService;
		}

		public boolean isValidUser(HttpSession session,String username, String password) throws SQLException
    {
		    return userService.isValidUser(session,username, password);
    }
		
}
