package com.mycompany.restauranteelbuensabor;

import java.util.Arrays;

public final class Datos {

    private static final String NOMBRE_RESTAURANTE = "El Buen Sabor";
    private static final String DIRECCION_RESTAURANTE = "Calle 15 #8-32, Valledupar";
    private static final String NIT_RESTAURANTE = "900.123.456-7";

    private static final String[] NOMBRES_PRODUCTOS = {
        "Bandeja Paisa",
        "Sancocho de Gallina",
        "Arepa con Huevo",
        "Jugo Natural",
        "Gaseosa",
        "Cerveza Poker",
        "Agua Panela",
        "Arroz con Pollo"
    };

    private static final double[] PRECIOS_PRODUCTOS = {
        32000,
        28000,
        8000,
        7000,
        4500,
        6000,
        3500,
        25000
    };

    private static final int[] CANTIDADES_PRODUCTOS = new int[NOMBRES_PRODUCTOS.length];

    private static int numeroMesaActual = 0;
    private static boolean mesaActiva = false;
    private static double totalFactura = 0.0;
    private static int numeroFactura = 1;
    private static String mensajeTemporal = "";

    private Datos() {
    }

    public static String getNombreRestaurante() {
        return NOMBRE_RESTAURANTE;
    }

    public static String getDireccionRestaurante() {
        return DIRECCION_RESTAURANTE;
    }

    public static String getNitRestaurante() {
        return NIT_RESTAURANTE;
    }

    public static int getCantidadProductos() {
        return NOMBRES_PRODUCTOS.length;
    }

    public static String getNombreProducto(int indice) {
        validarIndice(indice);
        return NOMBRES_PRODUCTOS[indice];
    }

    public static double getPrecioProducto(int indice) {
        validarIndice(indice);
        return PRECIOS_PRODUCTOS[indice];
    }

    public static int getCantidadProducto(int indice) {
        validarIndice(indice);
        return CANTIDADES_PRODUCTOS[indice];
    }

    public static void agregarCantidadProducto(int indice, int cantidad) {
        validarIndice(indice);
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        CANTIDADES_PRODUCTOS[indice] = CANTIDADES_PRODUCTOS[indice] + cantidad;
    }

    public static boolean tieneProductosEnPedido() {
        for (int cantidad : CANTIDADES_PRODUCTOS) {
            if (cantidad > 0) {
                return true;
            }
        }
        return false;
    }

    public static void reiniciarPedido() {
        Arrays.fill(CANTIDADES_PRODUCTOS, 0);
        numeroMesaActual = 0;
        mesaActiva = false;
        totalFactura = 0.0;
        mensajeTemporal = "";
    }

    public static int getNumeroMesaActual() {
        return numeroMesaActual;
    }

    public static void setNumeroMesaActual(int numeroMesaActual) {
        Datos.numeroMesaActual = numeroMesaActual;
    }

    public static boolean isMesaActiva() {
        return mesaActiva;
    }

    public static void setMesaActiva(boolean mesaActiva) {
        Datos.mesaActiva = mesaActiva;
    }

    public static double getTotalFactura() {
        return totalFactura;
    }

    public static void setTotalFactura(double totalFactura) {
        Datos.totalFactura = totalFactura;
    }

    public static int getNumeroFactura() {
        return numeroFactura;
    }

    public static void incrementarNumeroFactura() {
        numeroFactura = numeroFactura + 1;
    }

    public static String getMensajeTemporal() {
        return mensajeTemporal;
    }

    public static void setMensajeTemporal(String mensajeTemporal) {
        Datos.mensajeTemporal = mensajeTemporal;
    }

    private static void validarIndice(int indice) {
        if (indice < 0 || indice >= NOMBRES_PRODUCTOS.length) {
            throw new IndexOutOfBoundsException("Índice de producto inválido: " + indice);
        }
    }
}