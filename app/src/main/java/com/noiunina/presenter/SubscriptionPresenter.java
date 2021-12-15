package com.noiunina.presenter;

import com.noiunina.model.GestoreRichieste;
import com.noiunina.view.ISubscriptionView;

import java.util.ArrayList;

public class SubscriptionPresenter {

    public static ISubscriptionView iSubscriptionView;

    public SubscriptionPresenter(ISubscriptionView view){

        iSubscriptionView = view;

    }

    public SubscriptionPresenter(){

    }

    public ArrayList<String> getListaEsami(){

        ArrayList<String> listaEsami;

        GestoreRichieste sys = GestoreRichieste.getInstance();

        listaEsami = sys.getListaSottoscrizioniDisponibili();

        return listaEsami;

    }



}
