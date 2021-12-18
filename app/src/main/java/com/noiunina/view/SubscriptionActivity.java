package com.noiunina.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.noiunina.R;
import com.noiunina.presenter.SubscriptionPresenter;

import java.util.ArrayList;

public class SubscriptionActivity extends AppCompatActivity implements ISubscriptionView{

    ListView listView;
    ArrayList<String> listaEsami;
    SubscriptionPresenter presenter;
    ArrayAdapter arrayAdapter;
    boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        listView = findViewById(R.id.lista_sottoscrizioni);
        presenter = new SubscriptionPresenter(this);
        listaEsami = presenter.getListaEsami();

        arrayAdapter = new ArrayAdapter(this, R.layout.lista_layout, listaEsami);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.checkSottoscrizione(listaEsami.get(position));
            }
        });

    }

    @Override
    public void sottoscrizioneFallita() {
        SubscriptionActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(getApplicationContext(), "Non è stato possibile effetuare la sottoscrizione", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    );
    }

    @Override
    public void sottoscrizioneEffettuata() {
        SubscriptionActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(getApplicationContext(), "Sottoscrizione effettuata con successo", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        );
    }

    @Override
    public void checkSottoscrizioneTrue() {
        Toast toast = Toast.makeText(getApplicationContext(), "Sei già sottoscritto a questa chat", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void checkSottoscrizioneFalse(String esame) {
        presenter.effettuaSottoscrizione(esame);
    }
}