package com.fidelidad.services;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelidad.modelo.Cliente;
import com.fidelidad.modelo.Compra;
import com.fidelidad.modelo.Nivel;
import com.fidelidad.repositories.ClienteRepository;
import com.fidelidad.repositories.CompraRepository;

public class FidelidadServiceTest {

    private ClienteRepository clienteRepo;
    private FidelidadService service;
    private CompraRepository compraRepo;

    @BeforeEach
    void setUp() {
        clienteRepo = new ClienteRepository();
        compraRepo = new CompraRepository();
        service = new FidelidadService(clienteRepo, compraRepo);
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
    void cantidadComprasClienteEseDia_devuelveCantidadCorrecta() {
        String clienteId = "C001";
        LocalDate fecha = LocalDate.of(2024, 7, 7);

        compraRepo.agregar(new Compra("B001", clienteId, 100.0, fecha));
        compraRepo.agregar(new Compra("B002", clienteId, 150.0, fecha));
        compraRepo.agregar(new Compra("B003", clienteId, 200.0, LocalDate.of(2024, 7, 6))); // otra fecha
        compraRepo.agregar(new Compra("B004", "C002", 50.0, fecha)); // otro cliente

        int cantidad = service.cantidadComprasClienteEseDia(clienteId, fecha);

        assertEquals(2, cantidad);
    }
    @Test
    void eliminarCliente_eliminaClienteYComprasAsociadas() {
        Cliente cliente = new Cliente("C001", "Ana", "ana@email.com");
        clienteRepo.agregar(cliente);

        Compra compra1 = new Compra("B001", "C001", 1000, LocalDate.of(2023, 7, 1));
        Compra compra2 = new Compra("B002", "C001", 2000, LocalDate.of(2023, 7, 2));
        compraRepo.agregar(compra1);
        compraRepo.agregar(compra2);

        // Act
        service.eliminarCliente("C001");

        // Assert
        assertNull(clienteRepo.obtener("C001"), "El cliente debería haber sido eliminado");
        assertTrue(compraRepo.listar().isEmpty(), "Las compras del cliente deberían haber sido eliminadas");
    }





    //PROCESAR COMPRA
    @Test
    void procesarCompra_terceraCompraDelDia_recibeBonus() {
        Cliente cliente = new Cliente("C001", "Ana", "ana@email.com");
        clienteRepo.agregar(cliente);

        // Agregamos 2 compras previas para que esta sea la tercera
        LocalDate fecha = LocalDate.of(2023, 10, 1);
        compraRepo.agregar(new Compra("B001", "C001", 200.0, fecha)); // 1ra
        compraRepo.agregar(new Compra("B002", "C001", 100.0, fecha)); // 2da

        // Esta será la tercera
        Compra compra3 = new Compra("B003", "C001", 300.0, fecha); // 3 puntos base

        service.procesarCompra(compra3);

        // cálculo: base 3 + bonus 10 = 13 * 1.0 (nivel BRONCE) = 13 puntos
        Cliente actualizado = clienteRepo.obtener("C001");
        assertEquals(13, actualizado.getPuntos());

        List<Compra> compras = compraRepo.obtenerPorCliente("C001");
        assertEquals(3, compras.size());
        assertTrue(compras.stream().anyMatch(c -> c.getIdCompra().equals("B003")));
    }
    @Test
    void procesarCompra_cuartaCompraDelDia_noRecibeBonus() {
        Cliente cliente = new Cliente("C002", "Luis", "luis@email.com");
        clienteRepo.agregar(cliente);

        LocalDate fecha = LocalDate.of(2023, 10, 1);

        // Agregamos 3 compras previas en la misma fecha
        compraRepo.agregar(new Compra("B001", "C002", 100.0, fecha)); // 1 punto
        compraRepo.agregar(new Compra("B002", "C002", 200.0, fecha)); // 2 puntos
        compraRepo.agregar(new Compra("B003", "C002", 300.0, fecha)); // 3 puntos

        // Esta es la cuarta compra del día → no debe recibir bonus
        Compra compra4 = new Compra("B004", "C002", 400.0, fecha); // 4 puntos base

        service.procesarCompra(compra4);

        // Solo se suman los puntos base (4), sin bonus
        Cliente actualizado = clienteRepo.obtener("C002");
        assertEquals(4, actualizado.getPuntos());

        List<Compra> compras = compraRepo.obtenerPorCliente("C002");
        assertEquals(4, compras.size());
        assertTrue(compras.stream().anyMatch(c -> c.getIdCompra().equals("B004")));
    }
    @Test
    void procesarCompras_recalculaPuntosDesdeCero() {
        Cliente cliente = new Cliente("C003", "Valeria", "valeria@email.com");
        // cliente.setNivel(Nivel.BRONCE);
        cliente.sumarPuntosRegalados(5); // puntos iniciales
        clienteRepo.agregar(cliente);

        // Tres compras en orden de fechas diferentes
        compraRepo.agregar(new Compra("C001", "C003", 100.0, LocalDate.of(2023, 10, 1))); // 1 punto
        compraRepo.agregar(new Compra("C002", "C003", 100.0, LocalDate.of(2023, 10, 2))); // 1 punto
        compraRepo.agregar(new Compra("C003", "C003", 100.0, LocalDate.of(2023, 10, 2))); // 2da del día
        compraRepo.agregar(new Compra("C004", "C003", 100.0, LocalDate.of(2023, 10, 2))); // 3ra del día → +10 bonus

        // Ejecutamos el recálculo
        service.procesarCompras("C003");

        Cliente actualizado = clienteRepo.obtener("C003");
        // Cálculo:
        // inicio 5
        // +1 (día 1)
        // +1 (día 2 primera)
        // +1 (día 2 segunda)
        // +1 +10 bonus = 11 (día 2 tercera)
        // Total = 5 + 1 + 1 + 1 + 11 = 19
        assertEquals(19, actualizado.getPuntos());
    }

    @Test
    void procesarCompras_noDuplicaCompras() {
        Cliente cliente = new Cliente("C004", "Carlos", "carlos@email.com");
        // cliente.setNivel(Nivel.BRONCE);
        // cliente.setPuntosRegalados(0);
        clienteRepo.agregar(cliente);

        compraRepo.agregar(new Compra("Z001", "C004", 100.0, LocalDate.of(2023, 10, 1)));
        compraRepo.agregar(new Compra("Z002", "C004", 100.0, LocalDate.of(2023, 10, 1)));

        service.procesarCompras("C004");

        List<Compra> compras = compraRepo.obtenerPorCliente("C004");
        assertEquals(2, compras.size()); // no se duplicaron
    }



    //OBTENER MULTIPLICADOR DE CLIENTE
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