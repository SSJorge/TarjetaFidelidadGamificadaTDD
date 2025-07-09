package com.fidelidad;

import java.util.Scanner;

import com.fidelidad.modelo.Cliente;
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
                    gestionarClientes(scanner, clienteRepo);
                    break;
                case "2":
                    // gestionarCompras(scanner, servicio);
                    break;
                case "3":
                    // mostrarPuntos(scanner, clienteRepo);
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
    private static void gestionarClientes(Scanner scanner, ClienteRepository repo) {
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
                    repo.eliminar(idEliminar);
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
                        } catch (Exception e) {
                            System.out.println("Error al actualizar el correo: " + e.getMessage());
                        }
                        break;
                    case "4":
                        System.out.print("Puntos a sumar: ");
                        int puntosASumar = Integer.parseInt(scanner.nextLine());
                        // clienteActualizar.sumarPuntos(puntosASumar);
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }

            default:
                System.out.println("Opción inválida.");
        }
    }
    
}
