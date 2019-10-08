package com.mphasis.timetracker.delegate;

import java.sql.SQLException;

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

		public boolean isValidUser(String username, String password) throws SQLException
    {
		    return userService.isValidUser(username, password);
    }
		
}
