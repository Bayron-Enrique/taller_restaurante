public class Utilidades {

    public static boolean hayProductosEnPedido() {
        for (int i = 0; i < Datos.cant.length; i++) {
            if (Datos.cant[i] > 0) {
                return true;
            }
        }
        return false;
    }

    public static void reiniciarPedido() {
        for (int i = 0; i < Datos.cant.length; i++) {
            Datos.cant[i] = 0;
        }

        Datos.tot = 0;
        Datos.est = 0;
        Datos.ms = 0;
    }
}