package com.example.web;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnWeb, btnCalls, btnMap, btnContact,btnMyBrowser, btnPlayStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWeb = (Button)findViewById(R.id.btnWebbrowser);
        btnCalls = (Button) findViewById(R.id.btnMakecalls);
        btnMap = (Button) findViewById(R.id.btnShowMap);
        btnContact = (Button) findViewById(R.id.btnChooseContact);
        btnMyBrowser = (Button) findViewById(R.id.btnMyBrowser);
        btnPlayStore = (Button) findViewById(R.id.btnPlayStore);
        EditText aplicacioABuscar = (EditText) findViewById(R.id.textABuscar);

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(i);

            }
        }); // btnWeb


        btnCalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+3493123456"));
                // o bien:
                Intent i = new Intent(Intent.ACTION_DIAL); // ACTION_DIAL --> el usuario ha de llamar
                // ACTION_CALL --> automaticamente se llama OJO!! En el Manifest debe estat el user-permition de Call-Phone
                i.setData(Uri.parse("tel:+3493123456"));

                startActivity(i);
            }
        }); // btnCalls


        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.827500,-122.481670"));
                startActivity(i);
            }
        }); // btnMap


        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType(ContactsContract.Contacts.CONTENT_TYPE); // Indicamos que tipo de dato ha de devolver

                startActivityForResult(i,1);
            }
        }); // btnCalls


        btnMyBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //	Intent i = new Intent("com.exemples.MyBrowser");
                Intent i = new Intent(getApplicationContext(), MyBrowser.class);
                i.setData(Uri.parse("http://www.google.com"));

                startActivity(i);

            }
        }); //btnMyBrowser

        btnPlayStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=" + aplicacioABuscar.getText().toString())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/search?q=" + aplicacioABuscar.getText().toString())));
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        //super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Hemos podido coger un contacto
                Toast.makeText(getApplicationContext(), data.getData().toString(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getData().toString()));
                startActivity(i);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}