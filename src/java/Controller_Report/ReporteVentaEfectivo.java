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

import Logica_Negocio.PojosPersonalizados.RVentaEfectivo;
import Logica_Negocio.PojosPersonalizados.RProductos;
import static com.opensymphony.xwork2.Action.ERROR;
import net.sf.jasperreports.engine.JasperCompileManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import conexion.BaseConexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import org.apache.struts2.StrutsStatics;




public class ReporteVentaEfectivo extends ActionSupport{
     // crear las variables que interactuan acon el reporte
    private List< RVentaEfectivo>lista_diario=new ArrayList<>();
    //private String formato="XLS";
    private String formato="PDF";
    private String tipo_contenido="application/pdf";
    private String nombre_archivo="document.pdf";
    private String archivo_jasper="VentaEfectivo2.jasper";
    private HashMap Parametros_Reporte=new HashMap();
    private String ruta;
    public String execute_hola() throws Exception{
        // el obejto con el pojo 
         try {
             
             String query="select p.numero_pedido,codigo_producto,descripcion_producto,pr.precio_producto,\n" +
                    "cantidad_pedidoproducto,importe_pedidoproducto, \n" +
                    "(select sum(monto_total_pedido) from pedido where tipo_pago=1 and fecha_pedido=curdate()) as suma\n" +
                    " from pedido p \n" +
                    "inner join pedido_producto ped_pro on p.id_pedido=ped_pro.id_pedido\n" +
                    "inner join producto pr on ped_pro.id_producto =pr.id_producto\n" +
                    "where p.fecha_pedido=curdate() and  p.tipo_pago=1\n" +
                    " group by p.numero_pedido,pr.id_producto order by p.id_pedido";
             ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            //limpiar la lista
            lista_diario.clear();
            while(resul.next()){
                RVentaEfectivo obj_=new  RVentaEfectivo();
                obj_.setNumero_pedido(resul.getString(1));
                obj_.setCodigo_producto(resul.getString(2));
                obj_.setDescripcion_producto(resul.getString(3)); 
                obj_.setPrecio_producto(resul.getDouble(4));
                obj_.setCantidad_pedidoproducto(resul.getInt(5));
                obj_.setImporte_pedidoproducto(resul.getDouble(6));
                obj_.setSuma(resul.getDouble(7));
               
                lista_diario.add(obj_);
            }
               resul.close();
             try {
                 setRuta(getApplicationPath() + "reportes");
                 JasperCompileManager.compileReportToFile(
                         getApplicationPath() + "/reportes/VentaEfectivo2.jrxml",
                         getApplicationPath() + "/reportes/VentaEfectivo2.jasper");
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

    public List<RVentaEfectivo> getLista_diario() {
        return lista_diario;
    }

    public void setLista_diario(List<RVentaEfectivo> lista_diario) {
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
        return ((ServletContext)ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/");
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
     
     
    
}
