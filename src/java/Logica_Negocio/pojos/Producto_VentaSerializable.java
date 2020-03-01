/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio.pojos;

/**
 *
 * @author Rene Jose
 */
import java.io.Serializable;
public class Producto_VentaSerializable implements Serializable{
    private int id_producto;
    private String codigo_producto;
    private String nombre_producto;
    private int cantidad_llevar;
    private double precio;
    private double total_por_producto;

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getCantidad_llevar() {
        return cantidad_llevar;
    }

    public void setCantidad_llevar(int cantidad_llevar) {
        this.cantidad_llevar = cantidad_llevar;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal_por_producto() {
        return total_por_producto;
    }

    public void setTotal_por_producto(double total_por_producto) {
        this.total_por_producto = total_por_producto;
    }
    
    
    
    
}
