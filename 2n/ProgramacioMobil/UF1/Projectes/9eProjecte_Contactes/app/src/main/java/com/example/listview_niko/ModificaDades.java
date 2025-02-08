package com.example.listview_niko;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ModificaDades extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifica_dades);

        EditText nom = (EditText) findViewById(R.id.editNom);
        EditText cognom = (EditText) findViewById(R.id.editCognom);
        EditText adreca = (EditText) findViewById(R.id.editAdreca);
        EditText telefon = (EditText) findViewById(R.id.editTelefon);
        telefon.setInputType(InputType.TYPE_CLASS_PHONE);
        EditText mail = (EditText) findViewById(R.id.editMail);
        EditText data = (EditText) findViewById(R.id.editData);
        FloatingActionButton afegir = (FloatingActionButton) findViewById(R.id.afegirContacte);

        Bundle bundle = this.getIntent().getExtras();

        nom.setText(bundle.getString("Nom"));
        cognom.setText(bundle.getString("Cognom"));
        adreca.setText(bundle.getString("Adreça"));
        telefon.setText(String.valueOf(bundle.getInt("Telèfon")));
        mail.setText(bundle.getString("Mail"));
        data.setText(bundle.getString("Data"));

        Intent intent = new Intent();
        afegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundleTornar = new Bundle();
                bundleTornar.putString("Nom", nom.getText().toString());
                bundleTornar.putString("Cognom", cognom.getText().toString());
                bundleTornar.putString("Adreça", adreca.getText().toString());
                bundleTornar.putInt("Telèfon", Integer.parseInt(String.valueOf(telefon.getText())));
                bundleTornar.putString("Mail", mail.getText().toString());
                bundleTornar.putString("Data", data.getText().toString());
                bundleTornar.putInt("Posicio", bundle.getInt("Posicio"));
                intent.putExtras(bundleTornar);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
