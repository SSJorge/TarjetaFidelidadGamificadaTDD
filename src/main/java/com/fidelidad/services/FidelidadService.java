package com.fidelidad.services;

import com.fidelidad.repositories.ClienteRepository;
import com.fidelidad.repositories.CompraRepository;

public class FidelidadService {
    private final ClienteRepository clienteRepo;
    private final CompraRepository compraRepo;
    public FidelidadService(ClienteRepository clienteRepo, CompraRepository compraRepo) {
        this.clienteRepo = clienteRepo;
        this.compraRepo = compraRepo;
    }
    public int calcularPuntosASumar(double monto) {
        return (int) (monto / 100);
    }
    public double obtenerMultiplicador(String clienteId) {
        if (clienteId.equals("C001")) return 1.0;
        if (clienteId.equals("C002")) return 1.2;
        if (clienteId.equals("C003")) return 1.5;
        if (clienteId.equals("C004")) return 2.0;
        throw new IllegalArgumentException("Cliente no encontrado: " + clienteId);
    }
}
