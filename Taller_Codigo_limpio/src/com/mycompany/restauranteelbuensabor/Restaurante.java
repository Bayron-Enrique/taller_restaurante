package com.mycompany.restauranteelbuensabor;

public final class Restaurante {

    private static final String NOMBRE = "El Buen Sabor";
    private static final String DIRECCION = "Calle 15 #8-32, Valledupar";
    private static final String NIT = "900.123.456-7";

    private Restaurante() {
    }

    public static String getNombre() {
        return NOMBRE;
    }

    public static String getDireccion() {
        return DIRECCION;
    }

    public static String getNit() {
        return NIT;
    }
}