package com.example.herclass;

public class Usuario {
    public String nombre;
    public String contraseña;

    // Constructor vacío requerido por Firebase
    public Usuario() {}

    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }
}