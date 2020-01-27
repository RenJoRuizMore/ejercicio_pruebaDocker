

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accion.almacenero;

import Logica_Negocio.PojosPersonalizados.Factura;
import Logica_Negocio.PojosPersonalizados.Detalle_Factura;
import Logica_Negocio.PojosPersonalizados.UsuarioLogin;
import Logica_Negocio.PojosPersonalizados.Factura;
import Logica_Negocio.PojosPersonalizados.Salida;
import Logica_Negocio.PojosPersonalizados.Producto;
import Logica_Negocio.PojosPersonalizados.Proveedor;
import Logica_Negocio.pojos.TipoUsuario;
import Logica_Negocio.pojos.DetalleSalida;
import Logica_Negocio.pojos.Categoria;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import conexion.BaseConexion;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import Acciongenerico.AccionLoguear;

/**
 *
 * @author Rene Jose
 */
public class Almacen extends ActionSupport{
    List<TipoUsuario> lst_Roles= new ArrayList<>();
    Map<String,Object> datos_json=new HashMap<String,Object>();
    
    static List<DetalleSalida> list_DetalleSalida = new ArrayList<>();// para salidas de almacen
    static List<Detalle_Factura> list_DetalleFactura = new ArrayList<>();// para detallefactura de almacen
    // id de usuario para actualizar, eliminar
    private int id_persona_e;
    
    //FACURA    
    private int id_proveedor_fact;
    private int id_usuario_fact;
    private String txt_numero_fact;
    private String txt_fecha_fact;
    private double txt_monto_fact;
    private int txt_estado_fact;
    
    private int id_factura;
    private int id_detfactura;
    
    //salidas
    private int id_producto_s;
    private int txtcantidad;
    private int txtstock;    
    private String txtcod_producto_s;
    private String txtnombre_producto_s;
    
    private int id_tmp_detsal;
    private int txt_cantidad_p;
    
    private int id_salida;
    private int stado;
    private int id_detsalida;
    
    private int id_Salida;
    private int id_Prodcut;
    private int Cantidad;
    
    private int term;
    
    // texrto de busqueda
    private String texto_busqueda;
    private String texto_busqueda_categoria;
    private String texto_busqueda_pro;
    private String Texto_busqueda_sm;
    private String texto_busqueda_lc;
    
    private String texto_busqueda_factura;
    private String texto_busqueda_salida;
    
    private int cbx_filtro_sm;
    
    private int id_producto_add_listcompra;
    private int id_producto_del_listcompra;
    private int valoradddel;
    private int allproductos;
    private int productservis;
    
    //---- salida
    private String txt_numero_salida;
    private String txtfecha;
    private int id_usuario;
    
    //********* FACTURA COMPRA
    private int id_producto_df;
    private String cod_producto_df;
    private String nombre_producto_df;           
    private int cantidad_df; 
    private double precio_df; 
    private double importe_df;
    
    
    //***********************++++++++ DATOS CATEGORIA
    
    private String txt_descripcion_c;
    private int cbx_estado_c;
    private int id_categoria;
    
    //***********************++++++++ DATOS PRODUCTO
    
    private int Txt_id_producto;
    private String Txt_codigo_producto; 
    private int	Cbx_id_categoria; 	
    private String Txt_descripcion_producto; 
    private double Txt_precio_producto;
    private String Txt_unidades_producto; 
    private int	Txt_stock_producto ;
    private int	Txt_stock_a_producto ; 
    private int	Txt_stock_m_producto ; 
    private int	Txt_stock_minimo_producto; 
    private int	Txt_stock_minimo_mostrador_producto ; 
    private int	Txt_en_mostrador_producto ;
    private int Txt_en_listacompra;        
    private int	Txt_estado_producto;
    
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

    
    

    //******************** GET SET CATEGORIA********************************++++++++++++++++++++++++++++++++++++++++
    

    public String getTxt_descripcion_c() {
        return txt_descripcion_c;
    }

    public void setTxt_descripcion_c(String txt_descripcion_c) {
        this.txt_descripcion_c = txt_descripcion_c;
    }

    public int getCbx_estado_c() {
        return cbx_estado_c;
    }

    public void setCbx_estado_c(int cbx_estado_c) {
        this.cbx_estado_c = cbx_estado_c;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
    
    public String getTexto_busqueda_categoria() {
        return texto_busqueda_categoria;
    }

    public void setTexto_busqueda_categoria(String texto_busqueda_categoria) {
        this.texto_busqueda_categoria = texto_busqueda_categoria;
    }
    
    //******************** GET SET producto********************************++++++++++++++++++++++++++++++++++++++++

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

    public int getCbx_id_categoria() {
        return Cbx_id_categoria;
    }

    public void setCbx_id_categoria(int Cbx_id_categoria) {
        this.Cbx_id_categoria = Cbx_id_categoria;
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

    public String getTxt_unidades_producto() {
        return Txt_unidades_producto;
    }

    public void setTxt_unidades_producto(String Txt_unidades_producto) {
        this.Txt_unidades_producto = Txt_unidades_producto;
    }

    public int getTxt_stock_producto() {
        return Txt_stock_producto;
    }

    public void setTxt_stock_producto(int Txt_stock_producto) {
        this.Txt_stock_producto = Txt_stock_producto;
    }

    public int getTxt_stock_a_producto() {
        return Txt_stock_a_producto;
    }

    public void setTxt_stock_a_producto(int Txt_stock_a_producto) {
        this.Txt_stock_a_producto = Txt_stock_a_producto;
    }

    public int getTxt_stock_m_producto() {
        return Txt_stock_m_producto;
    }

    public void setTxt_stock_m_producto(int Txt_stock_m_producto) {
        this.Txt_stock_m_producto = Txt_stock_m_producto;
    }

    public int getTxt_stock_minimo_producto() {
        return Txt_stock_minimo_producto;
    }

    public void setTxt_stock_minimo_producto(int Txt_stock_minimo_producto) {
        this.Txt_stock_minimo_producto = Txt_stock_minimo_producto;
    }

    public int getTxt_stock_minimo_mostrador_producto() {
        return Txt_stock_minimo_mostrador_producto;
    }

    public void setTxt_stock_minimo_mostrador_producto(int Txt_stock_minimo_mostrador_producto) {
        this.Txt_stock_minimo_mostrador_producto = Txt_stock_minimo_mostrador_producto;
    }

    public int getTxt_en_mostrador_producto() {
        return Txt_en_mostrador_producto;
    }

    public void setTxt_en_mostrador_producto(int Txt_en_mostrador_producto) {
        this.Txt_en_mostrador_producto = Txt_en_mostrador_producto;
    }

    public int getTxt_en_listacompra() {
        return Txt_en_listacompra;
    }

    public void setTxt_en_listacompra(int Txt_en_listacompra) {
        this.Txt_en_listacompra = Txt_en_listacompra;
    }

    public int getTxt_estado_producto() {
        return Txt_estado_producto;
    }

    public void setTxt_estado_producto(int Txt_estado_producto) {
        this.Txt_estado_producto = Txt_estado_producto;
    }

    
    public String getTexto_busqueda_pro() {
        return texto_busqueda_pro;
    }

    public void setTexto_busqueda_pro(String texto_busqueda_pro) {
        this.texto_busqueda_pro = texto_busqueda_pro;
    }

    public int getAllproductos() {
        return allproductos;
    }

    public void setAllproductos(int allproductos) {
        this.allproductos = allproductos;
    }

    public int getProductservis() {
        return productservis;
    }

    public void setProductservis(int productservis) {
        this.productservis = productservis;
    }

    
    //******************** GET SET stock minimo sm ********************************++++++++++++++++++++++++++++++++++++++++
    
    public String getTexto_busqueda_sm() {
        return Texto_busqueda_sm;
    }

    public void setTexto_busqueda_sm(String Texto_busqueda_sm) {
        this.Texto_busqueda_sm = Texto_busqueda_sm;
    }

    public int getCbx_filtro_sm() {
        return cbx_filtro_sm;
    }

    public void setCbx_filtro_sm(int cbx_filtro_sm) {
        this.cbx_filtro_sm = cbx_filtro_sm;
    }

    public int getId_producto_add_listcompra() {
        return id_producto_add_listcompra;
    }

    public void setId_producto_add_listcompra(int id_producto_add_listcompra) {
        this.id_producto_add_listcompra = id_producto_add_listcompra;
    }

    public int getId_producto_del_listcompra() {
        return id_producto_del_listcompra;
    }

    public void setId_producto_del_listcompra(int id_producto_del_listcompra) {
        this.id_producto_del_listcompra = id_producto_del_listcompra;
    }

    public int getValoradddel() {
        return valoradddel;
    }

    public void setValoradddel(int valoradddel) {
        this.valoradddel = valoradddel;
    }
    
    
    //******************** GET SET lista compra lc ********************************++++++++++++++++++++++++++++++++++++++++

    public String getTexto_busqueda_lc() {
        return texto_busqueda_lc;
    }

    public void setTexto_busqueda_lc(String texto_busqueda_lc) {
        this.texto_busqueda_lc = texto_busqueda_lc;
    }
    
    
    
    //***********************++++++++  GET Y SET factura

    public String getTexto_busqueda_factura() {
        return texto_busqueda_factura;
    }

    public void setTexto_busqueda_factura(String texto_busqueda_factura) {
        this.texto_busqueda_factura = texto_busqueda_factura;
    }
    
    //***********************++++++++  GET Y SET factura

    public String getTexto_busqueda_salida() {
        return texto_busqueda_salida;
    }

    public void setTexto_busqueda_salida(String texto_busqueda_salida) {
        this.texto_busqueda_salida = texto_busqueda_salida;
    }
    
    
    //====================000 GET Y SET DETALLE-SALIDA

    public List<DetalleSalida> getList_DetalleSalida() {
        return list_DetalleSalida;
    }

    public void setList_DetalleSalida(List<DetalleSalida> list_DetalleSalida) {
        this.list_DetalleSalida = list_DetalleSalida;
    }

    public int getId_producto_s() {
        return id_producto_s;
    }

    public void setId_producto_s(int id_producto_s) {
        this.id_producto_s = id_producto_s;
    }

    public int getTxtcantidad() {
        return txtcantidad;
    }

    public void setTxtcantidad(int txtcantidad) {
        this.txtcantidad = txtcantidad;
    }

    public int getTxtstock() {
        return txtstock;
    }

    public void setTxtstock(int txtstock) {
        this.txtstock = txtstock;
    }

    public String getTxtcod_producto_s() {
        return txtcod_producto_s;
    }

    public void setTxtcod_producto_s(String txtcod_producto_s) {
        this.txtcod_producto_s = txtcod_producto_s;
    }

    public String getTxtnombre_producto_s() {
        return txtnombre_producto_s;
    }

    public void setTxtnombre_producto_s(String txtnombre_producto_s) {
        this.txtnombre_producto_s = txtnombre_producto_s;
    }

    public int getId_tmp_detsal() {
        return id_tmp_detsal;
    }

    public void setId_tmp_detsal(int id_tmp_detsal) {
        this.id_tmp_detsal = id_tmp_detsal;
    }

    public int getTxt_cantidad_p() {
        return txt_cantidad_p;
    }

    public void setTxt_cantidad_p(int txt_cantidad_p) {
        this.txt_cantidad_p = txt_cantidad_p;
    }
    
    //::::::::::::::::::::::::::::::::::::::::::  GET Y SETT DE SALIDA ************************************:+:

    public String getTxt_numero_salida() {
        return txt_numero_salida;
    }

    public void setTxt_numero_salida(String txt_numero_salida) {
        this.txt_numero_salida = txt_numero_salida;
    }

    public String getTxtfecha() {
        return txtfecha;
    }

    public void setTxtfecha(String txtfecha) {
        this.txtfecha = txtfecha;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    public int getId_detsalida() {
        return id_detsalida;
    }

    public void setId_detsalida(int id_detsalida) {
        this.id_detsalida = id_detsalida;
    }

    public int getId_Salida() {
        return id_Salida;
    }

    public void setId_Salida(int id_Salida) {
        this.id_Salida = id_Salida;
    }

    public int getId_Prodcut() {
        return id_Prodcut;
    }

    public void setId_Prodcut(int id_Prodcut) {
        this.id_Prodcut = id_Prodcut;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public int getStado() {
        return stado;
    }

    public void setStado(int stado) {
        this.stado = stado;
    }
    
//-------------------------- < GTE AN SET DE DETALLE FACTURA

    public static List<Detalle_Factura> getList_DetalleFactura() {
        return list_DetalleFactura;
    }

    public static void setList_DetalleFactura(List<Detalle_Factura> list_DetalleFactura) {
        Almacen.list_DetalleFactura = list_DetalleFactura;
    }

    public int getId_producto_df() {
        return id_producto_df;
    }

    public void setId_producto_df(int id_producto_df) {
        this.id_producto_df = id_producto_df;
    }

    public String getCod_producto_df() {
        return cod_producto_df;
    }

    public void setCod_producto_df(String cod_producto_df) {
        this.cod_producto_df = cod_producto_df;
    }

    public String getNombre_producto_df() {
        return nombre_producto_df;
    }

    public void setNombre_producto_df(String nombre_producto_df) {
        this.nombre_producto_df = nombre_producto_df;
    }

    public int getCantidad_df() {
        return cantidad_df;
    }

    public void setCantidad_df(int cantidad_df) {
        this.cantidad_df = cantidad_df;
    }

    public double getPrecio_df() {
        return precio_df;
    }

    public void setPrecio_df(double precio_df) {
        this.precio_df = precio_df;
    }

    public double getImporte_df() {
        return importe_df;
    }

    public void setImporte_df(double importe_df) {
        this.importe_df = importe_df;
    }

    public int getId_proveedor_fact() {
        return id_proveedor_fact;
    }

    public void setId_proveedor_fact(int id_proveedor_fact) {
        this.id_proveedor_fact = id_proveedor_fact;
    }

    public int getId_usuario_fact() {
        return id_usuario_fact;
    }

    public void setId_usuario_fact(int id_usuario_fact) {
        this.id_usuario_fact = id_usuario_fact;
    }

    public String getTxt_numero_fact() {
        return txt_numero_fact;
    }

    public void setTxt_numero_fact(String txt_numero_fact) {
        this.txt_numero_fact = txt_numero_fact;
    }

    public String getTxt_fecha_fact() {
        return txt_fecha_fact;
    }

    public void setTxt_fecha_fact(String txt_fecha_fact) {
        this.txt_fecha_fact = txt_fecha_fact;
    }

    public double getTxt_monto_fact() {
        return txt_monto_fact;
    }

    public void setTxt_monto_fact(double txt_monto_fact) {
        this.txt_monto_fact = txt_monto_fact;
    }

    public int getTxt_estado_fact() {
        return txt_estado_fact;
    }

    public void setTxt_estado_fact(int txt_estado_fact) {
        this.txt_estado_fact = txt_estado_fact;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_detfactura() {
        return id_detfactura;
    }

    public void setId_detfactura(int id_detfactura) {
        this.id_detfactura = id_detfactura;
    }
    
    
    
    
//::::::::::::::::::::::::::::::::::::::::::  C A T E G O R I A ************************************:+:
    
    //listar CATEGORIA
    public String lista_filtrada_categoria(){
        try {
            List<Categoria> lst_categoria= new ArrayList<>();
            //limpiar la lista
            lst_categoria.clear();
            String query="select id_categoria, descripcion_categoria,estado_categoria \n" +
                        "from categoria where descripcion_categoria like '%"+getTexto_busqueda_categoria()+"%' \n"
                        + " and id_categoria !=1 order by id_categoria desc";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Categoria obj_cat=new Categoria();
                
                obj_cat.setId_categoria(resul.getInt(1));
                obj_cat.setDescripcion_categoria(resul.getString(2));
                obj_cat.setEstado_categoria(resul.getInt(3));
                
                lst_categoria.add(obj_cat);
            }
            resul.close();
             datos_json.put("lista_busqueda_categoria",lst_categoria);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    
    //metodos para insertar
    public String insertar_categoria(){
        try {
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_registrar_categoria(?)}");
            sentencis.setString(1, getTxt_descripcion_c());            
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    //metodos para actualizar
    public String actualizar_categoria(){
        try {
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_actualizar_categoria(?, ?, ?)}");
            sentencis.setString(1, getTxt_descripcion_c());  
            sentencis.setInt(2, getCbx_estado_c());
            sentencis.setInt(3, getId_categoria());
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    
    //::::::::::::::::::::::::::::::::::::::::::  P R O D U C T O ************************************:+:
    
        
    public String cargar_categorias(){      
        // logica de negocio (traer las listar)
         try {
            List<Categoria> lst_categoria=new ArrayList<>();
            String sql_generico = "select * from categoria where estado_categoria=1 and id_categoria !=1";
            ResultSet resul = BaseConexion.getStatement().executeQuery(sql_generico);
            if (resul!=null) {
              
                while (resul.next()) {
                    Categoria obj_rol=new Categoria();
                    obj_rol.setId_categoria(resul.getInt(1));
                    obj_rol.setDescripcion_categoria(resul.getString(2));                
                    lst_categoria.add(obj_rol);
                }
                datos_json.put("lista_categorias",lst_categoria);               
            }           
            resul.close();
            return SUCCESS;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"problemas con enmtradas");
            return "error";
        }
    }
    
    //listar prodcuto
    public String lista_filtrada_producto(){
        try {
            String todos="";
            String productservis=" and pro.id_categoria != 1 ";
            if(getAllproductos()==1){todos=" and estado_producto = 1 ";}
            if(getProductservis()==1){productservis="";}
            List<Producto> lst_pro= new ArrayList<>();
            lst_pro.clear();//limpiar la lista
            String query="select * from producto pro inner join categoria cat on pro.id_categoria = cat.id_categoria \n" +
                        "where concat(codigo_producto,' ',descripcion_producto ) like '%"+getTexto_busqueda_pro()+"%' \n"
                        + productservis+ "  "+todos+" order by pro.id_producto desc ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Producto obj_=new Producto();
                obj_.setId_producto(resul.getInt(1));
                obj_.setCodigo_producto(resul.getString(2));
                obj_.setId_categoria(resul.getInt(3));                
                obj_.setDescripcion_producto(resul.getString(4));
                obj_.setPrecio_producto(resul.getDouble(5));             
                obj_.setUnidades_producto(resul.getString(6));
                obj_.setStock_a_producto(resul.getInt(7));
                obj_.setStock_m_producto(resul.getInt(8));
                obj_.setStock_minimo_producto(resul.getInt(9));
                obj_.setStock_minimo_mostrador_producto(resul.getInt(10));
                obj_.setEn_mostrador_producto(resul.getInt(11));
                obj_.setEn_listacompra(resul.getInt(12));
                obj_.setEstado_producto(resul.getInt(13)); 
                obj_.setDesc_categoria(resul.getString(15));
                lst_pro.add(obj_);
            }
            resul.close();
             datos_json.put("lista_busqueda_producto",lst_pro);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    
    //metodos para insertar
    public String insertar_producto(){
        try {
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_registrar_producto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            sentencis.setString(1, getTxt_codigo_producto());            
            sentencis.setInt(2, getCbx_id_categoria());   
            sentencis.setString (3, getTxt_descripcion_producto());   
            sentencis.setDouble(4, getTxt_precio_producto()); 
            sentencis.setString(5, getTxt_unidades_producto());
            sentencis.setInt(6, getTxt_stock_a_producto()); 
            sentencis.setInt(7, getTxt_stock_m_producto()); 
            sentencis.setInt(8, getTxt_stock_minimo_producto()); 
            sentencis.setInt(9, getTxt_stock_minimo_mostrador_producto()); 
            sentencis.setInt(10, getTxt_en_mostrador_producto());            
            
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    public String actualizar_producto(){
        try {
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_actualizar_producto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            sentencis.setString(1, getTxt_codigo_producto());            
            sentencis.setInt(2, getCbx_id_categoria());   
            sentencis.setString (3, getTxt_descripcion_producto());   
            sentencis.setDouble(4, getTxt_precio_producto()); 
            sentencis.setString(5, getTxt_unidades_producto());  
            sentencis.setInt(6, getTxt_stock_a_producto()); 
            sentencis.setInt(7, getTxt_stock_m_producto()); 
            sentencis.setInt(8, getTxt_stock_minimo_producto()); 
            sentencis.setInt(9, getTxt_stock_minimo_mostrador_producto()); 
            sentencis.setInt(10, getTxt_en_mostrador_producto()); 
            sentencis.setInt(11, getTxt_estado_producto()); 
            sentencis.setInt(12, getTxt_id_producto()); 
            
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    
    //::::::::::::::::::::::::::::::::::::::::::  S T O C K    M I N I M O  ************************************:+:
    
    
    //listar stock minimo
    public String lista_stock_minimo(){
        try {
            List<Producto> lst_pro= new ArrayList<>();
            lst_pro.clear();//limpiar la lista
            String columnas=" pro.stock_minimo_producto, pro.stock_a_producto ";
            String filtro =" and pro.stock_a_producto <= pro.stock_minimo_producto ";
            if(getCbx_filtro_sm()== 2){
                columnas=" pro.stock_minimo_mostrador_producto, pro.stock_m_producto ";
                filtro =" and pro.stock_m_producto <= pro.stock_minimo_mostrador_producto and pro.en_mostrador_producto = 1";
            }
            
            String query="select pro.id_producto, cat.descripcion_categoria, pro.codigo_producto,pro.descripcion_producto, "+columnas+" \n"
                    + " from producto pro inner join categoria cat on pro.id_categoria = cat.id_categoria where concat(codigo_producto,' ',descripcion_producto ) like '%"+getTexto_busqueda_sm()+"%' \n"
                    + filtro + " and pro.en_listacompra_producto = 0 and pro.estado_producto = 1 and pro.id_categoria != 1  order by descripcion_producto asc";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Producto obj_=new Producto();
                obj_.setId_producto(resul.getInt(1));                 
                obj_.setDesc_categoria(resul.getString(2));
                obj_.setCodigo_producto(resul.getString(3));             
                obj_.setDescripcion_producto(resul.getString(4));
                if(getCbx_filtro_sm()== 1){
                    obj_.setStock_minimo_producto(resul.getInt(5));
                    obj_.setStock_a_producto(resul.getInt(6));
                }else{
                    obj_.setStock_minimo_mostrador_producto(resul.getInt(5));
                    obj_.setStock_m_producto(resul.getInt(6));
                }
                lst_pro.add(obj_);
            }
            resul.close();
             datos_json.put("lista_busqueda_sm",lst_pro);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    
    public String add_del_listacompra(){
        try {
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_add_del_listacompra( ?, ?)}");
            
            sentencis.setInt(1, getValoradddel()); 
            sentencis.setInt(2, getId_producto_add_listcompra()); 
            
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    
    //::::::::::::::::::::::::::::::::::::::::::  L I S T A    C O M P R A  ************************************:+:
    

    public String lista_lista_compra(){
        try {
            List<Producto> lst_pro= new ArrayList<>();
            lst_pro.clear();//limpiar la lista
                       
            String query="select pro.id_producto, cat.descripcion_categoria, pro.codigo_producto,pro.descripcion_producto, pro.stock_minimo_producto, pro.stock_a_producto \n"
                    + " from producto pro inner join categoria cat on pro.id_categoria = cat.id_categoria where concat(codigo_producto,' ',descripcion_producto ) like '%"+getTexto_busqueda_lc()+"%' \n"
                    + " and pro.en_listacompra_producto = 1 and pro.estado_producto = 1 and pro.id_categoria != 1  order by descripcion_producto asc";
            
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Producto obj_=new Producto();
                obj_.setId_producto(resul.getInt(1));                 
                obj_.setDesc_categoria(resul.getString(2));
                obj_.setCodigo_producto(resul.getString(3));             
                obj_.setDescripcion_producto(resul.getString(4));
                obj_.setStock_minimo_producto(resul.getInt(5));
                obj_.setStock_a_producto(resul.getInt(6));
                lst_pro.add(obj_);
            }
            resul.close();
             datos_json.put("lista_busqueda_lc",lst_pro);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    
    
     //::::::::::::::::::::::::::::::::::::::::::  P R O V E E D O R  ************************************:+:
    
    // consulta filtrada
    public String busqueda_filtrada_proveedor(){
        try {
            List<Proveedor> lst_usuario= new ArrayList<>();
            //limpiar la lista
            lst_usuario.clear();
            String query="select * from proveedor  \n" +
                        " where nombre_proveedor like '%"+getTexto_busqueda()+"%' order by id_proveedor desc ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            //JOptionPane.showMessageDialog(null,"la ctm ! si llego ");
            while(resul.next()){
                // si esta activo no eliminado
                //if ( resul.getInt(9)==1) {
                Proveedor obj_user=new Proveedor();
                
                obj_user.setId_proveedor(resul.getInt(1));
                obj_user.setNombre_proveedor(resul.getString(2));
                obj_user.setRuc_proveedor(resul.getString(3));
                obj_user.setDireccion_proveedor(resul.getString(4));
                obj_user.setTelefono_proveedor(resul.getString(5));
                obj_user.setEmail_proveedor(resul.getString(6));
                obj_user.setEstado_proveedor(resul.getInt(7));                
                
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
    
    //metodos para insertar
    // llamar al procedimiento almacenado
    public String insertar_proveedor(){
        try {

            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_registrar_proveedor(?, ?, ?, ?, ?)}");
            sentencis.setString(1, getTxt_nombrepersona());            
            sentencis.setString(2, getTxt_documento());            
            sentencis.setString(3, getTxt_direccion());            
            sentencis.setString(4, getTxt_celular());
            sentencis.setString(5, getTxt_email());
            
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    //metodos para actualizar
    public String actualizar_proveedor(){
        try {

            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_actualizar_proveedor(?, ?, ?, ?, ?, ?, ?)}");
            sentencis.setString(1, getTxt_nombrepersona());            
            sentencis.setString(2, getTxt_documento());            
            sentencis.setString(3, getTxt_direccion());            
            sentencis.setString(4, getTxt_celular());
            sentencis.setString(5, getTxt_email());
            sentencis.setInt(6, getId_persona_e());
            sentencis.setInt(7, getCbx_estado_user());
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    
    
    //::::::::::::::::::::::::::::::::::::::::::   F A C T U R A   C O M P R A   ************************************:+       
    /// ==================== F A C T U R A ===========================>>>>>>>    
    
    //listar FACTURA
    public String lista_filtrada_factura(){
        try {
            List<Factura> lst_factura= new ArrayList<>();
            lst_factura.clear();//limpiar la lista
            String query=" SELECT \n" +
                        "	fact.id_factura,\n" +
                        "    fact.id_proveedor,\n" +
                        "    fact.numero_factura,\n" +
                        "    prov.nombre_proveedor,\n" +
                        "    fact.monto_factura,\n" +
                        "    fact.fecha_factura,\n" +
                        "    usu.nombre_usuario,\n" +
                        "    fact.estado_factura\n" +
                        "FROM \n" +
                        "	factura fact\n" +
                        "    INNER JOIN proveedor prov on prov.id_proveedor = fact.id_proveedor\n" +
                        "    INNER JOIN usuario usu on usu.id_usuario = fact.id_usuario \n"+
                        " where concat(fact.numero_factura,' ',prov.nombre_proveedor ) like '%"+getTexto_busqueda_factura()+"%'  order by fact.id_factura desc ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Factura obj_=new Factura();
                obj_.setId_factura(resul.getInt(1));
                obj_.setId_proveedor(resul.getInt(2));
                obj_.setNumero_factura(resul.getString(3));
                obj_.setNombre_proveedor(resul.getString(4));
                obj_.setMonto_factura(resul.getDouble(5));
                obj_.setFecha_factura(resul.getString(6));
                obj_.setNombre_usuario(resul.getString(7));
                obj_.setEstado_factura(resul.getInt(8)); 
                lst_factura.add(obj_);
            }
            resul.close();
             datos_json.put("lista_busqueda_factura",lst_factura);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    
    //---------------------- DETALLE   F A C T U R A   TMP ---------------------
    public String limpiar_detallefacturatmp(){
        try {
            list_DetalleFactura.clear();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    //listar 
    public String listar_detallefacturatmp(){
        try {            
            datos_json.put("lista_detfactura",list_DetalleFactura);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    public String add_listadetallefactura(){
        try {
            
            Detalle_Factura obj_=new Detalle_Factura();
            obj_.setId_producto(getId_producto_df());
            obj_.setCod_producto(getCod_producto_df());
            obj_.setNombre_producto(getNombre_producto_df());           
            obj_.setCantidad_detallefactura( getCantidad_df()); 
            obj_.setPrecio_detallefactura( getPrecio_df()); 
            obj_.setImporte_detallefactura(getImporte_df());         
            
            list_DetalleFactura.add(obj_);
            
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    public String del_listadetallefactura(){
        try {
            
            list_DetalleFactura.remove(getId_tmp_detsal());
            
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    // REGISTRAR FACTURA COMPRAAAAAA
    
    public String insertar_facturacompra(){
        try {
            
            //int id_usuario=3; //fsdfskjfsfhsfkj hskfhskdjfhskjf
            int id_factura=1;
            
            CallableStatement sentencif=BaseConexion.getprepareCall("{call sp_registrar_factura(?, ?, ?, ?, ?)}");
            
            sentencif.setInt(1, getId_proveedor_fact()); 
            sentencif.setInt(2, getId_usuario_fact());             
            sentencif.setString(3, getTxt_numero_fact()); 
            sentencif.setString(4, getTxt_fecha_fact());
            sentencif.setDouble(5, getTxt_monto_fact());
            
            sentencif.executeUpdate();  
            
            
            
            String query=" SELECT \n" +
                         "      id_factura \n" +
                         " FROM factura order by id_factura DESC LIMIT 1  ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                id_factura = resul.getInt(1);
            }
            resul.close();
            
            
            int idpro=1,cant=1;
            double importe=0,precio=0;
            for(int i=0 ; i < list_DetalleFactura.size() ; i++){
                
                idpro=list_DetalleFactura.get(i).getId_producto();
                cant=list_DetalleFactura.get(i).getCantidad_detallefactura();
                precio=list_DetalleFactura.get(i).getPrecio_detallefactura();
                importe=list_DetalleFactura.get(i).getImporte_detallefactura();
                
                CallableStatement sentencisx=BaseConexion.getprepareCall("{call sp_registrar_detallfactura(?, ?, ?, ?, ?)}");
                sentencisx.setInt(1,id_factura);                         
                sentencisx.setInt(2, idpro);
                sentencisx.setInt(3, cant);
                sentencisx.setDouble(4, precio);
                sentencisx.setDouble(5, importe);
                sentencisx.executeUpdate();
                
                CallableStatement sentencisr=BaseConexion.getprepareCall("{call sp_aumentar_stock(?, ?)}");
                sentencisr.setInt(1, cant);  
                sentencisr.setInt(2, idpro);
                sentencisr.executeUpdate();
                
                CallableStatement sentenciclc=BaseConexion.getprepareCall("{call sp_vaciar_listcompra( ? )}");
                sentenciclc.setInt(1, idpro);
                sentenciclc.executeUpdate();
                
                
            }
            
            list_DetalleFactura.clear();
            
            
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    public String lista_filtrada_detallefactura(){
        try {
            List<Detalle_Factura> lst_= new ArrayList<>();
            lst_.clear();//limpiar la lista
            String query =  "SELECT \n" +
                            "	 df.id_detallefactura,\n" +
                            "    df.id_factura,\n" +
                            "    df.id_producto,\n" +
                            "    pro.codigo_producto,\n" +
                            "    pro.descripcion_producto,\n" +
                            "    df.cantidad_detallefactura,\n" +
                            "    df.precio_detallefactura,\n" +
                            "    df.importe_detallefactural\n" +
                            "FROM \n" +
                            "	detalle_factura df\n" +
                            "    INNER JOIN producto pro on pro.id_producto = df.id_producto\n" +
                            "WHERE \n" +
                            "	df.id_factura = '"+getId_factura()+"' ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Detalle_Factura obj_=new Detalle_Factura();
                obj_.setId_detallefactura(resul.getInt(1));
                obj_.setId_factura(resul.getInt(2));          
                obj_.setId_producto(resul.getInt(3));
                obj_.setCod_producto(resul.getString(4));
                obj_.setNombre_producto(resul.getString(5));
                obj_.setCantidad_detallefactura(resul.getInt(6));
                obj_.setPrecio_detallefactura(resul.getDouble(7));
                obj_.setImporte_detallefactura(resul.getDouble(8));
                lst_.add(obj_);
            }
            resul.close();
             datos_json.put("lista_busqueda_detallefactura",lst_);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    // Eliminar detallesalida al procedimiento almacenado
    public String eliminar_detallefactura(){
        try {

            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_eliminar_detallefactura( ? )}");
            sentencis.setInt(1, getId_detfactura());
            
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    public String insertar_detallefactura(){
        try {
            
            //int id_usuario=3; //fsdfskjfsfhsfkj hskfhskdjfhskjf
            ///int id_factura=1;
            
                CallableStatement sentencisx=BaseConexion.getprepareCall("{call sp_registrar_detallfactura(?, ?, ?, ?, ?)}");
                sentencisx.setInt(1,getId_factura());                         
                sentencisx.setInt(2, getId_producto_df());
                sentencisx.setInt(3, getCantidad_df());
                sentencisx.setDouble(4, getPrecio_df());
                sentencisx.setDouble(5, getImporte_df());
                sentencisx.executeUpdate();
                
            
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }  
    public String actualizar_factura(){
        try {

            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_actualizar_factura(?, ?, ? ,?, ?, ?)}");
            
            sentencis.setInt(1, getId_proveedor_fact());             
            sentencis.setString(2, getTxt_numero_fact()); 
            sentencis.setString(3, getTxt_fecha_fact());
            sentencis.setDouble(4, getTxt_monto_fact());            
            sentencis.setInt(5, getStado());
            sentencis.setInt(6, getId_factura());
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    //::::::::::::::::::::::::::::::::::::::::::   S A L I D A S  ************************************:+       
    /// ==================== salidas ===========================>>>>>>>    
    
    
    //OBTENER NUMERO SALIDA 
    public String get_numerosalida(){
        try {
            List<Salida> lst_= new ArrayList<>();
            lst_.clear();//limpiar la lista
            String query=" SELECT \n" +
                         "      numero_salida \n" +
                         " FROM salida order by id_salida DESC LIMIT 1  ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Salida obj_=new Salida();;
                obj_.setNumero_salida(resul.getString(1));          
                lst_.add(obj_);
            }
            resul.close();
             datos_json.put("codigo_salida",lst_);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    
    //metodos para actualizar
    public String add_listadetallesalida(){
        try {
            
            DetalleSalida obj_=new DetalleSalida();
            obj_.setId_producto(getId_producto_s());
            obj_.setCod_producto(getTxtcod_producto_s());
            obj_.setNombre_producto(getTxtnombre_producto_s());
            obj_.setStock(getTxtstock());            
            obj_.setCantidad_detallesalida( getTxtcantidad());
                
            list_DetalleSalida.add(obj_);
            
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }    
    public String del_listadetallesalida(){
        try {
            
            list_DetalleSalida.remove(getId_tmp_detsal());
            
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }    
    //listar 
    public String listar_detallesalidatmp(){
        try {
            
            datos_json.put("lista_detsalida",list_DetalleSalida);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    //metodos para actualizar
    public String edit_detallesalidatmp(){
        try {

            list_DetalleSalida.get(getId_tmp_detsal()).setCantidad_detallesalida(getTxt_cantidad_p());
            
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    public String limpiar_detallesalidatmp(){
        try {
            list_DetalleSalida.clear();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
    public String insertar_salida(){
        try {
            
            int id_usuario = AccionLoguear.obj_user.getId_usuario();  //fsdfskjfsfhsfkj hskfhskdjfhskjf
            int id_pedido=1;
            
            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_registrar_salida(?, ?, ?)}");
            sentencis.setString(1, getTxt_numero_salida());                         
            sentencis.setInt(2, id_usuario);
            sentencis.setString(3, getTxtfecha());
            sentencis.executeUpdate();
            
            
            
            String query=" SELECT \n" +
                         "      id_salida \n" +
                         " FROM salida order by id_salida DESC LIMIT 1  ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                id_pedido = resul.getInt(1);
            }
            resul.close();
            
            
            int idpro=1,cant=1;
            for(int i=0 ; i < list_DetalleSalida.size() ; i++){
                
                idpro=list_DetalleSalida.get(i).getId_producto();
                cant=list_DetalleSalida.get(i).getCantidad_detallesalida();
                
                CallableStatement sentencisx=BaseConexion.getprepareCall("{call sp_registrar_detallesalida(?, ?, ?)}");
                sentencisx.setInt(1,id_pedido);                         
                sentencisx.setInt(2, idpro);
                sentencisx.setInt(3, cant);
                sentencisx.executeUpdate();
                
                CallableStatement sentencisr=BaseConexion.getprepareCall("{call sp_reducir_stock(?, ?)}");
                sentencisr.setInt(1, cant);  
                sentencisr.setInt(2, idpro);
                sentencisr.executeUpdate();
                
                CallableStatement sentencisrx=BaseConexion.getprepareCall("{call sp_aumentar_stock_mos(?, ?)}");
                sentencisrx.setInt(1, cant);  
                sentencisrx.setInt(2, idpro);
                sentencisrx.executeUpdate();
                
               
            
            }
                       
            list_DetalleSalida.clear();
            
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------
    //listar SALIDASssssssssssssssssssssssssssssssssssssssssssssss
    public String lista_filtrada_salida(){
        try {
            List<Salida> lst_= new ArrayList<>();
            lst_.clear();//limpiar la lista
            String query="SELECT   \n" +
"                               sa.id_salida , \n" +
"                               sa.numero_salida , \n" +
"                         	sa.fecha_salida  , \n" +
"                               (select sum(ds.cantidad_detallesalida) from detalle_salida ds WHERE ds.id_salida = sa.id_salida) as cantidad_pro, \n" +
"                               us.nombre_usuario, \n" +
"                               sa.estado_salida \n" +
"                          FROM \n" +
"                         	salida sa \n" +
"                               INNER JOIN usuario us on us.id_usuario = sa.id_usuario \n" +
"                        where sa.numero_salida like '%"+getTexto_busqueda_salida()+"%' order by sa.id_salida desc";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Salida obj_=new Salida();
                obj_.setId_salida(resul.getInt(1));
                obj_.setNumero_salida(resul.getString(2));
                obj_.setFecha_salida(resul.getString(3));
                obj_.setCantidad(resul.getInt(4));
                obj_.setNombre_usuario(resul.getString(5));
                obj_.setEstado_salida(resul.getInt(6)); 
                lst_.add(obj_);
            }
            resul.close();
             datos_json.put("lista_busqueda_salida",lst_);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    
    public String lista_filtrada_detallesalida(){
        try {
            List<DetalleSalida> lst_= new ArrayList<>();
            lst_.clear();//limpiar la lista
            String query =  " SELECT \n" +
                            "	 dsa.id_detallesalida,\n" +
                            "    dsa.id_salida,\n" +
                            "    dsa.id_producto,\n" +
                            "    pro.codigo_producto,\n" +
                            "    pro.descripcion_producto,\n" +
                            "    dsa.cantidad_detallesalida\n" +
                            "FROM \n" +
                            "	 detalle_salida dsa\n" +
                            "    INNER JOIN producto pro on pro.id_producto = dsa.id_producto\n" +
                            "WHERE \n" +
                            "	dsa.id_salida = '"+getId_salida()+"' ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                DetalleSalida obj_=new DetalleSalida();
                obj_.setId_detallesalida(resul.getInt(1));
                obj_.setId_salida(resul.getInt(2));          
                obj_.setId_producto(resul.getInt(3));
                obj_.setCod_producto(resul.getString(4));
                obj_.setNombre_producto(resul.getString(5));
                obj_.setCantidad_detallesalida(resul.getInt(6)); 
                lst_.add(obj_);
            }
            resul.close();
             datos_json.put("lista_busqueda_detallesalida",lst_);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    
    // Eliminar detallesalida al procedimiento almacenado
    public String eliminar_detallesalida(){
        try {

            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_eliminar_detallesalida( ? )}");
            sentencis.setInt(1, getId_detsalida());
            
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    // insertar detalle_salida al procedimiento almacenado
    public String insertar_detallesalidaxxx(){
        try {
            
            //int id_usuario=3; //fsdfskjfsfhsfkj hskfhskdjfhskjf
            ///int id_factura=1;
            
                CallableStatement sentencisx=BaseConexion.getprepareCall("{call sp_registrar_detallesalida_dos(?, ?, ?)}");
                sentencisx.setInt(1,getId_Salida());                         
                sentencisx.setInt(2, getId_Prodcut());
                sentencisx.setInt(3, getCantidad());
                sentencisx.executeUpdate();
                
            
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    } 
    //metodos para actualizar
    public String actualizar_salida(){
        try {

            CallableStatement sentencis=BaseConexion.getprepareCall("{call sp_actualizar_salida(?, ?, ?)}");
            sentencis.setString(1, getTxtfecha()); 
            sentencis.setInt(2, getStado());
            sentencis.setInt(3, getId_salida());
            sentencis.executeUpdate();
            return "mensaje";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
            return "error";
        }
    }
    
     //:::::::::::::::::::::::::::::::::::::::::: R E P O R T E S ************************************:+       
    /// ==================== R E P O R T E S ===========================>>>>>>>   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    ////////////////////////////////
    
    public String lista_busqueda_producto_autoc(){
        try {
            List<Producto> lst_pro= new ArrayList<>();
            lst_pro.clear();//limpiar la lista
            String query="select pro.descripcion_producto, pro.id_producto, pro.precio_producto, pro.stock_a_producto, pro.stock_m_producto, pro.codigo_producto from producto pro \n" +
                        "where pro.estado_producto = 1 ";
            ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            while(resul.next()){
                Producto obj_=new Producto();
                obj_.setValue(resul.getString(6) + " " +resul.getString(1)); 
                obj_.setId_producto(resul.getInt(2));   
//                obj_.setCodigo_producto(resul.getString(2));
//                obj_.setId_categoria(resul.getInt(3));                
//                obj_.setDescripcion_producto(resul.getString(4));
                obj_.setPrecio_producto(resul.getDouble(3));             
//                obj_.setUnidades_producto(resul.getString(6));
                obj_.setStock_a_producto(resul.getInt(4));
                obj_.setStock_m_producto(resul.getInt(5));
//                obj_.setStock_minimo_producto(resul.getInt(9));
//                obj_.setStock_minimo_mostrador_producto(resul.getInt(10));
//                obj_.setEn_mostrador_producto(resul.getInt(11));
//                obj_.setEn_listacompra(resul.getInt(12));
//                obj_.setEstado_producto(resul.getInt(13)); 
//                obj_.setDesc_categoria(resul.getString(15));
                lst_pro.add(obj_);
            }
            resul.close();
             datos_json.put("lista_busqueda_producto",lst_pro);
            return SUCCESS;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }
        return "error";
    }
    
}
