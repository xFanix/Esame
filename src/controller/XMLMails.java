package controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

public class XMLMails extends XMLParser {

	public enum MAILCONSTANTS {
		TITLE, BODY;
	}

	public XMLMails() {
		super(Config.URI_MAILS);
	}

	public String getData(String alias, HashMap<String, String> params) {
		String data = null;
		String path = new String("list/mail[@alias='" + alias + "']/body");
		try {
			XPathExpression expr = xpath.compile(path);
			data = (String) expr.evaluate(doc, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return subParams(data, params);
	}

	public String getData(String alias) {
		String data = null;
		String path = new String("list/mail[@alias='" + alias + "']/title");
		try {
			XPathExpression expr = xpath.compile(path);
			data = (String) expr.evaluate(doc, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return data;
	}

	private String subParams(String email, HashMap<String, String> params) {
		Iterator it = params.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry opt = (Map.Entry) it.next();
			email = email.replace("{" + (String) opt.getKey() + "}",
					(String) opt.getValue());
		}
		return email;
	}

}
