package com.noiunina.view;


import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.noiunina.R;
import com.noiunina.presenter.HomePrenotazionePresenter;
import com.noiunina.presenter.ListaPrenotazioniPresenter;

public class HomePrenotazioneActivity extends AppCompatActivity implements IHomePrenotazioneView{

    String[] permissions = {
            Manifest.permission.CAMERA
    };
    int PERM_CODE = 11;

    Button btnQRCode;
    Button btnListaBiblioteche;
    Button btnVisualizzaPrenotazioni;

    HomePrenotazionePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleziona_tipo_prenotazione);

        btnQRCode = findViewById(R.id.buttonQRCode);
        btnListaBiblioteche = findViewById(R.id.buttonAccediListaBiblioteche);
        btnVisualizzaPrenotazioni = findViewById(R.id.btnVisualizzaPrenotazioniEffettuate);

        presenter = new HomePrenotazionePresenter(this);

        btnQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QRCodeActivity.class));
            }
        });

        btnListaBiblioteche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getListaBiblioteche();
            }
        });

        btnVisualizzaPrenotazioni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListaPrenotazioniActivity.class));
            }
        });

    }

    @Override
    public void showGetListaBibliotecheError() {
        HomePrenotazioneActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(getApplicationContext(), "Non è stato possibile ottenre la lista delle biblioteche", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    public void goToListaBibliotecheActivity() {
        startActivity(new Intent(getApplicationContext(), ListaBibliotecheActivity.class));
    }

}
