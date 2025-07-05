package com.fidelidad.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FechaUtilTest {

    @Test
    void parsear_fechaValida_retornaLocalDateEsperado() {
        LocalDate fecha = FechaUtil.parsear("01-10-23");
        assertEquals(LocalDate.of(2023, 10, 1), fecha);
    }

    @Test
    void parsear_fechaInvalida_lanzaExcepcion() {
        assertThrows(DateTimeParseException.class, () -> {
            FechaUtil.parsear("2023/10/01");
        });
    }

    @Test
    void parsear_fechaInvalida_lanzaExcepcionConMensajePersonalizado() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            FechaUtil.parsear("2023/10/01");
        });

        assertEquals("Fecha inválida, use formato dd-MM-aa", ex.getMessage());
    }
}