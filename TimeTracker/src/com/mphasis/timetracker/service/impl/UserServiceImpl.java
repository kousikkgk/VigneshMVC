package com.mphasis.timetracker.service.impl;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.mphasis.timetracker.dao.UserDao;
import com.mphasis.timetracker.service.UserService;

public class UserServiceImpl implements UserService
{

		private UserDao userDao;

		public UserDao getUserDao()
		{
				return this.userDao;
		}

		public void setUserDao(UserDao userDao)
		{
				this.userDao = userDao;
		}

		@Override
		public boolean isValidUser(String username, String password) throws SQLException
		{
				return userDao.isValidUser(username, password);
		}

		@Override
		public String projName(HttpSession session) throws SQLException {
			return userDao.projName(session);
		}

}
