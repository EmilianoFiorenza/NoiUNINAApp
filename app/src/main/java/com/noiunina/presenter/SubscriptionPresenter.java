package com.noiunina.presenter;

import android.util.Log;

import com.noiunina.model.GestoreRichieste;
import com.noiunina.view.ISubscriptionView;

import java.util.ArrayList;

public class SubscriptionPresenter implements ISubscriptionPresenter{

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

    public void effettuaSottoscrizione(String esame){

        String TAG = "Sottoscrizione";
        Log.i(TAG, esame);

        GestoreRichieste sys = GestoreRichieste.getInstance();
        sys.getCredenzialiChat(esame);

    }

    public void checkSottoscrizione(String esame){

        boolean check = false;
        int i = 0;
        ArrayList<String> listaChatSottoscritte;

        GestoreRichieste sys = GestoreRichieste.getInstance();
        listaChatSottoscritte = sys.getListaChatSottoscritte();

        while(i<listaChatSottoscritte.size() && !check){

            if(esame.equals(listaChatSottoscritte.get(i))){
                check = true;
            }
            i++;
        }

        if(check){
            iSubscriptionView.checkSottoscrizioneTrue();
        }
        else{
            iSubscriptionView.checkSottoscrizioneFalse(esame);
        }

    }




    @Override
    public void sottoscrizioneFallita() {
        iSubscriptionView.sottoscrizioneFallita();
    }

    @Override
    public void setSottoscrizione(String esame, String codiceEsame) {

        GestoreRichieste sys = GestoreRichieste.getInstance();



        sys.addCredenzialiChatStudente(esame, codiceEsame);

        iSubscriptionView.sottoscrizioneEffettuata();

    }



}
