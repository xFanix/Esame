package database;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.Date;

public class AppelloDb {

	public boolean creaapp(String nome, java.util.Date date, String luogo, String corso, String tipo){		
		ResultSet rs=DbConnect.getinstance().queryex("insert into appello(nome,data,luogo,corso,tipo) VALUES ("+nome+",DATE("+date.toString()+"),"+luogo+","+corso+","+tipo+")");
		return rs!=null;
	}

	public boolean cancellaAppello(int id) {
		int cancellazione =  DbConnect.getinstance().queryupdate("DELETE FROM appello where id ="+id);
		return cancellazione > 0;
	}

	public boolean modificaAppello(int id, String nome, java.util.Date date, String luogo, String corso, String tipo){
		ResultSet rs=DbConnect.getinstance().queryex("UPDATE appello SET(nome='"+nome+"', data ='"+date.toString()+"', luogo='"+luogo+"', corso='"+corso+"', tipo='"+tipo+"') WHERE id="+id);
		return rs!=null;
	}
}
