/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio.PojosPersonalizados;

/**
 *
 * @author Rene Jose
 */
public class RVentaEfectivo {
        
 private String numero_pedido;
  private String codigo_producto; 
private String descripcion_producto;
private double precio_producto;
private int cantidad_pedidoproducto;
private double importe_pedidoproducto;
private double suma;

    public String getNumero_pedido() {
        return numero_pedido;
    }

    public void setNumero_pedido(String numero_pedido) {
        this.numero_pedido = numero_pedido;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public double getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(double precio_producto) {
        this.precio_producto = precio_producto;
    }

    public int getCantidad_pedidoproducto() {
        return cantidad_pedidoproducto;
    }

    public void setCantidad_pedidoproducto(int cantidad_pedidoproducto) {
        this.cantidad_pedidoproducto = cantidad_pedidoproducto;
    }

    public double getImporte_pedidoproducto() {
        return importe_pedidoproducto;
    }

    public void setImporte_pedidoproducto(double importe_pedidoproducto) {
        this.importe_pedidoproducto = importe_pedidoproducto;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }



        
        
    
}
