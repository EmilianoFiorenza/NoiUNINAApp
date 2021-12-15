package com.noiunina.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.noiunina.R;
import com.noiunina.presenter.SubscriptionPresenter;

import java.util.ArrayList;

public class SubscriptionActivity extends AppCompatActivity implements ISubscriptionView{

    ListView listView;
    ArrayList<String> listaEsami;
    SubscriptionPresenter presenter;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        listView = findViewById(R.id.lista_sottoscrizioni);
        presenter = new SubscriptionPresenter(this);
        listaEsami = presenter.getListaEsami();

        arrayAdapter = new ArrayAdapter(this, R.layout.lista_layout, listaEsami);

        listView.setAdapter(arrayAdapter);


    }
}