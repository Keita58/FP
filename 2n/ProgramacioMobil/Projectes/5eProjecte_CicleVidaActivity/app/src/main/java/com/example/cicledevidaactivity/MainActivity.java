package com.example.cicledevidaactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
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

        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
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