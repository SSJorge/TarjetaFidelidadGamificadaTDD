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

    // @Test
    // void crearCliente_correoInvalido_lanzaExcepcion() {
    //     assertThrows(IllegalArgumentException.class, () -> 
    //         new Cliente("C002", "Luis", "correo_invalido.com")
    //     );
    // }

    @Test
    void crearClienteConPuntos_correoInvalido_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Cliente("C002", "Luis", "correo_invalido.com", 5000)
        );
    }

    @Test
    void crearCliente_conPuntosNegativos_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("C002", "Luis", "luis@email.com", -10);
        });
    }
    @Test
    void crearCliente_conPuntos499_esBronce() {
        Cliente cliente = new Cliente("C010", "Juan", "juan@email.com", 499);
        assertEquals(Nivel.BRONCE, cliente.getNivel());
    }

    @Test
    void crearCliente_conPuntos500_esPlata() {
        Cliente cliente = new Cliente("C011", "Sofía", "sofia@email.com", 500);
        assertEquals(Nivel.PLATA, cliente.getNivel());
    }

    @Test
    void crearCliente_conPuntos1499_esPlata() {
        Cliente cliente = new Cliente("C012", "Carlos", "carlos@email.com", 1499);
        assertEquals(Nivel.PLATA, cliente.getNivel());
    }

    @Test
    void crearCliente_conPuntos1500_esOro() {
        Cliente cliente = new Cliente("C013", "Lucía", "lucia@email.com", 1500);
        assertEquals(Nivel.ORO, cliente.getNivel());
    }

    @Test
    void crearCliente_conPuntos2999_esOro() {
        Cliente cliente = new Cliente("C014", "Pedro", "pedro@email.com", 2999);
        assertEquals(Nivel.ORO, cliente.getNivel());
    }

    @Test
    void crearCliente_conPuntos3000_esPlatino() {
        Cliente cliente = new Cliente("C015", "Laura", "laura@email.com", 3000);
        assertEquals(Nivel.PLATINO, cliente.getNivel());
    }

    @Test
    void sumarPuntos_cruzaUmbral500_actualizaNivelAPlata() {
        Cliente cliente = new Cliente("C016", "Mario", "mario@email.com", 499);
        cliente.sumarPuntos(1);
        assertEquals(Nivel.PLATA, cliente.getNivel());
    }

    @Test
    void sumarPuntos_cruzaUmbral1500_actualizaNivelAOro() {
        Cliente cliente = new Cliente("C017", "Julia", "julia@email.com", 1490);
        cliente.sumarPuntos(10);
        assertEquals(Nivel.ORO, cliente.getNivel());
    }

    @Test
    void sumarPuntos_cruzaUmbral3000_actualizaNivelAPlatino() {
        Cliente cliente = new Cliente("C018", "Tomas", "tomas@email.com", 2990);
        cliente.sumarPuntos(10);
        assertEquals(Nivel.PLATINO, cliente.getNivel());
    }

    @Test
    void setPuntos_actualizaPuntajeCorrectamente() {
        Cliente cliente = new Cliente("C004", "Elena", "elena@email.com");

        cliente.sumarPuntos(100);
        cliente.sumarPuntos(50);

        assertEquals(150, cliente.getPuntos());
    }
    @Test
    void sumarPuntos_valorNegativo_lanzaExcepcion() {
        Cliente cliente = new Cliente("C019", "Renata", "renata@email.com", 100);

        assertThrows(IllegalArgumentException.class, () -> {
            cliente.sumarPuntos(-50);
        });
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
    void crearCliente_idNulo_lanzaExcepcion() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            new Cliente(null, "Ana", "ana@email.com")
        );
        assertEquals("ID no puede ser vacío", thrown.getMessage());
    }
    @Test
    void crearCliente_idVacio_lanzaExcepcion() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            new Cliente("", "Ana", "ana@email.com")
        );
        assertEquals("ID no puede ser vacío", thrown.getMessage());
    }
    @Test
    void crearCliente_correoNulo_lanzaExcepcion() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            new Cliente("C002", "Luis", null)
        );
        assertEquals("Correo inválido", ex.getMessage());
    }

    @Test
    void crearCliente_correoVacio_lanzaExcepcion() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            new Cliente("C002", "Luis", "   ")
        );
        assertEquals("Correo inválido", ex.getMessage());
    }

    @Test
    void crearCliente_correoSinArroba_lanzaExcepcion() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            new Cliente("C002", "Luis", "correo_invalido.com")
        );
        assertEquals("Correo inválido", ex.getMessage());
    }
    @Test
    void crearCliente_nombreVacio_lanzaExcepcion() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            new Cliente("C020", "", "ana@email.com")
        );
        assertEquals("Nombre no puede ser vacío", thrown.getMessage());
    }
    @Test
    void crearCliente_nombreNulo_lanzaExcepcion() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            new Cliente("C020", null, "ana@email.com")
        );
        assertEquals("Nombre no puede ser vacío", thrown.getMessage());
    }
    @Test
    void crearClienteConPuntos_idNulo_lanzaExcepcion() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            new Cliente(null, "Ana", "ana@email.com", 100)
        );
        assertEquals("ID no puede ser vacío", thrown.getMessage());
    }

    @Test
    void crearClienteConPuntos_idVacio_lanzaExcepcion() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            new Cliente("", "Ana", "ana@email.com", 100)
        );
        assertEquals("ID no puede ser vacío", thrown.getMessage());
    }

    @Test
    void crearClienteConPuntos_correoNulo_lanzaExcepcion() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            new Cliente("C002", "Luis", null, 100)
        );
        assertEquals("Correo inválido", ex.getMessage());
    }

    @Test
    void crearClienteConPuntos_correoVacio_lanzaExcepcion() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            new Cliente("C002", "Luis", "   ", 100)
        );
        assertEquals("Correo inválido", ex.getMessage());
    }

    @Test
    void crearClienteConPuntos_correoSinArroba_lanzaExcepcion() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            new Cliente("C002", "Luis", "correo_invalido.com", 100)
        );
        assertEquals("Correo inválido", ex.getMessage());
    }

    @Test
    void crearClienteConPuntos_nombreVacio_lanzaExcepcion() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            new Cliente("C020", "", "ana@email.com", 100)
        );
        assertEquals("Nombre no puede ser vacío", thrown.getMessage());
    }

    @Test
    void crearClienteConPuntos_nombreNulo_lanzaExcepcion() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            new Cliente("C020", null, "ana@email.com", 100)
        );
        assertEquals("Nombre no puede ser vacío", thrown.getMessage());
    }
    }
