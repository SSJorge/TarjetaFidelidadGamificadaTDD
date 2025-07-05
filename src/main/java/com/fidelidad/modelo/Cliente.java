package com.fidelidad.modelo;

public class Cliente {
    private final String id;
    private final String nombre;
    private final String correo;
    private int puntos;
    private int streakDias;
    private Nivel nivel;

    public Cliente(String id, String nombre, String correo) {
        if (!correo.contains("@")) {
            throw new IllegalArgumentException("Correo inválido");
        }
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.puntos = 0;
        this.streakDias = 0;
        this.nivel = Nivel.BRONCE;
    }
    public Cliente(String id, String nombre, String correo, int puntos) {
        if (!correo.contains("@")) {
            throw new IllegalArgumentException("Correo inválido");
        }
        if (puntos < 0) {
            throw new IllegalArgumentException("Puntos no puede ser negativo");
        }
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.puntos = puntos;
        this.streakDias = 0;
        this.nivel = calcularNivel(puntos);
    }
    private Nivel calcularNivel(int puntos) {
        if (puntos >= 3000) return Nivel.PLATINO;
        if (puntos >= 1500) return Nivel.ORO;
        if (puntos >= 500)  return Nivel.PLATA;
        return Nivel.BRONCE;
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

    public void sumarPuntos(int puntosASumar) {
        if (puntosASumar < 0) {
            throw new IllegalArgumentException("No se pueden sumar puntos negativos");
        }
        this.puntos += puntosASumar;
        this.nivel = calcularNivel(this.puntos);
    }

    public void setStreakDias(int dias) {
        this.streakDias = dias;
    }

    public Nivel getNivel() {
        return nivel;
    }
}   
