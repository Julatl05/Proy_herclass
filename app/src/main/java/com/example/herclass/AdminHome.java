package com.example.herclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);

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