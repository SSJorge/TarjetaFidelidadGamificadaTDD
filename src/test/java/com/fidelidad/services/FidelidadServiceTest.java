package com.fidelidad.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelidad.modelo.Cliente;
import com.fidelidad.repositories.ClienteRepository;

public class FidelidadServiceTest {

    private ClienteRepository clienteRepo;
    private FidelidadService service;

    @BeforeEach
    void setUp() {
        clienteRepo = new ClienteRepository();
        service = new FidelidadService(clienteRepo, null);
    }

    @Test
    void calcularPuntosASumar_redondeaHaciaAbajoPorCada100() {
        assertEquals(0, service.calcularPuntosASumar(99.99));
        assertEquals(1, service.calcularPuntosASumar(100));
        assertEquals(4, service.calcularPuntosASumar(450));
        assertEquals(0, service.calcularPuntosASumar(0));
        assertEquals(7, service.calcularPuntosASumar(799.99));
    }
    @Test
    void obtenerMultiplicador_clienteNivelBronce_devuelve1_0() {
        clienteRepo.agregar(new Cliente("C001", "Ana", "ana@email.com", 0)); // BRONCE
        assertEquals(1.0, service.obtenerMultiplicador("C001"));
    }

    @Test
    void obtenerMultiplicador_clienteNivelPlata_devuelve1_2() {
        clienteRepo.agregar(new Cliente("C002", "Luis", "luis@email.com", 600)); // PLATA
        assertEquals(1.2, service.obtenerMultiplicador("C002"));
    }

    @Test
    void obtenerMultiplicador_clienteNivelOro_devuelve1_5() {
        clienteRepo.agregar(new Cliente("C003", "Carla", "carla@email.com", 2000)); // ORO
        assertEquals(1.5, service.obtenerMultiplicador("C003"));
    }

    @Test
    void obtenerMultiplicador_clienteNivelPlatino_devuelve2_0() {
        clienteRepo.agregar(new Cliente("C004", "Sofía", "sofia@email.com", 3500)); // PLATINO
        assertEquals(2.0, service.obtenerMultiplicador("C004"));
    }

    @Test
    void obtenerMultiplicador_clienteNoExiste_lanzaExcepcion() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            service.obtenerMultiplicador("C999")
        );
        assertEquals("Cliente no encontrado: C999", ex.getMessage());
    }
}