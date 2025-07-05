package com.fidelidad.util;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class FechaUtilTest {

    @Test
    void parsear_fechaValida_retornaLocalDateEsperado() {
        LocalDate fecha = FechaUtil.parsear("01-10-23");
        assertEquals(LocalDate.of(2023, 10, 1), fecha);
    }
}