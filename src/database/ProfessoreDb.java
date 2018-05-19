package database;

import model.Corso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfessoreDb {
	public int creaprof(String nome, String cognome, String email, String username, String pass){		
		return DbConnect.getinstance().queryupdate("insert into professore(nome,cognome,email,username,pass) VALUES ('"+nome+"','"+cognome+"','"+email+"','"+username+"','"+pass+"')");
	}
	
	
	
	public int getProfId(String emailprof){
		ResultSet rs=DbConnect.getinstance().queryex("select id from professore where email='"+emailprof+"'");
		int id=0;
		try {
			while(rs.next()){
				id=rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public ArrayList<String> getNomeProf(String inizioNome){
		ResultSet rs=DbConnect.getinstance().queryex("select id,nome,cognome from professore where nome like '%"+inizioNome+"%' or cognome like '%"+inizioNome+"%'");
		ArrayList<String> List=new ArrayList<String>();
		try {
			while(rs.next()){
				List.add(rs.getString("cognome")+","+rs.getString("nome")+" - "+rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return List;
	}

	public boolean profesiste(String nomeutente, String password)throws Exception{
		String query="select count(*) as conto from professore where username = '"+nomeutente+"' and pass='"+password+"'";
		System.out.println(query);
		ResultSet rs=DbConnect.getinstance().queryex(query);
		int conto=0;
		try {
			while(rs.next()){
				conto=rs.getInt("conto");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conto == 1;
	}

	public ArrayList<Corso> elencocorsi(String username){
		String query="select c.nome, c.cfu, c.id, p.id as idprof from corso as c inner join professore as p on c.idprof = p.id where p.username = '"+username+"'";
		ResultSet rs=DbConnect.getinstance().queryex(query);
		ArrayList<Corso> listacorsi = new ArrayList<Corso>();
		try{
			while(rs.next()){
				Corso corso = new Corso();
				corso.setCfu(rs.getInt("cfu"));
				corso.setId(rs.getInt("id"));
				corso.setIdprof(rs.getInt("idprof"));
				corso.setNome(rs.getString("nome"));
				listacorsi.add(corso);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listacorsi;
	}

}
