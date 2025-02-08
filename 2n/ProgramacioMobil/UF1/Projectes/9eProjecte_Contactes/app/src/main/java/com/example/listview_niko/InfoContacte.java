package com.example.listview_niko;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InfoContacte extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_contacte);

        TextView nom = (TextView) findViewById(R.id.nomContacte);
        TextView adreca = (TextView) findViewById(R.id.adrecaContacte);
        TextView telefon = (TextView) findViewById(R.id.telefonContacte);
        TextView mail = (TextView) findViewById(R.id.mailContacte);
        TextView data = (TextView) findViewById(R.id.dataContacte);
        FloatingActionButton tornar = (FloatingActionButton) findViewById(R.id.tornarContactes);

        Bundle bundle = this.getIntent().getExtras();

        nom.setText(bundle.getString("Nom"));
        adreca.setText(bundle.getString("Adreça"));
        telefon.setText(Integer.toString(bundle.getInt("Telèfon")));
        mail.setText(bundle.getString("Mail"));
        data.setText(bundle.getString("Data"));

        tornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
