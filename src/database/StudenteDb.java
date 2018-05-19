package database;

import util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class StudenteDb {
	public int creastud(String nome, String cognome, String email, Date data, String username, String password){
		LocalDate date = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		return DbConnect.getinstance().queryupdate("insert into student(nome,cognome,email,datanascita,username,pass) VALUES ('"+nome+"','"+cognome+"','"+email+"','"+date+"','"+username+"','"+password+"')");
	}
	
	public boolean stud(){
		ResultSet rs=DbConnect.getinstance().queryex("select * from student");
		return rs!=null;
	}

	public int studesiste(String nomeutente, String password)throws Exception{
		String query="select id from student where username = '"+nomeutente+"' and pass='"+password+"'";
		System.out.println(query);
		ResultSet rs=DbConnect.getinstance().queryex(query);
		int id = -1;
		try {
			while(rs.next()){
				id=rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
}
