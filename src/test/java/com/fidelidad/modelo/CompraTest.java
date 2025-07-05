package com.fidelidad.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CompraTest {
    @Test
    void crearCompra_valoresInicialesCorrectos() {
        Compra compra = new Compra("B001", "C001",100.0, "2023-10-01");

        assertEquals("B001", compra.getIdCompra());
        assertEquals("C001", compra.getIdCliente());
        assertEquals(100.0, compra.getMonto());
        assertEquals("2023-10-01", compra.getFecha());
    }
}
