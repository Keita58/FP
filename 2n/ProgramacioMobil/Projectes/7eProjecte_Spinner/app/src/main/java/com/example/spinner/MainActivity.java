package com.example.spinner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> spinnerText = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        Button botoEnviar = (Button) findViewById(R.id.botoAfegir);
        botoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Spinnere.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayAdapter<String> AA = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerText);
        Spinner spinnerSpinner = (Spinner) findViewById(R.id.spinnerText);
        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                spinnerText.add(data.getStringExtra("Info"));
                spinnerSpinner.setAdapter(AA);
            }
        }
    }
}