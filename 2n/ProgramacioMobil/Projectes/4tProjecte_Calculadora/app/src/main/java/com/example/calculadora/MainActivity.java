package com.example.calculadora;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    float num1 = 0;
    boolean decimal = false;
    boolean num1Ocupat = false;
    String simbol = "";
    boolean negatiuNum = false;
    boolean divZero = false;
    boolean calculat = false;
    float numMemoria = 0;
    boolean infoMemoria = false;

    //Variable que utilitzem per evitar que a l'imprimir per pantalla els resultats numèrics
    //se'ns imprimeixin amb exponents
    DecimalFormat df = new DecimalFormat("#.#####");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView text = (TextView) findViewById(R.id.Text);
        TextView calculMostra = (TextView) findViewById(R.id.CalculMostra);

        /*
         *
         * OCL per als botons dels números. Cada número mira:
         * · Si el text actual del TextView no és un zero.
         * · Si estàs dividint entre zero.
         * · Si s'ha fet click al botó d'igual per mostrar el resultat calculat.
         *
         * Si en qualsevol d'aquests casos és veritat, en comptes d'afegir el número
         * al text actual del TextView canvia el contingut pel seu valor.
         *
         * En tots els números (excepte el zero), una vegada es canvia el contingut
         * pel seu valor canvia el valor de la variable booleana calculat a false
         * per permetre anar afegint els següents números contigus al TextView.
         *
         */
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.Num_0){
                    if(!text.getText().equals("0") && !divZero && !calculat)
                        text.setText(text.getText()+"0");
                    else if(divZero)
                        text.setText("0");
                }
                else if(view.getId() == R.id.Num_1) {
                    if(!text.getText().equals("0") && !divZero && !calculat)
                        text.setText(text.getText()+"1");
                    else {
                        text.setText("1");
                        calculat = false;
                    }
                }
                else if(view.getId() == R.id.Num_2) {
                    if(!text.getText().equals("0") && !divZero && !calculat)
                        text.setText(text.getText()+"2");
                    else{
                        text.setText("2");
                        calculat = false;
                    }
                }
                else if(view.getId() == R.id.Num_3) {
                    if(!text.getText().equals("0") && !divZero && !calculat)
                        text.setText(text.getText()+"3");
                    else{
                        text.setText("3");
                        calculat = false;
                    }
                }
                else if(view.getId() == R.id.Num_4) {
                    if(!text.getText().equals("0") && !divZero && !calculat)
                        text.setText(text.getText()+"4");
                    else{
                        text.setText("4");
                        calculat = false;
                    }
                }
                else if(view.getId() == R.id.Num_5) {
                    if(!text.getText().equals("0") && !divZero && !calculat)
                        text.setText(text.getText()+"5");
                    else{
                        text.setText("5");
                        calculat = false;
                    }
                }
                else if(view.getId() == R.id.Num_6) {
                    if(!text.getText().equals("0") && !divZero && !calculat)
                        text.setText(text.getText()+"6");
                    else{
                        text.setText("6");
                        calculat = false;
                    }
                }
                else if(view.getId() == R.id.Num_7) {
                    if(!text.getText().equals("0") && !divZero && !calculat)
                        text.setText(text.getText()+"7");
                    else{
                        text.setText("7");
                        calculat = false;
                    }
                }
                else if(view.getId() == R.id.Num_8) {
                    if(!text.getText().equals("0") && !divZero && !calculat)
                        text.setText(text.getText()+"8");
                    else{
                        text.setText("8");
                        calculat = false;
                    }
                }
                else if(view.getId() == R.id.Num_9) {
                    if(!text.getText().equals("0") && !divZero && !calculat)
                        text.setText(text.getText()+"9");
                    else{
                        text.setText("9");
                        calculat = false;
                    }
                }
                if(divZero)
                    divZero = false;
            }
        };

        findViewById(R.id.Num_0).setOnClickListener(ocl);
        findViewById(R.id.Num_1).setOnClickListener(ocl);
        findViewById(R.id.Num_2).setOnClickListener(ocl);
        findViewById(R.id.Num_3).setOnClickListener(ocl);
        findViewById(R.id.Num_4).setOnClickListener(ocl);
        findViewById(R.id.Num_5).setOnClickListener(ocl);
        findViewById(R.id.Num_6).setOnClickListener(ocl);
        findViewById(R.id.Num_7).setOnClickListener(ocl);
        findViewById(R.id.Num_8).setOnClickListener(ocl);
        findViewById(R.id.Num_9).setOnClickListener(ocl);

        /*
         *
         * OCL per la resta de botons de la calculadora.
         *
         * En els botons dels símbols matemàtics es comproven diverses condicions:
         * · Primer comprovem si el TextView no és buit.
         * · Si hi ha guardat un símbol matemàtic a la variable simbol.
         * · Si ja hem inserit un número anteriorment a la calculadora.
         *
         * Per cada condició es realitza una acció diferent:
         * · Si no hi ha cap número actualment al TextView no fem cap tipus de càlcul.
         * · Si ja existeix un símbol a la nostra variable simbol passem el nombre actual
         *   del TextView i el símbol guardat a la funció calculConcatenat.
         * · Si ja hem afegit un número a la calculadora anteriorment afegirà el nou número
         *   al número anterior (que tenim emmagatzemat a la variable num1).
         * · Si és el primer número que afegim a la calculadora afegirem aquest a la nostra
         *   variable num1 i canviarem el valor de la variable booleana num1Ocupat a true per
         *   a que es compleixi la condició anteriorment esmenada.
         *
         */

        View.OnClickListener oclSimbols = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.Calc_C) {
                    text.setText("0");
                    num1 = 0;
                    simbol = "";
                    num1Ocupat = false;
                    decimal = false;
                    calculMostra.setText("");
                }
                //Aquest if està posat per evitar problemes quan has intentat fer una divisió entre 0
                //(no et deixa fer cap tipus de càlcul, només esborrar la informació)
                if(!divZero) {
                    if(view.getId() == R.id.Calc_Decimal) {
                        if(!decimal && !text.getText().equals("")) {
                            text.setText(text.getText()+".");
                            decimal = true;
                        }
                    }
                    //Al botó d'igual es realitza el càlcul del símbol que tenim guardat entre el nombre
                    //de la variable num1 i el que hi ha escrit al TextView text i s'escriu per pantalla
                    //en el mateix
                    else if(view.getId() == R.id.Calc_Igual) {
                        if(!text.getText().equals("")) {
                            switch (simbol) {
                                case "+":
                                    float aux = Float.parseFloat(text.getText().toString());
                                    calculMostra.setText(df.format(num1) + "+" + df.format(aux) + "=");
                                    text.setText(df.format(num1+aux));
                                    break;
                                case "-":
                                    float aux3 = Float.parseFloat(text.getText().toString());
                                    calculMostra.setText(df.format(num1) + "-" + df.format(aux3) + "=");
                                    text.setText(df.format(num1-aux3));
                                    break;
                                case "/":
                                    if(Float.parseFloat(text.getText().toString()) != 0) {
                                        float aux5 = Float.parseFloat(text.getText().toString());
                                        calculMostra.setText(df.format(num1) + "/" + df.format(aux5) + "=");
                                        float div = num1/aux5;
                                        text.setText(df.format(div));
                                    }
                                    else {
                                        text.setText("No es pot dividir entre 0!");
                                        num1 = 0;
                                        divZero = true;
                                    }
                                    break;
                                case "X":
                                    float aux7 = Float.parseFloat(text.getText().toString());
                                    calculMostra.setText(df.format(num1) + "*" + df.format(aux7) + "=");
                                    float mult = num1*aux7;
                                    text.setText(df.format(mult));
                                    break;
                            }
                        }
                        else {
                            calculMostra.setText("");
                            text.setText("0");
                        }
                        simbol = "";
                        num1Ocupat = false;
                        decimal = false;
                        calculat = true;
                        negatiuNum = false;
                    }
                    else if(view.getId() == R.id.Calc_Div) {
                        if(!text.getText().equals("")) {
                            if(!simbol.equals("")) {
                                calculConcatenat(simbol, text);
                                calculMostra.setText(df.format(num1) + "/");
                            }
                            else {
                                if(num1Ocupat)
                                    num1 /= Float.parseFloat(text.getText().toString());
                                else {
                                    num1 = Float.parseFloat(text.getText().toString());
                                    calculMostra.setText(df.format(num1) + "/");
                                    num1Ocupat = true;
                                }
                            }
                            text.setText("");
                            simbol = "/";
                            decimal = false;
                            negatiuNum = false;
                        }
                    }
                    else if(view.getId() == R.id.Calc_Mult) {
                        if(!text.getText().equals("")) {
                            if(!simbol.equals("")) {
                                calculConcatenat(simbol, text);
                                calculMostra.setText(df.format(num1) + "*");
                            }
                            else {
                                if(num1Ocupat)
                                    num1 *= Float.parseFloat(text.getText().toString());
                                else {
                                    num1 = Float.parseFloat(text.getText().toString());
                                    calculMostra.setText(df.format(num1) + "*");
                                    num1Ocupat = true;
                                }
                            }
                            text.setText("");
                            simbol = "X";
                            decimal = false;
                            negatiuNum = false;
                        }
                    }
                    else if(view.getId() == R.id.Calc_Resta) {
                        if(!text.getText().equals("")) {
                            if(!simbol.equals("")) {
                                calculConcatenat(simbol, text);
                                calculMostra.setText(df.format(num1) + "-");
                            }
                            else {
                                if(num1Ocupat)
                                    num1 -= Float.parseFloat(text.getText().toString());
                                else {
                                    num1 = Float.parseFloat(text.getText().toString());
                                    calculMostra.setText(df.format(num1) + "-");
                                    num1Ocupat = true;
                                }
                            }
                            text.setText("");
                            simbol = "-";
                            decimal = false;
                            negatiuNum = false;
                        }
                    }
                    //El botó de símbol mira si hi ha cap text en el TextView. Si hi ha cap número mira si aquest ja
                    //l'hem posat en negatiu. Si no ho hem fet només posa el símbol de negatiu al davant del text.
                    //Si ja ho hem fet agafa el nombre, el multiplica per -1 i el torna a imprimir per pantalla
                    //(Mira per si de cas que no haguem intentat només posar un 0 en negatiu, si aquest és el cas
                    //treu la variable decimal de true a false per evitar problemes).
                    else if(view.getId() == R.id.Calc_Simbol) {
                        if(!text.getText().equals("0") && !text.getText().equals("") && !negatiuNum) {
                            negatiuNum = true;
                            text.setText("-"+text.getText());
                        }
                        else if(negatiuNum) {
                            float aux = Float.parseFloat(text.getText().toString());
                            aux *= -1;
                            text.setText(df.format(aux));
                            if (aux == 0)
                                decimal = false;
                            negatiuNum = false;
                        }
                    }
                    else if(view.getId() == R.id.Calc_Suma) {
                        if(!text.getText().equals("")) {
                            if(!simbol.equals("")) {
                                calculConcatenat(simbol, text);
                                calculMostra.setText(df.format(num1) + "+");
                            }
                            else {
                                if(num1Ocupat){
                                    num1 += Float.parseFloat(text.getText().toString());
                                }
                                else {
                                    num1 = Float.parseFloat(text.getText().toString());
                                    calculMostra.setText(df.format(num1) + "+");
                                    num1Ocupat = true;
                                }
                            }
                            text.setText("");
                            simbol = "+";
                            decimal = false;
                            negatiuNum = false;
                        }
                    }
                    else if(view.getId() == R.id.Calc_CE) {
                        text.setText("0");
                    }
                    else if(view.getId() == R.id.Calc_Borra) {
                        if(!text.getText().equals("") && text.getText().length() > 1) {
                            CharSequence llista = text.getText();
                            text.setText(llista.subSequence(0, llista.length() - 1));
                        }
                        else if (text.getText().length() == 1) {
                            text.setText("0");
                        }
                    }
                    //Aquests 4 últims botons són els que realitzen els càlculs a memòria
                    //Bàsicament realitzen tots els càlculs sobre una variable existent numMemoria i
                    //utilitzen una flag infoMemoria per informar de que hi ha nombres a la variable
                    //quan intentem imprimir per pantalla la informació de infoMemoria
                    else if(view.getId() == R.id.MemoryAdd) {
                        infoMemoria = true;
                        numMemoria += Float.parseFloat(text.getText().toString());
                    }
                    else if(view.getId() == R.id.MemorySub) {
                        infoMemoria = true;
                        numMemoria -= Float.parseFloat(text.getText().toString());
                    }
                    else if(view.getId() == R.id.MemoryResult) {
                        if(infoMemoria) {
                            text.setText(df.format(numMemoria));
                            calculat = true;
                        }
                    }
                    else if(view.getId() == R.id.MemoryClear) {
                        infoMemoria = false;
                        numMemoria = 0;
                    }
                }
            }
        };

        findViewById(R.id.MemoryClear).setOnClickListener(oclSimbols);
        findViewById(R.id.MemoryResult).setOnClickListener(oclSimbols);
        findViewById(R.id.MemoryAdd).setOnClickListener(oclSimbols);
        findViewById(R.id.MemorySub).setOnClickListener(oclSimbols);
        findViewById(R.id.Calc_C).setOnClickListener(oclSimbols);
        findViewById(R.id.Calc_Decimal).setOnClickListener(oclSimbols);
        findViewById(R.id.Calc_Div).setOnClickListener(oclSimbols);
        findViewById(R.id.Calc_Igual).setOnClickListener(oclSimbols);
        findViewById(R.id.Calc_Mult).setOnClickListener(oclSimbols);
        findViewById(R.id.Calc_Resta).setOnClickListener(oclSimbols);
        findViewById(R.id.Calc_Suma).setOnClickListener(oclSimbols);
        findViewById(R.id.Calc_Simbol).setOnClickListener(oclSimbols);
        findViewById(R.id.Calc_CE).setOnClickListener(oclSimbols);
        findViewById(R.id.Calc_Borra).setOnClickListener(oclSimbols);
    }

    /*
     *
     * Funció per realitzar els càlculs amb els paràmetres que se li passen (símbol i el segon número,
     * el primer és al variable num1 global de la classe)
     *
     * @param simbol  Símbol amb el que hem de realitzar el càlcul matemàtic
     * @param text    Últim número amb el que es realizta el càlcul
     */

    private void calculConcatenat(String simbol, TextView text) {
        switch (simbol) {
            case "+":
                num1 += Float.parseFloat(text.getText().toString());
                break;
            case "-":
                num1 -= Float.parseFloat(text.getText().toString());
                break;
            case "/":
                if(Float.parseFloat(text.getText().toString()) != 0) {
                    num1 /= Float.parseFloat(text.getText().toString());
                }
                else {
                    text.setText("No es pot dividir entre 0!");
                    num1 = 0;
                    divZero = true;
                }
                break;
            case "X":
                num1 *= Float.parseFloat(text.getText().toString());
                break;
        }
    }
}