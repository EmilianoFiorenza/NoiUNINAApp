package com.noiunina.model;

import android.util.Log;

import androidx.activity.result.contract.ActivityResultContracts;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestoreRichieste {

    private static GestoreRichieste instance = null;
    public Studente studente;
    public CredenzialiChat credenzialiChat;
    public static String URL_BROKER = "http://192.168.1.216:8080/api/v1/provaBroker";
    ArrayList<String> listaEsami;

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

    public void setStudente(String uuid, String nome, String cognome, String corso, String email){
        this.studente.uuid = uuid;
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

    public void getSottoscrizioniChat(){

        ServizioMessagisticaAPI servizioMessagisticaAPI = new ServizioMessagisticaAPI();

        String corso = studente.getCorso();
        String LISTACORSI = "ListaCorsi";

        servizioMessagisticaAPI.recuperaListaCorsi(URL_BROKER, corso, LISTACORSI);

    }

    public void inizializzazioneArrayListStudenteVuota(){

        this.studente.credenzialiChats = new ArrayList<CredenzialiChat>();

    }

    public void inizializzazioneArrayListStudente(String sottoscrizioni){

        inizializzazioneArrayListStudenteVuota();

        String TAG = "PROVACHECKGESTORERICHIESTE";

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
        ServizioMessagisticaAPI servizioMessagisticaAPI = new ServizioMessagisticaAPI();
        String corsoDiStudio = studente.getCorso();
        String uuid = studente.getUuid();

        servizioMessagisticaAPI.prendiCredenziali(URL_BROKER, uuid, esame, corsoDiStudio, getCredenziali);

    }

    public void deleteSottoscrizioneChat(String esame){

        String deleteCredenziali = "deleteSottoscrizione";
        ServizioMessagisticaAPI servizioMessagisticaAPI = new ServizioMessagisticaAPI();
        String uuid = studente.getUuid();

        servizioMessagisticaAPI.cancellaSottoscrizione(URL_BROKER, uuid, esame, deleteCredenziali);

    }

    public void checkSottoscrizioni(String uuid){

        String checkSottoscrizioni = "checkSottoscrizioni";
        ServizioAutenticazioneAPI servizioAutenticazioneAPI = new ServizioAutenticazioneAPI();

        servizioAutenticazioneAPI.checkSottoscrizioni(URL_BROKER, uuid, checkSottoscrizioni);


    }

    public ArrayList<String> getListaChatSottoscritte(){

        ArrayList<String> listaChatSottoscritte = new ArrayList<>();

        for(int i=0; i<this.studente.getCredenzialiChats().size(); i++){

            listaChatSottoscritte.add(this.studente.getCredenzialiChats().get(i).getEsame());

        }

        return listaChatSottoscritte;

    }

}




