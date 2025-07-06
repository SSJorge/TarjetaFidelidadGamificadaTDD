package com.fidelidad.repositories;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelidad.modelo.Compra;


public class CompraRepositoryTest {
    
    private CompraRepository repo;

    @BeforeEach
    void setUp() {
        repo = new CompraRepository();
    }

    @Test
    void agregar_compraSeAgregaCorrectamente() {
        LocalDate fecha = LocalDate.of(2023, 9, 15);
        Compra compra = new Compra("B001", "C001", 120.0, fecha);
        repo.agregar(compra);

        List<Compra> comprasInternas = repo.listar(); // Verificamos el estado interno

        assertEquals(1, comprasInternas.size());
        assertEquals("B001", comprasInternas.get(0).getIdCompra());
        assertEquals("C001", comprasInternas.get(0).getIdCliente());
        assertEquals(120.0, comprasInternas.get(0).getMonto());
        assertEquals(fecha, comprasInternas.get(0).getFecha());
    }
}
