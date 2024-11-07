package com.example.listview_niko;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class InfoContacte extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_contacte);

        TextView nom = (TextView) findViewById(R.id.nomContacte);
        TextView adreca = (TextView) findViewById(R.id.adrecaContacte);
        TextView telefon = (TextView) findViewById(R.id.telefonContacte);
        TextView mail = (TextView) findViewById(R.id.mailContacte);
        TextView data = (TextView) findViewById(R.id.dataContacte);

        Bundle bundle = this.getIntent().getExtras();
    }
}
