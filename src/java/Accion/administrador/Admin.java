/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accion.administrador;

import Logica_Negocio.PojosPersonalizados.Factura;
import Logica_Negocio.PojosPersonalizados.Producto;
import Logica_Negocio.PojosPersonalizados.Proveedor;
import Logica_Negocio.PojosPersonalizados.UsuarioLogin; 
import Logica_Negocio.pojos.TipoUsuario;
import Logica_Negocio.pojos.Usuario;
import Logica_Negocio.pojos.Ventas_report;
import Logica_Negocio.pojos.Productos_report;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import conexion.BaseConexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Rene Jose
 */
public class Admin extends ActionSupport{
    List<TipoUsuario> lst_Roles= new ArrayList<>();
    Map<String,Object> datos_json=new HashMap<String,Object>();
    // String para reporte 
    String sentenci_report_sql;
    // id de usuario para actualizar, eliminar
    private int id_persona_e;

    // texrto de busqueda
    private String texto_busqueda;
    private String texto_busqueda_servicio;
    //***********************++++++++ DATOS USUSARIOS
    private String txt_nombrepersona;
    private String txt_documento;
    private String txt_direccion;
    private String txt_celular;
    private String txt_email;
    private String txt_usuario;
    private String txt_password;
    private int cbx_rol_name;
    private int cbx_estado_user;
    
    
    //***********************++++++++ DATOS SERVICIO
    
    private int Txt_id_producto;
    private String Txt_codigo_producto; 
    private String Txt_descripcion_producto; 
    private double Txt_precio_producto;
    private int	Txt_estado_producto;
    
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
    // ******************* Reportes vaaribales 
    public ResultSet rs = null;
    public String submit = null;
    public InputStream fileInputStream;
    public String jasperPath = "";
    public String pdfName = "";
    public String rpt = "";
    //============ resulset
  
    
    //*******++++++
    public List<TipoUsuario> getLst_Roles() {
        return lst_Roles;
    }
    public void setLst_Roles(List<TipoUsuario> lst_Roles) {
        this.lst_Roles = lst_Roles;
    }

    public Map<String, Object> getDatos_json() {
        return datos_json;
    }

    public void setDatos_json(Map<String, Object> datos_json) {
        this.datos_json = datos_json;
    }
    
    
     public String getTexto_busqueda() {
        return texto_busqueda;
    }

    public void setTexto_busqueda(String texto_busqueda) {
        this.texto_busqueda = texto_busqueda;
    }
    
    //**********************************

    public String getTxt_direccion() {
        return txt_direccion;
    }

    public void setTxt_direccion(String txt_direccion) {
        this.txt_direccion = txt_direccion;
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

    public String getTxt_usuario() {
        return txt_usuario;
    }

    public void setTxt_usuario(String txt_usuario) {
        this.txt_usuario = txt_usuario;
    }

    public String getTxt_password() {
        return txt_password;
    }

    public void setTxt_password(String txt_password) {
        this.txt_password = txt_password;
    }

    public int getCbx_rol_name() {
        return cbx_rol_name;
    }

    public void setCbx_rol_name(int cbx_rol_name) {
        this.cbx_rol_name = cbx_rol_name;
    }

    public int getCbx_estado_user() {
        return cbx_estado_user;
    }

    public void setCbx_estado_user(int cbx_estado_user) {
        this.cbx_estado_user = cbx_estado_user;
    }
    
    
    //****************************************************++++++++++++++++++++++++++++++++++++++++

    public int getId_persona_e() {
        return id_persona_e;
    }

    public void setId_persona_e(int id_persona_e) {
        this.id_persona_e = id_persona_e;
    }
    
    //------------------------  GET Y SET SERIVCIOS ?======================

    public String getTexto_busqueda_servicio() {
        return texto_busqueda_servicio;
    }

    public void setTexto_busqueda_servicio(String texto_busqueda_servicio) {
        this.texto_busqueda_servicio = texto_busqueda_servicio;
    }

    public int getTxt_id_producto() {
        return Txt_id_producto;
    }

    public void setTxt_id_producto(int Txt_id_producto) {
        this.Txt_id_producto = Txt_id_producto;
    }

    public String getTxt_codigo_producto() {
        return Txt_codigo_producto;
    }

    public void setTxt_codigo_producto(String Txt_codigo_producto) {
        this.Txt_codigo_producto = Txt_codigo_producto;
    }

    public String getTxt_descripcion_producto() {
        return Txt_descripcion_producto;
    }

    public void setTxt_descripcion_producto(String Txt_descripcion_producto) {
        this.Txt_descripcion_producto = Txt_descripcion_producto;
    }

    public double getTxt_precio_producto() {
        return Txt_precio_producto;
    }

    public void setTxt_precio_producto(double Txt_precio_producto) {
        this.Txt_precio_producto = Txt_precio_producto;
    }

    public int getTxt_estado_producto() {
        return Txt_estado_producto;
    }

    public void setTxt_estado_producto(int Txt_estado_producto) {
        this.Txt_estado_producto = Txt_estado_producto;
    }
    
    public int getCbx_opcion() {
        return cbx_opcion;
    }

    //.............,,,,,,,,,,,,,, get y set compras
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

    
    //------- get and set report ventas

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
    
    
    ///...... prodcutso vendidod s set an get

    public int getCbx_productos() {
        return cbx_productos;
    }

    public void setCbx_productos(int cbx_productos) {
        this.cbx_productos = cbx_productos;
    }
    
    // ..... reportes 

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getJasperPath() {
        return jasperPath;
    }

    public void setJasperPath(String jasperPath) {
        this.jasperPath = jasperPath;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getRpt() {
        return rpt;
    }

    public void setRpt(String rpt) {
        this.rpt = rpt;
    }
    
    
    
    //-------------------------------------------------------------------------
    public String cargar_roles(){      
        // logica de negocio (traer las listar)
         try {
                List<TipoUsuario> lst_especialidad=new ArrayList<>();
            String sql_generico = "select * from tipo_usuario where estado_tipousuario=1 ";
            ResultSet resul = BaseConexion.getStatement().executeQuery(sql_generico);
            if (resul!=null) {
              
                while (resul.next()) {
                    TipoUsuario obj_rol=new TipoUsuario();
                    //preguntar si esta o no eliminado: 1 esta habilitado, 0 elimnado usaurio
                  obj_rol.setId_tipousuario(resul.getInt(1));
                  obj_rol.setDescripcion_tipousuario(resul.getString(2));
                
                  lst_especialidad.add(obj_rol);
                  //JOptionPane.showMessageDialog(null,lst_especialidad);
                }
                datos_json.put("lista_roles",lst_especialidad);
               
            }           
            resul.close();
            return SUCCESS;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"problemas con enmtradas");
            return "error";
        }
    }
    
    //metodos para insertar
    // llamar al procedimiento almacenado
    public String insertar(){
        try {

            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_registrar_usuario( ?, ?, ?, ?, ?)}");
            
            sentencis.setString(1, getTxt_nombrepersona());  
            sentencis.setString(2, getTxt_email());
            sentencis.setInt(3, getCbx_rol_name());
            sentencis.setString(4, getTxt_usuario());
            sentencis.setString(5, getTxt_password());
            
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
     
//    //listar los usaurios
//    public String listar_ususario(){
//        try {
//            List<UsuarioLogin> lst_usuarios= new ArrayList<>();
//            //limpiar la lista
//            lst_usuarios.clear();
//            String query="select per.id_persona, per.nombre_persona, per.documento_persona, per.direccion_persona, per.celular_persona, per.email_persona,    \n" +
//                        "us.id_usuario, us.clave_usuario, us.usuario_usuario, us.estado_usuario, ro.descripcion_tipousuario, ro.id_tipousuario\n" +
//                        "from persona per inner join usuario us on per.id_persona = us.id_persona \n" +
//                        "inner join tipo_usuario ro on us.id_tipousuario = ro.id_tipousuario";
//            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
//            //JOptionPane.showMessageDialog(null,"la ctm ! si llego ");
//            while(resul.next()){
//                // si esta activo no eliminado
//                //if ( resul.getInt(9)==1) {
//                UsuarioLogin obj_user=new UsuarioLogin();
//                
//                obj_user.setId_persona(resul.getInt(1));
//                obj_user.setNombres(resul.getString(2));
//                obj_user.setDocumento(resul.getString(3));
//                obj_user.setDireccion(resul.getString(4));
//                obj_user.setCelular(resul.getString(5));
//                obj_user.setEmail(resul.getString(6));
//                
//                obj_user.setId_usuario(resul.getInt(7));
//                obj_user.setPasswordd(resul.getString(8));                
//                obj_user.setLogin(resul.getString(9));
//                obj_user.setEstado_usuario(resul.getInt(10));
//                
//                obj_user.setTipo_usuario(resul.getString(11));
//                obj_user.setId_tipo_usuario(resul.getInt(12));
//                
//                lst_usuarios.add(obj_user);
//                //}
//            }
//            resul.close();
//             datos_json.put("lista",lst_usuarios);
//            return SUCCESS;
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"error: "+ e);
//        }
//        return "error";
//    }
    
    // consulta filtrada
    public String busqueda_filtrada(){
        try {
            List<UsuarioLogin> lst_usuario= new ArrayList<>();
            //limpiar la lista
            lst_usuario.clear();
            String query=" select  " +
                        "    us.id_usuario, " +
                        "    ro.descripcion_tipousuario, " +
                        "    ro.id_tipousuario, " +
                        "    us.nombre_usuario, " +
                        "    us.email_usuario, " +
                        "    us.clave_usuario, " +
                        "    us.usuario_usuario, " +
                        "    us.estado_usuario " +
                        " from usuario us " +
                        " inner join tipo_usuario ro on ro.id_tipousuario = us.id_tipousuario "+ 
                        " where us.nombre_usuario like '%"+getTexto_busqueda()+"%' order by us.id_usuario desc ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            //JOptionPane.showMessageDialog(null,"la ctm ! si llego ");
            while(resul.next()){
                // si esta activo no eliminado
                //if ( resul.getInt(9)==1) {
                UsuarioLogin obj_user=new UsuarioLogin();                
                
                obj_user.setId_usuario(resul.getInt(1));
                obj_user.setTipo_usuario(resul.getString(2));
                obj_user.setId_tipo_usuario(resul.getInt(3));
                obj_user.setNombres(resul.getString(4));
                obj_user.setEmail(resul.getString(5));                
                obj_user.setPasswordd(resul.getString(6));                
                obj_user.setLogin(resul.getString(7));
                obj_user.setEstado_usuario(resul.getInt(8));
                
                lst_usuario.add(obj_user);
                //}
            }
            resul.close();
             datos_json.put("lista_busqueda",lst_usuario);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    
    //metodos para actualizar
    public String actualizar(){
        try {

            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_actualizar_usuario(?, ?, ?, ?, ?, ?, ?)}");
            sentencis.setString(1, getTxt_nombrepersona()); 
            sentencis.setString(2, getTxt_email());
            sentencis.setInt(3, getCbx_rol_name());
            sentencis.setString(4, getTxt_usuario());
            sentencis.setString(5, getTxt_password());
            sentencis.setInt(6, getId_persona_e());
            sentencis.setInt(7, getCbx_estado_user());
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    
    
    
    
    /// ==================== S E R V I C I O S ===========================>>>>>>>    
    
    //listar serivio
    public String lista_filtrada_servicio(){
        try {
            List<Producto> lst_pro= new ArrayList<>();
            lst_pro.clear();//limpiar la lista
            String query="select id_producto , codigo_producto , descripcion_producto , precio_producto , estado_producto from producto where concat(codigo_producto,' ',descripcion_producto ) like '%"+getTexto_busqueda_servicio()+"%'  and id_categoria = 1 order by id_producto desc";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Producto obj_=new Producto();
                obj_.setId_producto(resul.getInt(1));
                obj_.setCodigo_producto(resul.getString(2));          
                obj_.setDescripcion_producto(resul.getString(3));
                obj_.setPrecio_producto(resul.getDouble(4));
                obj_.setEstado_producto(resul.getInt(5)); 
                lst_pro.add(obj_);
            }
            resul.close();
             datos_json.put("lista_busqueda_servicio",lst_pro);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    
    //metodos para insertar
    public String insertar_servicio(){
        try {
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_registrar_servicio( ?, ?, ? )}");
            
            sentencis.setString(1, getTxt_codigo_producto());    
            sentencis.setString (2, getTxt_descripcion_producto());   
            sentencis.setDouble(3, getTxt_precio_producto());             
            
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    public String actualizar_servicio(){
        try {
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_actualizar_servicio(?, ?, ?, ?, ?)}");
            
            sentencis.setString(1, getTxt_codigo_producto());  
            sentencis.setString (2, getTxt_descripcion_producto());   
            sentencis.setDouble(3, getTxt_precio_producto()); 
            sentencis.setInt(4, getTxt_estado_producto()); 
            sentencis.setInt(5, getTxt_id_producto()); 
            
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
 
    
    
    
    //-------------------------------------------------------------------------
//---------------------------------- REPORTE COMPRAS---------------------------------------
    //-------------------------------------------------------------------------
    public String cargar_proveedores(){      
         try {
            List<Proveedor> lst_especialidad=new ArrayList<>();
            String sql_generico = "select * from proveedor where estado_proveedor = 1 ";
            ResultSet resul = BaseConexion.getStatement().executeQuery(sql_generico);
            if (resul!=null) {              
                while (resul.next()) {
                    Proveedor obj=new Proveedor();
                    obj.setId_proveedor(resul.getInt(1));
                    obj.setNombre_proveedor(resul.getString(2));
                
                  lst_especialidad.add(obj);
                }
                datos_json.put("lista_proveedor",lst_especialidad);               
            }           
            resul.close();
            return SUCCESS;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"problemas con enmtradas");
            return "error";
        }
    }
    //.......
    public String busqueda_compras(){
        try {
            String fini = "";
            String ffin= "";            
            String idpro = "";
            if(getCbx_provee() != 0)
            idpro = " and fac.id_proveedor = '"+getCbx_provee()+"' ";
            if(getCbx_opcion() == 1){//dia
                fini = getTxt_fechai();
                ffin = getTxt_fechai();
            }else if(getCbx_opcion() == 2 ){//mes
                fini = getTxt_anio()+"-"+getCbx_mes()+"-01";
                ffin = getTxt_anio()+"-"+getCbx_mes()+"-31";
            }else if(getCbx_opcion() == 3 ){//anio
                fini = getTxt_anio()+"-01-01";
                ffin = getTxt_anio()+"-12-31";                
            }else{//fechas                
                fini = getTxt_fechai();
                ffin = getTxt_fechaf();
            }            
            
            List<Factura> lst= new ArrayList<>();
            lst.clear();
            String query =  " SELECT \n" +
                            "	  fac.numero_factura,\n" +
                            "     prov.nombre_proveedor,\n" +
                            "     fac.fecha_factura,\n" +
                            "     fac.monto_factura,\n" +
                            "     usu.nombre_usuario\n" +
                            "FROM \n" +
                            "	factura fac\n" +
                            "INNER JOIN proveedor prov on prov.id_proveedor = fac.id_proveedor    \n" +
                            "INNER JOIN usuario usu on usu.id_usuario = fac.id_usuario \n" +
                            "WHERE  \n" +
                            "	fac.fecha_factura BETWEEN '"+ fini +"' and '"+ ffin +"' and fac.estado_factura = 1 "+idpro;
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Factura obj=new Factura();                
                
                obj.setNumero_factura(resul.getString(1));
                obj.setNombre_proveedor(resul.getString(2));
                obj.setFecha_factura(resul.getString(3));
                obj.setMonto_factura(resul.getDouble(4));
                obj.setNombre_usuario(resul.getString(5));                
                
                lst.add(obj);
                //}
            }
            resul.close();
            datos_json.put("lista_busqueda_factura",lst);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    
    
    
    
    
    //-------------------------------------------------------------------------
//---------------------------------- REPORTE VENTAS---------------------------------------
    //-------------------------------------------------------------------------
    public String cargar_vendedores(){      
         try {
            List<Usuario> lst_especialidad=new ArrayList<>();
            String sql_generico = "select id_usuario, nombre_usuario from usuario where id_tipousuario = 2 and estado_usuario = 1 ";
            ResultSet resul = BaseConexion.getStatement().executeQuery(sql_generico);
            if (resul!=null) {              
                while (resul.next()) {
                    Usuario obj=new Usuario();
                    obj.setId_usuario(resul.getInt(1));
                    obj.setNombre_persona(resul.getString(2));
                
                  lst_especialidad.add(obj);
                }
                datos_json.put("lista_usuario",lst_especialidad);               
            }           
            resul.close();
            return SUCCESS;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"problemas con enmtradas");
            return "error";
        }
    }
    //.......
    public String busqueda_ventas(){
        try {
            String fini = "";
            String ffin= "";            
            String idven = "";
            String tipo = "";
            String turno = "";
            if(getCbx_vende()!= 0)
            idven = " and ped.id_usuario = '"+getCbx_vende()+"' ";
            if(getCbx_tipo()!= 0)
            tipo = " and ped.tipo_documento = '"+getCbx_tipo()+"' ";
            if(getCbx_turno()!= 0){
                if(getCbx_turno() == 1) turno = " and ped.hora_pedido BETWEEN  '08:00:00' and  '16:00:00' ";
                else turno = " and ped.hora_pedido BETWEEN  '16:00:01' and  '22:00:00' ";
            }
            
            if(getCbx_opcion() == 1){//dia
                fini = getTxt_fechai();
                ffin = getTxt_fechai();
            }else if(getCbx_opcion() == 2 ){//mes
                fini = getTxt_anio()+"-"+getCbx_mes()+"-01";
                ffin = getTxt_anio()+"-"+getCbx_mes()+"-31";
            }else if(getCbx_opcion() == 3 ){//anio
                fini = getTxt_anio()+"-01-01";
                ffin = getTxt_anio()+"-12-31";                
            }else{//fechas                
                fini = getTxt_fechai();
                ffin = getTxt_fechaf();
            }            
            
            List<Ventas_report> lst= new ArrayList<>();
            lst.clear();
            String query =  " SELECT \n" +
                            "	ped.tipo_documento,\n" +
                            "    usu.nombre_usuario,\n" +
                            "    ped.fecha_pedido,\n" +
                            "    ped.numero_pedido,\n" +
                            "    ped.tipo_pago,\n" +
                            "    ped.monto_total_pedido,\n" +
                            "    ped.hora_pedido,\n" +
                            "    ped.estado_pedido\n" + 
                            "FROM \n" +
                            "	pedido ped\n" +
                            "    INNER JOIN usuario usu ON usu.id_usuario = ped.id_usuario\n" +
                            "WHERE \n" +
                            "	ped.fecha_pedido BETWEEN '"+ fini +"' and '"+ ffin +"' "+idven + " " + tipo + turno;
            
            sentenci_report_sql =query;
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Ventas_report obj=new Ventas_report();
                obj.setTipo_documento(resul.getInt(1));
                obj.setNombre_usuario(resul.getString(2));
                obj.setFecha_pedido(resul.getString(3));
                obj.setNumero_pedido(resul.getString(4));
                obj.setTipo_pago(resul.getInt(5));
                obj.setMonto_total_pedido(resul.getDouble(6));
                obj.setHora_pedido(resul.getString(7));
                obj.setEstado_pedido(resul.getInt(8));  
                lst.add(obj);
                //}
            }
            resul.close();
            datos_json.put("lista_busqueda_ventas",lst);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    
    
    
    
    //-------------------------------------------------------------------------
//---------------------------------- REPORTE PRODUCTOS VENDIDOS---------------------------------------
    //-------------------------------------------------------------------------
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
    //.......
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
 //======================== reprote de Venta exportar pdf  ===============
 //  ==================== ===============================================
    
    public String exportarpdf_ventas() throws SQLException {
        try {
            String fini = "";
            String ffin = "";
            String idven = "";
            String tipo = "";
            String turno = "";
            if (getCbx_vende() != 0) {
                idven = " and ped.id_usuario = '" + getCbx_vende() + "' ";
            }
            if (getCbx_tipo() != 0) {
                tipo = " and ped.tipo_documento = '" + getCbx_tipo() + "' ";
            }
            if (getCbx_turno() != 0) {
                if (getCbx_turno() == 1) {
                    turno = " and ped.hora_pedido BETWEEN  '08:00:00' and  '16:00:00' ";
                } else {
                    turno = " and ped.hora_pedido BETWEEN  '16:00:01' and  '22:00:00' ";
                }
            }

            if (getCbx_opcion() == 1) {//dia
                fini = getTxt_fechai();
                ffin = getTxt_fechai();
            } else if (getCbx_opcion() == 2) {//mes
                fini = getTxt_anio() + "-" + getCbx_mes() + "-01";
                ffin = getTxt_anio() + "-" + getCbx_mes() + "-31";
            } else if (getCbx_opcion() == 3) {//anio
                fini = getTxt_anio() + "-01-01";
                ffin = getTxt_anio() + "-12-31";
            } else {//fechas                
                fini = getTxt_fechai();
                ffin = getTxt_fechaf();
            }

          
                String query = " SELECT \n"
                    + " (case ped.tipo_documento when 1 then 'Boleta' when  2 then 'Factura' end) as tipo_documento,\n"
                    + "    usu.nombre_usuario,\n"
                    + "    ped.fecha_pedido,\n"
                    + "    ped.numero_pedido,\n"
                    + "   (case ped.tipo_pago when 1 then 'Efectivo' when  2 then 'Tarjeta' end) as tipo_pago,\n"
                    + "    ped.monto_total_pedido\n"
                    + "FROM \n"
                    + "	pedido ped\n"
                    + "    INNER JOIN usuario usu ON usu.id_usuario = ped.id_usuario\n"
                    + "WHERE \n"
                    + "	ped.fecha_pedido BETWEEN '" + fini + "' and '" + ffin + "' " + idven + " " + tipo + turno;
           
            
            rs = BaseConexion.getStatement().executeQuery(query);
            jasperPath = ServletActionContext.getServletContext().getRealPath("/reportes");
            pdfName = "ReporteVentaBusqueda";
            rpt = "RVentasFacturasAdMIN.jrxml";
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
            HashMap<String, Object> pm = new HashMap<String, Object>();
//				String logo = jasperPath + "/ws.jpg";
//				pm.put("logo", logo);

            JasperReport jr = JasperCompileManager.compileReport(jasperPath + "/" + rpt);

            JasperPrint jp = JasperFillManager.fillReport(jr, pm, resultSetDataSource);

            JasperExportManager.exportReportToPdfFile(jp, jasperPath + pdfName + ".pdf");

            fileInputStream = new FileInputStream(new File(jasperPath + pdfName + ".pdf"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "puto error");
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    public String getSentenci_report_sql() {
        return sentenci_report_sql;
    }

    public void setSentenci_report_sql(String sentenci_report_sql) {
        this.sentenci_report_sql = sentenci_report_sql;
    }
    
    
    
    
}
