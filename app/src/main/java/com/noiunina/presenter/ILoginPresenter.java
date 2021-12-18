package com.noiunina.presenter;

public interface ILoginPresenter {

    void loginEseguitoConSuccesso(String uuid, String nome, String cognome, String corso, String email);
    void noSottoscrizioni();
    void siSottoscrizioni(String sottoscrizioni);
    void loginFallito();

}
