package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.stream.Collectors;

import database.CorsoDb;
import database.ProfessoreDb;
import database.StudenteDb;
import model.Appello;
import model.Corso;
import util.StringUtils;

public class FrontController {
	private CorsoDb corso = new CorsoDb();
	private ProfessoreDb prof = new ProfessoreDb();
	private StudenteDb stud = new StudenteDb();
	
	public boolean CreaStudente(String nome, String cognome, String email, Date data){
	String password=StringUtils.randomString(10);
	int num=((int)(Math.random()*999+1));
	String username=nome.toUpperCase()+"."+cognome.toUpperCase()+num;
	System.out.println(password);
	try {
		password=StringUtils.byteArrayToHexString(StringUtils.encrypt(password));
	} catch (Exception e) {
		e.printStackTrace();
	}
	return stud.creastud(nome, cognome, email, data, username, password)>0;
	}
	
	public boolean MakeCorso(String nome, String CFU){
		int num=corso.creacorso(nome, Integer.parseInt(CFU));
		return num>0;
	}
	
	public boolean CreaProfessore(String nome, String cognome, String email){
		String password=StringUtils.randomString(10);
		int num=((int)(Math.random()*999+1));
		String username=nome.toUpperCase()+"."+cognome.toUpperCase()+num;
		try {
			password=StringUtils.byteArrayToHexString(StringUtils.encrypt(password));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prof.creaprof(nome, cognome, email, username, password)>0;
	}
	
	public boolean AssegnaCorso(String nomecorso, String docente){
		String[] partscor = nomecorso.split("-");
		String part2cor = partscor[1];
		String[] partsdoc = docente.split("-");
		String part2doc = partsdoc[1];
		int num=corso.assegnacorso(part2cor.trim(),part2doc.trim());
		return num>0;
	}
	
	public ArrayList<String> getProfSuggestion(String inizioNome){
		return prof.getNomeProf(inizioNome);
	}
	public ArrayList<String> getCorsoSuggestion(String inizioNome){
		return corso.getNomeCorso(inizioNome);
	}
	
	public ArrayList<Appello> getAppelloByProf(String idprof){
		ArrayList<Corso> corsiList = corso.getCorsoByProf(idprof);
		ArrayList<Appello> appelliList = new ArrayList<Appello>();
		for(Corso c: corsiList){
			appelliList.addAll(corso.getAppelloByCorso(c.getId()));
		}
		System.out.println(corsiList.size());
		return appelliList;
	}

	public boolean login(String utente, String password, String type) throws Exception{
	boolean valid = false;
		String pass=StringUtils.byteArrayToHexString(StringUtils.encrypt(password));
		switch (type){
			case "Admin":
				Loginfo.getInstance().memorizzalogin(utente, Loginfo.Ruoli.Admin);
				break;
			case "Studente":
				valid = stud.studesiste(utente, pass);
				Loginfo.getInstance().memorizzalogin(utente, Loginfo.Ruoli.Studente);
				break;
			case "Docente":
				valid = prof.profesiste(utente, pass);
				Loginfo.getInstance().memorizzalogin(utente, Loginfo.Ruoli.Docente);
				break;
		}
		return valid;
	}

	public Vector<Corso> getCorsiByUsername(){
		ArrayList<Corso> corsi = prof.elencocorsi(Loginfo.getUsername());
		Vector<Corso> model = corsi.stream().map(t -> {
			Corso corso = new Corso();
			corso.setNome(t.getNome());
			corso.setId(t.getId());
			corso.setIdprof(t.getIdprof());
			corso.setCfu(t.getCfu());
			return corso;
		}).collect(Collectors.toCollection(Vector::new));;
	return model;
	}

}
