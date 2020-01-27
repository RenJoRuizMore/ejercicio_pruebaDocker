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
public class Usuario extends Persona{
    private int id_usuario;
    private int id_persona;
    private int id_tipousuario;
    private String usuario_usuario;
    private String clave_usuario;
    private int estado;
    private String codigo_sesion;
    private String descripcion_tipousuario;
    private String turno;

    public Usuario() {
    }

    public String getCodigo_sesion() {
        return codigo_sesion;
    }

    public void setCodigo_sesion(String codigo_sesion) {
        this.codigo_sesion = codigo_sesion;
    }

    public String getDescripcion_tipousuario() {
        return descripcion_tipousuario;
    }

    public void setDescripcion_tipousuario(String descripcion_tipousuario) {
        this.descripcion_tipousuario = descripcion_tipousuario;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_tipousuario() {
        return id_tipousuario;
    }

    public void setId_tipousuario(int id_tipousuario) {
        this.id_tipousuario = id_tipousuario;
    }

    public String getUsuario_usuario() {
        return usuario_usuario;
    }

    public void setUsuario_usuario(String usuario_usuario) {
        this.usuario_usuario = usuario_usuario;
    }

    public String getClave_usuario() {
        return clave_usuario;
    }

    public void setClave_usuario(String clave_usuario) {
        this.clave_usuario = clave_usuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
