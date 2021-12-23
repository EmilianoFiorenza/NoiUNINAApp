package com.noiunina.presenter;

import org.json.JSONArray;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface IPrenotazionePresenter {
//    void setStato(JSONArray stato);
//    ArrayList<String> getStato();
    void effettuaPrenotazione(String idBiblioteca, Timestamp oraInizio, Timestamp oraFine);
    void prenotazioneEseguitaConSuccesso();
    void prenotazioneFallita();

}
