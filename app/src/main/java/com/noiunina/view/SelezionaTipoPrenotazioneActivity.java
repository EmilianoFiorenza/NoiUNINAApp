package com.noiunina.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.noiunina.R;

import java.util.ArrayList;
import java.util.List;

public class SelezionaTipoPrenotazioneActivity extends AppCompatActivity implements View.OnClickListener {

    String[] permissions = {
            Manifest.permission.CAMERA
    };
    int PERM_CODE = 11;

    Button btnQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleziona_tipo_prenotazione);

        btnQRCode = findViewById(R.id.buttonQRCode);
        btnQRCode.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), QRCodeActivity.class));
    }

    private boolean checkpermissions(){
        List<String> listofpermisssions = new ArrayList<>();
        for (String perm: permissions){
            if (ContextCompat.checkSelfPermission(getApplicationContext(), perm) != PackageManager.PERMISSION_GRANTED){
                listofpermisssions.add(perm);
            }
        }
        if (!listofpermisssions.isEmpty()){
            ActivityCompat.requestPermissions(this, listofpermisssions.toArray(new String[listofpermisssions.size()]), PERM_CODE);
            return false;
        }
        return true;
    }

}
