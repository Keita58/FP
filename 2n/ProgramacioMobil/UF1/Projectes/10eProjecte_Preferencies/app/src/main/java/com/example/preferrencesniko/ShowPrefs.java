package com.example.preferrencesniko;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class ShowPrefs extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_prefs);
        
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        
        TextView text1 = (TextView)findViewById(R.id.textview1);
        TextView text2 = (TextView)findViewById(R.id.textview2);
        TextView text3 = (TextView)findViewById(R.id.textview3);
        TextView text4 = (TextView)findViewById(R.id.textView4);
             
        text1.setText(new Boolean(prefs.getBoolean("checkboxPref", false)).toString());
        text2.setText(prefs.getString("editTextPref", "<unset>"));;
        text3.setText(prefs.getString("listPref", "<unset>"));
        text4.setText(prefs.getString("VALOR", "null"));
    }
}
