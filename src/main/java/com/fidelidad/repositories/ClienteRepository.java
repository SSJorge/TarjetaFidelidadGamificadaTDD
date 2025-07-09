package com.fidelidad.repositories;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.fidelidad.modelo.Cliente;

public class ClienteRepository {
    private final Map<String, Cliente> clientes = new HashMap<>();

    public void agregar(Cliente cliente) {
        if (clientes.containsKey(cliente.getId())) {
            throw new IllegalArgumentException("Ya existe un cliente con ID: " + cliente.getId());
        }
        clientes.put(cliente.getId(), cliente);
    }

    public Cliente obtener(String id) {
        return clientes.get(id);
    }

    public List<Cliente> listar() {
        return new ArrayList<>(clientes.values());
    }
    public void eliminar(String id) {
        if (!clientes.containsKey(id)) {
            throw new NoSuchElementException("Cliente no encontrado: " + id);
        }
        clientes.remove(id);
    }
    public void actualizar(String idOriginal, Cliente clienteNuevo) {
        if (!clientes.containsKey(idOriginal)) {
            throw new NoSuchElementException("Cliente no encontrado: " + idOriginal);
        }
        if (!idOriginal.equals(clienteNuevo.getId()) && clientes.containsKey(clienteNuevo.getId())) {
            throw new IllegalArgumentException("Ya existe un cliente con ID: " + clienteNuevo.getId());
        }
        // Si cambió el ID, hay que eliminar el viejo y poner el nuevo
        if (!idOriginal.equals(clienteNuevo.getId())) {
            clientes.remove(idOriginal);
        }
        clientes.put(clienteNuevo.getId(), clienteNuevo);
    }
}
