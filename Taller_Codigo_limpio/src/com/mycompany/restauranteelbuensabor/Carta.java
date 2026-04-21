package com.mycompany.restauranteelbuensabor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Carta {

    private static final List<Producto> PRODUCTOS = Collections.unmodifiableList(Arrays.asList(
            new Producto("Bandeja Paisa",       32000),
            new Producto("Sancocho de Gallina",  28000),
            new Producto("Arepa con Huevo",       8000),
            new Producto("Jugo Natural",           7000),
            new Producto("Gaseosa",                4500),
            new Producto("Cerveza Poker",          6000),
            new Producto("Agua Panela",            3500),
            new Producto("Arroz con Pollo",       25000)
    ));

    private Carta() {
    }

    public static int getCantidadProductos() {
        return PRODUCTOS.size();
    }

    public static Producto getProducto(int indice) {
        if (indice < 0 || indice >= PRODUCTOS.size()) {
            throw new IndexOutOfBoundsException("Índice de producto inválido: " + indice);
        }
        return PRODUCTOS.get(indice);
    }

    public static List<Producto> getProductos() {
        return PRODUCTOS;
    }
}