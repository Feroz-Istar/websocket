package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pojo.User;

public class FetchUser {
	private static Connection connection=null;
	public static Statement statement=null;
	public FetchUser() {
		super();
		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres",
					"root");
			statement = connection.createStatement();
			
			 
			


		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<User> getUser() throws SQLException {
		ArrayList<User> users=new ArrayList<>();
		String sql="select * from users";
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
			Integer id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			Integer age  = resultSet.getInt("age") ;
			User user = new User(id, name, email, age);
			users.add(user);
		}
		
		return users;
	}
	
	public User AuthenticateUser(String user_email,String password) throws SQLException {
		String sql="select * from users where email ='"+user_email+"' and password ='"+password+"'";
		System.out.println("generated sql ----------- "+sql);
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
			Integer id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			Integer age  = resultSet.getInt("age") ;
			User user = new User(id, name, email, age);
			
			
			return user;
		}
		return null;

	}
	
	public User getSpecific(Integer ids) {
		
		
		String sql="select * from users where id="+ids;
		ResultSet resultSet;
		try {
			resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()) {
			Integer id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			Integer age  = resultSet.getInt("age") ;
			User user = new User(id, name, email, age);
			user.setStatus("online");
			return user;
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
