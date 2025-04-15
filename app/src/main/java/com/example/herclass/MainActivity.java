package com.example.herclass;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private EditText correo;
    private EditText contra;
    private FirebaseAuth nAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        correo = findViewById(R.id.email_inicio);
        contra = findViewById(R.id.contra_inicio);
        nAuth = FirebaseAuth.getInstance();
    }

    public void irRegistro(View view){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }

    public void iniciar(View view){
        nAuth.signInWithEmailAndPassword(correo.getText().toString().trim(), contra.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (correo.getText().toString().trim().equals("admin@herclass") && contra.getText().toString().equals("1234")){
                            Intent i = new Intent(getApplicationContext(), AdminHome.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(), "Bienvenido Administrador", Toast.LENGTH_SHORT).show();
                        }
                        if(task.isSuccessful()){
                            FirebaseUser user = nAuth.getCurrentUser();
                            Intent i = new Intent(getApplicationContext(), Home.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),  "Autentificacion correcta",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Autentificacion incorrecta",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}