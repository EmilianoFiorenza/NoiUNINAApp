package com.noiunina.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.noiunina.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton btnEffettuaPrenotazione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnEffettuaPrenotazione = findViewById(R.id.buttonPrenotazione);
        btnEffettuaPrenotazione.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), SelezionaTipoPrenotazioneActivity.class));
    }

}