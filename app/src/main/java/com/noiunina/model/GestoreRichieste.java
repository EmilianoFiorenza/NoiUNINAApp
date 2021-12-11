package com.noiunina.model;

public class GestoreRichieste {

    private static GestoreRichieste instance = null;
    public Studente studente;
    public static String URL_BROKER = "http://192.168.1.216:8080/api/v1/provaBroker";

    public static GestoreRichieste getInstance() {

        if (instance == null) {
            instance = new GestoreRichieste();
        }
        return instance;

    }

    public String getNomeStudente() {
        return studente.getNome();
    }


    public String getCognomeStudente() {
        return studente.getCognome();
    }

    public void setStudente(String nome, String cognome, String corso, String email){
        this.studente.nome = nome;
        this.studente.cognome = cognome;
        this.studente.corso = corso;
        this.studente.email = email;
    }


    public void richiestaLogin(String email, String pwd) {

        studente = new Studente();
        ServizioAutenticazioneAPI servizioAutenticazioneAPI = new ServizioAutenticazioneAPI();

        String SIGNIN = "login";

        servizioAutenticazioneAPI.login(email, pwd, URL_BROKER, SIGNIN);

    }

    public void richiestaRegistrazione(String nome, String cognome, String corso, String email, String pwd) {

        studente = new Studente();
        ServizioAutenticazioneAPI servizioAutenticazioneAPI = new ServizioAutenticazioneAPI();

        studente.setNome(nome);
        studente.setCognome(cognome);
        studente.setCorso(corso);
        studente.setEmail(email);

        String SIGNUP = "registrazione";

        servizioAutenticazioneAPI.registrazione(studente, pwd, URL_BROKER, SIGNUP);

    }


}




