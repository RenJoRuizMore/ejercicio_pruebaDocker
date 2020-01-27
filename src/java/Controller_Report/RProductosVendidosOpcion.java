/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Report;

/**
 *
 * @author Rene Jose
 */

import Logica_Negocio.PojosPersonalizados.RProductos;
import Logica_Negocio.PojosPersonalizados.RSalidaDiarias;
import Logica_Negocio.pojos.Productos_report;
import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import conexion.BaseConexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.apache.struts2.StrutsStatics;

public class RProductosVendidosOpcion extends ActionSupport {
    private List<RSalidaDiarias>lista_diario=new ArrayList<>();
    //private String formato="XLS";
    private String formato="PDF";
    private String tipo_contenido="application/pdf";
    private String nombre_archivo="document.pdf";
    private String archivo_jasper="RProductoVendidosT.jasper";
    private String txt_fechai;
    private String txt_fechaf;
    private int cbx_productos;
    private HashMap Parametros_Reporte=new HashMap();
    private String ruta;
    public String execute_hola() throws Exception{
        // el obejto con el pojo 
         
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
                
                lst.add(obj);}
                //}
               resul.close();
             try {
                 setRuta(getApplicationPath() + "reportes");
                 JasperCompileManager.compileReportToFile(
                         getApplicationPath() + "/reportes/RProductoVendidosT.jrxml",
                         getApplicationPath() + "/reportes/RProductoVendidosT.jasper");
                  JOptionPane.showMessageDialog(null,"todo ok ");
             } catch (JRException e) {
                 //JOptionPane.showMessageDialog(null, e);
                 System.out.println("xxxxxxxxxxxx "+e);                 
                 return ERROR;
             }
            return "success";
       
    }

    public List<RSalidaDiarias> getLista_diario() {
        return lista_diario;
    }

    public void setLista_diario(List<RSalidaDiarias> lista_diario) {
        this.lista_diario = lista_diario;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
        
         
        if ("XLS".equals(formato)) {
            nombre_archivo = "document.html";
        }
        
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    
    
    
    
     public int getCbx_productos() {
        return cbx_productos;
    }

    public void setCbx_productos(int cbx_productos) {
        this.cbx_productos = cbx_productos;
    }
    
    
    public String getTipo_contenido() {
        
            if ("XLS".equals(formato)) {
            tipo_contenido = "application/xls";
        } else if ("HTML".endsWith(formato)) {
            tipo_contenido = "text/html";
        }
        
        
        return tipo_contenido;
    }

    public void setTipo_contenido(String tipo_contenido) {
        this.tipo_contenido = tipo_contenido;
    }

    public String getNombre_archivo() {
        
        if ("XLS".equals(formato)) {
            nombre_archivo = "document.xls";
        } else if ("HTML".endsWith(formato)) {
            nombre_archivo = "document.html";
        }
       
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public String getArchivo_jasper() {
        return archivo_jasper;
    }

    public void setArchivo_jasper(String archivo_jasper) {
        this.archivo_jasper = archivo_jasper;
    }

    public HashMap getParametros_Reporte() {
        return Parametros_Reporte;
    }

    public void setParametros_Reporte(HashMap Parametros_Reporte) {
        this.Parametros_Reporte = Parametros_Reporte;
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
    
    
    
    
    // para obtenre la ruta 
     public String getApplicationPath() {
        return ((ServletContext)ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/");
    }
    
}
