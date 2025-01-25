package com.example.cicledevidaactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String tag = "Info_Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Log.i(tag, "Activity OnCreate");

        EditText text = (EditText) findViewById(R.id.textEnvia);
        Button boto = (Button) findViewById(R.id.button);

        boto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("Nom", "Marc");
                bundle.putString("Missatge", text.getText().toString());

                intent.putExtras(bundle);

                /*
                 * També es pot fer directamet a l'intent ->
                 * intent.putExtra("aaa", "bbb");
                 */

                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this, data.getStringExtra("Resultat").toString(), Toast.LENGTH_SHORT).show();
                //Toast el que fa és imprimir per pantalla un petit missatge amb el text que li indiquis, en auqets cas amb una durada
                //de curta duració (Tant pot ser amb un Length_Short com amb un Length_Long)
            }
            else {
                Toast.makeText(this, data.getStringExtra("ResultatKO").toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(tag, "Activity OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(tag, "Activity OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(tag, "Activity OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(tag, "Activity OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(tag, "Activity OnDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(tag, "Activity OnRestart");
    }
}