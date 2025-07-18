package com.fidelidad.modelo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
            assertEquals(1.2, Nivel.PLATA.getMultiplicador());
            assertEquals(1.5, Nivel.ORO.getMultiplicador());
            assertEquals(2.0, Nivel.PLATINO.getMultiplicador());
        }
}
