package com.noiunina.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.noiunina.presenter.HomeChatPresenter;
import com.noiunina.presenter.IHomeChatPresenter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ServizioMessagisticaAPI {

    IHomeChatPresenter iHomeChatPresenter = new HomeChatPresenter();

    public void recuperaListaCorsi(String URL_BROKER, String corso, String LISTACORSI){

        OkHttpClient client = new OkHttpClient();

        RequestBody corsorequest = new FormBody.Builder()
                .add("corso", corso)
                .build();

        Request request = new Request.Builder()
                .url(URL_BROKER+"/"+LISTACORSI)
                .post(corsorequest)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                iHomeChatPresenter.erroreRestituzioneListaCorsi();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful()) {
                    String url_servizio_recuperaListaCorsi = response.body().string();

                    url_servizio_recuperaListaCorsi = url_servizio_recuperaListaCorsi.replace("+", "%20");

                    String TAG1 = "RISPOSTA BROKER";
                    Log.i(TAG1, url_servizio_recuperaListaCorsi);

                    Request request = new Request.Builder()
                            .url(url_servizio_recuperaListaCorsi)
                            .get()
                            .build();

                    call = client.newCall(request);

                    call.enqueue(
                            new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    e.printStackTrace();
                                    iHomeChatPresenter.erroreRestituzioneListaCorsi();
                                }
                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    if (response.isSuccessful()) {

                                        String listaCorsi = response.body().string();

                                        String TAG = "Servizio Richiesta Lista Corsi";
                                        Log.i(TAG, listaCorsi);

                                        GestoreRichieste sys = GestoreRichieste.getInstance();
                                        sys.setListaSottoscrizioniDisponibili(listaCorsi);

                                        iHomeChatPresenter.getSubscriptionActivity();

                                    }
                                    else{
                                        iHomeChatPresenter.erroreRestituzioneListaCorsi();
                                        String TAG1 = "SERVIZIO RECUPERO CORSI DISPONIBILI";
                                        Log.i(TAG1,"Non e stato possibile effetuare la richiesta");
                                    }
                                }
                            }
                    );
                }
                else{
                    iHomeChatPresenter.erroreRestituzioneListaCorsi();
                    String TAG1 = "RISPOSTA BROKER";
                    Log.i(TAG1,"Non e stato possibile effetuare la richiesta");
                }
            }
        });


    }




}
