package com.projecte.formula1_clicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Configuracio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);

        //Botons menú
        Button trofeus = (Button) findViewById(R.id.BotoTrofeus);
        Button joc = (Button) findViewById(R.id.BotoJoc);

        joc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
