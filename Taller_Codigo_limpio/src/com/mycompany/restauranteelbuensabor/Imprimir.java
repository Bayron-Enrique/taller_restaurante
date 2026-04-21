package com.mycompany.restauranteelbuensabor;

public class Imprimir {

    private static final String SEPARADOR = "========================================";
    private static final String FORMATO_ITEM = "%-20s x%-6d $%,.0f%n";

    public static void mostrarCarta() {
        imprimirEncabezado();
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR);

        for (int indice = 0; indice < Carta.getCantidadProductos(); indice++) {
            Producto producto = Carta.getProducto(indice);
            System.out.printf("%d. %-22s $%,.0f%n",
                    (indice + 1),
                    producto.getNombre(),
                    producto.getPrecio());
        }

        System.out.println(SEPARADOR);
    }

    public static void mostrarPedido(Pedido pedido) {
        System.out.println("--- PEDIDO ACTUAL ---");

        for (ItemPedido item : pedido.getItems()) {
            System.out.printf(FORMATO_ITEM,
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal());
        }

        System.out.println("--------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", pedido.calcularSubtotal());
    }

    public static void imprimirFacturaCompleta(Factura factura) {
        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d%n", factura.getNumero());
        System.out.println("----------------------------------------");

        for (ItemPedido item : factura.getPedido().getItems()) {
            System.out.printf(FORMATO_ITEM,
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal());
        }

        imprimirTotales(factura);
        imprimirPie();
    }

    public static void imprimirFacturaResumen(Factura factura) {
        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", factura.getNumero());
        System.out.println("----------------------------------------");
        imprimirTotales(factura);
    }

    private static void imprimirEncabezado() {
        System.out.println(SEPARADOR);
        System.out.println("    " + Restaurante.getNombre());
        System.out.println("    " + Restaurante.getDireccion());
        System.out.println("    " + Restaurante.getNit());
        System.out.println(SEPARADOR);
    }

    private static void imprimirPie() {
        System.out.println(SEPARADOR);
        System.out.println("Gracias por su visita!");
        System.out.println(Restaurante.getNombre() + " - Valledupar");
        System.out.println(SEPARADOR);
    }

    private static void imprimirTotales(Factura factura) {
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", factura.calcularSubtotalConDescuento());
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", factura.calcularIVA());

        if (factura.calcularPropina() > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", factura.calcularPropina());
        }

        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", factura.calcularTotal());
        System.out.println(SEPARADOR);
    }
}