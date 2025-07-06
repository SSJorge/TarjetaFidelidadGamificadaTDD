package com.fidelidad.repositories;

import java.util.ArrayList;
import java.util.List;

import com.fidelidad.modelo.Compra;

public class CompraRepository {
    private final List<Compra> compras = new ArrayList<>();

    public void agregar(Compra compra) {
        compras.add(compra);
    }
    public List<Compra> listar() {
        return new ArrayList<>(compras);
    }
}
