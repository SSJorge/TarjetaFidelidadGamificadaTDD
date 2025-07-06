package com.fidelidad.repositories;

import java.time.LocalDate;
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
    public List<Compra> obtenerPorCliente(String idCliente) {
        if (idCliente.equals("C001")) {
            List<Compra> lista = new ArrayList<>();
            lista.add(new Compra("B001", "C001", 100.0, LocalDate.of(2023, 10, 1)));
            lista.add(new Compra("B003", "C001", 70.0, LocalDate.of(2023, 10, 3)));
            return lista;
        } else {
            return new ArrayList<>();
        }
    }
}
