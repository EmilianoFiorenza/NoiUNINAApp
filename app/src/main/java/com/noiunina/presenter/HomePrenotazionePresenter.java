package com.noiunina.presenter;

import com.noiunina.model.GestoreRichieste;

import org.json.JSONArray;

import java.util.ArrayList;

public class HomePrenotazionePresenter implements IHomePrenotazionePresenter{

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
}
