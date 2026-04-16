package com.mycompany.restauranteelbuensabor;

public class Proceso {

    private static final double TASA_IVA = 0.19;
    private static final double TASA_PROPINA = 0.10;
    private static final double TASA_DESCUENTO = 0.05;
    private static final double UMBRAL_PROPINA = 50000;
    private static final int MIN_ITEMS_DESCUENTO = 3;

    public static double calcularTotalFactura() {

        double subtotal = calcularSubtotal();
        int items = contarItems();

        double subtotalConDescuento = aplicarDescuento(subtotal, items);
        double iva = subtotalConDescuento * TASA_IVA;

        double totalConIva = subtotalConDescuento + iva;

        double propina = (totalConIva > UMBRAL_PROPINA)
                ? totalConIva * TASA_PROPINA
                : 0;

        double total = totalConIva + propina;

        // actualizar estado (IMPORTANTE)
        Datos.setTotalFactura(total);
        Datos.setMesaActiva(true);

        return total;
    }

    private static double calcularSubtotal() {
        double subtotal = 0;

        for (int i = 0; i < Datos.getCantidadProductos(); i++) {
            int cantidad = Datos.getCantidadProducto(i);

            if (cantidad > 0) {
                subtotal += Datos.getPrecioProducto(i) * cantidad;
            }
        }

        return subtotal;
    }

    private static int contarItems() {
        int contador = 0;

        for (int i = 0; i < Datos.getCantidadProductos(); i++) {
            if (Datos.getCantidadProducto(i) > 0) {
                contador++;
            }
        }

        return contador;
    }

    private static double aplicarDescuento(double subtotal, int items) {
        if (items > MIN_ITEMS_DESCUENTO) {
            return subtotal * (1 - TASA_DESCUENTO);
        }
        return subtotal;
    }
}