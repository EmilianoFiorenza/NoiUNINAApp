package com.noiunina.model;


import android.util.Log;

//import com.android.volley.Request;
import androidx.annotation.NonNull;

import com.android.volley.RequestQueue;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Request;
import okhttp3.Response;


public class GestoreRichieste {

    private static GestoreRichieste instance = null;
    public Studente studente;
    public static String URL_BROKER = "http://192.168.0.229:8080/api/v1/provaBroker";

    public static GestoreRichieste getInstace() {

        if (instance == null) {
            instance = new GestoreRichieste();
        }
        return instance;

    }

    public Studente getStudente() {

        return studente;
    }

    public void richiestaLogin(String email, String pwd) {

        String TAG1 = "StudenteID";
        Log.i(TAG1,studente.getUuid());

    }

    public void richiestaRegistrazione(String nome, String cognome, String corso, String email, String pwd) {

        studente.setUuid("Giuseppe");

        String TAG1 = "StudenteID";
        Log.i(TAG1,studente.getUuid());


        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyCVyi6NUC4iWzMdpPZ6hktYOx2i4d604dE";

        RequestQueue queue = Volley.newRequestQueue(MyApplication.getAppContext());

        JSONObject jsonObjectdati = new JSONObject();
        try {
            jsonObjectdati.put("email", email);
            jsonObjectdati.put("password", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
/*
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST,
                    url,
                    jsonObjectdati,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                String uuid = response.getString("localId");

                                studente.setUuid(uuid);

                                String TAG1 = "STAJ BELL";
                                Log.i(TAG1,"Tutto appost! Ricevuto!");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            error.printStackTrace();

                        }
                    }
                );

        queue.add(jsonObjectRequest);

        Log.i(TAG1,studente.getUuid());





*/



    }



        //risposta = api.post(URL,postData);


/*





        String uuid = "";

        try {
            uuid = risposta.getString("localId");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonObjectuuid = new JSONObject();
        try{
            jsonObjectuuid.put("uuid", uuid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonObjectdati = new JSONObject();
        try{
            jsonObjectuuid.put("nome", nome);
            jsonObjectuuid.put("cognome", cognome);
            jsonObjectuuid.put("corso", corso);
            jsonObjectuuid.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsonObjectuuid.put("dati", jsonObjectdati);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        URL = "https://noiunina-ca44b-default-rtdb.firebaseio.com/Utente/.json";
        risposta = api.put(URL, jsonObjectuuid);

        String TAG1 = "Gestore Richieste - Risposta";
        Log.i(TAG1, risposta.toString());
*/


    public void richiestaRegistrazione1(String nome, String cognome, String corso, String email, String pwd) {

        studente = new Studente();
        ServizioAutenticazioneAPI servizioAutenticazioneAPI = new ServizioAutenticazioneAPI();

        studente.setNome(nome);
        studente.setCognome(cognome);
        studente.setCorso(corso);
        studente.setEmail(email);
        studente.setPwd(pwd);

        String SIGNUP = "registrazione";

        servizioAutenticazioneAPI.registrazione(studente, URL_BROKER, SIGNUP);


    }


}



