package com.noiunina.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.noiunina.R;
import com.noiunina.presenter.TraduzionePresenter;

public class TraduzioneActivity extends AppCompatActivity implements ITraduzioneView{

    EditText inserisciTraduzioneET;
    TextView visualizzaTraduzioneTV;
    Button  btnTraduci;

    TraduzionePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traduzione);

        presenter = new TraduzionePresenter(this);

        inserisciTraduzioneET = findViewById(R.id.et_inserisci_testo_da_tradurre);
        visualizzaTraduzioneTV = findViewById(R.id.tv_visualizza_traduzione);
        btnTraduci = findViewById(R.id.btn_traduci);

        btnTraduci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.traduciTesto(inserisciTraduzioneET.getText().toString());
            }
        });


    }

    @Override
    public void setTraduzione(String traduzione) {

        TraduzioneActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizzaTraduzioneTV.setText(traduzione);
            }
        });
    }

    @Override
    public void setErrorTraduzione() {
        TraduzioneActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(getApplicationContext(),"Non è stato possibile ottenere la traduzione. Riprovare.",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}