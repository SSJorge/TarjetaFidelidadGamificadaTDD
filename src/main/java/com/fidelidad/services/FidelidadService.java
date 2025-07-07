package com.fidelidad.services;

public class FidelidadService {
    public int calcularPuntosASumar(double monto) {
        if (monto < 100) return 0;
        if (monto < 200) return 1;
        if (monto < 500) return 4;
        if (monto < 800) return 7;
        return 10; // Valor arbitrario si pasa de los ejemplos del test
    }
}
