package com.example.amagar_mostrartext;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView txtPatata = (TextView) findViewById(R.id.txtPatata);
        Button btnCanvi = (Button) findViewById(R.id.btnCanvi);

        View.OnClickListener oclMostra = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtPatata.getVisibility() == TextView.VISIBLE) {
                    txtPatata.setVisibility(TextView.INVISIBLE);
                    btnCanvi.setText("Mostra");
                }
                else {
                    txtPatata.setVisibility(TextView.VISIBLE);
                    btnCanvi.setText("Amaga");
                }
            }
        };
        btnCanvi.setOnClickListener(oclMostra);

        Button btnGran = (Button) findViewById(R.id.btnTextGran);
        Button btnPetit = (Button) findViewById(R.id.btnTextPetit);

        View.OnClickListener oclGran = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPatata.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtPatata.getTextSize() + 3f); // COMPLEX_UNIT_PX es refereix a l'unitat que estem posant a la dreta, en aquest cas píxels
            }
        };
        btnGran.setOnClickListener(oclGran);

        View.OnClickListener oclPetit = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPatata.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtPatata.getTextSize() - 3f);
            }
        };
        btnPetit.setOnClickListener(oclPetit);
    }
}