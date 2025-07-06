package com.fidelidad.repositories;
import java.util.HashMap;
import java.util.Map;

import com.fidelidad.modelo.Cliente;

public class ClienteRepository {
    private final Map<String, Cliente> clientes = new HashMap<>();

    public void agregar(Cliente cliente) {
        clientes.put(cliente.getId(), cliente);
    }

    public Cliente obtener(String id) {
        return clientes.get(id);
    }
}
