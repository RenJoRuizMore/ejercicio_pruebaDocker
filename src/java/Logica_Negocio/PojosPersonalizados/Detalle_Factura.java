/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio.PojosPersonalizados;

/**
 *
 * @author saavedra
 */
public class Detalle_Factura {
    
    private int id_detallefactura;
    private int	id_factura;
    private int id_producto;
    private String cod_producto;       
    private String nombre_producto;
    private int stock;
    private int cantidad_detallefactura;
    private double precio_detallefactura;
    private double importe_detallefactura;

    public int getId_detallefactura() {
        return id_detallefactura;
    }

    public void setId_detallefactura(int id_detallefactura) {
        this.id_detallefactura = id_detallefactura;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(String cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCantidad_detallefactura() {
        return cantidad_detallefactura;
    }

    public void setCantidad_detallefactura(int cantidad_detallefactura) {
        this.cantidad_detallefactura = cantidad_detallefactura;
    }

    public double getPrecio_detallefactura() {
        return precio_detallefactura;
    }

    public void setPrecio_detallefactura(double precio_detallefactura) {
        this.precio_detallefactura = precio_detallefactura;
    }

    public double getImporte_detallefactura() {
        return importe_detallefactura;
    }

    public void setImporte_detallefactura(double importe_detallefactura) {
        this.importe_detallefactura = importe_detallefactura;
    }

    
        
}
