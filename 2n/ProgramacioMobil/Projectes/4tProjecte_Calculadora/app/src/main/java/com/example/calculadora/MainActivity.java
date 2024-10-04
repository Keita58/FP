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

    DecimalFormat df = new DecimalFormat("#.#####");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView text = (TextView) findViewById(R.id.Text);
        TextView calculMostra = (TextView) findViewById(R.id.CalculMostra);

        Button Num_0 = (Button) findViewById(R.id.Num_0);
        Button Num_1 = (Button) findViewById(R.id.Num_1);
        Button Num_2 = (Button) findViewById(R.id.Num_2);
        Button Num_3 = (Button) findViewById(R.id.Num_3);
        Button Num_4 = (Button) findViewById(R.id.Num_4);
        Button Num_5 = (Button) findViewById(R.id.Num_5);
        Button Num_6 = (Button) findViewById(R.id.Num_6);
        Button Num_7 = (Button) findViewById(R.id.Num_7);
        Button Num_8 = (Button) findViewById(R.id.Num_8);
        Button Num_9 = (Button) findViewById(R.id.Num_9);
        Button MemClear = (Button) findViewById(R.id.MemoryClear);
        Button MemResult = (Button) findViewById(R.id.MemoryResult);
        Button MemAdd = (Button) findViewById(R.id.MemoryAdd);
        Button MemSub = (Button) findViewById(R.id.MemorySub);
        Button Calc_C = (Button) findViewById(R.id.Calc_C);
        Button Calc_Decimal = (Button) findViewById(R.id.Calc_Decimal);
        Button Calc_Div = (Button) findViewById(R.id.Calc_Div);
        Button Calc_Igual = (Button) findViewById(R.id.Calc_Igual);
        Button Calc_Mult = (Button) findViewById(R.id.Calc_Mult);
        Button Calc_Resta = (Button) findViewById(R.id.Calc_Resta);
        Button Calc_Suma = (Button) findViewById(R.id.Calc_Suma);
        Button Calc_Simbol = (Button) findViewById(R.id.Calc_Simbol);

        //OCL pels números
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(calculat)
                    calculMostra.setText("");

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

        Num_0.setOnClickListener(ocl);
        Num_1.setOnClickListener(ocl);
        Num_2.setOnClickListener(ocl);
        Num_3.setOnClickListener(ocl);
        Num_4.setOnClickListener(ocl);
        Num_5.setOnClickListener(ocl);
        Num_6.setOnClickListener(ocl);
        Num_7.setOnClickListener(ocl);
        Num_8.setOnClickListener(ocl);
        Num_9.setOnClickListener(ocl);

        //OCL per la resta
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
                if(!divZero) {
                    if(view.getId() == R.id.Calc_Decimal) {
                        if(!decimal && !text.getText().equals("")) {
                            text.setText(text.getText()+".");
                            decimal = true;
                        }
                    }
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
                        simbol = "";
                        num1Ocupat = false;
                        decimal = false;
                        calculat = true;
                    }
                    else if(view.getId() == R.id.Calc_Div) {
                        if(!text.getText().equals("")) {
                            if(!simbol.equals("")) {
                                calculConcatenat(simbol, text, calculMostra);
                                calculMostra.setText(df.format(num1) + "/");
                            }
                            else {
                                if(num1Ocupat)
                                    num1 /= Float.parseFloat(text.getText().toString());
                                else {
                                    num1 = Float.parseFloat(text.getText().toString());
                                    num1Ocupat = true;
                                }
                            }
                            text.setText("");
                            simbol = "/";
                            decimal = false;
                        }
                    }
                    else if(view.getId() == R.id.Calc_Mult) {
                        if(!text.getText().equals("")) {
                            if(!simbol.equals("")) {
                                calculConcatenat(simbol, text, calculMostra);
                                calculMostra.setText(df.format(num1) + "*");
                            }
                            else {
                                if(num1Ocupat)
                                    num1 *= Float.parseFloat(text.getText().toString());
                                else {
                                    num1 = Float.parseFloat(text.getText().toString());
                                    num1Ocupat = true;
                                }
                            }
                            text.setText("");
                            simbol = "X";
                            decimal = false;
                        }
                    }
                    else if(view.getId() == R.id.Calc_Resta) {
                        if(!text.getText().equals("")) {
                            if(!simbol.equals("")) {
                                calculConcatenat(simbol, text, calculMostra);
                                calculMostra.setText(df.format(num1) + "-");
                            }
                            else {
                                if(num1Ocupat)
                                    num1 -= Float.parseFloat(text.getText().toString());
                                else {
                                    num1 = Float.parseFloat(text.getText().toString());
                                    num1Ocupat = true;
                                }
                            }
                            text.setText("");
                            simbol = "-";
                            decimal = false;
                        }
                    }
                    else if(view.getId() == R.id.Calc_Simbol) {
                        if(!text.getText().equals("0") && !text.getText().equals("") && !negatiuNum) {
                            negatiuNum = true;
                            text.setText("-"+text.getText());
                        }
                        else if(negatiuNum) {
                            float aux = Float.parseFloat(text.getText().toString());
                            aux *= -1;
                            text.setText(df.format(aux));
                            if(aux == 0)
                                decimal = false;
                            negatiuNum = false;
                        }
                    }
                    else if(view.getId() == R.id.Calc_Suma) {
                        if(!text.getText().equals("")) {
                            if(!simbol.equals("")) {
                                calculConcatenat(simbol, text, calculMostra);
                                calculMostra.setText(df.format(num1) + "+");
                            }
                            else {
                                if(num1Ocupat){
                                    num1 += Float.parseFloat(text.getText().toString());
                                }
                                else {
                                    num1 = Float.parseFloat(text.getText().toString());
                                    num1Ocupat = true;
                                }
                            }
                            text.setText("");
                            simbol = "+";
                            decimal = false;
                        }
                    }
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

        Calc_C.setOnClickListener(oclSimbols);
        Calc_Div.setOnClickListener(oclSimbols);
        Calc_Decimal.setOnClickListener(oclSimbols);
        Calc_Mult.setOnClickListener(oclSimbols);
        Calc_Resta.setOnClickListener(oclSimbols);
        Calc_Suma.setOnClickListener(oclSimbols);
        Calc_Igual.setOnClickListener(oclSimbols);
        Calc_Simbol.setOnClickListener(oclSimbols);
        MemAdd.setOnClickListener(oclSimbols);
        MemSub.setOnClickListener(oclSimbols);
        MemResult.setOnClickListener(oclSimbols);
        MemClear.setOnClickListener(oclSimbols);
    }

    private void calculConcatenat(String simbol, TextView text, TextView calculMostra) {
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