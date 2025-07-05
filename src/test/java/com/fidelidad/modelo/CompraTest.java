package com.fidelidad.modelo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CompraTest {
    @Test
    void crearCompra_valoresInicialesCorrectos() {
        LocalDate fecha = LocalDate.of(2023, 10, 1);
        Compra compra = new Compra("B001", "C001", 100.0, fecha); // B de buy y C de client

        assertEquals("B001", compra.getIdCompra());
        assertEquals("C001", compra.getIdCliente());
        assertEquals(100.0, compra.getMonto());
        assertEquals(fecha, compra.getFecha());
    }
    
}
