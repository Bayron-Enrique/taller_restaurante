package com.mycompany.restauranteelbuensabor;

public final class ItemPedido {

    private final Producto producto;
    private int cantidad;

    public ItemPedido(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void agregarCantidad(int cantidadAdicional) {
        if (cantidadAdicional <= 0) {
            throw new IllegalArgumentException("La cantidad adicional debe ser mayor a cero.");
        }
        this.cantidad = this.cantidad + cantidadAdicional;
    }

    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return String.format("%-20s x%-6d $%,.0f",
                producto.getNombre(),
                cantidad,
                calcularSubtotal());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ItemPedido)) {
            return false;
        }
        ItemPedido otro = (ItemPedido) obj;
        return producto.equals(otro.producto);
    }

    @Override
    public int hashCode() {
        return producto.hashCode();
    }
}