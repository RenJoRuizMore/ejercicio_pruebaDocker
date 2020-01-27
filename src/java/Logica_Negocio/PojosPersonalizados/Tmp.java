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
public class Tmp extends Producto{
    
    private int id_tmp;
    private int id_pedido;
    private int cantidad_tmp;
    private double importe_tmp;
    private String numero_pedido;

    public int getId_tmp() {
        return id_tmp;
    }

    public void setId_tmp(int id_tmp) {
        this.id_tmp = id_tmp;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getCantidad_tmp() {
        return cantidad_tmp;
    }

    public void setCantidad_tmp(int cantidad_tmp) {
        this.cantidad_tmp = cantidad_tmp;
    }

    public double getImporte_tmp() {
        return importe_tmp;
    }

    public void setImporte_tmp(double importe_tmp) {
        this.importe_tmp = importe_tmp;
    }

    public String getNumero_pedido() {
        return numero_pedido;
    }

    public void setNumero_pedido(String numero_pedido) {
        this.numero_pedido = numero_pedido;
    }
    
    
}
