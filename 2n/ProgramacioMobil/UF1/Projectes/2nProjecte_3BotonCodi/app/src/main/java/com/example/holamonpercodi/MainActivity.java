package com.example.holamonpercodi;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        /*setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        TextView txtPantalla = new TextView(getApplicationContext());
        txtPantalla.setText("Hola món!");
        txtPantalla.setTextColor(Color.GREEN);
        txtPantalla.setTextSize(30);
        txtPantalla.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        /**
         * Layout sencer de l'aplicació
         * - Orientació en vertical
         * - Gravetat a 1 (per centrar el text)
         * - Gravetat vertical a center (per centrar tot el que hi ha a dins al centre de la pantalla)
         */
        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setVerticalGravity(Gravity.CENTER);

        /**
         * Layout únicament dels botons
         * - Orientació en horitzontal
         * - Gravetat a 1 (per centrar els botons al centre)
         */
        LinearLayout llBotons = new LinearLayout(getApplicationContext());
        llBotons.setOrientation(LinearLayout.HORIZONTAL);
        llBotons.setGravity(1);

        Button btnCanvi = new Button(getApplicationContext());
        btnCanvi.setText("Amaga");
        View.OnClickListener oclCanvi = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtPantalla.getVisibility() == TextView.VISIBLE) {
                    txtPantalla.setVisibility(TextView.INVISIBLE);
                    btnCanvi.setText("Mostra");
                }
                else {
                    txtPantalla.setVisibility(TextView.VISIBLE);
                    btnCanvi.setText("Amaga");
                }
            }
        };
        btnCanvi.setOnClickListener(oclCanvi);
        llBotons.addView(btnCanvi);

        Button btnGran = new Button(getApplicationContext());
        btnGran.setText("Gran");
        View.OnClickListener oclGran = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPantalla.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtPantalla.getTextSize() + 3f);
            }
        };
        btnGran.setOnClickListener(oclGran);
        llBotons.addView(btnGran);

        Button btnPetit = new Button(getApplicationContext());
        btnPetit.setText("Petit");
        View.OnClickListener oclPetit = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPantalla.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtPantalla.getTextSize() - 3f);
            }
        };
        btnPetit.setOnClickListener(oclPetit);
        llBotons.addView(btnPetit);

        linearLayout.addView(txtPantalla);
        linearLayout.setBackgroundResource(R.drawable.logo_android);
        linearLayout.addView(llBotons);

        setContentView(linearLayout);
    }
}