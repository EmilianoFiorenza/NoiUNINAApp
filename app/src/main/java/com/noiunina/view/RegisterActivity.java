package com.noiunina.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.noiunina.R;
import com.noiunina.model.Studente;
import com.noiunina.presenter.RegisterPresenter;

import java.io.IOException;

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
        Toast toast = Toast.makeText(getApplicationContext(),"Registrazione non riuscita.",Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void getLoginActivity() {

    }

    @Override
    public void showNameError() {

    }

    @Override
    public void showSurnameError() {

    }

    @Override
    public void showEmailError() {

    }

    @Override
    public void showPwdError() {

    }

    @Override
    public void showPwdMismatchError() {

    }
}