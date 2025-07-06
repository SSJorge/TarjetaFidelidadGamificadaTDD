package com.fidelidad.repositories;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    void obtener_clienteInexistente_retornaNull() {
        Cliente obtenido = repo.obtener("C999");
        assertNull(obtenido);
    }

    @Test
    void listar_devuelveTodosLosClientes() {
        Cliente otro = new Cliente("C002", "Luis", "luis@email.com", 200);
        repo.agregar(cliente);
        repo.agregar(otro);

        List<Cliente> lista = repo.listar();
        assertEquals(2, lista.size());
        assertTrue(lista.contains(cliente));
        assertTrue(lista.contains(otro));
    }

    @Test
    void eliminar_clienteExistente_funcionaCorrectamente() {
        repo.agregar(cliente);
        repo.eliminar("C001");

        assertNull(repo.obtener("C001"));
    }

    @Test
    void eliminar_clienteInexistente_lanzaExcepcion() {
        String idInexistente = "C999";
        NoSuchElementException ex = assertThrows(NoSuchElementException.class, () ->
            repo.eliminar(idInexistente)
        );
        assertEquals("Cliente no encontrado: "+idInexistente, ex.getMessage());
    }

    @Test
    void actualizar_clienteExistenteConMismoId_actualizaDatos() {
        repo.agregar(cliente);

        Cliente actualizado = new Cliente("C001", "Ana María", "ana.maria@email.com", 1000);
        repo.actualizar("C001", actualizado);

        Cliente resultado = repo.obtener("C001");
        assertEquals("Ana María", resultado.getNombre());
        assertEquals("ana.maria@email.com", resultado.getCorreo());
        assertEquals(1000, resultado.getPuntos());
    }

    @Test
    void actualizar_clienteExistenteConIdNuevo_cambiaClave() {
        repo.agregar(cliente);

        Cliente nuevo = new Cliente("C002", "Ana María", "ana.maria@email.com", 1200);
        repo.actualizar("C001", nuevo);

        assertNull(repo.obtener("C001")); // antiguo eliminado
        Cliente resultado = repo.obtener("C002");
        assertNotNull(resultado);
        assertEquals("Ana María", resultado.getNombre());
        assertEquals("ana.maria@email.com", resultado.getCorreo());
        assertEquals(1200, resultado.getPuntos());
    }


    
}
