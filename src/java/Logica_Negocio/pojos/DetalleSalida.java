/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio.pojos;

/**
 *
 * @author saavedra
 */
public class DetalleSalida {
    
    private int id_detallesalida;
    private int id_salida;
    private int id_producto;
    private int stock;
    private int cantidad_detallesalida;
    private String cod_producto;
    private String nombre_producto;
    

    public int getId_detallesalida() {
        return id_detallesalida;
    }

    public void setId_detallesalida(int id_detallesalida) {
        this.id_detallesalida = id_detallesalida;
    }

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad_detallesalida() {
        return cantidad_detallesalida;
    }

    public void setCantidad_detallesalida(int cantidad_detallesalida) {
        this.cantidad_detallesalida = cantidad_detallesalida;
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
    
    
}
