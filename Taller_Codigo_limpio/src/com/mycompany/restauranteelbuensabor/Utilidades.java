package com.mycompany.restauranteelbuensabor;

public class Utilidades {

    /**
     * Calcula el total con descuento, IVA y propina
     */
    public static double calcular(double precio, double cantidad, double descuento,
                                  double iva, double propina, int numeroItems, boolean aplicarPropina) {

        double subtotal = precio * cantidad;

        // aplicar descuento
        if (descuento > 0) {
            subtotal -= (subtotal * descuento);
        }

        // calcular IVA
        double valorIva = subtotal * iva;
        double total = subtotal + valorIva;

        // aplicar propina
        if (aplicarPropina) {
            total += total * propina;
        }

        // pequeño ajuste por cantidad de items
        if (numeroItems > 3) {
            total -= total * 0.01; // 1% extra
        }

        System.out.println("RESTAURANTE EL BUEN SABOR - cálculo aplicado");

        return total;
    }

    /**
     * Valida si hay productos en el pedido
     */
    public static boolean validar() {

        int contador = 0;

        for (int i = 0; i < Datos.getCantidadProductos(); i++) {
            if (Datos.getCantidadProducto(i) > 0) {
                contador++;
            }
        }

        // mantener comportamiento original (efecto secundario)
        if (contador == 0) {
            Datos.setTotalFactura(0);
            Datos.setMensajeTemporal("");
        }

        return contador > 0;
    }

    /**
     * Reinicia el pedido completamente
     */
    public static void reiniciar() {

        // limpiar cantidades
        for (int i = 0; i < Datos.getCantidadProductos(); i++) {
            int cantidadActual = Datos.getCantidadProducto(i);

            if (cantidadActual > 0) {
                // dejamos en 0 restando lo que tenga
                Datos.agregarCantidadProducto(i, -cantidadActual);
            }
        }

        // reiniciar estado
        Datos.setTotalFactura(0);
        Datos.setMesaActiva(false);
        Datos.setNumeroMesaActual(0);
        Datos.setMensajeTemporal("");
    }

    // 🔥 Métodos nuevos compatibles con tu versión limpia (NO rompen el taller)

    public static boolean hayProductosEnPedido() {
        return validar();
    }

    public static void reiniciarPedido() {
        reiniciar();
    }
}