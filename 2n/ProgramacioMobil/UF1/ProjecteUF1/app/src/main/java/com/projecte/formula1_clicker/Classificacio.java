package com.projecte.formula1_clicker;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.collection.ArraySortedMap;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Classificacio extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    HashMap dataFirebase = new HashMap<>();
    List<JugadorClassificacio> jugadors = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean("tema_app", true)) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.classificacio);
        this.database = FirebaseDatabase.getInstance();
        this.myRef = database.getReference("Jugadors");

        //Botons menú
        Button joc = (Button) findViewById(R.id.BotoJoc);
        Button config = (Button) findViewById(R.id.BotoConfiguracio);
        Button carrega = (Button) findViewById(R.id.Carrega);

        /**
         * Agafem tots els jugadors que hi ha a la base de dades per ordenar-los a una llista.
         */
        /*myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    JSONObject userJson = new JSONObject((Map) task.getResult().getValue());
                    dataFirebase = new Gson().fromJson(String.valueOf(userJson), LinkedHashMap.class);

                    //Agafem les claus ja que el Firebase ens retorna un LinkedHashMap
                    Set<String> keys = dataFirebase.keySet();

                    for (String key : keys) {
                        JsonObject jsonObject = new Gson().toJsonTree(dataFirebase.get(key)).getAsJsonObject();
                        Jugador j = new Gson().fromJson(String.valueOf(jsonObject), Jugador.class);
                        jugadors.add(new JugadorClassificacio(key, j));
                    }

                    jugadors.sort(new ComparadorVoltes());

                    //Aquest click està aquí per poder actualitzar la llista una vegada s'han agafat totes les dades
                    //i s'han realitzat les ordenacions pertinents.
                    carrega.performClick();
                }
            }
        });*/

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for(DataSnapshot d : dataSnapshot.getChildren()) {
                    String userID = d.getKey();
                    Jugador uInfo = new Jugador();
                    for(DataSnapshot a : d.getChildren()) {
                        switch (a.getKey()) {
                            case "nivellMillores":
                                uInfo.setNivellMillores((Map<String, Integer>) a.getValue());
                                break;
                            case "numVoltes":
                                uInfo.setNumVoltes((String) a.getValue());
                                break;
                            case "valorClick":
                                uInfo.setValorClick("1");
                                break;
                            case "voltesPerSegon":
                                uInfo.setVoltesPerSegon((Map<String, String>) a.getValue());
                                break;
                        }
                    }

                    jugadors.add(new JugadorClassificacio(userID, uInfo));
                }
                jugadors.sort(new ComparadorVoltes());

                //Aquest click està aquí per poder actualitzar la llista una vegada s'han agafat totes les dades
                //i s'han realitzat les ordenacions pertinents.
                carrega.performClick();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        };
        myRef.addValueEventListener(postListener);

        joc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Configuracio.class);
                startActivity(intent);
                finish();
            }
        });

        ClassificacioJugadors adaptador = new ClassificacioJugadors(this, jugadors);

        //Botó per actualitzar les dades a la classificació
        carrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView lstOpciones = (ListView) findViewById(R.id.ListJugadors);
                lstOpciones.setAdapter(adaptador);
            }
        });
    }

    class ClassificacioJugadors extends ArrayAdapter {

        Activity context;
        String cadena;
        BaseDeDades bd;
        int posJugador;

        public ClassificacioJugadors(Activity context, List<JugadorClassificacio> llista) {
            super(context, R.layout.llista_jugador, llista);
            this.context = (Activity) context;
            this.bd = new BaseDeDades(this.getContext(), "cadena.db", null, 1);
            this.cadena = bd.buscarUsuari();
            this.posJugador = 1;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            JugadorClassificacio aux = (JugadorClassificacio) getItem(position);

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.llista_jugador, parent, false);

            TextView pos = (TextView) convertView.findViewById(R.id.Posicio);
            TextView id = (TextView) convertView.findViewById(R.id.Id);
            TextView voltes = (TextView) convertView.findViewById(R.id.Voltes);

            pos.setText((position + 1) + ".");
            id.setText(aux.clau);
            voltes.setText(new BigDecimal(aux.j.numVoltes).setScale(2, RoundingMode.HALF_UP).toString());

            if(aux.clau.equals(cadena))
                convertView.setBackgroundColor(getResources().getColor(R.color.colorSecundary));

            return convertView;
        }
    }
}

/**
 * Classe per poder recuperar la info del Firebase correctament
 */
@IgnoreExtraProperties
class JugadorClassificacio {

    public String clau;
    public Jugador j;

    public JugadorClassificacio() {}

    public JugadorClassificacio(String clau, Jugador jugador) {
        this.clau = clau;
        this.j = jugador;
    }

    public String getClau() {
        return clau;
    }

    public void setClau(String clau) {
        this.clau = clau;
    }

    public Jugador getJ() {
        return j;
    }

    public void setJ(Jugador j) {
        this.j = j;
    }
}

/**
 * Comparador per ordenar els jugadors de la llista i que
 * apareguin a la classificació de més voltes a menys.
 */
class ComparadorVoltes implements Comparator<JugadorClassificacio> {

    @Override
    public int compare(JugadorClassificacio o1, JugadorClassificacio o2) {
        BigDecimal o1D = new BigDecimal(o1.j.numVoltes).setScale(2, RoundingMode.HALF_UP);
        BigDecimal o2D = new BigDecimal(o2.j.numVoltes).setScale(2, RoundingMode.HALF_UP);
        return o2D.compareTo(o1D);
    }
}
