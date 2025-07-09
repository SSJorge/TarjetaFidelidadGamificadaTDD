package com.fidelidad;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.fidelidad.modelo.Cliente;
import com.fidelidad.modelo.Compra;
import com.fidelidad.repositories.ClienteRepository;
import com.fidelidad.repositories.CompraRepository;
import com.fidelidad.services.FidelidadService;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteRepository clienteRepo = new ClienteRepository();
        CompraRepository compraRepo = new CompraRepository();
        FidelidadService servicio = new FidelidadService(clienteRepo, compraRepo);
        System.out.println("Hola, bienvenido al sistema de fidelización de clientes!");
        boolean continuar = true;
        // Scanner scanner = new Scanner(System.in);

        while (continuar) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Compras");
            System.out.println("3. Mostrar Puntos / Nivel de un Cliente");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    gestionarClientes(scanner, clienteRepo, servicio);
                    break;
                case "2":
                    // gestionarCompras(scanner, servicio);
                    break;
                case "3":
                    mostrarPuntos(scanner, clienteRepo);
                    break;
                case "4":
                    continuar = false;
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
        scanner.close();   
    }
    private static void gestionarClientes(Scanner scanner, ClienteRepository repo, FidelidadService servicio) {
        System.out.println("\n--- Gestión de Clientes ---");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Eliminar cliente");
        System.out.println("4. Actualizar cliente");
        System.out.print("Seleccione una opción: ");
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                System.out.println("\n--- Agregar Cliente ---");
                System.out.println("1. Sin puntos iniciales");
                System.out.println("2. Con puntos iniciales");
                System.out.print("Seleccione una opción: ");
                String opcion2 = scanner.nextLine();
                if (opcion2.equals("1")) {
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Correo: ");
                    String correo = scanner.nextLine();

                    try {
                        repo.agregar(new Cliente(id, nombre, correo));
                        System.out.println("Cliente agregado correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    return;
                }
                if (opcion2.equals("2")) {
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Correo: ");
                    String correo = scanner.nextLine();
                    System.out.print("Ingrese puntos iniciales: ");
                    int puntosIniciales = Integer.parseInt(scanner.nextLine());
                    try {
                        repo.agregar(new Cliente(id, nombre, correo, puntosIniciales));
                        System.out.println("Cliente agregado correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    return;
                }
                break;

            case "2":
                System.out.println("Clientes registrados:");
                for (Cliente c : repo.listar()) {
                    System.out.println("- " + c.getId() + " | " + c.getNombre() + " | " + c.getCorreo());
                }
                break;

            case "3":
                System.out.print("ID del cliente a eliminar: ");
                String idEliminar = scanner.nextLine();
                try {
                    servicio.eliminarCliente(idEliminar);
                    System.out.println("Cliente eliminado correctamente.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "4":
                Cliente clienteActualizar = null;
                System.out.print("ID del cliente a actualizar: ");
                String idActualizar = scanner.nextLine();
                try {
                    clienteActualizar = repo.obtener(idActualizar);
                    if (clienteActualizar == null) {
                        System.out.println("Cliente no encontrado.");
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                System.out.println("\n--- ¿Qué desea actualizar? ---");
                System.out.println("1. ID");
                System.out.println("2. Nombre");
                System.out.println("3. Correo");
                System.out.println("4. Sumar puntos");
                System.out.print("Seleccione una opción: ");
                opcion2 = scanner.nextLine();
                switch (opcion2) {
                    case "1":
                        System.out.print("Nuevo ID: ");
                        String nuevoId = scanner.nextLine();
                        try {
                            Cliente nuevoCliente = new Cliente(nuevoId, clienteActualizar.getNombre(), clienteActualizar.getCorreo(), clienteActualizar.getPuntos());
                            repo.actualizar(idActualizar, nuevoCliente);
                            System.out.println("Cliente actualizado correctamente.");
                        } catch (Exception e) {
                            System.out.println("Error al actualizar cliente: " + e.getMessage());
                            return;
                        }
                        break;
                    case "2":
                        System.out.print("Nuevo Nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        try {
                            Cliente nuevoCliente = new Cliente(idActualizar, nuevoNombre, clienteActualizar.getCorreo(), clienteActualizar.getPuntos());
                            repo.actualizar(idActualizar, nuevoCliente);
                            System.out.println("Cliente actualizado correctamente.");
                        } catch (Exception e) {
                            System.out.println("Error al actualizar el nombre: " + e.getMessage());
                        }
                        break;
                    case "3":
                        System.out.print("Nuevo Correo: ");
                        String nuevoCorreo = scanner.nextLine();
                        try {
                            Cliente nuevoCliente = new Cliente(idActualizar, clienteActualizar.getNombre(), nuevoCorreo, clienteActualizar.getPuntos());
                            repo.actualizar(idActualizar, nuevoCliente);
                            System.out.println("Cliente actualizado correctamente.");
                        } catch (Exception e) {
                            System.out.println("Error al actualizar el correo: " + e.getMessage());
                        }
                        break;
                    case "4":
                        System.out.print("Puntos a sumar: ");
                        int puntosASumar = Integer.parseInt(scanner.nextLine());
                        try {
                            clienteActualizar.sumarPuntosRegalados(puntosASumar);
                            System.out.println("Cliente actualizado correctamente.");
                        } catch (Exception e) {
                            System.out.println("Error al sumar puntos: " + e.getMessage());
                        }
                        // clienteActualizar.sumarPuntos(puntosASumar);
                        break;
                    default:
                        System.out.println(opcion2);
                        System.out.println("Opción inválida.");
                }

            // default:
            //     System.out.println("Opción inválidaaa.");
        }
    }
     private static void mostrarPuntos(Scanner scanner, ClienteRepository repo) {
        System.out.println("\n--- Mostrar Puntos/Nivel de un Cliente ---");
        System.out.print("ID: ");
        String idCliente = scanner.nextLine();

        Cliente cliente = repo.obtener(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Puntos: " + cliente.getPuntos());
        System.out.println("Nivel: " + cliente.getNivel());
    }
    private static void gestionarCompras(Scanner scanner, FidelidadService servicio, CompraRepository compraRepo) {
        System.out.println("\n--- Gestión de Compras ---");
        System.out.println("1. Agregar compra");
        System.out.println("2. Listar compras");
        System.out.println("3. Listar compras por cliente");
        System.out.println("4. Eliminar compra");
        System.out.print("Seleccione una opción: ");
        String opcion = scanner.nextLine();
        switch (opcion) {
            case "1":
                System.out.println("\n--- Agregar Compra ---");
                System.out.print("ID de la compra: ");
                String idCompra = scanner.nextLine();
                System.out.print("ID del cliente: ");
                String idCliente = scanner.nextLine();
                System.out.print("Monto de la compra: ");
                double monto = Double.parseDouble(scanner.nextLine());
                System.out.print("Fecha (DD-MM-AAAA): ");
                String fechaStr = scanner.nextLine();
                
                try {
                    LocalDate fecha = LocalDate.parse(fechaStr);
                    Compra compra = new Compra(idCompra, idCliente, monto, fecha);
                    servicio.procesarCompra(compra);
                    System.out.println("Compra agregada y procesada correctamente.");
                } catch (Exception e) {
                    System.out.println("Error al agregar compra: " + e.getMessage());
                }
                break;
            case "2":
                System.out.println("Compras registradas:");
                for (Compra c : compraRepo.listar()) {
                    System.out.println("- " + c.getIdCompra() + " | " + c.getIdCliente() + " | " + c.getMonto() + " | " + c.getFecha());
                }
                break;
            case "3":
                System.out.print("ID del cliente: ");
                String idClienteCompras = scanner.nextLine();
                List<Compra> comprasCliente = compraRepo.obtenerPorCliente(idClienteCompras);
                if (comprasCliente.isEmpty()) {
                    System.out.println("No se encontraron compras para el cliente con ID: " + idClienteCompras);
                } else {
                    System.out.println("Compras del cliente:");
                    for (Compra c : comprasCliente) {
                        System.out.println("- " + c.getIdCompra() + " | " + c.getMonto() + " | " + c.getFecha());
                    }
                }
                break;
            case "4":
                System.out.print("ID de la compra a eliminar: ");
                String idCompraEliminar = scanner.nextLine();
                try {
                    String resultado = compraRepo.eliminar(idCompraEliminar);
                    System.out.println(resultado);
                } catch (Exception e) {
                    System.out.println("Error al eliminar compra: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }
    
}
