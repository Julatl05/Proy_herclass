package com.example.herclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class AdminHome extends AppCompatActivity {
    EditText txtbuscar, resultado1, resultado2;
    Button btnbuscar;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);

        txtbuscar = findViewById(R.id.txtbuscar);
        resultado1 = findViewById(R.id.editTextResultado1);
        resultado2 = findViewById(R.id.editTextResultado2);
        btnbuscar = findViewById(R.id.btnbuscar);

        dbRef = FirebaseDatabase.getInstance().getReference("usuarios");

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarUsuario();
            }
        });
    }

    private void buscarUsuario() {
        String id = txtbuscar.getText().toString();

        if (id.isEmpty()) {
            Toast.makeText(this, "Ingresa un ID", Toast.LENGTH_SHORT).show();
            return;
        }

        dbRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String nombre = snapshot.child("nombre").getValue(String.class);
                    String contra = snapshot.child("contraseña").getValue(String.class);

                    resultado1.setText(nombre);
                    resultado2.setText(contra);
                } else {
                    Toast.makeText(AdminHome.this, "ID no encontrado", Toast.LENGTH_SHORT).show();
                    resultado1.setText("");
                    resultado2.setText("");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(AdminHome.this, "Error de base de datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Regresos(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    public void ad(View view){
        Intent i = new Intent(this, administracion.class);
        startActivity(i);
    }

    }

