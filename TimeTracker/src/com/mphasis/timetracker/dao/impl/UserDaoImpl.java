package com.mphasis.timetracker.dao.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.mphasis.timetracker.dao.UserDao;


/**
 * @author CENTAUR
 */
public class UserDaoImpl 
implements UserDao
{

		DataSource dataSource;

		public DataSource getDataSource()
		{
			System.out.println("dao impl");
				return this.dataSource;
		}

		public void setDataSource(DataSource dataSource)
		{
				this.dataSource = dataSource;
		}

		@Override
		public boolean isValidUser(HttpSession session,String username, String password) throws SQLException
		{
				//String query = "Select count(1) from employee where emp_id = ? and password = ? where emp_active='"+active+"'";
				String query = "Select count(1),emp_name from employee where emp_id = ? and password = ?";
				//System.out.println(query);
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				ResultSet resultSet = pstmt.executeQuery();
				if (resultSet.next())
				{
					session.setAttribute("empName", resultSet.getString(2));
					return (resultSet.getInt(1) > 0);
				}
				else
						return false;
		}

	}