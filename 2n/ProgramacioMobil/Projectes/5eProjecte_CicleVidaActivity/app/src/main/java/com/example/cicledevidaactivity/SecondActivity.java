package com.example.cicledevidaactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        TextView textView = (TextView) findViewById(R.id.missatgePrimerView);
        Button boto = (Button) findViewById(R.id.Retorn);
        Button botoKO = (Button) findViewById(R.id.KO);

        Bundle bundle = this.getIntent().getExtras();
        textView.setText("El missatge " + bundle.getString("Missatge") + " l'ha escrit l'usuari " + bundle.getString("Nom"));

        Intent intent = new Intent();
        boto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.Retorn) {
                    intent.putExtra("Resultat", "Resultat correcte");
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else if (view.getId() == R.id.KO){
                    intent.putExtra("ResultatKO", "Resultat incorrecte, error");
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
