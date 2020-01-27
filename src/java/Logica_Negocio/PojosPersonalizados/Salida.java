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
public class Salida {
    private int id_salida;
    private String numero_salida;
    private int id_usuario;
    private String nombre_usuario;
    private String fecha_salida;
    private int cantidad;
    private int estado_salida;

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    public String getNumero_salida() {
        return numero_salida;
    }

    public void setNumero_salida(String numero_salida) {
        this.numero_salida = numero_salida;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public int getEstado_salida() {
        return estado_salida;
    }

    public void setEstado_salida(int estado_salida) {
        this.estado_salida = estado_salida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
