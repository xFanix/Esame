package controller;

public class Config {
	private Config() {
	}

	//public String JARPATH = Config.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
	
	// Lunghezza stringhe
	public static final int MINPASSWORD = 5;
	public static final int MAX_GENERIC_STRING_LENGTH = 25;
	public static final int MAX_NAME_LENGTH = 10;

	// Rolecode
	public static final int ADMIN_ROLECODE = 3;
	public static final int MANAGER_ROLECODE = 2;
	public static final int USER_ROLECODE = 1;

	// SRC
	public static final String SRC_PATH = ".\\src";
	public static final String SRC_FOLDER_NAME = "src";
	public static final String SRC_EXT = ".txt";

	// ARRAY DATA
	public static final String[] Genders = new String[] { "M", "F" };

	// EMAIL
	public static final String EMAIL_USERNAME = "mariorossiesame";
	public static final String EMAIL_PASSWORD = "fafafafa";
	public static final boolean EMAIL_ENABLED = false;

	// Database
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "root";
	public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/";
	public static final String DB_NAME = "agroludos";
	
	// XML
	public static final String URI_MAILS = "/resources/MailMapping.xml";
	public static final Integer MAX_LIMIT = 100;

}
