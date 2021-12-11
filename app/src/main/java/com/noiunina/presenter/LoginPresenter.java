package com.noiunina.presenter;

import com.noiunina.model.GestoreRichieste;
import com.noiunina.view.ILoginView;

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
    public void loginEseguitoConSuccesso() {
        ILoginView.getHomeActivity();
    }

    @Override
    public void loginFallito() {
        ILoginView.showError();
    }
}

