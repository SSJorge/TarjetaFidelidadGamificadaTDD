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
}
