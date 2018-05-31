package database;

import model.Appello;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	public ArrayList<Appello> getAppelloById(int id) {
		ResultSet rs = DbConnect.getinstance().queryex("SELECT * FROM appello WHERE id="+id);
		ArrayList<Appello> List = new ArrayList<Appello>();
		try {
			while(rs.next()) {
				Appello appello=new Appello();
				appello.setCorso(rs.getString("corso"));
				appello.setData(rs.getString("data"));
				appello.setId(rs.getString("id"));
				appello.setLuogo(rs.getString("luogo"));
				appello.setNome(rs.getString("nome"));
				appello.setTipo(rs.getString("tipo"));
				List.add(appello);
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return List;
	}
}
