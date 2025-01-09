package com.projecte.formula1_clicker;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.os.Debug;
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

public class Main extends AppCompatActivity {
    static int valorClickUsuari;
    static BigInteger totalVoltes;
    static HashMap<String, Integer> nivellsMilloresHashMap;
    static HashMap<String, Integer> preuBaseMillores;
    static int milloraActiva; // Llista de millores -> aDavant = 0 | pneumatic = 1 | susDavant = 2 | cockpit = 3 | portons = 4 | fons = 5 | susTrasera = 6 | atraser = 7
    static BigInteger costMillora1;
    static BigInteger costMillora10;
    static BigInteger costMillora100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.joc);
        milloraActiva = -1;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("prova");
        //myRef.setValue("Hola, soy Niko!!");
        valorClickUsuari = 1; //Canviar això amb el firebase
        totalVoltes = new BigInteger("0"); //Canviar això amb el firebase

        //Menú
        Button trofeus = (Button) findViewById(R.id.BotoTrofeus);
        Button config = (Button) findViewById(R.id.BotoConfiguracio);

        //Num voltes
        TextView numVoltes = (TextView) findViewById(R.id.NombreVoltes);
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

        /**
         * Click de l'usuari al cotxe
         */
        clicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalVoltes = totalVoltes.add(BigInteger.valueOf(valorClickUsuari));
                numVoltes.setText(totalVoltes + " " + getString(R.string.Voltes));
                myRef.child("Voltes").setValue(totalVoltes);
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
                milloraActiva = 0;
                imatgeMillora.setImageResource(R.drawable.alero_davanter);
                imatgeMillora.setScaleX(1.5f);
                imatgeMillora.setScaleY(1.5f);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("aDavant"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100, "aDavant");
            }
        });

        pneumatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = 1;
                imatgeMillora.setImageResource(R.drawable.roda);
                imatgeMillora.setScaleX(1);
                imatgeMillora.setScaleY(1);
                imatgeMillora.setMaxHeight(imatgeMillora.getMaxWidth());
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("pneumatic"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100, "pneumatic");
            }
        });

        susDavant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = 2;
                imatgeMillora.setImageResource(R.drawable.suspensio_davantera);
                imatgeMillora.setScaleX(1);
                imatgeMillora.setScaleY(1);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("susDavant"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100, "susDavant");
            }
        });

        cockpit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = 3;
                imatgeMillora.setImageResource(R.drawable.cockpit);
                imatgeMillora.setScaleX(1);
                imatgeMillora.setScaleY(1);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("cockpit"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100, "cockpit");
            }
        });

        portons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = 4;
                imatgeMillora.setImageResource(R.drawable.portons);
                imatgeMillora.setScaleX(1);
                imatgeMillora.setScaleY(1);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("portons"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100, "portons");
            }
        });

        fons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = 5;
                imatgeMillora.setImageResource(R.drawable.fons);
                imatgeMillora.setScaleX(1);
                imatgeMillora.setScaleY(1);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("fons"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100, "fons");
            }
        });

        susTrasera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = 6;
                imatgeMillora.setImageResource(R.drawable.suspensio_trasera);
                imatgeMillora.setScaleX(1);
                imatgeMillora.setScaleY(1);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("susTrasera"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100, "susTrasera");
            }
        });

        aTraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = 7;
                imatgeMillora.setImageResource(R.drawable.alero_traser);
                imatgeMillora.setScaleX(1.5f);
                imatgeMillora.setScaleY(1.5f);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("aTraser"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100, "aTraser");
            }
        });

        plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (milloraActiva) {
                    case 0:
                        ComprovarCompra("aDavant", 1);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    default:
                        break;
                }
            }
        });

        plus10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (milloraActiva) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    default:
                        break;
                }
            }
        });

        plus100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (milloraActiva) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void ComprovarCompra(String millora, int tipusCompra) {
        switch (tipusCompra) {
            case 1:
                if(costMillora1.compareTo(totalVoltes) == 1) {
                    totalVoltes.subtract(costMillora1);
                    nivellsMilloresHashMap.put("aDavant", nivellsMilloresHashMap.get("aDavant") + 1);
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.NoPotsComprar), Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if(costMillora10.compareTo(totalVoltes) == 1) {
                    totalVoltes.subtract(costMillora10);
                    nivellsMilloresHashMap.put("aDavant", nivellsMilloresHashMap.get("aDavant") + 1);
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.NoPotsComprar), Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if(costMillora100.compareTo(totalVoltes) == 1) {
                    totalVoltes.subtract(costMillora100);
                    nivellsMilloresHashMap.put("aDavant", nivellsMilloresHashMap.get("aDavant") + 1);
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.NoPotsComprar), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void CanviTextBotons(TextView costPlus1, TextView costPlus10, TextView costPlus100, String millora) {
        BigInteger up1 = CalculEdificis(1, millora);
        costMillora1 = up1;
        int power = 0;
        while(up1.compareTo(new BigInteger("1000000")) == 1) {
            up1.divide(new BigInteger("1000000"));
            power++;
        }
        if(power > 0)
            costPlus1.setText(up1 + "x10^" + power + " " + getString(R.string.Voltes));
        else
            costPlus1.setText(up1 + " " + getString(R.string.Voltes));

        BigInteger up10 = CalculEdificis(10, millora);
        costMillora10 = up10;
        power = 0;
        while(up10.compareTo(new BigInteger("1000000")) == 1) {
            up10.divide(new BigInteger("1000000"));
            power++;
        }
        if(power > 0)
            costPlus10.setText(up10 + "x10^" + power + " " + getString(R.string.Voltes));
        else
            costPlus10.setText(up10 + " " + getString(R.string.Voltes));

        BigInteger up100 = CalculEdificis(100, millora);
        costMillora100 = up100;
        power = 0;
        while(up100.compareTo(new BigInteger("1000000")) == 1) {
            up100.divide(new BigInteger("1000000"));
            power++;
        }
        if(power > 0)
            costPlus100.setText(up100 + "x10^" + power + " " + getString(R.string.Voltes));
        else
            costPlus100.setText(up100 + " " + getString(R.string.Voltes));
    }

    private BigInteger CalculEdificis(int numEdificis, String millora) {

        BigInteger numBase = new BigInteger("1.15");
        BigInteger preuBase = new BigInteger(preuBaseMillores.get(millora).toString());
        return preuBase.multiply(numBase.pow(nivellsMilloresHashMap.get(millora) * numEdificis));
    }
}