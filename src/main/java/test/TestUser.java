package test;

import java.sql.SQLException;
import java.util.ArrayList;

import com.FetchUser;

import pojo.User;

public class TestUser {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
			String url="ws://localhost:4567/echo/1";
			System.out.println(url.substring(url.lastIndexOf("/")+1,url.length()));
	}

}
