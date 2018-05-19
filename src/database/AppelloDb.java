package database;

import java.sql.ResultSet;
import java.util.Date;

public class AppelloDb {
	public boolean creaapp(String nome, java.util.Date date, String luogo, String corso, String tipo){		
		ResultSet rs=DbConnect.getinstance().queryex("insert into appello(nome,data,luogo,corso,tipo) VALUES ("+nome+",DATE("+date.toString()+"),"+luogo+","+corso+","+tipo+")");
		return rs!=null;
	}
}
