/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio.pojos;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rene Jose
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class VentaSerializable implements Serializable{
    // creating tipe serializable 
    private static final long VersUI=10L;
    
    private String numero_pedido;
    private String nombre_vendedor;
    private double monto;
    private String dni;
    private int tipo_comprobante;
    private String nombre_cliente;
    private String direccion_cliente;
    private String celular;
    private double subtotal_todo;
    private double igv;
    private double total_todo;
    // listado de Productos 
    private List<Producto_VentaSerializable> lst_productos_venta= new ArrayList<>();
    // =============================== methods for class ==================================
    public void grabar_serialization(){
        try {
            
            FileOutputStream file_out= new FileOutputStream("sales.txt");
            ObjectOutputStream out_object= new ObjectOutputStream(file_out);
            // write obj in the txt
            out_object.writeObject(this);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"problema cpon el archivo "+ ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"problema con la serializacion "+ex);
        }
    };
    
   
    
    
    
    
    // ===================================== get and setter  ===============================
    
    public double getSubtotal_todo() {
        return subtotal_todo;
    }

    public void setSubtotal_todo(double subtotal_todo) {
        this.subtotal_todo = subtotal_todo;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal_todo() {
        return total_todo;
    }

    public void setTotal_todo(double total_todo) {
        this.total_todo = total_todo;
    }
    
    
    
    
    public String getNumero_pedido() {
        return numero_pedido;
    }

    public void setNumero_pedido(String numero_pedido) {
        this.numero_pedido = numero_pedido;
    }

    public String getNombre_vendedor() {
        return nombre_vendedor;
    }

    public void setNombre_vendedor(String nombre_vendedor) {
        this.nombre_vendedor = nombre_vendedor;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTipo_comprobante() {
        return tipo_comprobante;
    }

    public void setTipo_comprobante(int tipo_comprobante) {
        this.tipo_comprobante = tipo_comprobante;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public List<Producto_VentaSerializable> getLst_productos_venta() {
        return lst_productos_venta;
    }

    public void setLst_productos_venta(List<Producto_VentaSerializable> lst_productos_venta) {
        this.lst_productos_venta = lst_productos_venta;
    }
    
    // agregar productos a la lista
    public void add_producto_venta(Producto_VentaSerializable pro){
        this.lst_productos_venta.add(pro);
    }
    
     // remover productos a la lista
    public void remove_producto_venta(Producto_VentaSerializable pro){
        this.lst_productos_venta.remove(pro);
    }
    
    
}
