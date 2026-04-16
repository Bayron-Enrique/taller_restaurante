import java.util.Scanner;

public class RestauranteElBuenSabor {

    private static final String NOMBRE_RESTAURANTE = "EL BUEN SABOR";
    private static final String DIRECCION = "Calle 15 #8-32, Valledupar";
    private static final String NIT = "900.123.456-7";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean ejecutando = true;

        imprimirEncabezado();

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
                    System.out.println("Opción no válida. Seleccione entre 0 y 5.");
            }
        }

        scanner.close();
    }

    private static void imprimirEncabezado() {
        System.out.println("========================================");
        System.out.println("    RESTAURANTE " + NOMBRE_RESTAURANTE);
        System.out.println("    " + DIRECCION);
        System.out.println("    NIT: " + NIT);
        System.out.println("========================================");
    }

    private static void mostrarMenu() {
        System.out.println("1. Ver carta");
        System.out.println("2. Agregar producto al pedido");
        System.out.println("3. Ver pedido actual");
        System.out.println("4. Generar factura");
        System.out.println("5. Nueva mesa");
        System.out.println("0. Salir");
        System.out.println("========================================");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerEntero(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Entrada inválida. Intente nuevamente.");
            scanner.nextLine(); // limpiar buffer
            return -1;
        }
    }

    private static void agregarProducto(Scanner scanner) {
        System.out.println("--- AGREGAR PRODUCTO ---");

        System.out.print("Número de producto (1-" + Datos.nom.length + "): ");
        int numeroProducto = leerEntero(scanner);

        System.out.print("Cantidad: ");
        int cantidad = leerEntero(scanner);

        if (numeroProducto <= 0 || numeroProducto > Datos.nom.length) {
            System.out.println("Producto no válido.");
            return;
        }

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a cero.");
            return;
        }

        if (Datos.est == 0) {
            activarMesa(scanner);
        }

        Datos.cant[numeroProducto - 1] += cantidad;

        System.out.println("Producto agregado al pedido:");
        System.out.println(" -> " + Datos.nom[numeroProducto - 1] + " x" + cantidad);
    }

    private static void activarMesa(Scanner scanner) {
        System.out.print("Ingrese número de mesa: ");
        int numeroMesa = leerEntero(scanner);

        if (numeroMesa <= 0) {
            numeroMesa = 1;
        }

        Datos.ms = numeroMesa;
        Datos.est = 1;
    }

    private static void mostrarPedido() {
        if (Utilidades.validar()) {
            Imprimir.mostrarPedido();
        } else {
            System.out.println("No hay productos en el pedido actual.");
        }
    }

    private static void generarFactura() {
        if (Utilidades.validar()) {
            Proceso.calcularTotalFactura(); // método nuevo limpio
            Imprimir.imprimirFacturaCompleta();
        } else {
            System.out.println("No hay productos para facturar.");
        }
    }

    private static void nuevaMesa() {
        Utilidades.reiniciar();
        System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
    }
}