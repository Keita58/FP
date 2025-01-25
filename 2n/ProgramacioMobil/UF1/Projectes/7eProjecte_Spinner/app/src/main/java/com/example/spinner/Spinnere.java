package com.example.spinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Spinnere extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.spinnerview);

        EditText textView = (EditText) findViewById(R.id.textPerSpinner);
        Button boto = (Button) findViewById(R.id.botoEnviaText);
        Button botoKO = (Button) findViewById(R.id.botoCancela);

        Intent intent = new Intent();

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.botoEnviaText) {
                    intent.putExtra("Info", textView.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else if (view.getId() == R.id.botoCancela){
                    finish();
                }
            }
        };
        boto.setOnClickListener(ocl);
        botoKO.setOnClickListener(ocl);
    }
}
