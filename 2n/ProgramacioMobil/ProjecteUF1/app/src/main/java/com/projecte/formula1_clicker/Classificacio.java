package com.projecte.formula1_clicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Classificacio extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classificacio);

        //Botons menú
        Button joc = (Button) findViewById(R.id.BotoJoc);
        Button config = (Button) findViewById(R.id.BotoConfiguracio);

        joc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
