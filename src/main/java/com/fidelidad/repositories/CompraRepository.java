package com.fidelidad.repositories;

import java.util.ArrayList;
import java.util.List;
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
}
