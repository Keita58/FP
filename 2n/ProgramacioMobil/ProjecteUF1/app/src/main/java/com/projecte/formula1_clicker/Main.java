package com.projecte.formula1_clicker;

import android.content.res.Resources;
import android.os.Bundle;
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
import com.projecte.formula1_clicker.runnable.ADavant;
import com.projecte.formula1_clicker.runnable.ATraser;
import com.projecte.formula1_clicker.runnable.Cockpit;
import com.projecte.formula1_clicker.runnable.Fons;
import com.projecte.formula1_clicker.runnable.Pneumatic;
import com.projecte.formula1_clicker.runnable.Portons;
import com.projecte.formula1_clicker.runnable.SusDavant;
import com.projecte.formula1_clicker.runnable.SusTrasera;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
    static WeakReference<TextView> numVoltesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.joc);
        milloraActiva = "";

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("prova");
        //myRef.setValue("Hola, soy Niko!!");
        valorClickUsuari = 1; //Canviar això amb el firebase
        totalVoltes = new BigDecimal("0"); //Canviar això amb el firebase

        //Menú
        Button trofeus = (Button) findViewById(R.id.BotoTrofeus);
        Button config = (Button) findViewById(R.id.BotoConfiguracio);

        //Num voltes
        TextView numVoltes = (TextView) findViewById(R.id.NombreVoltes);
        numVoltesText = new WeakReference<>(numVoltes);
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
        ExecutorService pneumaticExecutor = Executors.newSingleThreadExecutor();
        ExecutorService susDavantExecutor = Executors.newSingleThreadExecutor();
        ExecutorService cockpitExecutor = Executors.newSingleThreadExecutor();
        ExecutorService portonsExecutor = Executors.newSingleThreadExecutor();
        ExecutorService fonsExecutor = Executors.newSingleThreadExecutor();
        ExecutorService susTraseraExecutor = Executors.newSingleThreadExecutor();
        ExecutorService aTraserExecutor = Executors.newSingleThreadExecutor();

        /**
         * Click de l'usuari al cotxe
         */
        clicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalVoltes = totalVoltes.add(BigDecimal.valueOf(valorClickUsuari));
                numVoltes.setText(totalVoltes.setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
                myRef.child("Voltes").setValue(totalVoltes.toString());
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
            }
        });

        cockpit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milloraActiva = "cockpit";
                imatgeMillora.setImageResource(R.drawable.cockpit);
                imatgeMillora.setScaleX(1);
                imatgeMillora.setScaleY(1);
                nivellMillora.setText(getString(R.string.Nivell) + " " + nivellsMilloresHashMap.get("cockpit"));

                CanviTextBotons(costPlus1, costPlus10, costPlus100);
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
                                aDavantExecutor[0].execute(new ADavant(voltesPerSegonMillores.get(milloraActiva)));
                            }
                            else {
                                aDavantExecutor[0].execute(new ADavant(new BigDecimal("0.1")));
                                voltesPerSegonMillores.put(milloraActiva, new BigDecimal("0.1"));
                            }
                            break;
                        case "pneumatic":
                            pneumatic.performClick();
                            pneumaticExecutor.execute(new Pneumatic());
                            break;
                        case "susDavant":
                            susDavant.performClick();
                            susDavantExecutor.execute(new SusDavant());
                            break;
                        case "cockpit":
                            cockpit.performClick();
                            cockpitExecutor.execute(new Cockpit());
                            break;
                        case "portons":
                            portons.performClick();
                            portonsExecutor.execute(new Portons());
                            break;
                        case "fons":
                            fons.performClick();
                            fonsExecutor.execute(new Fons());
                            break;
                        case "susTrasera":
                            susTrasera.performClick();
                            susTraseraExecutor.execute(new SusTrasera());
                            break;
                        case "aTraser":
                            aTraser.performClick();
                            aTraserExecutor.execute(new ATraser());
                            break;
                    }
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
                            break;
                        case "pneumatic":
                            pneumatic.performClick();
                            break;
                        case "susDavant":
                            susDavant.performClick();
                            break;
                        case "cockpit":
                            cockpit.performClick();
                            break;
                        case "portons":
                            portons.performClick();
                            break;
                        case "fons":
                            fons.performClick();
                            break;
                        case "susTrasera":
                            susTrasera.performClick();
                            break;
                        case "aTraser":
                            aTraser.performClick();
                            break;
                    }
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
                            break;
                        case "pneumatic":
                            pneumatic.performClick();
                            break;
                        case "susDavant":
                            susDavant.performClick();
                            break;
                        case "cockpit":
                            cockpit.performClick();
                            break;
                        case "portons":
                            portons.performClick();
                            break;
                        case "fons":
                            fons.performClick();
                            break;
                        case "susTrasera":
                            susTrasera.performClick();
                            break;
                        case "aTraser":
                            aTraser.performClick();
                            break;
                    }
                }
            }
        });
    }

    /**
     * Funció que cridaran tots els threads per sumar les voltes corresponents.
     * @param numVoltes Voltes a sumar de cada millora
     */
    public static synchronized void SumarVoltes(BigDecimal numVoltes) {
        Log.i("Sumar", "M'han cridat per sumar voltes!");
        totalVoltes = totalVoltes.add(numVoltes);
        numVoltesText.get().setText(totalVoltes.setScale(2, RoundingMode.HALF_UP) + " " + Resources.getSystem().getString(R.string.Voltes));
    }

    /**
     * Comprovem si es pot comprar la millora que se'ns passa per paràmetre. Si es pot augmentem el nombre
     * de nivells de la millora
     * @param tipusCompra Tipus de compra de nivells (1 - Un nivell, 2 - 10 nivells, 3 - 100 nivells)
     * @return Booleà de la comprovació interna respecte la realització
     * o no de la compra
     */
    private boolean ComprovarCompra(int tipusCompra) {
        switch (tipusCompra) {
            case 1:
                if(totalVoltes.compareTo(costMillora1) >= 0) {
                    totalVoltes.subtract(costMillora1);
                    numVoltesText.get().setText(totalVoltes.setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
                    nivellsMilloresHashMap.put(milloraActiva, nivellsMilloresHashMap.get(milloraActiva) + 1);
                    return true;
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.NoPotsComprar), Toast.LENGTH_SHORT).show();
                    return false;
                }
            case 2:
                if(totalVoltes.compareTo(costMillora10) >= 0) {
                    totalVoltes.subtract(costMillora10);
                    numVoltesText.get().setText(totalVoltes.setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
                    nivellsMilloresHashMap.put(milloraActiva, nivellsMilloresHashMap.get(milloraActiva) + 10);
                    return true;
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.NoPotsComprar), Toast.LENGTH_SHORT).show();
                    return false;
                }
            case 3:
                if(totalVoltes.compareTo(costMillora100) >= 0) {
                    totalVoltes.subtract(costMillora100);
                    numVoltesText.get().setText(totalVoltes.setScale(2, RoundingMode.HALF_UP) + " " + getString(R.string.Voltes));
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
}