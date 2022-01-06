package com.noiunina.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.noiunina.R;
import com.noiunina.presenter.QRCodePresenter;

public class QRCodeActivity extends AppCompatActivity implements IQRCodeView{

    CodeScanner codeScanner;
    CodeScannerView codeScannerView;
    QRCodePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        codeScannerView = (CodeScannerView) findViewById(R.id.scannerView);
        codeScanner = new CodeScanner(this, codeScannerView);

        presenter = new QRCodePresenter(this);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        presenter.checkNomeBiblioteca(result.getText());
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestCamera();
    }

    private void requestCamera() {
        codeScanner.startPreview();
    }

    @Override
    public void getPrenotazioneActivity() {
        startActivity(new Intent(getApplicationContext(), PrenotazioneActivity.class));
    }

    @Override
    public void showErrorQRCode() {
        QRCodeActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(getApplicationContext(),"QRCode non valido",Toast.LENGTH_SHORT);
                toast.show();
                QRCodeActivity.this.onResume();
            }
        });
    }

}