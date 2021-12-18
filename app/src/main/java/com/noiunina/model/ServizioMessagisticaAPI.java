package com.noiunina.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.noiunina.presenter.HomeChatPresenter;
import com.noiunina.presenter.IHomeChatPresenter;
import com.noiunina.presenter.ISubscriptionPresenter;
import com.noiunina.presenter.SubscriptionPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ServizioMessagisticaAPI {

    IHomeChatPresenter iHomeChatPresenter = new HomeChatPresenter();
    ISubscriptionPresenter iSubscriptionPresenter = new SubscriptionPresenter();

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

                    url_servizio_recuperaListaCorsi = url_servizio_recuperaListaCorsi.replace("+","%20");

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

    public void prendiCredenziali(String URL_BROKER, String uuid, String esame, String corsoDiStudio, String getCredenziali){

        OkHttpClient client = new OkHttpClient();

        RequestBody corsorequest = new FormBody.Builder()
                .add("esame", esame)
                .add("corsoDiStudio", corsoDiStudio)
                .build();

        Request request = new Request.Builder()
                .url(URL_BROKER+"/"+getCredenziali)
                .post(corsorequest)
                .build();

        Call call = client.newCall(request);

        call.enqueue(
                new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    iSubscriptionPresenter.sottoscrizioneFallita();
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String url_getCodice = response.body().string();

                        String TAG1 = "RISPOSTA BROKER";
                        Log.i(TAG1, url_getCodice);

                        OkHttpClient client = new OkHttpClient();

                        Request request = new Request.Builder()
                                .url(url_getCodice)
                                .get()
                                .build();

                        call = client.newCall(request);

                        call.enqueue(
                                new Callback() {
                                    @Override
                                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                        e.printStackTrace();
                                    }

                                    @Override
                                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                        if (response.isSuccessful()) {
                                            String codice = response.body().string();

                                            String TAG1 = "RISPOSTA SERVIZIO SOTTOSCRIZIONE";
                                            Log.i(TAG1, "E' stato prelevato il seguente codice: "+codice);

                                            String sottoscriviStudente = "sottoscriviStudente";
                                            sottoscriviStudenteAChat(URL_BROKER, uuid, sottoscriviStudente, codice, esame);

                                        }
                                        else{
                                            String TAG1 = "RISPOSTA SERVIZIO SOTTOSCRIZIONE";
                                            Log.i(TAG1,"Non e stato possibile effetuare la richiesta");
                                        }
                                    }
                                }
                        );
                    }
                    else{
                        iSubscriptionPresenter.sottoscrizioneFallita();
                        String TAG1 = "RISPOSTA BROKER";
                        Log.i(TAG1,"Non e stato possibile effetuare la richiesta");
                    }
                    }
                }
            );
    }

    public void sottoscriviStudenteAChat(String URL_BROKER, String uuid, String sottoscriviStudente, String codice, String esame){

        OkHttpClient client = new OkHttpClient();

        RequestBody corsorequest = new FormBody.Builder()
                .add("uuid", uuid)
                .build();

        Request request = new Request.Builder()
                .url(URL_BROKER+"/"+sottoscriviStudente)
                .post(corsorequest)
                .build();

        Call call = client.newCall(request);

        call.enqueue(
                new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String url_sottoscrizioneStudenteAChat = response.body().string();

                            String TAG1 = "RISPOSTA SERVIZIO SOTTOSCRIZIONE";
                            Log.i(TAG1, url_sottoscrizioneStudenteAChat);

                            String codiceEsame = codice.replace("\"", "");

                            JSONObject userSub = new JSONObject();
                            try{
                                userSub.put(esame, codiceEsame);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                            RequestBody body = RequestBody.create(JSON, userSub.toString());

                            Request request = new Request.Builder()
                                    .url(url_sottoscrizioneStudenteAChat)
                                    .patch(body)
                                    .build();

                            call = client.newCall(request);

                            call.enqueue(
                                    new Callback() {
                                        @Override
                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                            iSubscriptionPresenter.sottoscrizioneFallita();
                                            e.printStackTrace();
                                        }

                                        @Override
                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                            if (response.isSuccessful()) {
                                                String TAG1 = "RISPOSTA SERVIZIO SOTTOSCRIZIONE";
                                                Log.i(TAG1,"Sottoscrizione esame: "+esame+" - codice: "+codice+" effettuata");

                                                iSubscriptionPresenter.setSottoscrizione(esame, codiceEsame);


                                            }
                                            else{
                                                String TAG1 = "RISPOSTA SERVIZIO SOTTOSCRIZIONE";
                                                Log.i(TAG1,"Non e stato possibile effetuare la sottoscrizione");
                                            }
                                        }
                                    }
                                );
                        }
                        else{
                            iSubscriptionPresenter.sottoscrizioneFallita();
                            String TAG1 = "RISPOSTA BROKER";
                            Log.i(TAG1,"Non e stato possibile effetuare la richiesta");
                        }
                    }
                }
            );
    }


}
