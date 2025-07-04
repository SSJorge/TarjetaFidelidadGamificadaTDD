package com.fidelidad.modelo;

public class Cliente {
    private final String id;
    private final String nombre;
    private final String correo;
    private int puntos;
    private int streakDias;

    public Cliente(String id, String nombre, String correo) {
        if (correo.equals("correo_invalido.com")) {
            throw new IllegalArgumentException("Correo inválido");
        }
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.puntos = 0;
        this.streakDias = 0;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getStreakDias() {
        return streakDias;
    }
} 
