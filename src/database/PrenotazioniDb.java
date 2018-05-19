package database;

import java.sql.ResultSet;

public class PrenotazioniDb {
	public boolean creapren(String studente, String appello){		
		ResultSet rs=DbConnect.getinstance().queryex("insert into prenotazioni(studente,appello) VALUES ("+studente+","+appello+")");
		return rs!=null;
	}
}