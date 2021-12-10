package com.noiunina.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.noiunina.R;

import com.noiunina.presenter.RegisterPresenter;



public class RegisterActivity extends AppCompatActivity implements IRegisterView{

    EditText registerNome;
    EditText registerCognome;
    Spinner registerCorso;
    EditText registerEmail;
    EditText registerPwd;
    EditText registerConfermaPwd;

    RegisterPresenter registerPresenter;

    Button registerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerNome = findViewById(R.id.nome);
        registerCognome = findViewById(R.id.cognome);
        registerCorso = findViewById(R.id.corso);
        registerEmail = findViewById(R.id.email);
        registerPwd = findViewById(R.id.pwd);
        registerConfermaPwd = findViewById(R.id.pwd1);

        registerBtn = findViewById(R.id.buttonRegister);

        registerPresenter = new RegisterPresenter(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPresenter.effettuaRegistrazione(registerNome.getText().toString(), registerCognome.getText().toString(),
                        registerCorso.getSelectedItem().toString(), registerEmail.getText().toString(),registerPwd.getText().toString(),
                        registerConfermaPwd.getText().toString());

            }
        });

    }

    @Override
    public void showRegistrationError() {

        RegisterActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(getApplicationContext(),"Registrazione non riuscita.",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    @Override
    public void getLoginActivity() {
        RegisterActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(getApplicationContext(),"Registrazione effettuata con successo",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void showNameError() {
        registerNome.setError("Inserire un nome valido");
    }

    @Override
    public void showSurnameError() {
        registerCognome.setError("Inserire un cognome valido");
    }

    @Override
    public void showEmailError() {
        registerEmail.setError("Inserire una mail valida");
    }

    @Override
    public void showPwdError() {
        registerPwd.setError("Inserire una password valida");
    }

    @Override
    public void showPwdMismatchError() {
        registerConfermaPwd.setError("Le password non corrispondono");
    }
}