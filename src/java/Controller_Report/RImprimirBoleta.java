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

import Logica_Negocio.PojosPersonalizados.RImprimirBoletaP;
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
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
public class RImprimirBoleta extends ActionSupport{
    // crear las variables que interactuan acon el reporte
    private List< RImprimirBoletaP>lista_diario=new ArrayList<>();
    //private String formato="XLS";
    private String formato="PDF";
    private String tipo_contenido="application/pdf";
    private String nombre_archivo="document.pdf";
    private String archivo_jasper="BoletaVentas.jasper";
    private HashMap<String, Object> Parametros_Reporte=new HashMap();
    static String numero_pedido_e;
    private String ruta;
    public String execute_hola() throws Exception{
        // el obejto con el pojo 
         try {
             
            // imagen_logo= getApplicationPath() + "/reportes";
             
             String query="select nombre_cliente,p.numero_pedido,p.fecha_pedido,\n" +
                        " pp.cantidad_pedidoproducto,descripcion_producto,importe_pedidoproducto,\n" +
                        " monto_total_pedido\n" +
                        " from pedido p inner join\n" +
                        " pedido_producto pp on p.id_pedido=pp.id_pedido \n" +
                        " inner join producto pro on pro.id_producto=pp.id_producto \n" +
                        " inner join cliente c on p.id_cliente=c.id_cliente\n" +
                        " where numero_pedido='"+getNumero_pedido_e() +"'";
             ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            //limpiar la lista
            lista_diario.clear();
            while(resul.next()){
                RImprimirBoletaP obj_=new  RImprimirBoletaP();
                obj_.setNombre_cliente(resul.getString(1));
                obj_.setNumero_pedido(resul.getString(2));
                obj_.setFecha_pedido(resul.getDate(3)); 
                obj_.setCantidad_pedidoproducto(resul.getInt(4));
                obj_.setDescripcion_producto(resul.getString(5));
                obj_.setImporte_pedidoproducto(resul.getDouble(6));
                obj_.setMonto_total_pedido(resul.getDouble(7));
                lista_diario.add(obj_);
            }
               resul.close();
             try {
                 setRuta(getApplicationPath() + "reportes");
               //  Parametros_Reporte.put(getRuta(), getApplicationPath() + "reportes");
                 JasperCompileManager.compileReportToFile(
                         getApplicationPath() + "/reportes/BoletaVentas.jrxml",
                         getApplicationPath() + "/reportes/BoletaVentas.jasper");
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

    public List<RImprimirBoletaP> getLista_diario() {
        return lista_diario;
    }

    public void setLista_diario(List<RImprimirBoletaP> lista_diario) {
        this.lista_diario = lista_diario;
    }

    public String getNumero_pedido_e() {
        return numero_pedido_e;
    }

    public void setNumero_pedido_e(String numero_pedido_e) {
        this.numero_pedido_e = numero_pedido_e;
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

    public HashMap<String, Object> getParametros_Reporte() {
        return Parametros_Reporte;
    }

    public void setParametros_Reporte(HashMap<String, Object> Parametros_Reporte) {
        this.Parametros_Reporte = Parametros_Reporte;
    }



    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

  
    
    
    // para obtenre la ruta 
     public String getApplicationPath() {
        return ((ServletContext)ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/");
    }
    
}
