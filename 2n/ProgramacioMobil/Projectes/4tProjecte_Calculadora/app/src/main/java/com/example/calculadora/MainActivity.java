package com.example.calculadora;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    double num1 = 0;
    String simbol = "";
    boolean negatiuNum = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView text = (TextView) findViewById(R.id.Text);

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
                if(view.getId() == R.id.Num_0){
                    text.setText(text.getText()+"0");
                }
                else if(view.getId() == R.id.Num_1) {
                    text.setText(text.getText()+"1");
                }
                else if(view.getId() == R.id.Num_2) {
                    text.setText(text.getText()+"2");
                }
                else if(view.getId() == R.id.Num_3) {
                    text.setText(text.getText()+"3");
                }
                else if(view.getId() == R.id.Num_4) {
                    text.setText(text.getText()+"4");
                }
                else if(view.getId() == R.id.Num_5) {
                    text.setText(text.getText()+"5");
                }
                else if(view.getId() == R.id.Num_6) {
                    text.setText(text.getText()+"6");
                }
                else if(view.getId() == R.id.Num_7) {
                    text.setText(text.getText()+"7");
                }
                else if(view.getId() == R.id.Num_8) {
                    text.setText(text.getText()+"8");
                }
                else if(view.getId() == R.id.Num_9) {
                    text.setText(text.getText()+"9");
                }
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
                    text.setText("");
                    num1 = 0;
                }
                if(view.getId() == R.id.Calc_Decimal) {
                    text.setText(text.getText()+".");
                }
                if(view.getId() == R.id.Calc_Igual) {
                    switch (simbol) {
                        case "+":
                            double aux = Double.parseDouble(text.getText().toString());
                            String aux2 = Double.toString(num1+aux);
                            text.setText(aux2);
                            break;
                        case "-":
                            break;
                        case "/":
                            break;
                        case "X":
                            break;
                    }
                }
                if(view.getId() == R.id.Calc_Div) {
                    if(text.getText() != "") {
                        num1 = Double.parseDouble(text.getText().toString());
                        text.setText("");
                        simbol = "/";
                    }
                }
                if(view.getId() == R.id.Calc_Mult) {
                    if(text.getText() != "") {
                        num1 = Double.parseDouble(text.getText().toString());
                        text.setText("");
                        simbol = "X";
                    }
                }
                if(view.getId() == R.id.Calc_Resta) {
                    if(text.getText() != "") {
                        num1 = Double.parseDouble(text.getText().toString());
                        text.setText("");
                        simbol = "-";
                    }
                }
                if(view.getId() == R.id.Calc_Simbol) {
                    negatiuNum = true;
                    text.setText("-"+text.getText());
                }
                if(view.getId() == R.id.Calc_Suma) {
                    if(text.getText() != "") {
                        num1 = Double.parseDouble(text.getText().toString());
                        text.setText("");
                        simbol = "+";
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
    }
}