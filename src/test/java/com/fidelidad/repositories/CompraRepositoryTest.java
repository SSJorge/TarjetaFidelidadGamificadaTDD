package com.fidelidad.repositories;

import com.fidelidad.modelo.Compra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CompraRepositoryTest {
    
    private CompraRepository repo;

    @BeforeEach
    void setUp() {
        repo = new CompraRepository();
    }

    @Test
    void agregar_compraSeAgregaCorrectamente() {
        Compra compra = new Compra("B001", "C001", 120.0, LocalDate.of(2023, 9, 15));
        repo.agregar(compra);

        List<Compra> comprasInternas = repo.listar(); // Verificamos el estado interno

        assertEquals(1, comprasInternas.size());
        assertEquals("B001", comprasInternas.get(0).getIdCompra());
        assertEquals("C001", comprasInternas.get(0).getIdCliente());
        assertEquals(120.0, comprasInternas.get(0).getMonto());
    }
}
