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
public class PedidoTemp{
    private int id_temp;
    private int id_perdido;
    private int id_producto;
    private int cantidad;
    private double importe;
    private double total;
    private double igv;
    private int tipo_documento;
    private int id_cliente;
    private String nombre;
    private String dni;
    private double subtototal;

    public double getSubtototal() {
        return subtototal;
    }

    public void setSubtototal(double subtototal) {
        this.subtototal = subtototal;
    }
    
    
    
    
    
    public int getId_temp() {
        return id_temp;
    }

    public void setId_temp(int id_temp) {
        this.id_temp = id_temp;
    }

    public int getId_perdido() {
        return id_perdido;
    }

    public void setId_perdido(int id_perdido) {
        this.id_perdido = id_perdido;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public int getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(int tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    
  
    
    
}
