package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
		private static String username="root";
		private static String password="toor";
		private static String nomedb="esameing";
		private static Statement st;
		private static DbConnect instance;
	  	private DbConnect(){
	  		
	  	}
	  	public static DbConnect getinstance(){
	  		if(instance==null){
	  			instance=new DbConnect();
	  		}
	  		return instance;
	  	}
	  	public static void connect(){
	  		try {
				Class.forName( "com.mysql.jdbc.Driver" );
				String connection = "jdbc:mysql://localhost:3306/"+nomedb;
				Connection conn = DriverManager.getConnection(connection,username,password);
				System.out.println(conn.getSchema());
				st=conn.createStatement();
	  		} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	  	}
	  	public static ResultSet queryex(String query){
	  		try {
				return st.executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	  	}
	  	public static int queryupdate(String query){
	  		try {
				return st.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
	  	}
}

