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
    public void eliminarCliente(String clienteId) {
        compraRepo.eliminarPorCliente(clienteId);
        clienteRepo.eliminar(clienteId);
    }
    public void procesarCompra(Compra compra) {
        procesarCompra(compra, true); // true = agregar a repo
    }
    public void procesarCompra(Compra compra, boolean agregarARepo) {
        String idCliente = compra.getIdCliente();
        LocalDate fecha = compra.getFecha();

        int comprasPrevias = cantidadComprasClienteEseDia(idCliente, fecha);
        int puntosTotales = calcularPuntosASumar(compra.getMonto());

        // solo si esta es la tercera (es decir, hay 2 previas)
        if (comprasPrevias == 2) {
            puntosTotales += 10;
        }

        double multiplicador = obtenerMultiplicador(idCliente);
        int puntosFinales = (int) Math.floor(puntosTotales * multiplicador);

        Cliente cliente = clienteRepo.obtener(idCliente);
        cliente.sumarPuntos(puntosFinales);

        if (agregarARepo) {
            compraRepo.agregar(compra);
        }
    }
    
}
