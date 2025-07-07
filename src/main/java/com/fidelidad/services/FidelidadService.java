package com.fidelidad.services;

import com.fidelidad.modelo.Cliente;
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
        Cliente cliente = clienteRepo.obtener(clienteId);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado: " + clienteId);
        }
        return cliente.getNivel().getMultiplicador();
    }
}
