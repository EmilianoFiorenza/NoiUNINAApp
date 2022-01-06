package com.noiunina.presenter;

import com.noiunina.model.GestoreRichieste;
import com.noiunina.view.IChatView;
import com.noiunina.view.IListaPrenotazioniView;

import java.util.ArrayList;

public class ListaPrenotazioniPresenter {

    public static IListaPrenotazioniView iListaPrenotazioniView;

    public ListaPrenotazioniPresenter(IListaPrenotazioniView view) {

        iListaPrenotazioniView = view;

    }

    public ArrayList<String> getListaNomiBiblioteca(){

        GestoreRichieste sys = GestoreRichieste.getInstance();

        return sys.getListaNomiBiblioteca();
    }

    public ArrayList<String> getListaOraInizio(){

        GestoreRichieste sys = GestoreRichieste.getInstance();

        return sys.getListaOraInizio();
    }

    public ArrayList<String> getListaOraFine(){

        GestoreRichieste sys = GestoreRichieste.getInstance();

        return sys.getListaOraFine();
    }

    public ArrayList<String> getListaDataPren(){

        GestoreRichieste sys = GestoreRichieste.getInstance();

        return sys.getListaDataPren();
    }

    public ArrayList<String> getListaId(){

        GestoreRichieste sys = GestoreRichieste.getInstance();

        return sys.getListaId();

    }

    public void setPrenotazioneDaVisualizzare(String id){

        GestoreRichieste sys = GestoreRichieste.getInstance();

        sys.setPrenotazioneDaVisualizzare(id);

        iListaPrenotazioniView.goToDatiPrenotazioneActivity();


    }

}
