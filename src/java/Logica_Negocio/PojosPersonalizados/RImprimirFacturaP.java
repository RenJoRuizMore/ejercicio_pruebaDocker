/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio.PojosPersonalizados;

import java.sql.Date;

/**
 *
 * @author Rene Jose
 */
public class RImprimirFacturaP {
    
    private String nombre_cliente;
    private String numero_pedido;
     private String direccion_cliente;
    
     private String documento_cliente;
    private Date fecha_pedido;
    private int cantidad_pedidoproducto;
    private double precio_producto;
    private String descripcion_producto;
    private double importe_pedidoproducto;
    private double monto_total_pedido;
     private double monto_t_producto;
     private double igv_pedido;

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getNumero_pedido() {
        return numero_pedido;
    }

    public void setNumero_pedido(String numero_pedido) {
        this.numero_pedido = numero_pedido;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public double getIgv_pedido() {
        return igv_pedido;
    }

    public void setIgv_pedido(double igv_pedido) {
        this.igv_pedido = igv_pedido;
    }

    
    
    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public String getDocumento_cliente() {
        return documento_cliente;
    }

    public void setDocumento_cliente(String documento_cliente) {
        this.documento_cliente = documento_cliente;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public int getCantidad_pedidoproducto() {
        return cantidad_pedidoproducto;
    }

    public void setCantidad_pedidoproducto(int cantidad_pedidoproducto) {
        this.cantidad_pedidoproducto = cantidad_pedidoproducto;
    }

    public double getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(double precio_producto) {
        this.precio_producto = precio_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public double getImporte_pedidoproducto() {
        return importe_pedidoproducto;
    }

    public void setImporte_pedidoproducto(double importe_pedidoproducto) {
        this.importe_pedidoproducto = importe_pedidoproducto;
    }

    public double getMonto_total_pedido() {
        return monto_total_pedido;
    }

    public void setMonto_total_pedido(double monto_total_pedido) {
        this.monto_total_pedido = monto_total_pedido;
    }

    public double getMonto_t_producto() {
        return monto_t_producto;
    }

    public void setMonto_t_producto(double monto_t_producto) {
        this.monto_t_producto = monto_t_producto;
    }
    
    
}
