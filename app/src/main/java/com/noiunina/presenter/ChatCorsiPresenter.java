package com.noiunina.presenter;

import com.noiunina.model.GestoreRichieste;
import com.noiunina.view.IChatCorsiView;

import java.util.ArrayList;

public class ChatCorsiPresenter {

    public static IChatCorsiView iChatCorsiView;

    public ChatCorsiPresenter(IChatCorsiView view) {

        iChatCorsiView = view;

    }

    public ChatCorsiPresenter() {

    }

    public ArrayList<String> getChatSottoscritte() {

       GestoreRichieste sys = GestoreRichieste.getInstance();
       ArrayList<String> listaChatSottoscritte;
       listaChatSottoscritte = sys.getListaChatSottoscritte();

        return listaChatSottoscritte;

    }

    public void checkSottoscrizioniEffettuate(ArrayList<String> listaChatSottoscritte){

        if(listaChatSottoscritte.isEmpty()) {
            iChatCorsiView.mostraDisclaimer();
        }
        else{
            iChatCorsiView.mostraChat();
        }

    }
}

