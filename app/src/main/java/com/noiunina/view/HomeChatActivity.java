package com.noiunina.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.noiunina.R;
import com.noiunina.presenter.HomeChatPresenter;

public class HomeChatActivity extends AppCompatActivity implements IHomeChatView{

    Button buttonSottoscrivi;
    HomeChatPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_chat);

        buttonSottoscrivi = findViewById(R.id.buttonSottoscritiviChat);
        presenter = new HomeChatPresenter(this);


        buttonSottoscrivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getListaEsami();
            }
        });
    }

    @Override
    public void getSubscriptionActivityFailed() {
        Toast toast = Toast.makeText(getApplicationContext(),"Non è stato possibile ottenre la lista chat da sottoscrivere",Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void getSubscriptionActivitySuccess() {
        startActivity(new Intent(getApplicationContext(), SubscriptionActivity.class));
    }
}