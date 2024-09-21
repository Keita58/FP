package com.example.contar_letras;

import androidx.appcompat.app.AppCompatActivity;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Le ponemos una imagen de fondo a la pantalla
        // getWindow().setBackgroundDrawableResource(android.R.drawable.arrow_down_float);
        getWindow().setBackgroundDrawableResource(R.drawable.ic_launcher_background);

        // Le cambiamos titulo a la pantalla aunque no hace falta... ya esta en
        // el Layout principal.
        getWindow().setTitle(getResources().getText(R.string.title_activity_main));

        Button cmdCuenta = (Button) findViewById(R.id.btnCuenta);
        View.OnClickListener oclBoton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mostrar_resultado();
            }
        };
        cmdCuenta.setOnClickListener(oclBoton);

        EditText txtText = (EditText)findViewById(R.id.txtTexto);
        txtText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mostrar_resultado();
            }
        });

        txtText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Mostrar_resultado();
                return false;
            }
        });

        txtText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Mostrar_resultado();
            }
        });
    }



    void Mostrar_resultado (){
        EditText txtText = (EditText)findViewById(R.id.txtTexto);

        TextView lblLletres = (TextView) findViewById(R.id.lblLletres);
        lblLletres.setText( getResources().getString(R.string.text_lblLletre).toString() + String.valueOf(txtText.getText().length()));
    };
}