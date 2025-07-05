package com.fidelidad.modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NivelTest {
    @Test
    void nivel_enumTieneLosValoresCorrectos() {
        Nivel[] niveles = Nivel.values();

        assertArrayEquals(
            new Nivel[]{Nivel.BRONCE, Nivel.PLATA, Nivel.ORO, Nivel.PLATINO},
            niveles
        );
    }

    @Test
        void nivel_getMultiplicador_devuelveValorEsperado() {
            assertEquals(1.0, Nivel.BRONCE.getMultiplicador());
            assertEquals(1.1, Nivel.PLATA.getMultiplicador());
            assertEquals(1.2, Nivel.ORO.getMultiplicador());
            assertEquals(1.3, Nivel.PLATINO.getMultiplicador());
        }
}
