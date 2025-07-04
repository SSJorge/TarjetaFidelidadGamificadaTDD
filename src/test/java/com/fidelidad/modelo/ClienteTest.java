package com.fidelidad.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ClienteTest {
    @Test
    void crearCliente_valoresInicialesCorrectos() {
        Cliente cliente = new Cliente();

        assertEquals("C001", cliente.getId());
        assertEquals("Ana", cliente.getNombre());
        assertEquals("ana@email.com", cliente.getCorreo());
        assertEquals(0, cliente.getPuntos());
        assertEquals(0, cliente.getStreakDias());
    }
}
