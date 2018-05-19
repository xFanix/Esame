package database;

import java.sql.ResultSet;

public class AdminDb {
	public boolean creaadmin(String username, String pass){		
		ResultSet rs=DbConnect.getinstance().queryex("insert into admin(username,pass) VALUES ("+username+","+pass+")");
		return rs!=null;
	}
}
