package com.noiunina.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GestoreRichieste {

    private static GestoreRichieste instance = null;
    public Studente studente;
    public CredenzialiChat credenzialiChat;
    public Messaggio mex;
    public static String URL_BROKER = "http://192.168.0.80:8080/api/v1/provaBroker";
    public String currentChat;
    public ArrayList<String> listaEsami;
    public ArrayList<String> listaBiblioteche;
    public ArrayList<String> listaStato;
    public ArrayList<Messaggio> conversazione;

    public static GestoreRichieste getInstance() {

        if (instance == null) {
            instance = new GestoreRichieste();
        }
        return instance;

    }

    public void addCredenzialiChatStudente(String esame, String codiceCorso){

        this.credenzialiChat = new CredenzialiChat(esame, codiceCorso);
        this.studente.credenzialiChats.add(credenzialiChat);

    }

    public void addMessagioSuLista(String mittente, String messaggio, String uid){

        this.mex = new Messaggio(mittente, messaggio, uid);
        this.conversazione.add(this.mex);

    }

    public void inizilizzaConversazione(){

        this.conversazione = new ArrayList<Messaggio>();

    }

    public int getSizeMsg(){

        return this.conversazione.size();

    }

    public ArrayList<String> getListaMittenti(){

        ArrayList<String> listaMittenti = new ArrayList<>();

        for(int i=0; i<this.conversazione.size(); i++){
            listaMittenti.add(this.conversazione.get(i).getMittente());
        }

        return listaMittenti;
    }

    public ArrayList<String> getListaTestoMessaggi(){

        ArrayList<String> listaTesto = new ArrayList<>();

        for(int i=0; i<this.conversazione.size(); i++){
            listaTesto.add(this.conversazione.get(i).getTesto());
        }

        return listaTesto;
    }

    public ArrayList<String> getListaUid(){

        ArrayList<String> listaUid = new ArrayList<>();

        for(int i=0; i<this.conversazione.size(); i++){
            listaUid.add(this.conversazione.get(i).getUid());
        }

        return listaUid;
    }

    public void deleteCredenzialiChatStudente(String esame){

        for(int i = 0; i<this.studente.credenzialiChats.size(); i++){

            if(esame.equals(this.studente.credenzialiChats.get(i).getEsame())){
                this.studente.credenzialiChats.remove(i);
            }
        }
    }

    public String getNomeStudente() {
        return studente.getNome();
    }

    public String getCognomeStudente() {
        return studente.getCognome();
    }

    public String getUidStudente(){ return studente.getUuid();}

    public void setStudente(String uuid, String nome, String cognome, String corso, String email){
        this.studente.uuid = uuid;
        this.studente.nome = nome;
        this.studente.cognome = cognome;
        this.studente.corso = corso;
        this.studente.email = email;
    }

    public void richiestaLogin(String email, String pwd) {

        studente = new Studente();
        ServizioAutenticazioneAPI servizioAutenticazioneAPI = ServizioAutenticazioneAPI.getInstance();

        String SIGNIN = "login";

        servizioAutenticazioneAPI.login(email, pwd, URL_BROKER, SIGNIN);

    }

    public void richiestaRegistrazione(String nome, String cognome, String corso, String email, String pwd) {

        studente = new Studente();
        ServizioAutenticazioneAPI servizioAutenticazioneAPI = ServizioAutenticazioneAPI.getInstance();

        studente.setNome(nome);
        studente.setCognome(cognome);
        studente.setCorso(corso);
        studente.setEmail(email);

        String SIGNUP = "registrazione";

        servizioAutenticazioneAPI.registrazione(studente, pwd, URL_BROKER, SIGNUP);

    }

    public void richiestaPrenotazione(String idBiblioteca, Timestamp oraInizio, Timestamp oraFine){
        ServizioPrenotazioneAPI servizioPrenotazioneAPI = ServizioPrenotazioneAPI.getInstance();

        String PRENOTAZIONE = "prenotazione";

        servizioPrenotazioneAPI.prenotazione(this.studente.uuid, this.studente.nome, this.studente.cognome, this.studente.email, idBiblioteca, oraInizio, oraFine, URL_BROKER, PRENOTAZIONE);
    }

    public void richiestaBiblioteche(){
        ServizioPrenotazioneAPI servizioPrenotazioneAPI = ServizioPrenotazioneAPI.getInstance();

        String BIBLIOTECHE = "biblioteche";

        servizioPrenotazioneAPI.getBiblioteche(URL_BROKER, BIBLIOTECHE);
    }

    public void setListaBiblioteche(JSONArray biblioteche){
        String biblio = biblioteche.toString();

        biblio = biblio.replace("\"", "");
        biblio = biblio.replace("[","");
        biblio = biblio.replace("]","");

        listaBiblioteche = new ArrayList<>(Arrays.asList(biblio.split(",")));

    }

    public ArrayList<String> getListaBiblioteche(){

        return listaBiblioteche;
    }


    public void richiestaStato(){
        ServizioPrenotazioneAPI servizioPrenotazioneAPI = ServizioPrenotazioneAPI.getInstance();

        String STATO = "stato";

        servizioPrenotazioneAPI.getStato(URL_BROKER, STATO);
    }


    public void setListaStato(JSONArray stato) {
        String stat = stato.toString();

        stat = stat.replace("\"", "");
        stat = stat.replace("[","");
        stat = stat.replace("]","");

        listaStato = new ArrayList<>(Arrays.asList(stat.split(",")));
    }

    public ArrayList<String> getListaStato() {

        return listaStato;
    }

    public void getSottoscrizioniChat(){

        ServizioMessagisticaAPI servizioMessagisticaAPI = ServizioMessagisticaAPI.getInstance();

        String corso = studente.getCorso();
        String LISTACORSI = "ListaCorsi";

        servizioMessagisticaAPI.recuperaListaCorsi(URL_BROKER, corso, LISTACORSI);

    }

    public void inizializzazioneArrayListStudenteVuota(){

        this.studente.credenzialiChats = new ArrayList<CredenzialiChat>();

    }

    public void inizializzazioneArrayListStudente(String sottoscrizioni){

        inizializzazioneArrayListStudenteVuota();

        try {
            JSONObject jsonObject = new JSONObject(sottoscrizioni.trim());
            Iterator<String> keys = jsonObject.keys();

            while(keys.hasNext()) {
                String key = keys.next();

                this.credenzialiChat = new CredenzialiChat(key, jsonObject.get(key).toString());
                this.studente.credenzialiChats.add(credenzialiChat);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setListaSottoscrizioniDisponibili(String esami){

        esami = esami.replace("\"", "");
        esami = esami.replace("[","");
        esami = esami.replace("]","");

        listaEsami = new ArrayList<>(Arrays.asList(esami.split(",")));

    }

    public ArrayList<String> getListaSottoscrizioniDisponibili(){

        return listaEsami;
    }

    public void getCredenzialiChat(String esame){

        String getCredenziali = "getCredenziali";
        ServizioMessagisticaAPI servizioMessagisticaAPI = ServizioMessagisticaAPI.getInstance();
        String corsoDiStudio = studente.getCorso();
        String uuid = studente.getUuid();

        servizioMessagisticaAPI.prendiCredenziali(URL_BROKER, uuid, esame, corsoDiStudio, getCredenziali);

    }

    public void deleteSottoscrizioneChat(String esame){

        String deleteCredenziali = "deleteSottoscrizione";
        ServizioMessagisticaAPI servizioMessagisticaAPI = ServizioMessagisticaAPI.getInstance();
        String uuid = studente.getUuid();

        servizioMessagisticaAPI.cancellaSottoscrizione(URL_BROKER, uuid, esame, deleteCredenziali);

    }

    public void checkSottoscrizioni(String uuid){

        String checkSottoscrizioni = "checkSottoscrizioni";
        ServizioAutenticazioneAPI servizioAutenticazioneAPI = ServizioAutenticazioneAPI.getInstance();

        servizioAutenticazioneAPI.checkSottoscrizioni(URL_BROKER, uuid, checkSottoscrizioni);


    }

    public ArrayList<String> getListaChatSottoscritte(){

        ArrayList<String> listaChatSottoscritte = new ArrayList<>();

        for(int i=0; i<this.studente.getCredenzialiChats().size(); i++){

            listaChatSottoscritte.add(this.studente.getCredenzialiChats().get(i).getEsame());

        }

        return listaChatSottoscritte;

    }

    public void setCurrentChat(String chat){

        this.currentChat = chat;

    }

    public String getCurrentChatTitle(){

        return this.currentChat;

    }

    public void getMessageList(String chat){

        String codice = null;
        int i = 0;
        boolean trovato = false;

        while(i<this.studente.credenzialiChats.size() && !trovato){
            if(chat.equals(this.studente.getCredenzialiChats().get(i).getEsame())){
                codice = this.studente.credenzialiChats.get(i).getCodice();
                trovato = true;
            }
            else{
                i++;
            }
        }

        ServizioMessagisticaAPI servizioMessagisticaAPI = ServizioMessagisticaAPI.getInstance();
        String chatURL = "myChat";
        servizioMessagisticaAPI.getMessageList(URL_BROKER, codice, chatURL);

    }

    public void inviaMessaggio(String testo){

        String mittente = getNomeStudente()+" "+getCognomeStudente();
        String uid = getUidStudente();

        ServizioMessagisticaAPI servizioMessagisticaAPI = ServizioMessagisticaAPI.getInstance();
        servizioMessagisticaAPI.invioMessaggio(testo, mittente, uid);

    }

    public void aggiornaListaMessaggi(){

        ServizioMessagisticaAPI servizioMessagisticaAPI = ServizioMessagisticaAPI.getInstance();

        servizioMessagisticaAPI.refreshMessageList();


    }



}