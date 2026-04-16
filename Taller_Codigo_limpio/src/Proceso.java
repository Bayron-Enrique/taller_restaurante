public class Proceso {

    // Constantes (eliminan magic numbers)
    private static final double TASA_IVA = 0.19;
    private static final double TASA_PROPINA = 0.10;
    private static final double TASA_DESCUENTO = 0.05;
    private static final double UMBRAL_PROPINA = 50000;
    private static final int MIN_ITEMS_DESCUENTO = 3;

    public static double calcularTotalFactura() {
        double subtotal = calcularSubtotal();
        int cantidadItems = contarItems();

        double subtotalConDescuento = aplicarDescuento(subtotal, cantidadItems);
        double iva = calcularIVA(subtotalConDescuento);
        double propina = calcularPropina(subtotalConDescuento + iva);

        double total = subtotalConDescuento + iva + propina;

        // Actualizar estado (se mantiene comportamiento original)
        Datos.est = 1;
        Datos.tot = total;

        return total;
    }

    private static double calcularSubtotal() {
        double subtotal = 0;

        for (int i = 0; i < Datos.nom.length; i++) {
            if (Datos.cant[i] > 0) {
                subtotal += Datos.p[i] * Datos.cant[i];
            }
        }

        return subtotal;
    }

    private static int contarItems() {
        int contador = 0;

        for (int i = 0; i < Datos.cant.length; i++) {
            if (Datos.cant[i] > 0) {
                contador++;
            }
        }

        return contador;
    }

    private static double aplicarDescuento(double subtotal, int cantidadItems) {
        if (cantidadItems > MIN_ITEMS_DESCUENTO) {
            return subtotal - (subtotal * TASA_DESCUENTO);
        }
        return subtotal;
    }

    private static double calcularIVA(double base) {
        return base * TASA_IVA;
    }

    private static double calcularPropina(double totalConIva) {
        if (totalConIva > UMBRAL_PROPINA) {
            return totalConIva * TASA_PROPINA;
        }
        return 0;
    }
}