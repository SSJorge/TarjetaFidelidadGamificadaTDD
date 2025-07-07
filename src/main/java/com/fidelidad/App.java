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
        System.out.print("Seleccione una opción: ");
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
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

            default:
                System.out.println("Opción inválida.");
        }
    }
    
}
