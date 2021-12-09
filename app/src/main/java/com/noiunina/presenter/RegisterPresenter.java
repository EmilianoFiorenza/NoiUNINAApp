package com.noiunina.presenter;


import android.util.Log;

import com.noiunina.model.GestoreRichieste;
import com.noiunina.view.ILoginView;
import com.noiunina.view.IRegisterView;

import java.io.IOException;

public class RegisterPresenter {

    IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView view){
        this.iRegisterView = view;
    }


    public void effettuaRegistrazione(String nome, String cognome, String corso, String email, String pwd, String pwd2){


        if(nome.isEmpty()){
            iRegisterView.showNameError();
        }
        else if(cognome.isEmpty()){
            iRegisterView.showSurnameError();
        }
        else if(email.isEmpty() | !isEmailValid(email)){
            iRegisterView.showEmailError();
        }
        else if(pwd.isEmpty()){
            iRegisterView.showPwdError();
        }
        else if(!pwd.equals(pwd2)){
            iRegisterView.showPwdMismatchError();
        }else{
            GestoreRichieste sys = GestoreRichieste.getInstace();
            sys.richiestaRegistrazione1(nome, cognome, corso, email, pwd);
            iRegisterView.getLoginActivity();

        }


    }


    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
