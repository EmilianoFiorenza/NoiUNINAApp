package com.noiunina.model;
import android.util.Log;

import androidx.annotation.NonNull;

import com.noiunina.presenter.RegisterPresenter;
import com.noiunina.view.ILoginView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServizioAutenticazioneAPI {

    RegisterPresenter registerPresenter;

    public void registrazione(Studente studente, String URL_BROKER, String SIGNUP){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL_BROKER+"/"+SIGNUP)
                .get()
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
                            String url_servizio_reg = response.body().string();

                            String TAG1 = "RISPOSTA BROKER";
                            Log.i(TAG1, url_servizio_reg);

                            RequestBody formBody = new FormBody.Builder()
                            .add("email", studente.getEmail())
                            .add("password", studente.getPwd())
                            .build();

                            Request request = new Request.Builder()
                                    .url(url_servizio_reg)
                                    .post(formBody)
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

                                                String TAG1 = "SERVIZIO REGISTRAZIONE";
                                                Log.i(TAG1,"Registrazione effettuata!");

                                                String setDataUser = "setUserData";

                                                setDatiUtente(studente, URL_BROKER, setDataUser);

                                            }
                                            else{

                                                registerPresenter.registrazioneFallita();
                                                String TAG1 = "SERVIZIO REGISTRAZIONE";
                                                Log.i(TAG1,"Non e stato possibile effetuare la richiesta");
                                            }

                                        }
                                    }
                            );

                        }
                        else{

                            registerPresenter.registrazioneFallita();
                            String TAG1 = "RISPOSTA BROKER";
                            Log.i(TAG1,"Non e stato possibile effetuare la richiesta");
                        }

                    }
                }
        );

    }

    public void setDatiUtente(Studente studente, String URL_BROKER, String setDataUser){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL_BROKER+"/"+setDataUser)
                .get()
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
                            String url_servizio_setData = response.body().string();

                            String TAG1 = "RISPOSTA BROKER";
                            Log.i(TAG1, url_servizio_setData);

                            JSONObject userField = new JSONObject();
                            try{
                                userField.put("nome", studente.getNome());
                                userField.put("cognome", studente.getCognome());
                                userField.put("corso", studente.getCorso());
                                userField.put("email", studente.getEmail());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                            RequestBody body = RequestBody.create(JSON, userField.toString());

                            Request request = new Request.Builder()
                                    .url(url_servizio_setData)
                                    .post(body)
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

                                                String TAG1 = "SERVIZIO SET DATI UTENTE";
                                                Log.i(TAG1,"Dati utente settati con successo!");
                                            }
                                            else{

                                                registerPresenter.registrazioneFallita();

                                                String TAG1 = "SERVIZIO SET DATI UTENTEE";
                                                Log.i(TAG1,"Non e stato possibile effetuare il settaggio");
                                            }
                                        }
                                    }
                            );
                        }
                        else{

                            registerPresenter.registrazioneFallita();
                            String TAG1 = "RISPOSTA BROKER";
                            Log.i(TAG1,"Non e stato possibile effetuare la richiesta");
                        }
                    }
                }
        );
    }
}
