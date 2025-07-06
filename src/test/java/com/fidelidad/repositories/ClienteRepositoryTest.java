package com.fidelidad.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelidad.modelo.Cliente;
import com.fidelidad.modelo.Nivel;

public class ClienteRepositoryTest {
    private ClienteRepository repo;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        repo = new ClienteRepository();
        cliente = new Cliente("C001", "Ana", "ana@email.com", 1000);
    }

    @Test
    void agregar_y_obtenerCliente_funcionaCorrectamente() {
        repo.agregar(cliente);
        Cliente obtenido = repo.obtener("C001");

        assertNotNull(obtenido);
        assertEquals("C001", obtenido.getId());
        assertEquals("Ana", obtenido.getNombre());
        assertEquals("ana@email.com", obtenido.getCorreo());
        assertEquals(Nivel.PLATA, obtenido.getNivel());
    }

    
}
