package com.noiunina.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.noiunina.presenter.HomePrenotazionePresenter;
import com.noiunina.presenter.IHomePrenotazionePresenter;
import com.noiunina.presenter.IPrenotazionePresenter;
import com.noiunina.presenter.PrenotazionePresenter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.sql.Timestamp;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServizioPrenotazioneAPI {

    private static ServizioPrenotazioneAPI instance = null;

    IHomePrenotazionePresenter iHomePrenotazionePresenter = new HomePrenotazionePresenter();
    IPrenotazionePresenter iPrenotazionePresenter = new PrenotazionePresenter();

    public static ServizioPrenotazioneAPI getInstance() {

        if (instance == null) {
            instance = new ServizioPrenotazioneAPI();
        }
        return instance;
    }

    public void prenotazione(String uuid, String nome, String cognome, String email, String idBiblioteca, Timestamp oraInizio, Timestamp oraFine, String URL_BROKER, String PRENOTAZIONE){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL_BROKER+"/"+PRENOTAZIONE)
                .get()
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                iPrenotazionePresenter.prenotazioneFallita();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String url_servizio_prenotazione = response.body().string();

                    String TAG = "RISPOSTA BROKER";
                    Log.i(TAG, url_servizio_prenotazione);

                    RequestBody formBody = new FormBody.Builder()
                            .add("nome", nome)
                            .add("cognome", cognome)
                            .add("email", email)
                            .add("idBiblioteca", idBiblioteca)
                            .add("oraInizio", oraInizio.toString())
                            .add("oraFine", oraFine.toString())
                            .build();

                    Request request = new Request.Builder()
                            .url(url_servizio_prenotazione)
                            .post(formBody)
                            .build();

                    call = client.newCall(request);

                    call.enqueue(
                            new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    e.printStackTrace();
                                    iPrenotazionePresenter.prenotazioneFallita();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    if (response.isSuccessful()){
                                        String TAG = "SERVIZIO PRENOTAZIONE";
                                        Log.i(TAG, "Prenotazione effettuata");

                                        iPrenotazionePresenter.prenotazioneEseguitaConSuccesso();
                                    }
                                    else{
                                        iPrenotazionePresenter.prenotazioneFallita();
                                        String TAG = "SERVIZIO PRENOTAZIONE";
                                        Log.i(TAG, "Prenotazione fallita");
                                    }

                                }
                            }
                    );
                }
                else{
                    //iPrenotazionePresenter.prenotazioneFallita();
                    String TAG = "SERVIZIO PRENOTAZIONE";
                    Log.i(TAG, "Prenotazione fallita");
                }
            }
        });
    }


    public void getBiblioteche(String URL_BROKER, String LISTABIBLIOTECHE){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL_BROKER+"/"+LISTABIBLIOTECHE)
                .get()
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                //iHomePrenotazionePresenter.getBibliotecheFallita();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String url_servizio_getbiblioteche = response.body().string();

                    String TAG = "RISPOSTA BROKER";
                    Log.i(TAG, url_servizio_getbiblioteche);

                    Request request = new Request.Builder()
                            .url(url_servizio_getbiblioteche)
                            .get()
                            .build();

                    call = client.newCall(request);

                    call.enqueue(
                            new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    e.printStackTrace();
                                    //iHomePrenotazionePresenter.getBibliotecheFallita();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    if (response.isSuccessful()){
                                        String TAG = "SERVIZIO BIBLIOTECHE";
                                        Log.i(TAG, "Biblioteche recuperate");

                                        try {

                                            JSONArray biblioteche = new JSONArray(response.body().string());
                                            iHomePrenotazionePresenter.setBiblioteche(biblioteche);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    else{
                                        //iHomePrenotazionePresenter.getbibliotecheFallita();
                                        String TAG = "SERVIZIO BIBLIOTECHE";
                                        Log.i(TAG, "Impossibile recuperare le biblioteche");
                                    }

                                }
                            }
                    );
                }
                else{
                    //iHomePrenotazionePresenter.getBibliotecheFallita();
                    String TAG = "SERVIZIO BIBLIOTECHE";
                    Log.i(TAG, "Impossibile recuperare le biblioteche");
                }
            }
        });


    }
/*
    public void getStato(String URL_BROKER, String STATO){ //va aggiunto idBiblioteca
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL_BROKER+"/"+STATO)
                .get() //post idBiblioteca
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                //iPrenotazionePresenter.getStatoFallita();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String url_servizio_getstato = response.body().string();

                    String TAG = "RISPOSTA BROKER";
                    Log.i(TAG, url_servizio_getstato);

                    Request request = new Request.Builder()
                            .url(url_servizio_getstato)
                            .get()
                            .build();

                    call = client.newCall(request);

                    call.enqueue(
                            new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    e.printStackTrace();
                                    //iPrenotazionePresenter.getStatoFallita();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    if (response.isSuccessful()){
                                        String TAG = "SERVIZIO STATO";
                                        Log.i(TAG, "Stato recuperato");

                                        try {

                                            JSONArray stato = new JSONArray(response.body().string());
                                            iPrenotazionePresenter.setStato(stato);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    else{
                                        //iPrenotazionePresenter.getStatoFallita();
                                        String TAG = "SERVIZIO STATO";
                                        Log.i(TAG, "Impossibile recuperare lo stato");
                                    }

                                }
                            }
                    );
                }
                else{
                    //iPrenotazionePresenter.getStatoFallita();
                    String TAG = "SERVIZIO STATO";
                    Log.i(TAG, "Impossibile recuperare lo stato");
                }
            }
        });
    }


 */
}
