package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

public class RestauranteElBuenSabor {

    private static Pedido pedidoActual = null;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ejecutando = true;

        while (ejecutando) {

            mostrarMenu();
            int opcion = leerEntero(scanner);

            switch (opcion) {

                case 1:
                    Imprimir.mostrarCarta();
                    break;

                case 2:
                    agregarProducto(scanner);
                    break;

                case 3:
                    mostrarPedido();
                    break;

                case 4:
                    generarFactura();
                    break;

                case 5:
                    nuevaMesa();
                    break;

                case 0:
                    ejecutando = false;
                    System.out.println("Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("    " + Restaurante.getNombre());
        System.out.println("========================================");
        System.out.println("1. Ver carta");
        System.out.println("2. Agregar producto");
        System.out.println("3. Ver pedido");
        System.out.println("4. Generar factura");
        System.out.println("5. Nueva mesa");
        System.out.println("0. Salir");
        System.out.print("Seleccione: ");
    }

    private static int leerEntero(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine();
            return -1;
        }
    }

    private static void agregarProducto(Scanner scanner) {

        System.out.print("Producto (1-" + Carta.getCantidadProductos() + "): ");
        int indice = leerEntero(scanner) - 1;

        System.out.print("Cantidad: ");
        int cantidad = leerEntero(scanner);

        if (indice < 0 || indice >= Carta.getCantidadProductos()) {
            System.out.println("Producto inválido.");
            return;
        }

        if (cantidad <= 0) {
            System.out.println("Cantidad inválida.");
            return;
        }

        if (pedidoActual == null) {
            pedidoActual = crearNuevoPedido(scanner);
        }

        Producto producto = Carta.getProducto(indice);
        pedidoActual.agregarItem(producto, cantidad);

        System.out.println("Agregado: " + producto.getNombre() + " x" + cantidad);
    }

    private static Pedido crearNuevoPedido(Scanner scanner) {
        System.out.print("Número de mesa: ");
        int numeroMesa = leerEntero(scanner);

        if (numeroMesa <= 0) {
            numeroMesa = 1;
        }

        return new Pedido(numeroMesa);
    }

    private static void mostrarPedido() {
        if (pedidoActual != null && pedidoActual.tieneProductos()) {
            Imprimir.mostrarPedido(pedidoActual);
        } else {
            System.out.println("No hay productos en el pedido.");
        }
    }

    private static void generarFactura() {
        if (pedidoActual == null || !pedidoActual.tieneProductos()) {
            System.out.println("No hay productos para facturar.");
            return;
        }

        Factura factura = new Factura(pedidoActual);
        Imprimir.imprimirFacturaCompleta(factura);
        pedidoActual = null;
    }

    private static void nuevaMesa() {
        pedidoActual = null;
        System.out.println("Mesa reiniciada.");
    }
}