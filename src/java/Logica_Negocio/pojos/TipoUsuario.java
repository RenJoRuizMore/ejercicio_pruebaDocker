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
public class TipoUsuario {
    private int id_tipousuario;
    private String descripcion_tipousuario;
    private int estado_tipousuario;

    public int getId_tipousuario() {
        return id_tipousuario;
    }

    public void setId_tipousuario(int id_tipousuario) {
        this.id_tipousuario = id_tipousuario;
    }

    public String getDescripcion_tipousuario() {
        return descripcion_tipousuario;
    }

    public void setDescripcion_tipousuario(String descripcion_tipousuario) {
        this.descripcion_tipousuario = descripcion_tipousuario;
    }

    public int getEstado_tipousuario() {
        return estado_tipousuario;
    }

    public void setEstado_tipousuario(int estado_tipousuario) {
        this.estado_tipousuario = estado_tipousuario;
    }
    
    
}
