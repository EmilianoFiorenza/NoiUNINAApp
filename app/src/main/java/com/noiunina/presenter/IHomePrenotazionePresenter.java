package com.noiunina.presenter;

import org.json.JSONArray;

import java.util.ArrayList;

public interface IHomePrenotazionePresenter {
    void setBiblioteche(JSONArray biblioteche);
    ArrayList<String> getBiblioteche();
}
