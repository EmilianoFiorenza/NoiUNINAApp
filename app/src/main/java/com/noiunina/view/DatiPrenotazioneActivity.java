package com.noiunina.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.noiunina.R;
import com.noiunina.presenter.DatiPrenotazionePresenter;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class DatiPrenotazioneActivity extends AppCompatActivity {

    TextView textViewId;
    TextView textViewStudente;
    TextView textViewEmail;
    TextView textViewNomeBiblioteca;
    TextView textViewOrario;
    TextView textViewData;

    ImageView imageViewQRCode;

    String nomeStudente;
    String cognomeStudente;
    String email;
    String id;
    String nomeBiblioteca;
    String oraInizio;
    String oraFine;
    String dataPren;

    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    DatiPrenotazionePresenter datiPrenotazionePresenter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dati_prenotazione);

        textViewId = findViewById(R.id.textView_uid);
        textViewStudente = findViewById(R.id.textView_studente);
        textViewEmail = findViewById(R.id.textView_email);
        textViewNomeBiblioteca = findViewById(R.id.textView_Biblioteca);
        textViewOrario = findViewById(R.id.textView_orario);
        textViewData = findViewById(R.id.textView_data);

        imageViewQRCode = findViewById(R.id.imageView_QRCode);

        datiPrenotazionePresenter = new DatiPrenotazionePresenter();

        nomeStudente = datiPrenotazionePresenter.getNomeStudente();
        cognomeStudente = datiPrenotazionePresenter.getCognomeStudente();
        email = datiPrenotazionePresenter.getEmailStudente();
        id = datiPrenotazionePresenter.getIdPrenotazione();
        nomeBiblioteca = datiPrenotazionePresenter.getNomeBiblioteca();
        oraInizio = datiPrenotazionePresenter.getOraInizio();
        oraFine = datiPrenotazionePresenter.getOraFine();
        dataPren = datiPrenotazionePresenter.getDataPren();

        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = Math.min(width, height);
        smallerDimension = smallerDimension * 3;

        qrgEncoder = new QRGEncoder(
                id, null,
                QRGContents.Type.TEXT,
                smallerDimension);
        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            imageViewQRCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Log.v("GenerateQRCode", e.toString());
        }

        textViewId.setText("ID: "+id);
        textViewStudente.setText("Studente: "+nomeStudente+" "+cognomeStudente);
        textViewEmail.setText("Email: "+email);
        textViewNomeBiblioteca.setText("Locazione: "+nomeBiblioteca);
        textViewOrario.setText("Orario: "+oraInizio+"-"+oraFine);
        textViewData.setText("Data: "+dataPren);

    }
}