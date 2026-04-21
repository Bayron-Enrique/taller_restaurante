package com.mycompany.restauranteelbuensabor;

public final class Factura {

    private static final double TASA_IVA = 0.19;
    private static final double TASA_PROPINA = 0.10;
    private static final double TASA_DESCUENTO = 0.05;
    private static final double UMBRAL_PROPINA = 50000;
    private static final int MIN_ITEMS_DESCUENTO = 3;

    private final Pedido pedido;
    private final int numero;

    public Factura(Pedido pedido, int numero) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo.");
        }
        if (!pedido.tieneProductos()) {
            throw new IllegalStateException("No se puede generar una factura sin productos.");
        }
        if (numero <= 0) {
            throw new IllegalArgumentException("El número de factura debe ser mayor a cero.");
        }
        this.pedido = pedido;
        this.numero = numero;
    }

    public double calcularSubtotal() {
        return pedido.calcularSubtotal();
    }

    public double calcularDescuento() {
        if (pedido.contarItemsDiferentes() > MIN_ITEMS_DESCUENTO) {
            return calcularSubtotal() * TASA_DESCUENTO;
        }
        return 0;
    }

    public double calcularSubtotalConDescuento() {
        return calcularSubtotal() - calcularDescuento();
    }

    public double calcularIVA() {
        // El IVA se calcula sobre el subtotal ya descontado, según DIAN 2024
        return calcularSubtotalConDescuento() * TASA_IVA;
    }

    public double calcularPropina() {
        double totalConIva = calcularSubtotalConDescuento() + calcularIVA();

        // La propina aplica sobre el total con IVA incluido, no sobre el subtotal
        if (calcularSubtotalConDescuento() > UMBRAL_PROPINA) {
            return totalConIva * TASA_PROPINA;
        }
        return 0;
    }

    public double calcularTotal() {
        double totalConIva = calcularSubtotalConDescuento() + calcularIVA();
        return totalConIva + calcularPropina();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public int getNumero() {
        return numero;
    }
}