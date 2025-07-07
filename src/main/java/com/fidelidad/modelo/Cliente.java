package com.fidelidad.modelo;

public class Cliente {
    private final String id;
    private final String nombre;
    private final String correo;
    private int puntos;
    private int streakDias;
    private Nivel nivel;
    private int puntosRegalados; //para recalculo de puntos

    public Cliente(String id, String nombre, String correo) {
        validarDatos(id, nombre, correo);
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.puntos = 0;
        this.streakDias = 0;
        this.nivel = Nivel.BRONCE;
        this.puntosRegalados = 0;
    }
    public Cliente(String id, String nombre, String correo, int puntos) {
        validarDatos(id, nombre, correo);
        if (puntos < 0) {
            throw new IllegalArgumentException("Puntos no puede ser negativo");
        }
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.puntos = puntos;
        this.streakDias = 0;
        this.nivel = calcularNivel(puntos);
        this.puntosRegalados = puntos;
    }
    private Nivel calcularNivel(int puntos) {
        if (puntos >= 3000) return Nivel.PLATINO;
        if (puntos >= 1500) return Nivel.ORO;
        if (puntos >= 500)  return Nivel.PLATA;
        return Nivel.BRONCE;
    }
    private void validarDatos(String id, String nombre, String correo) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("ID no puede ser vacío");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre no puede ser vacío");
        if (correo == null || correo.isBlank() || !correo.contains("@")) {
            throw new IllegalArgumentException("Correo inválido");
        }
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

    public int getPuntosRegalados() {
        return puntosRegalados;
    }

    public void sumarPuntosRegalados(int puntosASumar) {
        this.puntosRegalados += puntosASumar;
    }

    public void sumarPuntos(int puntosASumar) {
        this.puntos += puntosASumar;
        this.nivel = calcularNivel(this.puntos);
    }

    public void setStreakDias(int dias) {
        if (dias < 0) {
            throw new IllegalArgumentException("Streak de días no puede ser negativo");
        }
        this.streakDias = dias;
    }
    public void sumarDia() {
        this.streakDias++;
    }

    public Nivel getNivel() {
        return nivel;
    }
}   
