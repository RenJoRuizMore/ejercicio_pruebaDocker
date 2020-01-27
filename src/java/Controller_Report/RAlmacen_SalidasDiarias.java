/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Report;

import Logica_Negocio.PojosPersonalizados.RProductos;
import Logica_Negocio.PojosPersonalizados.RSalidaDiarias;
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

/**
 *
 * @author Rene Jose
 */
public class RAlmacen_SalidasDiarias extends ActionSupport {
     private List<RSalidaDiarias>lista_diario=new ArrayList<>();
    //private String formato="XLS";
    private String formato="PDF";
    private String tipo_contenido="application/pdf";
    private String nombre_archivo="document.pdf";
    private String archivo_jasper="RSalidaDiaria.jasper";
    private HashMap Parametros_Reporte=new HashMap();
    private String ruta;
    public String execute_hola() throws Exception{
        // el obejto con el pojo 
         try {
             String query="select s.numero_salida,p.descripcion_producto,"
                         + "p.precio_producto,cantidad_detallesalida\n" +
                           "from salida s inner join detalle_salida d on "
                         + "s.id_salida= d.id_salida \n" +
                          "inner join producto p on p.id_producto=d.id_producto"
                         + " where date(fecha_salida)=curdate() \n" +
                           "and estado_salida=1;";
             ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            //limpiar la lista
            lista_diario.clear();
            while(resul.next()){
                RSalidaDiarias obj_=new RSalidaDiarias();
                obj_.setNumero_salida(resul.getString(1));
                obj_.setDescripcion_producto(resul.getString(2));
                obj_.setPrecio_producto(resul.getDouble(3)); 
                obj_.setCantidad_detallesalida(resul.getInt(4));
                
                lista_diario.add(obj_);
            }
               resul.close();
             try {
                 setRuta(getApplicationPath() + "reportes");
                 JasperCompileManager.compileReportToFile(
                         getApplicationPath() + "/reportes/RSalidaDiaria.jrxml",
                         getApplicationPath() + "/reportes/RSalidaDiaria.jasper");
                 
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
