package com.example.listview_niko;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreaContacte extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifica_dades);

        EditText nom = (EditText) findViewById(R.id.editNom);
        EditText cognom = (EditText) findViewById(R.id.editCognom);
        EditText adreca = (EditText) findViewById(R.id.editAdreca);
        EditText telefon = (EditText) findViewById(R.id.editTelefon);
        EditText mail = (EditText) findViewById(R.id.editMail);
        EditText data = (EditText) findViewById(R.id.editData);
        FloatingActionButton afegir = (FloatingActionButton) findViewById(R.id.afegirContacte);

        Intent intent = new Intent();
        afegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundleTornar = new Bundle();
                bundleTornar.putString("Nom", nom.getText().toString());
                bundleTornar.putString("Cognom", cognom.getText().toString());
                bundleTornar.putString("Adreça", adreca.getText().toString());
                bundleTornar.putString("Telèfon", telefon.getText().toString());
                bundleTornar.putString("Mail", mail.getText().toString());
                bundleTornar.putString("Data", data.getText().toString());
                intent.putExtras(bundleTornar);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
