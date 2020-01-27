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
public abstract class Persona {
    private int id_persona;
    private int tipo_persona;
    private String nombre_persona;
    private String documento_persona;
    private String direccion_persona;
    private String celular_persona;
    private String email_persona;

    public Persona() {
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(int tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getDocumento_persona() {
        return documento_persona;
    }

    public void setDocumento_persona(String documento_persona) {
        this.documento_persona = documento_persona;
    }

    public String getDireccion_persona() {
        return direccion_persona;
    }

    public void setDireccion_persona(String direccion_persona) {
        this.direccion_persona = direccion_persona;
    }

    public String getCelular_persona() {
        return celular_persona;
    }

    public void setCelular_persona(String celular_persona) {
        this.celular_persona = celular_persona;
    }

    public String getEmail_persona() {
        return email_persona;
    }

    public void setEmail_persona(String email_persona) {
        this.email_persona = email_persona;
    }
    
}
