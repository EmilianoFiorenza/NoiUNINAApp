package com.noiunina.view;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.noiunina.R;
import com.noiunina.presenter.LoginPresenter;


public class LoginActivity extends AppCompatActivity implements ILoginView {

    EditText eEmail;
    EditText ePassword;
    TextView eRegistrati;
    Button eLogin;
    LoginPresenter presenter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        eEmail = findViewById(R.id.etemail);
        ePassword = findViewById(R.id.etPassword);
        eRegistrati = findViewById(R.id.tvRegistrati);
        eLogin = findViewById(R.id.buttonLogin); // (Button) cast

        presenter = new LoginPresenter(this);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    presenter.effettuaLogin(eEmail.getText().toString(), ePassword.getText().toString());
            }
        });


        eRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

    }

    @Override
    public void showError() {
        Toast toast = Toast.makeText(getApplicationContext(),"Utente non registrato o email/password errata",Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void getHomeActivity() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }

    @Override
    public void showValidationEmailError() {
        eEmail.setError("Inserire una email valida");
    }

    @Override
    public void showValidationPwdError() {
        ePassword.setError("Inserire una password valida");

    }


}