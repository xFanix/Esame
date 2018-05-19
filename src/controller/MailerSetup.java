package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MailerSetup {
	private HashMap<String, String> p = new HashMap<String, String>();
	private XMLMails mailer = new XMLMails();
	private ArrayList<String> destination = new ArrayList<String>();
	private Iterator it = destination.iterator();
	private String title = new String();
	private String message = new String();
	private String alias;
	private int counter = 0;

	public MailerSetup(String alias) {
		this.alias = alias;
	}

	public HashMap<String, String> getParams() {
		return p;
	}

	public void addParam(String key, String value) {
		p.put(key, value);
	}

	public String getMessage() {
		return mailer.getData(alias, p);
	}

	public String getTitle() {
		return mailer.getData(alias);
	}

	public String getDestination() {
		try {
			return destination.get(counter++);
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
	}

	public void setDestination(String dest) {
		this.destination.add(dest);
	}

	public boolean ok() {
		if (counter == destination.size())
			return false;
		if (destination.size() == 0)
			return false;
		return true;
	}

}
