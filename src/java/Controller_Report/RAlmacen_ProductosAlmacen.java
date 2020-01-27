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

import Logica_Negocio.PojosPersonalizados.Producto;
import Logica_Negocio.PojosPersonalizados.RProductos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import Logica_Negocio.PojosPersonalizados.Pojo_Reporte_Diario_aten_paciente;
import static com.opensymphony.xwork2.Action.SUCCESS;
//import javax.servlet.ServletContext;



import org.apache.struts2.StrutsStatics;


import net.sf.jasperreports.engine.JasperCompileManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import conexion.BaseConexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

public class RAlmacen_ProductosAlmacen extends ActionSupport{
    
    // crear las variables que interactuan acon el reporte
    private List<RProductos>lista_diario=new ArrayList<>();
    //private String formato="XLS";
    private String formato="PDF";
    private String tipo_contenido="application/pdf";
    private String nombre_archivo="document.pdf";
    private String archivo_jasper="ProductosEnAlmacen.jasper";
    private HashMap Parametros_Reporte=new HashMap();
    private String ruta;
    public String execute_hola() throws Exception{
        // el obejto con el pojo 
         try {
             String query="select pro.codigo_producto,cat.descripcion_categoria,\n" +
                            "pro.descripcion_producto, pro.stock_minimo_producto,\n" +
                            "pro.stock_a_producto  from producto pro inner join \n" +
                            "categoria cat on pro.id_categoria = cat.id_categoria\n" +
                            "where concat(codigo_producto,' ',descripcion_producto ) like '% %'\n" +
                            "and pro.en_listacompra_producto = 1 and pro.estado_producto = 1 \n" +
                            "and pro.id_categoria != 1  order by descripcion_producto asc";
             ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            //limpiar la lista
            lista_diario.clear();
            while(resul.next()){
                RProductos obj_=new RProductos();
                obj_.setCodigo_producto(resul.getString(1));
                obj_.setDescripcion_categoria(resul.getString(2));
                obj_.setDescripcion_producto(resul.getString(3)); 
                obj_.setStock_minimo_producto(resul.getInt(4));
                obj_.setStock(resul.getInt(5));
                lista_diario.add(obj_);
            }
               resul.close();
             try {
                 setRuta(getApplicationPath() + "reportes");
                 JasperCompileManager.compileReportToFile(
                         getApplicationPath() + "/reportes/ProductosEnAlmacen.jrxml",
                         getApplicationPath() + "/reportes/ProductosEnAlmacen.jasper");
             } catch (JRException e) {
                 //JOptionPane.showMessageDialog(null, e);
                 
                 System.out.println("xxxxxxxxxxxx "+e);                 
                 return ERROR;
             }
            return "success";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error en la consulta : "+ e);
        }
         
        return "error";
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    
    
    
    public List<RProductos> getLista_diario() {
        return lista_diario;
    }

    public void setLista_diario(List<RProductos> lista_diario) {
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
    
    
    // para obtenre la ruta 
     public String getApplicationPath() {
        return "";//((ServletContext)ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/");
    }
    
    
}
