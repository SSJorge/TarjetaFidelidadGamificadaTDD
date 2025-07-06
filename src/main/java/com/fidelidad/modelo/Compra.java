package com.fidelidad.modelo;

import java.time.LocalDate;

public class Compra {
    private final String idCompra;
    private final String idCliente;
    private final double monto;
    private final LocalDate fecha;

    public Compra(String idCompra, String idCliente, double monto, LocalDate fecha) {
        if (monto < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo.");
        }
        if (idCompra == null || idCompra.isBlank()) {
            throw new IllegalArgumentException("ID de compra no puede ser vacío");
        }
        if (idCliente == null || idCliente.isBlank()) {
            throw new IllegalArgumentException("ID de cliente no puede ser vacío");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("Fecha no puede ser nula");
        }
        this.idCompra = idCompra;
        this.idCliente = idCliente;
        this.monto = monto;
        this.fecha = fecha;
    }
    public String getIdCompra() {
        return idCompra;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }
} 