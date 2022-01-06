package com.noiunina.presenter;

import com.noiunina.model.GestoreRichieste;

public class DatiPrenotazionePresenter {


    public String getNomeStudente(){

        GestoreRichieste sys = GestoreRichieste.getInstance();

        return sys.getNomeStudente();
    }


    public String getCognomeStudente(){

        GestoreRichieste sys = GestoreRichieste.getInstance();

        return sys.getCognomeStudente();
    }


    public String getEmailStudente(){

        GestoreRichieste sys = GestoreRichieste.getInstance();

        return sys.getEmailStudente();
    }


    public String getIdPrenotazione(){

        GestoreRichieste sys = GestoreRichieste.getInstance();

        return sys.getIdPrenotazione();

    }


    public String getNomeBiblioteca(){

        GestoreRichieste sys = GestoreRichieste.getInstance();

        return sys.getNomeBibliotecaPrenotata();
    }


    public String getOraInizio(){

        GestoreRichieste sys = GestoreRichieste.getInstance();

        return sys.getOraInizio();

    }


    public String getOraFine(){

        GestoreRichieste sys = GestoreRichieste.getInstance();

        return sys.getOraFine();

    }


    public String getDataPren(){

        GestoreRichieste sys = GestoreRichieste.getInstance();

        return sys.getDataPren();
    }

}