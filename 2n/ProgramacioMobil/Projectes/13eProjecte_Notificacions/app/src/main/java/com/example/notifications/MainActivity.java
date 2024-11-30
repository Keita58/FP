package com.example.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {




    /*


    Crear notificaciones en una aplicación Android utilizando Java en Android Studio es un proceso bastante sencillo. Aquí te proporciono una guía paso a paso para implementar notificaciones locales:

            1. Configuración Inicial
                Asegúrate de tener las siguientes configuraciones y dependencias:

                Dependencias en build.gradle (app)
                Asegúrate de incluir la dependencia de notificaciones si aún no la tienes:

                dependencies {
                    implementation 'androidx.core:core:1.8.0' // Necesaria para las notificaciones
                }
                Permisos
                Para notificaciones simples, no es necesario añadir permisos específicos en el archivo AndroidManifest.xml. Sin embargo, asegúrate de que tu AndroidManifest.xml está configurado correctamente.

                <uses-permission android:name="android.permission.VIBRATE" />
            2. Crear la Notificación

                Crear un canal de notificación (para Android Oreo y versiones posteriores)
                Para versiones de Android 8.0 (API 26) y superiores, debes crear un canal de notificación.
                Este paso es obligatorio para que las notificaciones funcionen correctamente.

                Crea el canal de notificación en tu MainActivity.java o cualquier otra actividad que maneje las notificaciones:


   */

    private static final String CHANNEL_ID = "my_channel_id";  // Canal de Notificacions.


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


        // Quan entrem, mirem si venim d'na notificacion

        NotificationManager nm =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent miIntent =getIntent();
        if (miIntent.getExtras() != null ) {  // Mirem si hi ha dades, en cas contrari, passem
            if (miIntent.getExtras().getInt("CODI",-1) >= 0  ){
                String sTemp= String.valueOf(miIntent.getExtras().getInt("CODI"));
                Toast.makeText(getApplicationContext(), "Notificacio tractada= "+sTemp, Toast.LENGTH_SHORT).show();
            }

        }





        createNotificationChannel();

        Button notifyButton = findViewById(R.id.btnNotificar);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBasicNotification();
            }
        });


        Button accionButton = findViewById(R.id.btnNotificaAccion);
        accionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPendingIntentNotification();
            }
        });








    }  // onCreate()

    /* Creació del Canal de Notificacions que es ccrea nomes sila versió d'Android es Oreo (API 26) o superior. Els canals
    * fan que el usuair puguin configurar el comportament de les notificacions per diferents categories de notificacions */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Channel";
            String description = "Channel for my notifications";
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




    private void sendPendingIntentNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("CODI",83); // Enviem una informació a l'activity. Per exemple el 83
        // Per exemple, podrien enviar a l'actitity de veure detall de misatges de Whatsapp elcodi  del contacte.
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        // Crear una notificación con accion :

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentTitle("Notificación con acción")
                .setContentText("Este es el texto de la notificación.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)  // La notificació s'elimina al gestionar-la per l'usuari
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..Much lo" +
                                "nger text that cannot fit one line..Much longer text that cann" +
                                "ot fit one line..Much longer text that cannot fit one line..."))
                .build();

        // Mostrar la notificación


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify((int) SystemClock.currentThreadTimeMillis(), notification);  // El primer parámetro es el ID de la notificación
        // En aquest cas, fem que les notificacios es vagin acumulant....

    }



}