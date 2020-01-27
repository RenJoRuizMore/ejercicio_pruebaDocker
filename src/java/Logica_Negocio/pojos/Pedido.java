/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio.pojos;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Rene Jose
 */
public class Pedido {
    
    private int id_pedido;
    private String nombre;
    private String numero_pedido;
    private int tipo_documento;
    private int tipo_pago;
    private double monto_total_pedido;
    private Date fecha_pedido;

    private Time hora_pedido;
    private int id_cliente;
    private int id_usuario;
    private double igv_pedido;
    private int estado_pedido;
    double pago_con;
    double vuelto;
    
    

    public Pedido() {
    }

    public String getNumero_pedido() {
        return numero_pedido;
    }

    public void setNumero_pedido(String numero_pedido) {
        this.numero_pedido = numero_pedido;
    }

    public int getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(int tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public int getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(int tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public double getMonto_total_pedido() {
        return monto_total_pedido;
    }

    public void setMonto_total_pedido(double monto_total_pedido) {
        this.monto_total_pedido = monto_total_pedido;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public Time getHora_pedido() {
        return hora_pedido;
    }

    public void setHora_pedido(Time hora_pedido) {
        this.hora_pedido = hora_pedido;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public double getIgv_pedido() {
        return igv_pedido;
    }

    public void setIgv_pedido(double igv_pedido) {
        this.igv_pedido = igv_pedido;
    }

    public int getEstado_pedido() {
        return estado_pedido;
    }

    public void setEstado_pedido(int estado_pedido) {
        this.estado_pedido = estado_pedido;
    }

    public double getPago_con() {
        return pago_con;
    }

    public void setPago_con(double pago_con) {
        this.pago_con = pago_con;
    }

    public double getVuelto() {
        return vuelto;
    }

    public void setVuelto(double vuelto) {
        this.vuelto = vuelto;
    }

    
    
    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
    
    
    
}
