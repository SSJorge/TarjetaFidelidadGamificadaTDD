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
}
