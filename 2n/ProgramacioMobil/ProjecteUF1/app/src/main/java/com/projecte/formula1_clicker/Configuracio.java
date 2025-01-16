package com.projecte.formula1_clicker;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Objects;

public class Configuracio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit(); //Per editar a mà els valors del layot de configuració

        //Botons menú
        Button trofeus = (Button) findViewById(R.id.BotoTrofeus);
        Button joc = (Button) findViewById(R.id.BotoJoc);

        trofeus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Classificacio.class);
                startActivity(intent);
                finish();
            }
        });

        joc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Botons configuració
        Button guardar = (Button) findViewById(R.id.GuardarBoto);
        Button esborrar = (Button) findViewById(R.id.EsborrarBoto);
        Switch tema = (Switch) findViewById(R.id.TemaApp);

        if(prefs.getBoolean("tema_app", true)) {
            tema.setChecked(true);
        }

        tema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tema.isChecked()) {
                    editor.putBoolean("tema_app", true);
                    editor.apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else {
                    editor.putBoolean("tema_app", false);
                    editor.apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        esborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostraAvis();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Música
                MediaPlayer click = MediaPlayer.create(Configuracio.this, R.raw.fiaum);
                click.start();
                Intent intent = new Intent();
                intent.putExtra("Guardar", true);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void mostraAvis() {
        Dialog d = new Dialog(this, R.style.Theme_Formula1_Clicker);
        d.setContentView(R.layout.avis);

        //Botons d'opcions
        Button elimina = (Button) d.findViewById(R.id.BotoAvis);
        Button cancela = (Button) d.findViewById(R.id.BotoCancelar);

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Eliminat", true);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });

        d.show();
    }
}
