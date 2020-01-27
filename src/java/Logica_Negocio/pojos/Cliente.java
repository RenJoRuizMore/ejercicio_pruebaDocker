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
public class Cliente extends Persona{
    private int id_cliente;
    private int estado_cliente;
    private String value;
    private String label;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getEstado_cliente() {
        return estado_cliente;
    }

    public void setEstado_cliente(int estado_cliente) {
        this.estado_cliente = estado_cliente;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
     
     
    
}
