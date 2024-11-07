package com.example.listview_niko;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {

	final ArrayList<Titular> Contactes = new ArrayList<>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		Contactes.add(new Titular("Marc","Sánchez López", 622260896, "Grugliasco, 61", "a", "26/08/2000"));

		/* *********************
        // Conectem al ListView com el Spinner pero sortira molt simple,.
        
        ArrayAdapter<String> adaptador = new  ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, datos);
        
        ListView lstOpcions = (ListView) findViewById(R.id.lstOpcions);
        
        lstOpcions.setAdapter(adaptador);
        
        // Anem a controlar la pulsacio sobre un element del ListView
        
        lstOpcions.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {				
				// TODO Auto-generated method stub
				// int arg2 -->  Indica la posisi� de l'element de l'array
			}
		});
        *********************/
        
        // Anem a fer-ho amb una vista personalitzada (Fem la clase AdaptadorTitulares propia)
        // que construir� la vista personalitzada a partir de la classe Titular i el Layout listitem_titular.

        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
        
        ListView lstOpciones = (ListView) findViewById(R.id.lstOpcions);
        
        lstOpciones.setAdapter(adaptador);
    
        lstOpciones.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), Contactes.get(arg2).getNom(), Toast.LENGTH_SHORT).show();
			}
		});

		FloatingActionButton fab = findViewById(R.id.crearPerfil);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_SHORT)
						.setAction("Action", null).show();
			}
		});
    } // onCreate

  
    // ************* PErsonalizamos el Array Adapter 
    
    class AdaptadorTitulares extends ArrayAdapter  {
 
		Activity context;

		public AdaptadorTitulares(Activity context) {
			super(context, R.layout.listitem_titular, Contactes);
			this.context = (Activity) context;			
		}

		Button Detalls = (Button) findViewById(R.id.detallContacte);
		Button Modifica = (Button) findViewById(R.id.modificarContacte);
		Button Elimina = (Button) findViewById(R.id.eliminarContacte);

		// GetView s'executa per cada element de l'array de dades i el que fa
		// es "inflar" el layout del XML que hem creat
		
    	@Override    	
    	public View getView(int position, View convertView, ViewGroup parent) {

    		// Inflem el Layout
    		LayoutInflater inflater = context.getLayoutInflater();

    		// Sobre el layout crear (inflat) dupliquem el layour creat amb els objectes, view personals.
    		View item = inflater.inflate(R.layout.listitem_titular, null);

    		// OJOOOO!!!!! hemos de hacer el findViewById del item que tenemos inflado.
    		TextView lblTitulo = (TextView) item.findViewById(R.id.lblNom);
    		lblTitulo.setText(Contactes.get(position).getNom().toString() + " " + Contactes.get(position).getCognom().toString());

    		TextView lblSubTitulo = (TextView) item.findViewById(R.id.lblData);
    		// Log.i("Niko","3->"+datosE[position].getSubtitulo().toString() );
			DateFormat date = new SimpleDateFormat("dd/LL/YYYY");
			String dateFormat = date.format(Contactes.get(position).getDataNaixement());
    		lblSubTitulo.setText(dateFormat);
    		// Log.i("Niko","4");

			Detalls.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Bundle bundle = new Bundle();
					bundle.putString("Nom", Contactes.get(position).getNom());
					bundle.putString("Adreça", Contactes.get(position).getAdreca());
					bundle.putInt("Telèfon", Contactes.get(position).getTelefon());
					bundle.putString("Mail", Contactes.get(position).getMail());
					bundle.putString("Data", Contactes.get(position).getDataNaixement());
					Intent intent = new Intent(getApplicationContext(), InfoContacte.class);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});

			Modifica.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

				}
			});

			Elimina.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

				}
			});

			if (position%2==0) {
				item.setBackgroundColor(Color.WHITE);
			}else{
				item.setBackgroundColor(Color.BLUE);
			}
    		return (item);    		
    	}
    	
    }
    
    
    
}
