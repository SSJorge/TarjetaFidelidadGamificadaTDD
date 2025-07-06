package com.fidelidad.repositories;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public List<Cliente> listar() {
        return new ArrayList<>(clientes.values());
    }
    public void eliminar(String id) {

        clientes.remove(id);
    }
}
