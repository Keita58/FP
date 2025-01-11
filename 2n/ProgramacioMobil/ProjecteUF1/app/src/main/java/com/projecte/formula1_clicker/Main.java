package com.projecte.formula1_clicker;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projecte.formula1_clicker.runnable.Thread;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends AppCompatActivity {
    static int valorClickUsuari;
    static BigDecimal totalVoltes;
    static HashMap<String, Integer> nivellsMilloresHashMap;
    static HashMap<String, Integer> preuBaseMillores;
    static HashMap<String, BigDecimal> voltesPerSegonMillores;
    static String milloraActiva;
    static BigDecimal costMillora1;
    static BigDecimal costMillora10;
    static BigDecimal costMillora100;
    static TextView numVoltes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.joc);
        milloraActiva = "";
        MessageDigest codificacio;

        try {
            codificacio = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] encodedhash = codificacio.digest(originalString.getBytes(StandardCharsets.UTF_8));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("prova");
        //myRef.child("voltes").setValue(totalVoltes.toString());
        valorClickUsuari = 1; //Canviar això amb el firebase
        totalVoltes = new BigDecimal("0"); //Canviar això amb el firebase

        //Menú
        Button trofeus = (Button) findViewById(R.id.BotoTrofeus);
        Button config = (Button) findViewById(R.id.BotoConfiguracio);

        //Num voltes
        numVoltes = (TextView) findViewById(R.id.NombreVoltes);
        numVoltes.setText("0 voltes"); //Canviar això amb el firebase

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

        nivellsMilloresHashMap = new HashMap<>();
        nivellsMilloresHashMap.put("aDavant", 0);
        nivellsMilloresHashMap.put("pneumatic", 0);
        nivellsMilloresHashMap.put("susDavant", 0);
        nivellsMilloresHashMap.put("cockpit", 0);
        nivellsMilloresHashMap.put("portons", 0);
        nivellsMilloresHashMap.put("fons", 0);
        nivellsMilloresHashMap.put("susTrasera", 0);
        nivellsMilloresHashMap.put("aTraser", 0);

        //Cost inicial de les millores
        preuBaseMillores = new HashMap<>();
        preuBaseMillores.put("aDavant", 20);
        preuBaseMillores.put("pneumatic", 100);
        preuBaseMillores.put("susDavant", 1100);
        preuBaseMillores.put("cockpit", 15000);
        preuBaseMillores.put("portons", 150000);
        preuBaseMillores.put("fons", 1400000);
        preuBaseMillores.put("susTrasera", 20000000);
        preuBaseMillores.put("aTraser", 330000000);

        //Voltes per segon que realitza cada millora
        voltesPerSegonMillores = new HashMap<>();
        voltesPerSegonMillores.put("aDavant", new BigDecimal("0"));
        voltesPerSegonMillores.put("pneumatic", new BigDecimal("0"));
        voltesPerSegonMillores.put("susDavant", new BigDecimal("0"));
        voltesPerSegonMillores.put("cockpit", new BigDecimal("0"));
        voltesPerSegonMillores.put("portons", new BigDecimal("0"));
        voltesPerSegonMillores.put("fons", new BigDecimal("0"));
        voltesPerSegonMillores.put("susTrasera", new BigDecimal("0"));
        voltesPerSegonMillores.put("aTraser", new BigDecimal("0"));

        //Executor service per cada millora
        final ExecutorService[] aDavantExecutor = {Executors.newSingleThreadExecutor()};
        final ExecutorService[] pneumaticExecutor = {Executors.newSingleThreadExecutor()};
        final ExecutorService[] susDavantExecutor = {Executors.newSingleThreadExecutor()};
        final ExecutorService[] cockpitExecutor = {Executors.newSingleThreadExecutor()};
        final ExecutorService[] portonsExecutor = {Executors.newSingleThreadExecutor()};
        final ExecutorService[] fonsExecutor = {Executors.newSingleThreadExecutor()};
        final ExecutorService[] susTraseraExecutor = {Executors.newSingleThreadExecutor()};
        final ExecutorService[] aTraserExecutor = {Executors.newSingleThreadExecutor()};

        /**
         * Click de l'usuari al cotxe
         */
        clicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalVoltes = totalVoltes.add(BigDecimal.valueOf(valorClickUsuari));
                numVoltes.setText(totalVoltes.setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
            }
        });

        /**
         * Totes les millores del joc
         * CompareTo de BigInteger:
         * - 0 si son iguals els valors
         * - 1 si el valor que no passem per paràmetre és més gran que el que passem per paràmetre
         * - -1 si el valor que no passem per paràmetre és més petit que el que passem per paràmetre
         */
        aDavant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = "aDavant";
                imatgeMillora.setImageResource(R.drawable.alero_davanter);
                imatgeMillora.setScaleX(1.5f);
                imatgeMillora.setScaleY(1.5f);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("aDavant"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100);
                produccioMillora.setText(voltesPerSegonMillores.get(milloraActiva).setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
            }
        });

        aDavant.performClick(); //Realitzem un click per situar la primera millora al principi de l'aplicació

        pneumatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = "pneumatic";
                imatgeMillora.setImageResource(R.drawable.roda);
                imatgeMillora.setScaleX(1);
                imatgeMillora.setScaleY(1);
                imatgeMillora.setMaxHeight(imatgeMillora.getMaxWidth());
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("pneumatic"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100);
                produccioMillora.setText(voltesPerSegonMillores.get(milloraActiva).setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
            }
        });

        susDavant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = "susDavant";
                imatgeMillora.setImageResource(R.drawable.suspensio_davantera);
                imatgeMillora.setScaleX(1);
                imatgeMillora.setScaleY(1);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("susDavant"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100);
                produccioMillora.setText(voltesPerSegonMillores.get(milloraActiva).setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
            }
        });

        cockpit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = "cockpit";
                imatgeMillora.setImageResource(R.drawable.cockpit);
                imatgeMillora.setScaleX(1.25f);
                imatgeMillora.setScaleY(1.25f);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("cockpit"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100);
                produccioMillora.setText(voltesPerSegonMillores.get(milloraActiva).setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
            }
        });

        portons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = "portons";
                imatgeMillora.setImageResource(R.drawable.portons);
                imatgeMillora.setScaleX(1);
                imatgeMillora.setScaleY(1);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("portons"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100);
                produccioMillora.setText(voltesPerSegonMillores.get(milloraActiva).setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
            }
        });

        fons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = "fons";
                imatgeMillora.setImageResource(R.drawable.fons);
                imatgeMillora.setScaleX(1);
                imatgeMillora.setScaleY(1);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("fons"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100);
                produccioMillora.setText(voltesPerSegonMillores.get(milloraActiva).setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
            }
        });

        susTrasera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = "susTrasera";
                imatgeMillora.setImageResource(R.drawable.suspensio_trasera);
                imatgeMillora.setScaleX(1);
                imatgeMillora.setScaleY(1);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("susTrasera"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100);
                produccioMillora.setText(voltesPerSegonMillores.get(milloraActiva).setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
            }
        });

        aTraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = "aTraser";
                imatgeMillora.setImageResource(R.drawable.alero_traser);
                imatgeMillora.setScaleX(1.25f);
                imatgeMillora.setScaleY(1.25f);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("aTraser"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100);
                produccioMillora.setText(voltesPerSegonMillores.get(milloraActiva).setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
            }
        });

        plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ComprovarCompra(1)) {
                    switch (milloraActiva) {
                        case "aDavant":
                            aDavant.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("0.1")));
                                aDavantExecutor[0].shutdownNow();
                                aDavantExecutor[0] = Executors.newSingleThreadExecutor();
                                aDavantExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                aDavantExecutor[0].execute(new Thread(new BigDecimal("0.1")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("0.1"));
                            }
                            break;
                        case "pneumatic":
                            pneumatic.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("1")));
                                pneumaticExecutor[0].shutdownNow();
                                pneumaticExecutor[0] = Executors.newSingleThreadExecutor();
                                pneumaticExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                pneumaticExecutor[0].execute(new Thread(new BigDecimal("1")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("1"));
                            }
                            break;
                        case "susDavant":
                            susDavant.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("10")));
                                susDavantExecutor[0].shutdownNow();
                                susDavantExecutor[0] = Executors.newSingleThreadExecutor();
                                susDavantExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                susDavantExecutor[0].execute(new Thread(new BigDecimal("10")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("10"));
                            }
                            break;
                        case "cockpit":
                            cockpit.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("50")));
                                cockpitExecutor[0].shutdownNow();
                                cockpitExecutor[0] = Executors.newSingleThreadExecutor();
                                cockpitExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                cockpitExecutor[0].execute(new Thread(new BigDecimal("50")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("50"));
                            }
                            break;
                        case "portons":
                            portons.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("250")));
                                portonsExecutor[0].shutdownNow();
                                portonsExecutor[0] = Executors.newSingleThreadExecutor();
                                portonsExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                portonsExecutor[0].execute(new Thread(new BigDecimal("250")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("250"));
                            }
                            break;
                        case "fons":
                            fons.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("1350")));
                                fonsExecutor[0].shutdownNow();
                                fonsExecutor[0] = Executors.newSingleThreadExecutor();
                                fonsExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                fonsExecutor[0].execute(new Thread(new BigDecimal("1350")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("1350"));
                            }
                            break;
                        case "susTrasera":
                            susTrasera.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("7680")));
                                susTraseraExecutor[0].shutdownNow();
                                susTraseraExecutor[0] = Executors.newSingleThreadExecutor();
                                susTraseraExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                susTraseraExecutor[0].execute(new Thread(new BigDecimal("7680")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("7680"));
                            }
                            break;
                        case "aTraser":
                            aTraser.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("25000")));
                                aTraserExecutor[0].shutdownNow();
                                aTraserExecutor[0] = Executors.newSingleThreadExecutor();
                                aTraserExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                aTraserExecutor[0].execute(new Thread(new BigDecimal("25000")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("25000"));
                            }
                            break;
                    }
                    produccioMillora.setText(voltesPerSegonMillores.get(milloraActiva).setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
                }
            }
        });

        plus10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ComprovarCompra(2)) {
                    switch (milloraActiva) {
                        case "aDavant":
                            aDavant.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("1")));
                                aDavantExecutor[0].shutdownNow();
                                aDavantExecutor[0] = Executors.newSingleThreadExecutor();
                                aDavantExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                aDavantExecutor[0].execute(new Thread(new BigDecimal("1")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("1"));
                            }
                            break;
                        case "pneumatic":
                            pneumatic.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("10")));
                                pneumaticExecutor[0].shutdownNow();
                                pneumaticExecutor[0] = Executors.newSingleThreadExecutor();
                                pneumaticExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                pneumaticExecutor[0].execute(new Thread(new BigDecimal("10")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("10"));
                            }
                            break;
                        case "susDavant":
                            susDavant.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("100")));
                                susDavantExecutor[0].shutdownNow();
                                susDavantExecutor[0] = Executors.newSingleThreadExecutor();
                                susDavantExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                susDavantExecutor[0].execute(new Thread(new BigDecimal("100")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("100"));
                            }
                            break;
                        case "cockpit":
                            cockpit.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("500")));
                                cockpitExecutor[0].shutdownNow();
                                cockpitExecutor[0] = Executors.newSingleThreadExecutor();
                                cockpitExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                cockpitExecutor[0].execute(new Thread(new BigDecimal("500")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("500"));
                            }
                            break;
                        case "portons":
                            portons.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("2500")));
                                portonsExecutor[0].shutdownNow();
                                portonsExecutor[0] = Executors.newSingleThreadExecutor();
                                portonsExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                portonsExecutor[0].execute(new Thread(new BigDecimal("2500")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("2500"));
                            }
                            break;
                        case "fons":
                            fons.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("13500")));
                                fonsExecutor[0].shutdownNow();
                                fonsExecutor[0] = Executors.newSingleThreadExecutor();
                                fonsExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                fonsExecutor[0].execute(new Thread(new BigDecimal("13500")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("13500"));
                            }
                            break;
                        case "susTrasera":
                            susTrasera.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("76800")));
                                susTraseraExecutor[0].shutdownNow();
                                susTraseraExecutor[0] = Executors.newSingleThreadExecutor();
                                susTraseraExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                susTraseraExecutor[0].execute(new Thread(new BigDecimal("76800")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("76800"));
                            }
                            break;
                        case "aTraser":
                            aTraser.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("250000")));
                                aTraserExecutor[0].shutdownNow();
                                aTraserExecutor[0] = Executors.newSingleThreadExecutor();
                                aTraserExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                aTraserExecutor[0].execute(new Thread(new BigDecimal("250000")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("250000"));
                            }
                            break;
                    }
                    produccioMillora.setText(voltesPerSegonMillores.get(milloraActiva).setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
                }
            }
        });

        plus100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ComprovarCompra(3)) {
                    switch (milloraActiva) {
                        case "aDavant":
                            aDavant.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("10")));
                                aDavantExecutor[0].shutdownNow();
                                aDavantExecutor[0] = Executors.newSingleThreadExecutor();
                                aDavantExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                aDavantExecutor[0].execute(new Thread(new BigDecimal("10")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("10"));
                            }
                            break;
                        case "pneumatic":
                            pneumatic.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("100")));
                                pneumaticExecutor[0].shutdownNow();
                                pneumaticExecutor[0] = Executors.newSingleThreadExecutor();
                                pneumaticExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                pneumaticExecutor[0].execute(new Thread(new BigDecimal("100")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("100"));
                            }
                            break;
                        case "susDavant":
                            susDavant.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("1000")));
                                susDavantExecutor[0].shutdownNow();
                                susDavantExecutor[0] = Executors.newSingleThreadExecutor();
                                susDavantExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                susDavantExecutor[0].execute(new Thread(new BigDecimal("1000")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("1000"));
                            }
                            break;
                        case "cockpit":
                            cockpit.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("5000")));
                                cockpitExecutor[0].shutdownNow();
                                cockpitExecutor[0] = Executors.newSingleThreadExecutor();
                                cockpitExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                cockpitExecutor[0].execute(new Thread(new BigDecimal("5000")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("5000"));
                            }
                            break;
                        case "portons":
                            portons.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("25000")));
                                portonsExecutor[0].shutdownNow();
                                portonsExecutor[0] = Executors.newSingleThreadExecutor();
                                portonsExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                portonsExecutor[0].execute(new Thread(new BigDecimal("25000")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("25000"));
                            }
                            break;
                        case "fons":
                            fons.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("135000")));
                                fonsExecutor[0].shutdownNow();
                                fonsExecutor[0] = Executors.newSingleThreadExecutor();
                                fonsExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                fonsExecutor[0].execute(new Thread(new BigDecimal("135000")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("135000"));
                            }
                            break;
                        case "susTrasera":
                            susTrasera.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("768000")));
                                susTraseraExecutor[0].shutdownNow();
                                susTraseraExecutor[0] = Executors.newSingleThreadExecutor();
                                susTraseraExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                susTraseraExecutor[0].execute(new Thread(new BigDecimal("768000")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("768000"));
                            }
                            break;
                        case "aTraser":
                            aTraser.performClick();
                            if(voltesPerSegonMillores.get(milloraActiva).compareTo(new BigDecimal("0")) == 1) {
                                voltesPerSegonMillores.put(milloraActiva, voltesPerSegonMillores.get(milloraActiva).add(new BigDecimal("2500000")));
                                aTraserExecutor[0].shutdownNow();
                                aTraserExecutor[0] = Executors.newSingleThreadExecutor();
                                aTraserExecutor[0].execute(new Thread(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                aTraserExecutor[0].execute(new Thread(new BigDecimal("2500000")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("2500000"));
                            }
                            break;
                    }
                    produccioMillora.setText(voltesPerSegonMillores.get(milloraActiva).setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
                }
            }
        });

        //Comptador per les voltes
        Comptador comptador = new Comptador(999999999, 500);
        comptador.start();
    }

    /**
     * Funció per actualitzar les voltes acumulades.
     */
    private void ActualitzaVoltes() {
        numVoltes.setText(totalVoltes.setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
    }

    /**
     * Funció que cridaran tots els threads per sumar les voltes corresponents.
     * @param numVoltes Voltes a sumar de cada millora
     */
    public static synchronized void SumarVoltes(BigDecimal numVoltes) {
        Log.i("Sumar", "M'han cridat per sumar voltes!");
        totalVoltes = totalVoltes.add(numVoltes);
    }

    /**
     * Comprovem si es pot comprar la millora que se'ns passa per paràmetre. Si es pot augmentem el nombre
     * de nivells de la millora.
     * @param tipusCompra Tipus de compra de nivells (1 - Un nivell, 2 - 10 nivells, 3 - 100 nivells)
     * @return Booleà de la comprovació interna respecte la realització
     * o no de la compra
     */
    private boolean ComprovarCompra(int tipusCompra) {
        switch (tipusCompra) {
            case 1:
                if(totalVoltes.compareTo(costMillora1) >= 0) {
                    totalVoltes = totalVoltes.subtract(costMillora1);
                    numVoltes.setText(totalVoltes.setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
                    nivellsMilloresHashMap.put(milloraActiva, nivellsMilloresHashMap.get(milloraActiva) + 1);
                    return true;
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.NoPotsComprar), Toast.LENGTH_SHORT).show();
                    return false;
                }
            case 2:
                if(totalVoltes.compareTo(costMillora10) >= 0) {
                    totalVoltes = totalVoltes.subtract(costMillora10);
                    numVoltes.setText(totalVoltes.setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
                    nivellsMilloresHashMap.put(milloraActiva, nivellsMilloresHashMap.get(milloraActiva) + 10);
                    return true;
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.NoPotsComprar), Toast.LENGTH_SHORT).show();
                    return false;
                }
            case 3:
                if(totalVoltes.compareTo(costMillora100) >= 0) {
                    totalVoltes = totalVoltes.subtract(costMillora100);
                    numVoltes.setText(totalVoltes.setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
                    nivellsMilloresHashMap.put(milloraActiva, nivellsMilloresHashMap.get(milloraActiva) + 100);
                    return true;
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.NoPotsComprar), Toast.LENGTH_SHORT).show();
                    return false;
                }
            default:
                return false;
        }
    }

    /**
     *
     * @param costPlus1
     * @param costPlus10
     * @param costPlus100
     */
    private void CanviTextBotons(TextView costPlus1, TextView costPlus10, TextView costPlus100) {
        BigDecimal up1 = CalculEdificis(1, milloraActiva);
        costMillora1 = up1;
        int power = 0;
        while(up1.compareTo(new BigDecimal("10000")) == 1) {
            if(up1.compareTo(new BigDecimal("1000000")) == 1) {
                up1 = up1.divide(new BigDecimal("1000000"));
                power += 6;
            }
            else if(up1.compareTo(new BigDecimal("10000")) == 1) {
                up1 = up1.divide(new BigDecimal("10000"));
                power += 4;
            }
        }
        if(power > 0)
            costPlus1.setText(up1.setScale(2, RoundingMode.HALF_UP) + "x10^" + power + " " + getString(R.string.Voltes));
        else
            costPlus1.setText(up1.setScale(0, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));

        BigDecimal up10 = CalculEdificis(10, milloraActiva);
        costMillora10 = up10;
        power = 0;
        while(up10.compareTo(new BigDecimal("10000")) == 1) {
            if(up10.compareTo(new BigDecimal("1000000")) == 1) {
                up10 = up10.divide(new BigDecimal("1000000"));
                power += 6;
            }
            else if(up10.compareTo(new BigDecimal("10000")) == 1) {
                up10 = up10.divide(new BigDecimal("10000"));
                power += 4;
            }
        }
        if(power > 0)
            costPlus10.setText(up10.setScale(2, RoundingMode.HALF_UP) + "x10^" + power + " " + getString(R.string.Voltes));
        else
            costPlus10.setText(up10.setScale(0, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));

        BigDecimal up100 = CalculEdificis(100, milloraActiva);
        costMillora100 = up100;
        power = 0;
        while(up100.compareTo(new BigDecimal("10000")) == 1) {
            if(up100.compareTo(new BigDecimal("1000000")) == 1) {
                up100 = up100.divide(new BigDecimal("1000000"));
                power += 6;
            }
            else if(up100.compareTo(new BigDecimal("10000")) == 1) {
                up100 = up100.divide(new BigDecimal("10000"));
                power += 4;
            }
        }
        if(power > 0)
            costPlus100.setText(up100.setScale(2, RoundingMode.HALF_UP) + "x10^" + power + " " + getString(R.string.Voltes));
        else
            costPlus100.setText(up100.setScale(0, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
    }

    /**
     * Càlcul del cost dels nivells de les millores.
     * @param numEdificis Número de nivells a comprar
     * @param millora Nom de la millora que estem comprant
     * @return Cost de la compra dels nivells en format BigDecimal
     */
    private BigDecimal CalculEdificis(int numEdificis, String millora) {
        BigDecimal numBase = new BigDecimal("1.15");
        BigDecimal preuBase = new BigDecimal(preuBaseMillores.get(millora).toString());
        BigDecimal aux = new BigDecimal("0");
        int pot = nivellsMilloresHashMap.get(millora);
        for(int i = 0; i < numEdificis; i++) {
            aux = aux.add(preuBase.multiply(numBase.pow(pot)));
            pot++;
        }
        return aux;
    }

    /**
     * Comptador per cridar cada 500ms la funció {@link #ActualitzaVoltes()}.
     */
    public class Comptador extends CountDownTimer {

        public Comptador(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            Comptador aux = new Comptador(999999999, 500);
            aux.start();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            ActualitzaVoltes();
        }
    }
}