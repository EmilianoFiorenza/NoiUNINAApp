package com.noiunina.presenter;

import com.noiunina.model.GestoreRichieste;

import org.json.JSONArray;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class PrenotazionePresenter implements IPrenotazionePresenter{

    /*
    public static IPrenotazioneView iPrenotazioneView;

    public PrenotazionePresenter(IPrenotazioneView view){

        iPrenotazioneView = view;

    }
     */


    public PrenotazionePresenter(){

    }

    @Override
    public void setBiblioteche(JSONArray biblioteche) {
        GestoreRichieste sys = GestoreRichieste.getInstance();

        sys.setListaBiblioteche(biblioteche);
    }

    @Override
    public ArrayList<String> getBiblioteche(){

        ArrayList<String> listaBiblioteche;
        GestoreRichieste sys = GestoreRichieste.getInstance();

        sys.richiestaBiblioteche();

        listaBiblioteche = sys.getListaBiblioteche();

        return listaBiblioteche;

    }

    @Override
    public void setStato(JSONArray stato) {
        GestoreRichieste sys = GestoreRichieste.getInstance();

        sys.setListaStato(stato);
    }

    @Override
    public ArrayList<String> getStato() {
        ArrayList<String> listaStato;
        GestoreRichieste sys = GestoreRichieste.getInstance();

        sys.richiestaStato();

        listaStato = sys.getListaStato();

        return listaStato;
    }

    @Override
    public void effettuaPrenotazione(String idBiblioteca, Timestamp oraInizio, Timestamp oraFine){
        GestoreRichieste sys = GestoreRichieste.getInstance();
        sys.richiestaPrenotazione(idBiblioteca, oraInizio, oraFine);
    }

    @Override
    public void prenotazioneEseguitaConSuccesso() {

    }

    @Override
    public void prenotazioneFallita() {
        //IPrenotazioneView.showError();
    }
}
