package com.example.preferrencesniko;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.preference.Preference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Preference feedbackPref = findPreference("canvia_color");

        if (prefs.getBoolean("canvia_color", true)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        Button editPrefs = (Button) findViewById(R.id.prefButton);

        editPrefs.setOnClickListener(new View.OnClickListener() {
               public void onClick(View view) {
                   Intent myIntent = new Intent(view.getContext(), PonerPrefs.class);
                   startActivityForResult(myIntent, 0);
               }
       });

       Button showPrefs = (Button) findViewById(R.id.showButton);

       showPrefs.setOnClickListener(new View.OnClickListener() {
               public void onClick(View view) {
                   Intent myIntent = new Intent(view.getContext(), ShowPrefs.class);
                   startActivityForResult(myIntent, 0);
               }

       });
    
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Preference feedbackPref = findPreference("canvia_color");

        if (prefs.getBoolean("canvia_color", true)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            Log.i("hola", "entro night");
            View actual = findViewById(android.R.id.content);
            actual.setBackgroundColor(Color.DKGRAY);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            Log.i("hola", "entro light");
            View actual = findViewById(android.R.id.content);
            actual.setBackgroundColor(Color.WHITE);
        }
    }
}
