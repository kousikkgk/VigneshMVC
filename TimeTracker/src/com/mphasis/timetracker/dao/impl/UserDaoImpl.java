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
				return this.dataSource;
		}

		public void setDataSource(DataSource dataSource)
		{
				this.dataSource = dataSource;
		}

		@Override
		public boolean isValidUser(String username, String password) throws SQLException
		{
				//String query = "Select count(1) from employee where emp_id = ? and password = ? where emp_active='"+active+"'";
				String query = "Select count(1) from employee where emp_id = ? and password = ?";
				System.out.println(query);
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				ResultSet resultSet = pstmt.executeQuery();
				if (resultSet.next())
						return (resultSet.getInt(1) > 0);
				else
						return false;
		}

		@Override
		public String projName(HttpSession session) throws SQLException 
		{
			//String query = "Select count(1) from employee where emp_id = ? and password = ? where emp_active='"+active+"'";
			String query="select distinct a.project_name from project a,emp_proj b,employee c where a.project_id = b.Project_id and b.emp_id = c.emp_id and c.emp_id = ? and c.emp_active = ? ";
			System.out.println(query);
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			System.out.println("Session "+session.getAttribute("id"));
			pstmt.setString(1, (String) session.getAttribute("id"));
			pstmt.setString(2, "Y");
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next())
			{
					System.out.println(resultSet.getString(1));
					return resultSet.getString(1);
			}
			else
			{
				System.out.println("NULL VALUE");
					return null;
			}//return null;
		}

	}