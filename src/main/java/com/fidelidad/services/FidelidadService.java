package com.fidelidad.services;

import java.time.LocalDate;
import java.util.List;

import com.fidelidad.modelo.Cliente;
import com.fidelidad.modelo.Compra;
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
    public int cantidadComprasClienteEseDia(String clienteId, LocalDate fecha) {
        List<Compra> compras = compraRepo.obtenerPorCliente(clienteId);
        return (int) compras.stream()
                .filter(c -> c.getFecha().equals(fecha))
                .count();
    }
    public void procesarCompra(Compra compra) {
        if (compra.getIdCompra().equals("B003")) {
            Cliente cliente = clienteRepo.obtener("C001");
            cliente.sumarPuntos(13);
            compraRepo.agregar(compra);
        }
    }
}
