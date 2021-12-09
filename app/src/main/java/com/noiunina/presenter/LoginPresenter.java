package com.noiunina.presenter;


import com.noiunina.model.GestoreRichieste;
import com.noiunina.view.ILoginView;

public class LoginPresenter{

    ILoginView ILoginView;

    public LoginPresenter(ILoginView view){

        this.ILoginView = view;

    }

    public void effettuaLogin(String email, String pwd){


        if(email.isEmpty() | !isEmailValid(email)){
            ILoginView.showValidationEmailError();
        }
        else if(pwd.isEmpty()){
            ILoginView.showValidationPwdError();
        }
        else{
            GestoreRichieste sys = GestoreRichieste.getInstace();
            sys.richiestaLogin(email, pwd);
            ILoginView.getHomeActivity();

        }

    }

    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}

