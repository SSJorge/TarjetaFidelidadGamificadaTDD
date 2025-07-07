package com.fidelidad.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FidelidadServiceTest {

    private FidelidadService service;

    @BeforeEach
    void setUp() {
        service = new FidelidadService(null, null); // No usa los repos en este método
    }

    @Test
    void calcularPuntosASumar_redondeaHaciaAbajoPorCada100() {
        assertEquals(0, service.calcularPuntosASumar(99.99));
        assertEquals(1, service.calcularPuntosASumar(100));
        assertEquals(4, service.calcularPuntosASumar(450));
        assertEquals(0, service.calcularPuntosASumar(0));
        assertEquals(7, service.calcularPuntosASumar(799.99));
    }
}