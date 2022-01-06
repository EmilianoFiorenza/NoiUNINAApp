package com.noiunina.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class GestoreRichieste {

    private static GestoreRichieste instance = null;
    public Studente studente;
    public CredenzialiChat credenzialiChat;
    public Prenotazione prenotazione;
    public Messaggio mex;
    public static String URL_BROKER = "http://192.168.1.216:8081/api/v1/provaBroker";
    public String currentChat;
    public ArrayList<String> listaEsami;
    public ArrayList<String> listaBibliotecheDisponibili;
    public ArrayList<Messaggio> conversazione;
    public String nomeBibliotecaDaPrenotare;

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

        this.conversazione = new ArrayList<>();

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

    public String getEmailStudente() {
        return studente.getEmail();
    }

    public String getIdPrenotazione() {
        return this.prenotazione.getId();
    }

    public String getNomeBibliotecaPrenotata() {
        return this.prenotazione.getNomeBiblioteca();
    }

    public String getOraInizio() {

        return this.prenotazione.getOraInizio();
    }

    public String getOraFine(){

        return this.prenotazione.getOraFine();

    }

    public String getDataPren(){

        return this.prenotazione.getDataPrenotazione();

    }


    public String getUidStudente(){
        return studente.getUuid();
    }

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

    public void getSottoscrizioniChat(){

        ServizioMessagisticaAPI servizioMessagisticaAPI = ServizioMessagisticaAPI.getInstance();

        String corso = studente.getCorso();
        String LISTACORSI = "ListaCorsi";

        servizioMessagisticaAPI.recuperaListaCorsi(URL_BROKER, corso, LISTACORSI);

    }

    public void inizializzazioneArrayListSottoscrizioniStudenteVuota(){

        this.studente.credenzialiChats = new ArrayList<>();

    }

    public void inizializzazioneArrayListPrenotazioniStudenteVuota(){

        this.studente.prenotazioni = new ArrayList<>();

    }

    public void inizializzazioneArrayListSottoscrizioniStudente(String sottoscrizioni){

        inizializzazioneArrayListSottoscrizioniStudenteVuota();

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

    public void inizializzazioneArrayListPrenotazioniStudente(String risposta){

        inizializzazioneArrayListPrenotazioniStudenteVuota();

        ArrayList<String> listaPrenotazioni = new ArrayList<>();

        JSONArray jsonarray;
        try {
            jsonarray = new JSONArray(risposta);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String id = jsonobject.getString("id");
                String nomeBiblioteca = jsonobject.getString("libraryName");
                String oraInizio = jsonobject.getString("oraInizio");
                String oraFine = jsonobject.getString("oraFine");
                String dataPren = jsonobject.getString("dataPren");
                addPrenotazione(id,nomeBiblioteca,oraInizio,oraFine,dataPren);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setListaSottoscrizioniDisponibili(ArrayList<String> listaEsami){

        this.listaEsami = listaEsami;

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

    public void effettuaTraduzione(String testo){

        ServizioTraduzioneAPI servizioTraduzioneAPI = ServizioTraduzioneAPI.getInstance();

        String traduzioneTesto = "traduzioneTesto";

        servizioTraduzioneAPI.traduzioneTesto(URL_BROKER, testo, traduzioneTesto);

    }

    public void setNomeBiblioteca(String nomeBiblioteca){

        this.nomeBibliotecaDaPrenotare = nomeBiblioteca;

    }

    public String getNomeBiblioteca(){

        return this.nomeBibliotecaDaPrenotare;

    }

    public void prendiListaBibliotecheDisponibili(){

        ServizioPrenotazioneAPI servizioPrenotazioneAPI = ServizioPrenotazioneAPI.getInstance();
        String LISTA_BIBLIOTECHE_DISPONIBILI = "listaBibliotecheDisponibili";

        servizioPrenotazioneAPI.recuperaListaBibliotecheDisponibili(URL_BROKER, LISTA_BIBLIOTECHE_DISPONIBILI);

    }

    public void setListaBibliotecheDisponibili(ArrayList<String> listaBibliotecheDisponibili){

        this.listaBibliotecheDisponibili = listaBibliotecheDisponibili;

    }

    public ArrayList<String> getListaBibliotecheDisponibili(){

        return this.listaBibliotecheDisponibili;
    }

    public void checkNomeBiblioteca(String nomeBiblioteca){

        ServizioPrenotazioneAPI servizioPrenotazioneAPI = ServizioPrenotazioneAPI.getInstance();

        String CHECK_NOME_BIBLIOTECA = "checkNomeBiblioteca";

        servizioPrenotazioneAPI.checkLibrary(URL_BROKER, nomeBiblioteca, CHECK_NOME_BIBLIOTECA);

    }

    public void effettuaPrenotazione(String nomeBiblioteca, String dataPren, String oraInizio, String oraFine){

        String nomeStudente = this.studente.getNome();
        String cognomeStudente = this.studente.getCognome();
        String emailStudente = this.studente.getEmail();

        String PRENOTAZIONE = "prenotazione";

        ServizioPrenotazioneAPI servizioPrenotazioneAPI = ServizioPrenotazioneAPI.getInstance();

        servizioPrenotazioneAPI.prenotazione(URL_BROKER, nomeStudente, cognomeStudente, emailStudente, nomeBiblioteca,
                oraInizio, oraFine, dataPren, PRENOTAZIONE);

    }

    public void addPrenotazione(String id, String nomeBiblioteca, String oraInizio, String oraFine, String dataPren){

        this.prenotazione = new Prenotazione(id, nomeBiblioteca, oraInizio, oraFine, dataPren);
        this.studente.prenotazioni.add(this.prenotazione);

    }

    public void checkPrenotazioni(){

        String email = this.studente.getEmail();
        ServizioAutenticazioneAPI servizioAutenticazioneAPI = ServizioAutenticazioneAPI.getInstance();
        String CHECK_PRENOTAZIONI = "checkPrenotazioni";

        servizioAutenticazioneAPI.checkPrenotazioni(URL_BROKER, email, CHECK_PRENOTAZIONI);

    }

    public ArrayList<String> getListaNomiBiblioteca(){

        ArrayList<String> listaNomiBiblioteca = new ArrayList<>();

        for(int i=0; i<this.studente.prenotazioni.size(); i++){
            listaNomiBiblioteca.add(this.studente.prenotazioni.get(i).getNomeBiblioteca());
        }

        return listaNomiBiblioteca;
    }

    public ArrayList<String> getListaOraInizio(){

        ArrayList<String> listaOraInizio = new ArrayList<>();

        for(int i=0; i<this.studente.prenotazioni.size(); i++){
            listaOraInizio.add(this.studente.prenotazioni.get(i).getOraInizio());
        }

        return listaOraInizio;
    }

    public ArrayList<String> getListaOraFine(){

        ArrayList<String> listaOraFine = new ArrayList<>();

        for(int i=0; i<this.studente.prenotazioni.size(); i++){
            listaOraFine.add(this.studente.prenotazioni.get(i).getOraFine());
        }

        return listaOraFine;
    }

    public ArrayList<String> getListaDataPren(){

        ArrayList<String> listaDataPren = new ArrayList<>();

        for(int i=0; i<this.studente.prenotazioni.size(); i++){
            listaDataPren.add(this.studente.prenotazioni.get(i).getDataPrenotazione());
        }

        return listaDataPren;
    }

    public ArrayList<String> getListaId(){

        ArrayList<String> listaId = new ArrayList<>();

        for(int i=0; i<this.studente.prenotazioni.size(); i++){
            listaId.add(this.studente.prenotazioni.get(i).getId());
        }

        return listaId;


    }

    public void setPrenotazioneDaVisualizzare(String id){

        for(int i=0; i<this.studente.getPrenotazioni().size(); i++){
            if(id.equals(this.studente.getPrenotazioni().get(i).getId())){

                this.prenotazione = new Prenotazione(this.studente.getPrenotazioni().get(i).getId(),
                       this.studente.getPrenotazioni().get(i).getNomeBiblioteca(),
                       this.studente.getPrenotazioni().get(i).getOraInizio(),
                       this.studente.getPrenotazioni().get(i).getOraFine(),
                       this.studente.getPrenotazioni().get(i).getDataPrenotazione());

            }
        }


    }
}