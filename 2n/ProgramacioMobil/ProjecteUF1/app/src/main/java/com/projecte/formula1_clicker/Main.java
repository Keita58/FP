package com.projecte.formula1_clicker;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main extends AppCompatActivity {
    static int valorClickUsuari;
    static int totalVoltes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.joc);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("prova");
        myRef.setValue("Hola, soy Niko!!");
        valorClickUsuari = 1;
        totalVoltes = 0;

        //Menú
        Button trofeus = (Button) findViewById(R.id.BotoTrofeus);
        Button config = (Button) findViewById(R.id.BotoConfiguracio);

        //Num voltes
        TextView numVoltes = (TextView) findViewById(R.id.NombreVoltes);

        //Diferents opcions de millora
        Button aDavant = (Button) findViewById(R.id.ADavant);
        Button pneumatic = (Button) findViewById(R.id.Pneumatic);
        Button susDavant = (Button) findViewById(R.id.SusDavant);
        Button cockpit = (Button) findViewById(R.id.Cockpit);
        Button portons = (Button) findViewById(R.id.Portons);
        Button fons = (Button) findViewById(R.id.Fons);
        Button susTrasera = (Button) findViewById(R.id.SusTrasera);
        Button aTraser = (Button) findViewById(R.id.ATraser);

        //Característiques de les millores
        TextView nivellMillora = (TextView) findViewById(R.id.NivellMillora);
        ImageView imatgeMillora = (ImageView) findViewById(R.id.ImatgeMillora);
        Button plus1 = (Button) findViewById(R.id.Plus1);
        Button plus10 = (Button) findViewById(R.id.Plus10);
        Button plus100 = (Button) findViewById(R.id.Plus100);
        TextView costPlus1 = (TextView) findViewById(R.id.CostPlus1);
        TextView costPlus10 = (TextView) findViewById(R.id.CostPlus10);
        TextView costPlus100 = (TextView) findViewById(R.id.CostPlus100);
        TextView produccioMillora = (TextView) findViewById(R.id.ProdMillora);

        //Clicker
        FrameLayout clicker = (FrameLayout) findViewById(R.id.Clicker);

        clicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalVoltes += valorClickUsuari;
                numVoltes.setText(totalVoltes + " " + getString(R.string.Voltes));
                myRef.child("numero").setValue(totalVoltes);
            }
        });
    }
}