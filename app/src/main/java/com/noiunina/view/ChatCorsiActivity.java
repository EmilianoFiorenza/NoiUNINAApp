package com.noiunina.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.noiunina.R;
import com.noiunina.presenter.ChatCorsiPresenter;

import java.util.ArrayList;

public class ChatCorsiActivity extends AppCompatActivity implements IChatCorsiView {

    ListView listView;
    ArrayList<String> listaChatSottoscritte;
    ChatCorsiPresenter presenter;
    ArrayAdapter arrayAdapter;
    TextView disclaimerText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_corsi);

        listView = findViewById(R.id.lista_chat_sottoscritte);
        disclaimerText = findViewById(R.id.disclaimer_lista_chat);

        presenter = new ChatCorsiPresenter(this);
        listaChatSottoscritte = presenter.getChatSottoscritte();

        arrayAdapter = new ArrayAdapter(this, R.layout.lista_layout, listaChatSottoscritte);

        presenter.checkSottoscrizioniEffettuate(listaChatSottoscritte);

    }

    @Override
    public void mostraDisclaimer() {
        disclaimerText.setText("Non hai ancora effettuato sottoscrizzioni a chat. Vai alla sezione 'Sottoscriviti a Gruppi Chat' ed iscriviti ad una chat!");
    }

    @Override
    public void mostraChat() {
        listView.setAdapter(arrayAdapter);
    }
}