package com.fidelidad.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ClienteTest {
    //VALIDACIONES INICIALES
    @Test
    void crearCliente_valoresInicialesCorrectos() {
        Cliente cliente = new Cliente("C001", "Ana", "ana@email.com");
        assertEquals("C001", cliente.getId());
        assertEquals("Ana", cliente.getNombre());
        assertEquals("ana@email.com", cliente.getCorreo());
        assertEquals(0, cliente.getPuntos());
        assertEquals(0, cliente.getStreakDias());
        assertEquals(0, cliente.getPuntosRegalados());
    }
    @Test
    void crearClienteConPuntos_valoresInicialesCorrectos() {
        Cliente cliente = new Cliente("C001", "Ana", "ana@email.com", 1000);
        assertEquals("C001", cliente.getId());
        assertEquals("Ana", cliente.getNombre());
        assertEquals("ana@email.com", cliente.getCorreo());
        assertEquals(1000, cliente.getPuntos());
        assertEquals(1000, cliente.getPuntosRegalados());
        assertEquals(0, cliente.getStreakDias());
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
    @Test
    void crearCliente_conPuntosNegativos_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("C002", "Luis", "luis@email.com", -10);
        });
    }




    //ACTUALIZACIONES
    @Test
    void setPuntos_actualizaValorCorrectamente() {
        Cliente cliente = new Cliente("C001", "Ana", "ana@email.com");

        cliente.setPuntos(150);

        assertEquals(150, cliente.getPuntos(), "El valor de puntos debe ser actualizado correctamente");
    }
    @Test
    void sumarPuntos_actualizaPuntajeCorrectamente() {
        Cliente cliente = new Cliente("C004", "Elena", "elena@email.com");
        cliente.sumarPuntos(100);
        cliente.sumarPuntos(50);
        assertEquals(150, cliente.getPuntos());
    }
    @Test
    void sumarDia_actualizaStreakDiasCorrectamente() {
        Cliente cliente = new Cliente("C004", "Elena", "elena@email.com");
        cliente.sumarDia();
        cliente.sumarDia();
        assertEquals(2, cliente.getStreakDias());
    }
    @Test
    void setStreakDias_actualizaStreakDiasCorrectamente() {
        Cliente cliente = new Cliente("C004", "Elena", "elena@email.com");

        cliente.setStreakDias(2);

        assertEquals(2, cliente.getStreakDias());
    }
    @Test
    void setStreakDias_valorNegativo_lanzaExcepcion() {
        Cliente cliente = new Cliente("C019", "Renata", "renata@email.com", 100);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            cliente.setStreakDias(-3);
        });
        assertEquals("Streak de días no puede ser negativo", ex.getMessage());
    }
    @Test
    void sumarPuntosRegalados_variasVeces_sumaAcumulativa() {
        Cliente cliente = new Cliente("C002", "Luis", "luis@email.com");

        cliente.sumarPuntosRegalados(30);
        cliente.sumarPuntosRegalados(20);

        assertEquals(50, cliente.getPuntosRegalados());
    }

    @Test
    void sumarPuntosRegalados_conValorNegativo_restaPuntos() {
        Cliente cliente = new Cliente("C003", "Laura", "laura@email.com");

        cliente.sumarPuntosRegalados(100);
        cliente.sumarPuntosRegalados(-20);

        assertEquals(80, cliente.getPuntosRegalados());
    }
    @Test
    void sumarPuntosRegalados_conRestaExcesiva_lanzaExcepcion() {
        Cliente cliente = new Cliente("C003", "Laura", "laura@email.com");
        int puntos = 100;
        cliente.sumarPuntosRegalados(puntos);
        int puntosRestar = 300;
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            cliente.sumarPuntosRegalados(-1 * puntosRestar);
        });
        assertEquals("No se pueden restar más de " + puntos + " puntos", ex.getMessage());
    }
    



    //VALORES LIMITES DE NIVEL
    @Test
    void crearCliente_nivelInicialEsBronce() {
        Cliente cliente = new Cliente("C003", "Mario", "mario@email.com");
        assertEquals(Nivel.BRONCE, cliente.getNivel());
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
    void crearCliente_conPuntos3001_esPlatino() {
        Cliente cliente = new Cliente("C015", "Laura", "laura@email.com", 3001);
        assertEquals(Nivel.PLATINO, cliente.getNivel());
    }



    //CRUZAR UMBRALES
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
    void sumarPuntos_noCambiaNivelPermaneceBronce() {
        Cliente cliente = new Cliente("C053", "Bruno", "bruno@email.com", 100);
        cliente.sumarPuntos(50);
        assertEquals(Nivel.BRONCE, cliente.getNivel());
    }
    @Test
    void sumarPuntos_noCambiaNivelPermanecePlata() {
        Cliente cliente = new Cliente("C054", "Valentina", "valentina@email.com", 600);
        cliente.sumarPuntos(100); // llega a 700
        assertEquals(Nivel.PLATA, cliente.getNivel());
    }
    @Test
    void sumarPuntos_noCambiaNivelPermaneceOro() {
        Cliente cliente = new Cliente("C055", "Esteban", "esteban@email.com", 2000);
        cliente.sumarPuntos(200);
        assertEquals(Nivel.ORO, cliente.getNivel());
    }
    }
