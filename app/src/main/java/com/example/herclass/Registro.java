package com.example.herclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registro extends AppCompatActivity {
    private FirebaseAuth nAuth;
    private EditText correo;
    private EditText contra1;
    private EditText contra2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        nAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.email_registro);
        contra1 = findViewById(R.id.contra1_registro);
        contra2 = findViewById(R.id.contra2_registro);

    }

    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = nAuth.getCurrentUser();
    }

    public void registrar (View view){
        if(contra1.getText().toString().equals(contra2.getText().toString())){
            nAuth.createUserWithEmailAndPassword(correo.getText().toString(), contra1.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Usuario creado.",Toast.LENGTH_SHORT).show();
                                FirebaseUser user = nAuth.getCurrentUser();
                                Intent i =new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                            }else {
                                Toast.makeText(getApplicationContext(), "Autentificacion fallida", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }
    }



    public void irInicio(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}