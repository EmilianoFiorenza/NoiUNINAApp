package com.noiunina.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.noiunina.R;
import com.noiunina.presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements IHomeView{

    ImageButton btnEffettuaPrenotazione;
    ImageButton btnTraduciTesto;
    ImageButton btnChat;
    TextView textVNome;
    TextView textVCognome;

    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnEffettuaPrenotazione = findViewById(R.id.buttonPrenotazione);
        btnTraduciTesto = findViewById(R.id.buttonTraduzione);
        btnChat = findViewById(R.id.buttonChat);

        textVNome = findViewById(R.id.textNome);
        textVCognome = findViewById(R.id.textCognome);

        homePresenter = new HomePresenter(this);
        homePresenter.getCredenziali();

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeChatActivity.class));
            }
        });


        btnEffettuaPrenotazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SelezionaTipoPrenotazioneActivity.class));
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void mostraCredenziali(String nomeStudente, String cognomeStudente) {
        textVNome.setText("Nome: "+nomeStudente);
        textVCognome.setText("Cognome: "+cognomeStudente);
    }
}