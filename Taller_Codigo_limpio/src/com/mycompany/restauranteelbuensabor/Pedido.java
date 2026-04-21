package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Pedido {

    private final List<ItemPedido> items = new ArrayList<>();
    private int numeroMesa;
    private boolean activo;

    public Pedido(int numeroMesa) {
        if (numeroMesa <= 0) {
            throw new IllegalArgumentException("El número de mesa debe ser mayor a cero.");
        }
        this.numeroMesa = numeroMesa;
        this.activo = true;
    }

    public void agregarItem(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }

        for (ItemPedido item : items) {
            if (item.getProducto().equals(producto)) {
                item.agregarCantidad(cantidad);
                return;
            }
        }

        items.add(new ItemPedido(producto, cantidad));
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemPedido item : items) {
            subtotal = subtotal + item.calcularSubtotal();
        }
        return subtotal;
    }

    public int contarItemsDiferentes() {
        return items.size();
    }

    public boolean tieneProductos() {
        return !items.isEmpty();
    }

    public void limpiar() {
        items.clear();
        activo = false;
    }

    public List<ItemPedido> getItems() {
        return Collections.unmodifiableList(items);
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- PEDIDO MESA ").append(numeroMesa).append(" ---\n");
        for (ItemPedido item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append(String.format("%-27s $%,.0f", "Subtotal:", calcularSubtotal()));
        return sb.toString();
    }
}
