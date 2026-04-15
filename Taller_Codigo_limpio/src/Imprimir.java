package com.mycompany.restauranteelbuensabor;

public class Imprimir {

    private static final String SEPARADOR = "========================================";
    private static final String FORMATO_ITEM = "%-20s x%-6d $%,.0f%n";

    private static final double TASA_IVA = 0.19;
    private static final double TASA_PROPINA = 0.10;
    private static final double TASA_DESCUENTO = 0.05;
    private static final double UMBRAL_PROPINA = 50000;
    private static final int MIN_ITEMS_DESCUENTO = 3;

    public static void mostrarCarta() {
        imprimirEncabezado();
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR);

        for (int i = 0; i < Datos.getCantidadProductos(); i++) {
            System.out.printf("%d. %-22s $%,.0f%n",
                    (i + 1),
                    Datos.getNombreProducto(i),
                    Datos.getPrecioProducto(i));
        }

        System.out.println(SEPARADOR);
    }

    public static void mostrarPedido() {
        double subtotal = 0;

        System.out.println("--- PEDIDO ACTUAL ---");

        for (int i = 0; i < Datos.getCantidadProductos(); i++) {
            int cantidad = Datos.getCantidadProducto(i);

            if (cantidad > 0) {
                double precio = Datos.getPrecioProducto(i);
                double parcial = precio * cantidad;

                System.out.printf(FORMATO_ITEM,
                        Datos.getNombreProducto(i),
                        cantidad,
                        parcial);

                subtotal += parcial;
            }
        }

        System.out.println("--------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }

    public static void imprimirFacturaCompleta() {
        FacturaData factura = calcularFactura();

        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d%n", Datos.getNumeroFactura());
        System.out.println("----------------------------------------");

        for (int i = 0; i < Datos.getCantidadProductos(); i++) {
            int cantidad = Datos.getCantidadProducto(i);

            if (cantidad > 0) {
                double precio = Datos.getPrecioProducto(i);

                System.out.printf(FORMATO_ITEM,
                        Datos.getNombreProducto(i),
                        cantidad,
                        precio * cantidad);
            }
        }

        imprimirTotales(factura);
        imprimirPie();

        Datos.incrementarNumeroFactura();
        Datos.setMesaActiva(false);
        Datos.setTotalFactura(factura.total);
    }

    public static void imprimirFacturaResumen() {
        FacturaData factura = calcularFactura();

        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", Datos.getNumeroFactura());
        System.out.println("----------------------------------------");

        imprimirTotales(factura);
    }

    private static void imprimirEncabezado() {
        System.out.println(SEPARADOR);
        System.out.println("    " + Datos.getNombreRestaurante());
        System.out.println("    " + Datos.getDireccionRestaurante());
        System.out.println("    " + Datos.getNitRestaurante());
        System.out.println(SEPARADOR);
    }

    private static void imprimirPie() {
        System.out.println(SEPARADOR);
        System.out.println("Gracias por su visita!");
        System.out.println(Datos.getNombreRestaurante() + " - Valledupar");
        System.out.println(SEPARADOR);
    }

    private static void imprimirTotales(FacturaData factura) {
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", factura.subtotalConDescuento);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", factura.iva);

        if (factura.propina > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", factura.propina);
        }

        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", factura.total);
        System.out.println(SEPARADOR);
    }

    private static FacturaData calcularFactura() {
        double subtotal = 0;
        int items = 0;

        for (int i = 0; i < Datos.getCantidadProductos(); i++) {
            int cantidad = Datos.getCantidadProducto(i);

            if (cantidad > 0) {
                subtotal += Datos.getPrecioProducto(i) * cantidad;
                items++;
            }
        }

        double subtotalConDescuento = (items > MIN_ITEMS_DESCUENTO)
                ? subtotal * (1 - TASA_DESCUENTO)
                : subtotal;

        double iva = subtotalConDescuento * TASA_IVA;

        double totalConIva = subtotalConDescuento + iva;

        double propina = (subtotalConDescuento > UMBRAL_PROPINA)
                ? totalConIva * TASA_PROPINA
                : 0;

        double total = totalConIva + propina;

        return new FacturaData(subtotalConDescuento, iva, propina, total);
    }

    private static class FacturaData {
        double subtotalConDescuento;
        double iva;
        double propina;
        double total;

        public FacturaData(double sub, double iva, double prop, double total) {
            this.subtotalConDescuento = sub;
            this.iva = iva;
            this.propina = prop;
            this.total = total;
        }
    }
}