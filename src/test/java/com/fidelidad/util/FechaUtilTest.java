package com.fidelidad.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class FechaUtilTest {

    @Test
    void parsear_fechaValida_retornaLocalDateEsperado() {
        LocalDate fecha = FechaUtil.parsear("01-10-2023");
        assertEquals(LocalDate.of(2023, 10, 1), fecha);
    }

    @Test
    void parsear_fechaInvalida_lanzaExcepcionConMensajePersonalizado() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            FechaUtil.parsear("2023/10/01");
        });

        assertEquals("Fecha inválida, use formato dd-MM-aaaa", ex.getMessage());
    }

    @Test
    void parsear_nullLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            FechaUtil.parsear(null);
        });
    }

    @Test
    void constructor_privado_lanzaAssertionError() throws Exception {
        Constructor<FechaUtil> constructor = FechaUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException ex = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(ex.getCause() instanceof AssertionError);
        assertEquals("No se debe instanciar FechaUtil", ex.getCause().getMessage());
    }
}