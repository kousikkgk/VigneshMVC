/**
 *
 */
package com.mphasis.timetracker.service;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

/**
 * @author CENTAUR
 *
 */
public interface UserService
{
		public boolean isValidUser(String username, String password) throws SQLException;
		public String projName(HttpSession session) throws SQLException;
}
