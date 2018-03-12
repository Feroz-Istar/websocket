package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SaveChat {
	private static Connection connection=null;
	public static Statement statement=null;
	public SaveChat() {
		super();
		// TODO Auto-generated constructor stub
		
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

	
	public void saveChat(Integer sender_id, Integer reciever_id,String message) {
		String sql="INSERT INTO public.chat ( sender, reciever, message) VALUES ( "
				+ sender_id
				+ ", "
				+ reciever_id
				+ ", '"
				+ message
				+ "')";
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
