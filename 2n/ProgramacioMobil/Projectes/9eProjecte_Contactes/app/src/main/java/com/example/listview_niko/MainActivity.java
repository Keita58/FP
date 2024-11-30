package com.example.listview_niko;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private static final String CHANNEL_ID = "canal_notis";
	SQLiteDatabase db;
	BaseDeDades contactes;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		createNotificationChannel();

		if (prefs.getBoolean("tema_app", true)) {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
		}
		else {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
		}
		contactes = new BaseDeDades(this, "contactes.db", null, 1);;
		db = contactes.getWritableDatabase();

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
    
//        lstOpciones.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				Toast.makeText(getApplicationContext(), Contactes.get(arg2).getNom(), Toast.LENGTH_SHORT).show();
//			}
//		});

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.crearPerfil);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), CreaContacte.class);
				startActivityForResult(intent, 2);
			}
		});

		FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.config);
		fab2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), Config.class);
				startActivity(intent);
			}
		});
    } // onCreate

  
    // ************* PErsonalizamos el Array Adapter 
    
    class AdaptadorTitulares extends ArrayAdapter  {

		Activity context;
		ArrayList<Titular> Contactes = contactes.selectBD();

		public AdaptadorTitulares(Activity context) {
			super(context, R.layout.listitem_titular, contactes.selectBD());
			contactes = new BaseDeDades(getApplicationContext(), "contactes.db", null, 1);;
			this.context = (Activity) context;
		}

		// GetView s'executa per cada element de l'array de dades i el que fa
		// es "inflar" el layout del XML que hem creat
		
    	@Override    	
    	public View getView(int position, View convertView, ViewGroup parent) {

    		// Inflem el Layout
    		LayoutInflater inflater = context.getLayoutInflater();

    		// Sobre el layout crear (inflat) dupliquem el layour creat amb els objectes, view personals.
    		View item = inflater.inflate(R.layout.listitem_titular, null);

			Button Detalls = (Button) item.findViewById(R.id.detallContacte);
			Button Modifica = (Button) item.findViewById(R.id.modificarContacte);
			Button Elimina = (Button) item.findViewById(R.id.eliminarContacte);

    		// OJOOOO!!!!! hemos de hacer el findViewById del item que tenemos inflado.
    		TextView lblTitulo = (TextView) item.findViewById(R.id.lblNom);
    		lblTitulo.setText(Contactes.get(position).getNom().toString() + " " + Contactes.get(position).getCognom().toString());

    		TextView lblSubTitulo = (TextView) item.findViewById(R.id.lblData);
    		lblSubTitulo.setText(Integer.toString(Contactes.get(position).getTelefon()));

			Detalls.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Bundle bundle = new Bundle();
					bundle.putString("Nom", Contactes.get(position).getNom() + " " + Contactes.get(position).getCognom());
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
					Bundle bundle = new Bundle();
					bundle.putInt("Posicio", Contactes.get(position).getIdBD());
					bundle.putString("Nom", Contactes.get(position).getNom());
					bundle.putString("Cognom", Contactes.get(position).getCognom());
					bundle.putString("Adreça", Contactes.get(position).getAdreca());
					bundle.putInt("Telèfon", Contactes.get(position).getTelefon());
					bundle.putString("Mail", Contactes.get(position).getMail());
					bundle.putString("Data", Contactes.get(position).getDataNaixement());
					Intent intent = new Intent(getApplicationContext(), ModificaDades.class);
					intent.putExtras(bundle);
					startActivityForResult(intent, 1);
				}
			});

			Elimina.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					eliminaContacte(Contactes.get(position).getIdBD());
				}
			});

			if (position%2==0) {
				item.setBackgroundColor(getResources().getColor(R. color. colorSecundary));
			}else{
				item.setBackgroundColor(getResources().getColor(R. color. colorTerciary));
			}
    		return (item);    		
    	}
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		contactes = new BaseDeDades(getApplicationContext(), "contactes.db", null, 1);;

		if(requestCode == 1) {
			if(data != null) {
				Bundle bundle = data.getExtras();
				contactes.modificaContacteBD(bundle.getInt("Posicio"), new Titular(bundle.getString("Nom"), bundle.getString("Cognom"), bundle.getInt("Telèfon"), bundle.getString("Adreça"), bundle.getString("Mail"), bundle.getString("Data")));
			}
		}
		else if(requestCode == 2) {
			if(data != null) {
				Bundle bundle = data.getExtras();
				if(bundle.getString("Nom").isEmpty() && bundle.getString("Cognom").isEmpty() && bundle.getString("Telèfon").isEmpty()) {
					Toast.makeText(this, "Has de posar la informació bàsica per al nou contacte! \n(Nom, Cognom i Telèfon)", Toast.LENGTH_SHORT).show();
				}
				else {
					Titular t = new Titular(bundle.getString("Nom"), bundle.getString("Cognom"), Integer.parseInt(bundle.getString("Telèfon")), bundle.getString("Adreça"), bundle.getString("Mail"), bundle.getString("Data"));
					contactes.crearContacteBD(t);
					sendPendingIntentNotification(contactes.selectBDEspecific(t));
				}
			}
		}

		AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
		ListView lstOpciones = (ListView) findViewById(R.id.lstOpcions);
		lstOpciones.setAdapter(adaptador);
	}

	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

		if (prefs.getBoolean("tema_app", true)) {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
		}
		else {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
		}
	}

	private void eliminaContacte(int position) {
		contactes = new BaseDeDades(getApplicationContext(), "contactes.db", null, 1);
		contactes.eliminaContacteBD(position);
		AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
		ListView lstOpciones = (ListView) findViewById(R.id.lstOpcions);
		lstOpciones.setAdapter(adaptador);
	}

	private void createNotificationChannel() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			CharSequence name = "Canal";
			String description = "Canal per a les notificacions";
			int importance = NotificationManager.IMPORTANCE_DEFAULT;
			NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
			channel.setDescription(description);
			NotificationManager notificationManager = getSystemService(NotificationManager.class);
			notificationManager.createNotificationChannel(channel);
		}
	}

	private void sendBasicNotification() {
		// Crear una notificación
		/*  Fem servor NptificationCompat.Builder per fer la notificació i fem servir mètodes per donar aspecte i comportament
		 * a la Notificació  (Icone, Títol, Test,...
		 * Finalment, fem el Build per contruirla */
		Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
				.setSmallIcon(android.R.drawable.ic_dialog_info)
				.setContentTitle("Notificación de prueba")
				.setContentText("Este es el texto de la notificación.")
				.setPriority(NotificationCompat.PRIORITY_DEFAULT)
				.build();

		// Mostrar la notificación
		/* Per visulizar la notificació al usuari necessitem  un NotificationManager.
		 * El mètode notify() pren un ID únic per cad anotificació.
		 *
		 * Si li enviem un mateix ID, la notficació es "machaca".
		 * Si fem servir un ID diferent per cada notificació, s'acumulen.  */
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(1, notification);  // El primer parámetro es el ID de la notificación
	}
}
