package com.example.m8_uf1_a16_amics_firebase;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a storage reference from our app
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hola, soy Niko!!");


        // Read from the database

        ValueEventListener myValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.i("Niko", "Value is: " + value);
                Toast.makeText(getApplicationContext(),""+value,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.i("Niko", "Failed to read value.", error.toException());
            }
        };
        // myRef = database.getReference("message");
        myRef = database.getReference("message");
        myRef.addValueEventListener(myValueEventListener); // Es crea el escoltador persistnt sobre "message"



        // Guardar clases tambien es posible con setValue
        myRef = database.getReference();  // Cambiamos la referencia a la raiz  para crear elemento "users·
        User user = new User("Niko", "milbicis@gmail.com");
        myRef.child("users").child("codigo1").setValue(user);
        myRef.child("users").child("codigo2").setValue (new User("Edu","Edu@gmail.com"));

        // Per llegir totes les dades d'un cop a partir d'un punt del arbre de dades : get()

        myRef.child("users").child("codigo1").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.i("Niko", "Error getting data", task.getException());
                }
                else {
                    Log.i("Niko", String.valueOf(task.getResult().getValue()));
                    JSONObject userJson = new JSONObject((Map) task.getResult().getValue());
                    User user1 = new Gson().fromJson(String.valueOf(userJson), User.class);
                    Log.i("Niko", user1.getUsername());
                }
            }
        });





        // Vamos a leer un array de usuarios
/* ***************************
        myRef.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.i("Niko", "Error getting data", task.getException());
                }
                else {
                    List<User> usuaris = new ArrayList<>();
                    try {
                        JSONArray usersJson = new JSONArray((Map) task.getResult().getValue());
                        for (int i=0; i< usersJson.length(); i++){
                            JSONObject userJson = usersJson.getJSONObject(i);
                            User newUser = new Gson().fromJson(String.valueOf(userJson), User.class);
                            usuaris.add(newUser);
                        }
                        Log.i("Niko", usuaris.toString());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });

******************* */



    }
}




class User {
    public String username;
    public String email;
    public User() { }
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    public String getUsername () {
        return this.username;
    }
}








