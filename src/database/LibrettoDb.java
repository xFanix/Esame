package database;

import java.sql.ResultSet;

public class LibrettoDb {
	public boolean creapren(int idcorso, int matr){		
		ResultSet rs=DbConnect.getinstance().queryex("insert into libretto(idcorso,matr) VALUES ("+idcorso+","+matr+")");
		return rs!=null;
	}
}
