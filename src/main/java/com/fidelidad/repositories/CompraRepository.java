package com.fidelidad.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.fidelidad.modelo.Compra;

public class CompraRepository {
    private final List<Compra> compras = new ArrayList<>();

    public void agregar(Compra compra) {
        compras.add(compra);
    }
    public List<Compra> listar() {
        return new ArrayList<>(compras);
    }
    public List<Compra> obtenerPorCliente(String idCliente) {
        return compras.stream()
            .filter(c -> c.getIdCliente().equals(idCliente))
            .collect(Collectors.toList());
    }
    public String eliminar(String idCompra) {
        for (int i = 0; i < compras.size(); i++) {
            Compra c = compras.get(i);
            if (c.getIdCompra().equals(idCompra)) {
                compras.remove(i);
                return "Compra eliminada: " + idCompra;
            }
        }
        throw new NoSuchElementException("Compra no encontrada: " + idCompra);
    }
    public String eliminarPorCliente(String idCliente) {
        long cantidadAntes = compras.stream()
            .filter(c -> c.getIdCliente().equals(idCliente))
            .count();
        compras.removeIf(c -> c.getIdCliente().equals(idCliente));
        return "Compras eliminadas del cliente: " + idCliente + " (total: " + cantidadAntes + ")";
    }
    public List<Compra> listarPorFecha(LocalDate fecha) {
        return compras.stream()
            .filter(c -> c.getFecha().equals(fecha))
            .collect(Collectors.toList());
    }
}
