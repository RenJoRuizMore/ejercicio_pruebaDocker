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
public class Producto {
    private String value;
    private int id_producto;
    private String codigo_producto; 
    private int	id_categoria; 
    private String desc_categoria; 
    private String descripcion_producto; 
    private double precio_producto;
    private String unidades_producto; 
    private int	stock_producto ;
    private int	stock_a_producto ; 
    private int	stock_m_producto ; 
    private int	stock_minimo_producto; 
    private int	stock_minimo_mostrador_producto ; 
    private int	en_mostrador_producto ;
    private int en_listacompra;        
    private int	estado_producto;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
    
    
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

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public double getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(double precio_producto) {
        this.precio_producto = precio_producto;
    }

    public String getUnidades_producto() {
        return unidades_producto;
    }

    public void setUnidades_producto(String unidades_producto) {
        this.unidades_producto = unidades_producto;
    }

    public int getStock_producto() {
        return stock_producto;
    }

    public void setStock_producto(int stock_producto) {
        this.stock_producto = stock_producto;
    }

    public int getStock_a_producto() {
        return stock_a_producto;
    }

    public void setStock_a_producto(int stock_a_producto) {
        this.stock_a_producto = stock_a_producto;
    }

    public int getStock_m_producto() {
        return stock_m_producto;
    }

    public void setStock_m_producto(int stock_m_producto) {
        this.stock_m_producto = stock_m_producto;
    }

    public int getStock_minimo_producto() {
        return stock_minimo_producto;
    }

    public void setStock_minimo_producto(int stock_minimo_producto) {
        this.stock_minimo_producto = stock_minimo_producto;
    }

    public int getStock_minimo_mostrador_producto() {
        return stock_minimo_mostrador_producto;
    }

    public void setStock_minimo_mostrador_producto(int stock_minimo_mostrador_producto) {
        this.stock_minimo_mostrador_producto = stock_minimo_mostrador_producto;
    }

    public int getEn_mostrador_producto() {
        return en_mostrador_producto;
    }

    public void setEn_mostrador_producto(int en_mostrador_producto) {
        this.en_mostrador_producto = en_mostrador_producto;
    }

    public int getEn_listacompra() {
        return en_listacompra;
    }

    public void setEn_listacompra(int en_listacompra) {
        this.en_listacompra = en_listacompra;
    }

    public String getDesc_categoria() {
        return desc_categoria;
    }

    public void setDesc_categoria(String desc_categoria) {
        this.desc_categoria = desc_categoria;
    }
    
    public int getEstado_producto() {
        return estado_producto;
    }

    public void setEstado_producto(int estado_producto) {
        this.estado_producto = estado_producto;
    }
    
    
    
}
