package controller;

import java.util.Date;

public class Loginfo {
    private static Loginfo ourInstance = new Loginfo();
    public static enum Ruoli {Admin, Studente, Docente};
    private static Ruoli ruolo;



    private static String username = new String();
    private static Date orariologin;
    public static Loginfo getInstance() {
        return ourInstance;
    }
    private Loginfo() {
    }

    public static void memorizzalogin(String username1, Ruoli ruolo1){
        ruolo = ruolo1;
        username = username1;
        orariologin = new Date();
    }

    public static Ruoli getRuolo() {
        return ruolo;
    }

    public static void setRuolo(Ruoli ruolo) {
        Loginfo.ruolo = ruolo;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Loginfo.username = username;
    }

    public static Date getOrariologin() {
        return orariologin;
    }

    public static void setOrariologin(Date orariologin) {
        Loginfo.orariologin = orariologin;
    }

}
