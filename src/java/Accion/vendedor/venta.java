/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accion.vendedor;
import Logica_Negocio.pojos.Cliente;
import Logica_Negocio.PojosPersonalizados.Producto;
import Logica_Negocio.PojosPersonalizados.Tmp;
import Logica_Negocio.pojos.Pedido;
import static com.opensymphony.xwork2.Action.SUCCESS;
import conexion.BaseConexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Rene Jose
 */
public class venta {
    
    Map<String,Object> datos_json=new HashMap<String,Object>();
    
    // -------------------------- 
    private int id_pedido;
    private int id_producto;
    private int cantidad;
    private double importe;
    private int id_temp;
    private int ndigitos;
    private String numero_pedido;
    
    
    //***********************++++++++ variables CLIENTEEE    
    private int id_cliente;
    private int tipo_cliente;
    private String txt_nombrepersona;
    private String txt_documento;
    private String txt_direccion;
    private String txt_celular;
    private String txt_email;
    private int estado;    
    //***********************++++++++ variables TMP   
    private String snombre;
    private String sdni;
    private double digv;
    private double dvalor_neto;
    private int ntipo_doc;
    private int id_user_e;
    private double valor_total_producto_e;
    
    
    private String nombre_produc;
    private String codigo_produc;
    //***********************+++++++ varibles extras para TEMP  
    private int id_usuario_venta;
    
    //***********************++++++++ variables TMP   
    // --------------------------- set and get cliente

    public Map<String, Object> getDatos_json() {
        return datos_json;
    }

    public void setDatos_json(Map<String, Object> datos_json) {
        this.datos_json = datos_json;
    }

    public double getValor_total_producto_e() {
        return valor_total_producto_e;
    }

    public void setValor_total_producto_e(double valor_total_producto_e) {
        this.valor_total_producto_e = valor_total_producto_e;
    }

    public int getId_usuario_venta() {
        return id_usuario_venta;
    }

    public void setId_usuario_venta(int id_usuario_venta) {
        this.id_usuario_venta = id_usuario_venta;
    }

    
    
    
    public String getNombre_produc() {
        return nombre_produc;
    }

    public void setNombre_produc(String nombre_produc) {
        this.nombre_produc = nombre_produc;
    }

    public String getCodigo_produc() {
        return codigo_produc;
    }

    public void setCodigo_produc(String codigo_produc) {
        this.codigo_produc = codigo_produc;
    }

    
    
    
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(int tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    public String getTxt_nombrepersona() {
        return txt_nombrepersona;
    }

    public void setTxt_nombrepersona(String txt_nombrepersona) {
        this.txt_nombrepersona = txt_nombrepersona;
    }

    public String getTxt_documento() {
        return txt_documento;
    }

    public void setTxt_documento(String txt_documento) {
        this.txt_documento = txt_documento;
    }

    public String getTxt_direccion() {
        return txt_direccion;
    }

    public void setTxt_direccion(String txt_direccion) {
        this.txt_direccion = txt_direccion;
    }

    public String getTxt_celular() {
        return txt_celular;
    }

    public void setTxt_celular(String txt_celular) {
        this.txt_celular = txt_celular;
    }

    public String getTxt_email() {
        return txt_email;
    }

    public void setTxt_email(String txt_email) {
        this.txt_email = txt_email;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    //------- tmp set y get-----------------------------------------------------

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
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

    public int getId_temp() {
        return id_temp;
    }

    public void setId_temp(int id_temp) {
        this.id_temp = id_temp;
    }

    public int getNdigitos() {
        return ndigitos;
    }

    public void setNdigitos(int ndigitos) {
        this.ndigitos = ndigitos;
    }

    public String getNumero_pedido() {
        return numero_pedido;
    }

    public void setNumero_pedido(String numero_pedido) {
        this.numero_pedido = numero_pedido;
    }

    public String getSnombre() {
        return snombre;
    }

    public void setSnombre(String snombre) {
        this.snombre = snombre;
    }

    public String getSdni() {
        return sdni;
    }

    public void setSdni(String sdni) {
        this.sdni = sdni;
    }

    public double getDigv() {
        return digv;
    }

    public void setDigv(double digv) {
        this.digv = digv;
    }

    public double getDvalor_neto() {
        return dvalor_neto;
    }

    public void setDvalor_neto(double dvalor_neto) {
        this.dvalor_neto = dvalor_neto;
    }

    public int getNtipo_doc() {
        return ntipo_doc;
    }

    public void setNtipo_doc(int ntipo_doc) {
        this.ntipo_doc = ntipo_doc;
    }

    public int getId_user_e() {
        return id_user_e;
    }

    public void setId_user_e(int id_user_e) {
        this.id_user_e = id_user_e;
    }
    
    
    
    
    
    
    // --------------------------- metodos
    
    
    public String insertar_cliente(){
        try {

            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_registrar_cliente(?, ?, ?, ?, ?, ?)}");
            sentencis.setInt(1, getTipo_cliente()); 
            sentencis.setString(2, getTxt_nombrepersona());            
            sentencis.setString(3, getTxt_documento());            
            sentencis.setString(4, getTxt_direccion());            
            sentencis.setString(5, getTxt_celular());
            sentencis.setString(6, getTxt_email());
            
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    public String actualizar_cliente(){
        try {
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_actualizar_cliente(?, ?, ?, ?, ?, ?, ?, ?)}");
            sentencis.setInt(1, getTipo_cliente());
            sentencis.setString(2, getTxt_nombrepersona());            
            sentencis.setString(3, getTxt_documento());            
            sentencis.setString(4, getTxt_direccion());            
            sentencis.setString(5, getTxt_celular());
            sentencis.setString(6, getTxt_email());
            sentencis.setInt(7, getEstado());
            sentencis.setInt(8, getId_cliente());
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    ///// ----------------------- para autocompletado
    public String lista_busqueda_cliente(){
        try {
            List<Cliente> lista = new ArrayList<>();
            lista.clear();//limpiar la lista
            String query = " select * from cliente ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Cliente obj_=new Cliente();
                obj_.setValue(resul.getString(4)); 
                obj_.setLabel(resul.getString(4)); 
                obj_.setId_cliente(resul.getInt(1));   
                obj_.setTipo_persona(resul.getInt(2));   
                obj_.setNombre_persona(resul.getString(3));                           
                obj_.setDocumento_persona(resul.getString(4));
                obj_.setDireccion_persona(resul.getString(5));                                            
                obj_.setCelular_persona(resul.getString(6));
                obj_.setEmail_persona(resul.getString(7));  
                lista.add(obj_);
            }
            resul.close();
             datos_json.put("lista_busqueda_clientes",lista);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    /// ---------- para ACTUALIZAR CLIENTE
    public String get_idclientelast(){
        try {
            List<Cliente> lst_= new ArrayList<>();
            lst_.clear();//limpiar la lista
            String query=" SELECT \n" +
                         "      id_cliente \n" +
                         " FROM cliente order by id_cliente DESC LIMIT 1  ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Cliente obj_=new Cliente();;
                obj_.setId_cliente(resul.getInt(1));          
                lst_.add(obj_);
            }
            resul.close();
                datos_json.put("cliente_id",lst_);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    //------------ LISTAR TMP DETALLE
    public String lista_filtrada_tmp(){
        try {
            List<Tmp> lst_= new ArrayList<>();
            lst_.clear();//limpiar la lista
                String query =  "SELECT \n" +
    "                                df.id_temp,\n" +
    "                                df.id_perdido,\n" +
    "                                df.id_producto,\n" +
    "                                pro.codigo_producto,\n" +
    "                                pro.descripcion_producto,\n" +
    "                                df.cantidad,\n" +
    "                                pro.precio_producto,\n" +
    "                                df.importe\n" +
    "                            FROM \n" +
    "                            	temp df \n" +
    "                                INNER JOIN producto pro on pro.id_producto = df.id_producto\n" +
    "                            WHERE \n" +
    "                            	df.id_perdido = '"+getId_pedido()+"' ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Tmp obj_=new Tmp();
                obj_.setId_tmp(resul.getInt(1));
                obj_.setId_pedido(resul.getInt(2));          
                obj_.setId_producto(resul.getInt(3));
                obj_.setCodigo_producto(resul.getString(4));
                obj_.setDescripcion_producto(resul.getString(5));
                obj_.setCantidad_tmp(resul.getInt(6));
                obj_.setPrecio_producto(resul.getDouble(7));
                obj_.setImporte_tmp(resul.getDouble(8));
                lst_.add(obj_);
            }
            resul.close();
             datos_json.put("lista_busqueda_tmp",lst_);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    public String insertar_temp(){
        try {
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_registrar_temp(?, ?, ?, ?)}");
            sentencis.setInt(1, getId_pedido()); 
            sentencis.setInt(2, getId_producto());            
            sentencis.setInt(3, getCantidad());            
            sentencis.setDouble(4, getImporte());
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error la insertar : "+ e);
            return "error";
        }
    }
    public String eliminar_temp(){
        try {
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_eliminar_temp( ? )}");
            sentencis.setInt(1, getId_temp());            
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    public String get_numeropedido(){
        try {
           int ncorrelativo_venta=0;
            String query="SELECT n_codigo FROM correlativos where id_actor= "+ getId_user_e();
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                
               ncorrelativo_venta= resul.getInt(1);          
               
            }
            resul.close();
             datos_json.put("numero_pedido",ncorrelativo_venta);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
     
    public String actualizar_tmp(){
        try {
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_actualizar_temp(?, ?, ?)}");
            sentencis.setInt(1, getCantidad());
            sentencis.setDouble(2, getImporte());
            sentencis.setInt(3, getId_temp());
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    
     public String send_caja_venta(){
        try {
            CallableStatement sentencis=
                    BaseConexion.getprepareCall("{call sp_actualizar_paraventa_pedido(?,?,?,?,?,?,?,?,?)}");
            
            sentencis.setInt(1, getId_pedido()); 
            sentencis.setDouble(2, getDvalor_neto());            
            sentencis.setDouble(3, getDigv());            
            sentencis.setInt(4, getNtipo_doc());  
            
             sentencis.setInt(5, getId_cliente()); 
            sentencis.setString(6, getSnombre());            
            sentencis.setString(7, getSdni());            
            sentencis.setInt(8, getId_user_e());
            sentencis.setDouble(9, getValor_total_producto_e()); 
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
     
      ///// ----------------------- para lista de reprote de ventas
    public String listar_ventas_report(){
        try {
            List<Pedido> lista = new ArrayList<>();
            lista.clear();//limpiar la lista
            String query = "select numero_pedido,pago_con,igv_pedido,vuelto,monto_total_pedido,estado_pedido\n" +
            "from pedido where  fecha_pedido= curdate() and id_usuario= "+ getId_usuario_venta();
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Pedido obj_=new Pedido();
                
                obj_.setNumero_pedido(resul.getString(1));   
                obj_.setPago_con(resul.getInt(2));   
                obj_.setIgv_pedido(resul.getDouble(3));                           
                obj_.setVuelto(resul.getDouble(4));
                obj_.setMonto_total_pedido(resul.getDouble(5));                                            
                obj_.setEstado_pedido(resul.getInt(6));
                
                lista.add(obj_);
            }
            resul.close();
             datos_json.put("lista_venta_usu",lista);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    } 
     
     
    
    
}
