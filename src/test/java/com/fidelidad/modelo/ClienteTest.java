package com.fidelidad.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ClienteTest {
    @Test
    void crearCliente_valoresInicialesCorrectos() {
        Cliente cliente = new Cliente("C001", "Ana", "ana@email.com");

        assertEquals("C001", cliente.getId());
        assertEquals("Ana", cliente.getNombre());
        assertEquals("ana@email.com", cliente.getCorreo());
        assertEquals(0, cliente.getPuntos());
        assertEquals(0, cliente.getStreakDias());
    }
    @Test
    void crearClienteConPuntos_valoresInicialesCorrectos() {
        Cliente cliente = new Cliente("C001", "Ana", "ana@email.com", 1000);

        assertEquals("C001", cliente.getId());
        assertEquals("Ana", cliente.getNombre());
        assertEquals("ana@email.com", cliente.getCorreo());
        assertEquals(1000, cliente.getPuntos());
        assertEquals(0, cliente.getStreakDias());
    }

    @Test
    void crearCliente_correoInvalido_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Cliente("C002", "Luis", "correo_invalido.com")
        );
    }

    @Test
    void crearClienteConPuntos_correoInvalido_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Cliente("C002", "Luis", "correo_invalido.com", 5000)
        );
    }

    @Test
    void setPuntos_actualizaPuntajeCorrectamente() {
        Cliente cliente = new Cliente("C004", "Elena", "elena@email.com");

        cliente.sumarPuntos(100);
        cliente.sumarPuntos(50);

        assertEquals(150, cliente.getPuntos());
    }

    @Test
    void setStreakDias_actualizaStreakDiasCorrectamente() {
        Cliente cliente = new Cliente("C004", "Elena", "elena@email.com");

        cliente.setStreakDias(2);

        assertEquals(2, cliente.getStreakDias());
    }
    @Test
    void crearCliente_nivelInicialEsBronce() {
        Cliente cliente = new Cliente("C003", "Mario", "mario@email.com");

        assertEquals(Nivel.BRONCE, cliente.getNivel());
    }
    @Test
    void setNivel_actualizaNivelCorrectamente() {
        Cliente cliente = new Cliente("C005", "Lucía", "lucia@email.com");

        cliente.setNivel(Nivel.ORO);

        assertEquals(Nivel.ORO, cliente.getNivel());
    }
}
