package com.mphasis.timetracker.viewBean;

public class LoginBean
{
		
		private String username;

		private String password;

		public String getPassword()
		{
			System.out.println("get password");
				return this.password;
		}

		public String getUsername()
		{
				return this.username;
		}

		public void setUsername(String username)
		{
			System.out.println("set username"+username);
				this.username = username;
		}

		public void setPassword(String password)
		{
				this.password = password;
		}


}
