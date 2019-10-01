package com.mphasis.timetracker.dao;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

/**
 * @author CENTAUR
 * This interface will be used to communicate with the
 * Database
 */
public interface UserDao
{
		public boolean isValidUser(String username, String password) throws SQLException;

		public String projName(HttpSession session) throws SQLException;
}
