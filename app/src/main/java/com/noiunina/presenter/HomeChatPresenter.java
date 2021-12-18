package com.noiunina.presenter;

import com.noiunina.model.GestoreRichieste;
import com.noiunina.view.IHomeChatView;

public class HomeChatPresenter implements IHomeChatPresenter{

    public static IHomeChatView iHomeChatView;

    public HomeChatPresenter(IHomeChatView view){

        iHomeChatView = view;

    }

    public HomeChatPresenter(){

    }

    public void getListaEsami(){

        GestoreRichieste sys = GestoreRichieste.getInstance();
        sys.getSottoscrizioniChat();

    }


    @Override
    public void erroreRestituzioneListaCorsi() {

        iHomeChatView.getSubscriptionActivityFailed();
    }

    @Override
    public void getSubscriptionActivity(String listaCorsi) {

        GestoreRichieste sys = GestoreRichieste.getInstance();
        sys.setListaSottoscrizioniDisponibili(listaCorsi);

        iHomeChatView.getSubscriptionActivitySuccess();
    }

}
