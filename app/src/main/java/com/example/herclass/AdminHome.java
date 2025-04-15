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
        String email = txtbuscar.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "Ingresa un correo", Toast.LENGTH_SHORT).show();
            return;
        }

        dbRef.orderByChild("nombre").equalTo(email)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot usuarioSnap : snapshot.getChildren()) {
                                String uid = usuarioSnap.getKey();
                                String contra = usuarioSnap.child("contraseña").getValue(String.class);

                                resultado1.setText(uid);
                                resultado2.setText(contra);
                                break;
                            }
                        } else {
                            Toast.makeText(AdminHome.this, "Correo no encontrado", Toast.LENGTH_SHORT).show();
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

