/**
 *
 */
package com.mphasis.timetracker.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author CENTAUR
 *
 */
public interface UserService
{
		public boolean isValidUser(HttpSession session,String username, String password) throws SQLException;
		
}
