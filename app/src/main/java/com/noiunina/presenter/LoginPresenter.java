package com.noiunina.presenter;

import com.noiunina.model.GestoreRichieste;
import com.noiunina.view.ILoginView;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter implements ILoginPresenter{

    public static ILoginView ILoginView;

    public LoginPresenter(ILoginView view){

        ILoginView = view;

    }

    public LoginPresenter(){

    }

    public void effettuaLogin(String email, String pwd){

        if(email.isEmpty() | !isEmailValid(email)){
            ILoginView.showValidationEmailError();
        }
        else if(pwd.isEmpty()){
            ILoginView.showValidationPwdError();
        }
        else{
            GestoreRichieste sys = GestoreRichieste.getInstance();
            sys.richiestaLogin(email, pwd);
        }

    }

    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void memorizzaDatiStudente(String uuid, String risposta) {

        GestoreRichieste sys = GestoreRichieste.getInstance();

        try {
            JSONObject jsonDatiUtente = new JSONObject(risposta);
            sys.setStudente(uuid, jsonDatiUtente.get("nome").toString(), jsonDatiUtente.get("cognome").toString(),
                    jsonDatiUtente.get("corso").toString(), jsonDatiUtente.get("email").toString());
            sys.checkSottoscrizioni(uuid);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void checkSottoscrizioni(String risposta) {

        GestoreRichieste sys = GestoreRichieste.getInstance();

        if(risposta.equals("null")){
            sys.inizializzazioneArrayListSottoscrizioniStudenteVuota();
        }
        else{
            sys.inizializzazioneArrayListSottoscrizioniStudente(risposta);
        }

        sys.checkPrenotazioni();



    }

    @Override
    public void prenotazioniTrovate(String risposta) {

        GestoreRichieste sys = GestoreRichieste.getInstance();

        sys.inizializzazioneArrayListPrenotazioniStudente(risposta);

        ILoginView.getHomeActivity();

    }

    @Override
    public void prenotazioniNonTrovate() {

        GestoreRichieste sys = GestoreRichieste.getInstance();

        sys.inizializzazioneArrayListPrenotazioniStudenteVuota();

        ILoginView.getHomeActivity();

    }

    @Override
    public void loginFallito() {
        ILoginView.showError();
    }
}

