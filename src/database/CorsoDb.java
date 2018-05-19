package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Appello;
import model.Corso;

public class CorsoDb {
	public int creacorso(String nome, int cfu){		
		return DbConnect.getinstance().queryupdate("insert into corso(nome,cfu) VALUES ('"+nome+"','"+cfu+"')");
	}
	
	public ArrayList<String> getNomeCorso(String inizioNome){
		ResultSet rs=DbConnect.getinstance().queryex("select id,nome from corso where nome like '%"+inizioNome+"%'");
		ArrayList<String> List=new ArrayList<String>();
		try {
			while(rs.next()){
				List.add(rs.getString("nome")+" - "+rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return List;
	}
	
	public int assegnacorso(String idcorso, String iddocente){
		return DbConnect.getinstance().queryupdate("update corso set idprof='"+iddocente+"' where id="+idcorso);
	}
	
	public ArrayList<Corso> getCorsoByProf(int idprofessor){
		ResultSet rs=DbConnect.getinstance().queryex("select * from corso where idprof="+idprofessor);
		ArrayList<Corso> List = new ArrayList<Corso>();
		try {
			while(rs.next()){
				Corso corso=new Corso();
				corso.setIdprof(rs.getInt("idprof"));
				corso.setCfu(rs.getInt("cfu"));
				corso.setId(rs.getInt("id"));
				corso.setNome(rs.getString("nome"));
				List.add(corso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return List;
	}
	
	public ArrayList<Appello> getAppelloByCorso(int idcorso){
		ResultSet rs=DbConnect.getinstance().queryex("select * from appello where corso="+idcorso);
		ArrayList<Appello> List = new ArrayList<Appello>();
		try {
			while(rs.next()){
				Appello appello=new Appello();
				appello.setCorso(rs.getString("corso"));
				appello.setData(rs.getString("data"));
				appello.setId(rs.getString("id"));
				appello.setLuogo(rs.getString("luogo"));
				appello.setNome(rs.getString("nome"));
				appello.setTipo(rs.getString("tipo"));
				List.add(appello);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return List;
	}
}