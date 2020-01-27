/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accion.cajero;

import Logica_Negocio.PojosPersonalizados.PedidoTemp;
import Logica_Negocio.PojosPersonalizados.Producto;
import Logica_Negocio.PojosPersonalizados.Tmp;
import Logica_Negocio.pojos.Cliente;
import Logica_Negocio.pojos.Pedido;
import Logica_Negocio.pojos.Productos_report;
import Logica_Negocio.pojos.Usuario;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import conexion.BaseConexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Rene Jose
 */
public class Accion_Cajero extends ActionSupport{
    
      
    Map<String,Object> datos_json=new HashMap<String,Object>();
    
    // -------------------------- 
    int id_pedido_e;
    // --------------------------  variables para realizar venta
    String numero_pedido_e ;
    int tipo_pago_e ;
    int id_usuario_caja_e;
    String turno_mov_caja_e;
    int flag_tarjeta ;
    String numero_voucher_e;
    String sustento_e ;
    String banco_e ;
    String tarjeta_e;
    int id_usuario_vendedor_e;
    double pago_con_e;
    double vuelto_e;
    double monto_t_producto_e;
    // -------------------------- reportes
    int id_producto_v;
    int id_venta;
    // ========================== variables para apertura de caja 
    double monto_inicio_e;
    String motivo_apertura_caja_e;
    String turno_e;
    int id_usuario_e;
     //****************** COMPRAS
    private int cbx_opcion;
    private String txt_fechai;
    private String txt_fechaf;
    private String cbx_mes;
    private String txt_anio;
    private int cbx_provee;
    
    private int cbx_vende;
    private int cbx_tipo;
    private int cbx_turno;
    
    private int cbx_productos;

    public Map<String, Object> getDatos_json() {
        return datos_json;
    }

    public void setDatos_json(Map<String, Object> datos_json) {
        this.datos_json = datos_json;
    }

    public double getMonto_inicio_e() {
        return monto_inicio_e;
    }

    public void setMonto_inicio_e(double monto_inicio_e) {
        this.monto_inicio_e = monto_inicio_e;
    }

    public double getMonto_t_producto_e() {
        return monto_t_producto_e;
    }

    public void setMonto_t_producto_e(double monto_t_producto_e) {
        this.monto_t_producto_e = monto_t_producto_e;
    }

    
    
    public String getMotivo_apertura_caja_e() {
        return motivo_apertura_caja_e;
    }

    public void setMotivo_apertura_caja_e(String motivo_apertura_caja_e) {
        this.motivo_apertura_caja_e = motivo_apertura_caja_e;
    }

    public String getTurno_e() {
        return turno_e;
    }

    public void setTurno_e(String turno_e) {
        this.turno_e = turno_e;
    }

    public int getId_usuario_e() {
        return id_usuario_e;
    }

    public void setId_usuario_e(int id_usuario_e) {
        this.id_usuario_e = id_usuario_e;
    }

    
    
    public int getId_pedido_e() {
        return id_pedido_e;
    }

    public void setId_pedido_e(int id_pedido_e) {
        this.id_pedido_e = id_pedido_e;
    }

    public String getNumero_pedido_e() {
        return numero_pedido_e;
    }

    public void setNumero_pedido_e(String numero_pedido_e) {
        this.numero_pedido_e = numero_pedido_e;
    }

    public int getTipo_pago_e() {
        return tipo_pago_e;
    }

    public void setTipo_pago_e(int tipo_pago_e) {
        this.tipo_pago_e = tipo_pago_e;
    }

    public int getId_usuario_caja_e() {
        return id_usuario_caja_e;
    }

    public void setId_usuario_caja_e(int id_usuario_caja_e) {
        this.id_usuario_caja_e = id_usuario_caja_e;
    }

    public String getTurno_mov_caja_e() {
        return turno_mov_caja_e;
    }

    public void setTurno_mov_caja_e(String turno_mov_caja_e) {
        this.turno_mov_caja_e = turno_mov_caja_e;
    }

    public int getFlag_tarjeta() {
        return flag_tarjeta;
    }

    public void setFlag_tarjeta(int flag_tarjeta) {
        this.flag_tarjeta = flag_tarjeta;
    }

    public String getNumero_voucher_e() {
        return numero_voucher_e;
    }

    public void setNumero_voucher_e(String numero_voucher_e) {
        this.numero_voucher_e = numero_voucher_e;
    }

    public String getSustento_e() {
        return sustento_e;
    }

    public void setSustento_e(String sustento_e) {
        this.sustento_e = sustento_e;
    }

    public String getBanco_e() {
        return banco_e;
    }

    public void setBanco_e(String banco_e) {
        this.banco_e = banco_e;
    }

    public String getTarjeta_e() {
        return tarjeta_e;
    }

    public void setTarjeta_e(String tarjeta_e) {
        this.tarjeta_e = tarjeta_e;
    }

    public int getId_usuario_vendedor_e() {
        return id_usuario_vendedor_e;
    }

    public void setId_usuario_vendedor_e(int id_usuario_vendedor_e) {
        this.id_usuario_vendedor_e = id_usuario_vendedor_e;
    }

    public double getPago_con_e() {
        return pago_con_e;
    }

    public void setPago_con_e(double pago_con_e) {
        this.pago_con_e = pago_con_e;
    }

    public double getVuelto_e() {
        return vuelto_e;
    }

    public void setVuelto_e(double vuelto_e) {
        this.vuelto_e = vuelto_e;
    }

    public int getId_producto_v() {
        return id_producto_v;
    }

    public void setId_producto_v(int id_producto_v) {
        this.id_producto_v = id_producto_v;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getCbx_opcion() {
        return cbx_opcion;
    }

    public void setCbx_opcion(int cbx_opcion) {
        this.cbx_opcion = cbx_opcion;
    }

    public String getTxt_fechai() {
        return txt_fechai;
    }

    public void setTxt_fechai(String txt_fechai) {
        this.txt_fechai = txt_fechai;
    }

    public String getTxt_fechaf() {
        return txt_fechaf;
    }

    public void setTxt_fechaf(String txt_fechaf) {
        this.txt_fechaf = txt_fechaf;
    }

    public String getCbx_mes() {
        return cbx_mes;
    }

    public void setCbx_mes(String cbx_mes) {
        this.cbx_mes = cbx_mes;
    }

    public String getTxt_anio() {
        return txt_anio;
    }

    public void setTxt_anio(String txt_anio) {
        this.txt_anio = txt_anio;
    }

    public int getCbx_provee() {
        return cbx_provee;
    }

    public void setCbx_provee(int cbx_provee) {
        this.cbx_provee = cbx_provee;
    }

    public int getCbx_vende() {
        return cbx_vende;
    }

    public void setCbx_vende(int cbx_vende) {
        this.cbx_vende = cbx_vende;
    }

    public int getCbx_tipo() {
        return cbx_tipo;
    }

    public void setCbx_tipo(int cbx_tipo) {
        this.cbx_tipo = cbx_tipo;
    }

    public int getCbx_turno() {
        return cbx_turno;
    }

    public void setCbx_turno(int cbx_turno) {
        this.cbx_turno = cbx_turno;
    }

    public int getCbx_productos() {
        return cbx_productos;
    }

    public void setCbx_productos(int cbx_productos) {
        this.cbx_productos = cbx_productos;
    }
    
    
    
    ///// ----------------------- para autocompletado
    public String lista_busqueda_num_pedido(){
        try {
            List<Pedido> lista_pedido = new ArrayList<>();
            lista_pedido.clear();//limpiar la lista
            String query = "select distinct(id_perdido) from temp";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Pedido obj_=new Pedido();
               // Pedido obj =new Pedido();
                obj_.setId_pedido(resul.getInt(1));
                obj_.setNombre("o paryillano ");
                lista_pedido.add(obj_); 
            }
           
            resul.close();
             datos_json.put("lista_pedido_p",lista_pedido);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }

  
    
   ///// ----------------------- para autocompletado
    public String lista_datos_pedidos(){
        try {
            List<Pedido> lista_pedido = new ArrayList<>();
            lista_pedido.clear();//limpiar la lista
            String query = "select distinct(id_perdido) from temp";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Pedido obj_=new Pedido();
               // Pedido obj =new Pedido();
                obj_.setId_pedido(resul.getInt(1));
                obj_.setNombre("o paryillano ");
                lista_pedido.add(obj_); 
            }
           
            resul.close();
             datos_json.put("lista_pedido_p",lista_pedido);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    
    
    ///// ----------------------- para traer datos de pedido 
    public String lista_datos(){
        try {
            List<PedidoTemp> lista_pedido = new ArrayList<>();
            lista_pedido.clear();//limpiar la lista
            String query = "select * from  temp where id_perdido = " + getId_pedido_e();
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                PedidoTemp obj_=new PedidoTemp();
               // Pedido obj =new Pedido();
                obj_.setId_temp(resul.getInt(1));
                obj_.setId_perdido(resul.getInt(2));
                obj_.setId_producto(resul.getInt(3));
                obj_.setCantidad(resul.getInt(4));
                obj_.setImporte(resul.getDouble(5));
                obj_.setTotal(resul.getDouble(6));
                obj_.setIgv(resul.getDouble(7));
                obj_.setTipo_documento(resul.getInt(8));
                obj_.setId_cliente(resul.getInt(9));
                obj_.setNombre(resul.getString(10));
                obj_.setDni(resul.getString(11));
                obj_.setSubtototal(resul.getDouble(12));
                lista_pedido.add(obj_); 
            }
           
            resul.close();
             datos_json.put("lst_datos_pedido",lista_pedido);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    } 
    
    
    ///// ----------------------- para realizar venta 
     public String enviar_venta(){
        try {
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_realizar_venta(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            sentencis.setString(1, getNumero_pedido_e()); 
            sentencis.setInt(2, getTipo_pago_e());            
            sentencis.setInt(3, getId_usuario_caja_e());            
            sentencis.setString(4, getTurno_mov_caja_e());
            sentencis.setInt(5, getFlag_tarjeta()); 
           
            sentencis.setString(6, getNumero_voucher_e()); 
            sentencis.setString(7, getSustento_e()); 
            sentencis.setString(8, getBanco_e()); 
            sentencis.setString(9, getTarjeta_e()); 
            sentencis.setInt(10, getId_usuario_vendedor_e()); 
             sentencis.setDouble(11, getPago_con_e()); 
            sentencis.setDouble(12, getVuelto_e()); 
            sentencis.setDouble(13, getMonto_t_producto_e()); 
            sentencis.executeUpdate(); 
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error la insertar : "+ e);
            return "error";
        }
    }
     
     //=====================  listar vendedores para reportes ============================
     public String lista_vendedores_venta(){
        try {
            
            List<Usuario> lista_pedido = new ArrayList<>();
            lista_pedido.clear();//limpiar la lista
            String query = "select us.id_usuario,us.id_tipousuario,nombre_usuario,\n" +
                            "ses.turno_sesion from  sesion ses  inner join \n" +
                            "usuario us on us.id_usuario=ses.id_usuario \n" +
                            "inner join tipo_usuario ro   on ro.id_tipousuario=us.id_tipousuario \n" +
                            "where  ses.fecha_sesion=curdate() and us.id_tipousuario= 2\n" +
                            "group by us.id_usuario; ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Usuario obj_=new Usuario();
               // Pedido obj =new Pedido();
                obj_.setId_usuario(resul.getInt(1));
                obj_.setId_tipousuario(resul.getInt(2));
                obj_.setNombre_persona(resul.getString(3));
                obj_.setTurno(resul.getString(4));
                lista_pedido.add(obj_); 
            }
           
            resul.close();
            datos_json.put("lst_datos_usuario",lista_pedido);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    // ====================================== aperturar caja ======================
     
      public String aperturar_caja(){
        try {
            
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_apertura_caja(?,?,?,?)}");
            sentencis.setDouble(1, getMonto_inicio_e()); 
            sentencis.setString(2, getMotivo_apertura_caja_e());            
            sentencis.setString(3, getTurno_e());            
            sentencis.setInt(4, getId_usuario_e());
            sentencis.executeUpdate(); 
            ResultSet set =sentencis.getResultSet();
            set.next();
            int valor= set.getInt(1);
            
            datos_json.put("resultado",valor);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error la insertar : "+ e);
            return "error";
        }
    }
     
    // ====================================== aperturar caja ======================
     
      public String verificar_aperturar_caja(){
        try {
            
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_verificarapertura_caja(?,?)}");
            sentencis.setString(1, getTurno_e());            
            sentencis.setInt(2, getId_usuario_e());
            sentencis.executeUpdate(); 
            ResultSet set =sentencis.getResultSet();
            set.next();
            int valor= set.getInt(1);
            
            datos_json.put("resultado",valor);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error la insertar : "+ e);
            return "error";
        }
    }
      
      // ====================================== cerrar caja ======================
     
      public String cerrar_caja(){
        try {
            
            CallableStatement sentencis=BaseConexion.getprepareCall("{call cerrar_caja(?,?,?,?)}");
            sentencis.setDouble(1, getMonto_inicio_e()); 
            sentencis.setString(2, getMotivo_apertura_caja_e());            
            sentencis.setString(3, getTurno_e());            
            sentencis.setInt(4, getId_usuario_e());
            sentencis.executeUpdate(); 
            ResultSet set =sentencis.getResultSet();
            set.next();
            int valor= set.getInt(1);
            
            datos_json.put("resultado",valor);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error la insertar : "+ e);
            return "error";
        }
    }  
      
     public String busqueda_provendidos(){
        try {
            String fini = getTxt_fechai();
            String ffin = getTxt_fechaf();            
            String idpro = "";
            if(getCbx_productos() != 0)
            idpro = " and pro.id_producto = '"+getCbx_productos()+"' ";
            
            List<Productos_report> lst= new ArrayList<>();
            lst.clear();
            String query =  " SELECT\n" +
                            "	 pro.descripcion_producto,\n" +
                            "    pro.stock_a_producto,\n" +
                            "    pro.stock_m_producto,    \n" +
                            "	( select sum(pp.cantidad_pedidoproducto) FROM pedido_producto pp WHERE pp.id_producto = pro.id_producto) as 			kantidad , \n" +
                            "    pro.precio_producto    \n" +
                            "FROM \n" +
                            "    producto pro\n" +
                            "    INNER JOIN pedido_producto pp ON pp.id_producto = pro.id_producto\n" +
                            "    INNER JOIN pedido ped ON ped.id_pedido = pp.id_pedido \n" +
                            "WHERE  \n" +
                            "	ped.fecha_pedido BETWEEN '"+ fini +"' and '"+ ffin +"'  "+idpro +"  GROUP by pp.id_producto ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Productos_report obj=new Productos_report();                
                
                obj.setNombre_pro(resul.getString(1));
                obj.setSa(resul.getInt(2));
                obj.setSm(resul.getInt(3));
                obj.setCant(resul.getInt(4));
                obj.setPrecio(resul.getDouble(5));                
                
                lst.add(obj);
                //}
            }
            resul.close();
            datos_json.put("lista_busqueda_provendidos",lst);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }   
    
     
      public String cargar_productos(){      
         try {
            List<Producto> lst_especialidad=new ArrayList<>();
            String sql_generico = "select id_producto, descripcion_producto from producto where estado_producto = 1 and id_categoria != 1";
            ResultSet resul = BaseConexion.getStatement().executeQuery(sql_generico);
            if (resul!=null) {              
                while (resul.next()) {
                    Producto obj=new Producto();
                    obj.setId_producto(resul.getInt(1));
                    obj.setDescripcion_producto(resul.getString(2));
                
                  lst_especialidad.add(obj);
                }
                datos_json.put("lista_productos",lst_especialidad);               
            }           
            resul.close();
            return SUCCESS;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"problemas con enmtradas");
            return "error";
        }
    }
     
     
}
