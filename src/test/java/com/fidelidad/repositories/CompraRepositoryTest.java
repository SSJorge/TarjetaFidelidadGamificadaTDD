package com.fidelidad.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelidad.modelo.Compra;


public class CompraRepositoryTest {
    
    private CompraRepository repo;

    @BeforeEach
    void setUp() {
        repo = new CompraRepository();
    }
    //OBTENER
    @Test
    void getCompra_existente_devuelveCompra() {
        Compra compra = new Compra("CP001", "CL001", 100.0, LocalDate.of(2023, 10, 1));
        repo.agregar(compra);

        Compra encontrada = repo.getCompra("CP001");

        assertNotNull(encontrada);
        assertEquals("CP001", encontrada.getIdCompra());
        assertEquals("CL001", encontrada.getIdCliente());
        assertEquals(100.0, encontrada.getMonto());
    }

    @Test
    void getCompra_noExistente_lanzaExcepcion() {
        Exception ex = assertThrows(NoSuchElementException.class, () -> {
            repo.getCompra("INEXISTENTE");
        });

        assertEquals("Compra no encontrada: INEXISTENTE", ex.getMessage());
    }

    //AGREGAR
    @Test
    void agregar_compraSeAgregaCorrectamente() {
        LocalDate fecha = LocalDate.of(2023, 9, 15);
        Compra compra = new Compra("B001", "C001", 120.0, fecha);
        repo.agregar(compra);

        List<Compra> comprasInternas = repo.listar(); // Verificamos el estado interno

        assertEquals(1, comprasInternas.size());
        assertEquals("B001", comprasInternas.get(0).getIdCompra());
        assertEquals("C001", comprasInternas.get(0).getIdCliente());
        assertEquals(120.0, comprasInternas.get(0).getMonto());
        assertEquals(fecha, comprasInternas.get(0).getFecha());
    }
    @Test
    void agregar_compraConIdDuplicado_lanzaExcepcion() {
        Compra original = new Compra("B001", "C001", 100.0, LocalDate.of(2023, 10, 1));
        Compra duplicada = new Compra("B001", "C002", 150.0, LocalDate.of(2023, 10, 2));

        repo.agregar(original);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            repo.agregar(duplicada)
        );

        assertEquals("Ya existe una compra con ID: B001", ex.getMessage());
    }



    //OBTENER POR CLIENTE
    @Test
    void obtenerPorCliente_devuelveComprasDeCliente() {
        Compra c1 = new Compra("B001", "C001", 100.0, LocalDate.of(2023, 10, 1));
        Compra c2 = new Compra("B002", "C002", 50.0, LocalDate.of(2023, 10, 2));
        Compra c3 = new Compra("B003", "C001", 70.0, LocalDate.of(2023, 10, 3));
        repo.agregar(c1);
        repo.agregar(c2);
        repo.agregar(c3);

        List<Compra> comprasC001 = repo.obtenerPorCliente("C001");

        assertEquals(2, comprasC001.size());
        assertTrue(comprasC001.stream().allMatch(c -> c.getIdCliente().equals("C001")));
    }
    @Test
    void obtenerPorCliente_clienteSinCompras_devuelveListaVacia() {
        Compra c1 = new Compra("B001", "C001", 100.0, LocalDate.of(2023, 10, 1));
        repo.agregar(c1);

        List<Compra> comprasC999 = repo.obtenerPorCliente("C999");

        assertNotNull(comprasC999);
        assertTrue(comprasC999.isEmpty());
    }



    //ELIMINAR
    @Test
    void eliminar_compraExistente_laElimina() {
        Compra compra = new Compra("B001", "C001", 100.0, LocalDate.of(2023, 10, 1));
        repo.agregar(compra);

        String mensaje = repo.eliminar("B001");
        assertEquals(mensaje, "Compra eliminada: " + compra.getIdCompra());

        List<Compra> compras = repo.listar();
        assertTrue(compras.isEmpty());
    }
    @Test
    void eliminar_compraInexistente_lanzaExcepcion() {
        String idInexistente = "B999";

        NoSuchElementException ex = assertThrows(NoSuchElementException.class, () -> {
            repo.eliminar(idInexistente);
        });

        assertEquals("Compra no encontrada: " + idInexistente, ex.getMessage());
    }
    @Test
    void eliminarPorCliente_eliminaTodasLasComprasDelCliente() {
        repo.agregar(new Compra("B001", "C001", 100.0, LocalDate.of(2023, 10, 1)));
        repo.agregar(new Compra("B002", "C001", 150.0, LocalDate.of(2023, 10, 2)));
        repo.agregar(new Compra("B003", "C002", 80.0, LocalDate.of(2023, 10, 3)));

        String resultado = repo.eliminarPorCliente("C001");

        assertEquals("Compras eliminadas del cliente: C001 (total: 2)", resultado);

        List<Compra> restantes = repo.listar();
        assertEquals(1, restantes.size());
        assertEquals("C002", restantes.get(0).getIdCliente());
    }
    @Test
    void eliminar_compraNoCoincidente_listaNoVacia_lanzaExcepcion() {
        repo.agregar(new Compra("B001", "C001", 100.0, LocalDate.of(2023, 10, 1)));
        repo.agregar(new Compra("B002", "C002", 150.0, LocalDate.of(2023, 10, 2)));

        NoSuchElementException ex = assertThrows(NoSuchElementException.class, () -> {
            repo.eliminar("B999"); // ID que no existe
        });

        assertEquals("Compra no encontrada: B999", ex.getMessage());
    }



    //LISTAR
    @Test
    void listar_devuelveTodasLasComprasAgregadas() {
        Compra c1 = new Compra("B001", "C001", 80.0, LocalDate.of(2023, 8, 10));
        Compra c2 = new Compra("B002", "C002", 150.0, LocalDate.of(2023, 8, 12));

        repo.agregar(c1);
        repo.agregar(c2);

        List<Compra> lista = repo.listar();

        assertEquals(2, lista.size());
        assertTrue(lista.stream().anyMatch(c -> c.getIdCompra().equals("B001")));
        assertTrue(lista.stream().anyMatch(c -> c.getIdCompra().equals("B002")));
    }
    @Test
    void listar_sinCompras_devuelveListaVacia() {
        List<Compra> compras = repo.listar();
        assertNotNull(compras);
        assertTrue(compras.isEmpty());
    }
    @Test
    void listarPorFecha_devuelveSoloComprasEnEsaFecha() {
        Compra c1 = new Compra("B001", "C001", 100.0, LocalDate.of(2023, 10, 1));
        Compra c2 = new Compra("B002", "C002", 150.0, LocalDate.of(2023, 10, 2));
        Compra c3 = new Compra("B003", "C001", 80.0, LocalDate.of(2023, 10, 1));

        repo.agregar(c1);
        repo.agregar(c2);
        repo.agregar(c3);

        List<Compra> resultado = repo.listarPorFecha(LocalDate.of(2023, 10, 1));

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(c -> c.getFecha().equals(LocalDate.of(2023, 10, 1))));
    }
    @Test
    void listarPorMes_devuelveSoloComprasDelMesYAnio() {
        repo.agregar(new Compra("B001", "C001", 100.0, LocalDate.of(2023, 10, 1)));
        repo.agregar(new Compra("B002", "C002", 150.0, LocalDate.of(2023, 10, 15)));
        repo.agregar(new Compra("B003", "C003", 200.0, LocalDate.of(2024, 11, 1)));

        List<Compra> resultado = repo.listarPorMes(2023, 10);

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(c ->
            c.getFecha().getYear() == 2023 && c.getFecha().getMonthValue() == 10
        ));
    }
    @Test
    void listarPorAnio_devuelveSoloComprasDelAnio() {
        repo.agregar(new Compra("B001", "C001", 100.0, LocalDate.of(2023, 1, 10)));
        repo.agregar(new Compra("B002", "C002", 150.0, LocalDate.of(2023, 5, 20)));
        repo.agregar(new Compra("B003", "C003", 200.0, LocalDate.of(2024, 3, 15)));

        List<Compra> resultado = repo.listarPorAnio(2023);

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(c -> c.getFecha().getYear() == 2023));
    }
    @Test
    void listarPorFecha_fechaSinCompras_devuelveListaVacia() {
        repo.agregar(new Compra("B001", "C001", 100.0, LocalDate.of(2023, 10, 1)));

        List<Compra> resultado = repo.listarPorFecha(LocalDate.of(2023, 12, 25));

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
    @Test
    void listarPorMes_mesSinCompras_devuelveListaVacia() {
        repo.agregar(new Compra("B001", "C001", 100.0, LocalDate.of(2023, 9, 1)));

        List<Compra> resultado = repo.listarPorMes(2023, 10);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
    @Test
    void listarPorAnio_anioSinCompras_devuelveListaVacia() {
        repo.agregar(new Compra("B001", "C001", 100.0, LocalDate.of(2022, 10, 1)));

        List<Compra> resultado = repo.listarPorAnio(2023);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
}
