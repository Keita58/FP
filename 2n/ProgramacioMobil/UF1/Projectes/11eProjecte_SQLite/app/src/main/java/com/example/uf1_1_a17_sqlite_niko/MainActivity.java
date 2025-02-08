package com.example.uf1_1_a17_sqlite_niko;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        //  Creem l�objecte de la clase SQLiteOpenHelper que hem creat:
        // Per la simple ceraci� de l'objecte si la BBDD no existeix, es crea, i si la versi� que li passem es superior
        // a l'anterior, es fa el m�tode onUpgrade
        // Si la versi� �s la mateixa, simplment s'obre.
        
        MiAgendaBBDD miAgenda = new MiAgendaBBDD(this, "Agenda", null, 1);
        // Creamos  una instancia de la BBDD obteniendo un objeto, referencia a la bbDd para escribir o
        // para leer
        // getWritableDatabase--> Per obtenir access d'escritutra a la BBDD
        // getReadableDatabase--> Per obtenir access de lectura a la BBDD
        
        // El m�todes m�s importants de la classe SQLiteOpenHelper:
        // close() --> Tanca conexi� a la BBDD
        // getWritableDatabase--> Per obtenir access d'escritutra a la BBDD        
        // getReadableDatabase--> Per obtenir access de lectura a la BBDD
        // onCreate(SQLiteDatabase db) --> Per crear la BBDD
        // onDowngrade(SQLiteDatabase db, int oldversion, int newversion) --> Per degradar la BBDD
        // onOpen(SQLiteDatabase db) --> Per obre conexi�p
        // onUpgrate(SQLiteDatabase db, int oldversion, int newversion)) --> Per actulitzar la versi� de la BBDD
       
        
        /* 
         PEr accedir a la BBDD es fa en dos fases:
         Perimer, obtenim un objecte del tipo SqLiteDatabase i posteriorment fem servir les funcions per gestionar
         les dades de la BBDD. 
         Suposem que la BBDD est� completament creada, totes les seves taules, relacions, etc...
         En cas de que la sent�ncia retorni dades, timdrem access a un objecte CURSOR.
         
          */
         
        
 
        SQLiteDatabase db =  miAgenda.getWritableDatabase();
        
        // Vamos a comprobar que todo ha ido bien y luego a insertar datos
        if (db != null)    
        {
         	// Insertamos 5 registros para probar
        	for(int i=1; i<=5; i++)
        	{
        		String nombre = "Contacto "+i;
        		String email = "mail"+i+"@gmail.es";
        		
        		db.execSQL("INSERT INTO Agenda (nom, email) VALUES ('"+nombre+"', '"+email+"')");
        		
        		
        	}
        	// NOTA: Per a comprovar les dades, SQLite deixa un fitxer a 
        	// la SD:  /data/data/<paquet.java.de.la.aplicacio.>/databases/<nom_BBDD>
        	// a SQLite les dades de cada aplicaci� nom�s les poden visualitzar la mateixa aplicaci�
        	// Per compartir dades hem de fer servir els provider services.
        	// PEr buscar-lo hem d'anar a la perspectiva DDMS de eclipse i a la pestanya "File Explorer"
        	// Evidement si tanquen el simulador s'esborra tot.
        	
        	
        	
        	// con execSQL podemos ejecutar cualquier sentencia SQL pero para los insert, delete y update
        	// existe otra forma:
        	// Con la clase ContentValues, donde ponemos la coleccion de valores por parejas Campo-valor
        	// y con el m�todo insert, update o delete.
        	
        	
        	/* Per l'access a la BBDD farem servir dos tipus de m�todes
        	 1) fer servir les les funcions RAWQUERY() i EXECSQL(). La primera retorna un cursor
        	  amb les dades (select), i l'EXECSQL es fa servir per sent�ncies que no retornes resultat
        	  (update, insert, delete) 
        	  
        	 2) Fer servir els m�todes INSERT, REPLACE, QUERY i DELETE passant-li els par�metres 
        	 oportuns.        	          	         	 
        	 
        	 */
        	
        	
        	
        	ContentValues nuevoRegistro =  new ContentValues();
        	nuevoRegistro.put("nom", "Niko");
        	nuevoRegistro.put("email", "niko@gmail.com");
        	db.insert("Agenda", null, nuevoRegistro); // El segundo parametro es para cuando se inserta unregistro vacio
        	
        	// Ahora lo modificamos
        	ContentValues nuevosValores = new ContentValues();
        	nuevosValores.put("email", "nicolas@gmail.com");
        	db.update("Agenda", nuevosValores, "nom='Niko'", null);
        	
        	// Otra opcion es usar condiciones del where con parametros.
        	// Estos parametros se indican mediante una tabla donde la posicion determina el orden
        	
        	String[] argumentos = new String[] {"niko","nicolas"};
        	db.update("Agenda", nuevosValores, "nom=? or nom= ?", argumentos);
        	
        	// Esta forma de usar parametros nos libera de tener que crear la sentencia SQL "a pelo"
        	// contatenando cadenas y de mas yq ue nos de un error dificil de localizar.
        	
        	
        	
        	// VAMOS A CONSULTAR REGISTROS !!!!!!!!!!!!!!
        	// Como todo en la vida hay mas de una forma de hacer las cosas:
        	// 1. metodo rawQuery() de la clase SQLiteDatabase . Le tenemos que hacer nosotros el SELECT
        	// 2. metodo query() de la clase SQLiteDatabase. tiene argumentos para columnas, where, group by, having y orderby... por separado y el construye el SELECT
        	// Ambos devuelven un objeto de la clase Cursos que luego podemos recorrer.
        	
        	
        	Cursor c1 = db.rawQuery("SELECT id, nom, email FROM Agenda WHERE nom='nicolas'", null);
        	// Tambien podiamos haber usado argumentos:
        	String[] arguswhere = new String[]{"nicolas"};
        	Cursor c2 = db.rawQuery("SELECT id, nom, email FROM Agenda WHERE nom=?", arguswhere);
       
        	// la segunda forma
        	
        	String[] argus2where = new String[]{"nicolas"};
        	String[] columnas = new String[] {"id", "nom", "email"}; // Lista de coampos a recuperar.
       	
        	Cursor c3= db.query("Agenda", columnas, "nom='nicolas'", null, null, null, null); // Sin Parametros
        	Cursor c4= db.query("Agenda", columnas, "nom=?", argus2where, null, null, null); // Con parametros
        	
        	// Ahora solo faltaria recorrer el Cursor
        	// Tenemos los metodos moveToFirst y moveToNext de la clase Cursor que devuelve TRUE si han ido bien
        	// otros moveToiPRevisus, moveToLast, moveToPosition
        	// Si devuelven FALSE es que  no hay registro en esa posicion
        	// Luego, una vez situados, accedemos al campo deseado mediante getXXX(indice). 
        	// donde XXX es el tipo de datos del campo y indice es la posicio de la columna (OJO!! Empieza por 0)
        	
        	
        	if (c1!= null && c1.moveToFirst())
        	{
        		do {
        			int miId = c1.getInt(0);
        			String miNombre = c1.getString(1);
        			String miEmail = c1.getString(2);
        		} while (c1.moveToNext());
        		
        	}
        	
        	// Con getCount tenemos el numero de registros
        	
        	// Mostrem un misatge per pantalla
        	Toast.makeText(this, "Finalitzat: tot Correcte", Toast.LENGTH_LONG).show();
        	
        	
        	db.close(); // Cerramos la BBDD
        	
        	
        	// PAra comprobar los datos, podemos descargarnos el fichero al PC y comprobarlos con un 
        	// administrador de SQLite (SQLite Administrator) o bien usar la utilidad adb.exe de la 
        	// platform-tools de android (buscarlo en directorio de instalacion). 
        	// adb,exe -s emulador-5554 shell
        	// dentro del shell # ejecutamos sqlite3 /data/data/<paquet.java.de.la.aplicacio.>/databases/<nom_BBDD>
        	// y nos saldra shell sqlite. Aqui podemos hacer sentencias sql.
        	// para salir   .exit  i exit
        	
        	
        } 

        
        
        
    }


    
}
