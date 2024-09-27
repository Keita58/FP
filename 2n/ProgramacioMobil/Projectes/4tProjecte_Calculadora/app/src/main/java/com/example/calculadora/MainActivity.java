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

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case (R.id.Num_0):
                }
            }
        };
    }
}