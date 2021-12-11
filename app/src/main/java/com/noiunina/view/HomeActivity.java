package com.noiunina.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.noiunina.R;
import com.noiunina.presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements IHomeView{

    ImageButton btnEffettuaPrenotazione;
    TextView textVNome;
    TextView textVCognome;

    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnEffettuaPrenotazione = findViewById(R.id.buttonPrenotazione);
        textVNome = findViewById(R.id.textNome);
        textVCognome = findViewById(R.id.textCognome);

        homePresenter = new HomePresenter(this);
        homePresenter.getCredenziali();


    }

    @Override
    public void mostraCredenziali(String nomeStudente, String cognomeStudente) {
        textVNome.setText("Nome: "+nomeStudente);
        textVCognome.setText("Cognome: "+cognomeStudente);
    }
}