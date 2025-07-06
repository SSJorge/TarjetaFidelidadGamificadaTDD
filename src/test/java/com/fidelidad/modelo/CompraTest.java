package com.fidelidad.modelo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void crearCompra_conMontoNegativo_lanzaExcepcion() {
        LocalDate fecha = LocalDate.of(2023, 10, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            new Compra("B001", "C001", -50.0, fecha);
        });
    }
    //VALIDACIONES DE NULL Y VACIO
    @Test
    void crearCompra_idCompraNulo_lanzaExcepcion() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            new Compra(null, "C001", 100.0, LocalDate.now())
        );
        assertEquals("ID de compra no puede ser vacío", thrown.getMessage());
    }
    @Test
    void crearCompra_idCompraVacio_lanzaExcepcion() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            new Compra("   ", "C001", 100.0, LocalDate.now())
        );
        assertEquals("ID de compra no puede ser vacío", thrown.getMessage());
    }
    }
