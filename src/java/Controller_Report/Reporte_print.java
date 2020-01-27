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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import Logica_Negocio.PojosPersonalizados.Pojo_Reporte_Diario_aten_paciente;
import static com.opensymphony.xwork2.Action.SUCCESS;
import javax.servlet.ServletContext;



import org.apache.struts2.StrutsStatics;


import net.sf.jasperreports.engine.JasperCompileManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import conexion.BaseConexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

public class Reporte_print extends ActionSupport{
    
    // crear las variables que interactuan acon el reporte
    private List<Producto>lista_diario=new ArrayList<>();
    private String formato="PDF";
    private String tipo_contenido="application/pdf";
    private String nombre_archivo="document.pdf";
    private String archivo_jasper="reportusuario.jasper";
    private HashMap Parametros_Reporte=new HashMap();
    private String ruta;
    public String execute_hola() throws Exception{
        // el obejto con el pojo 
         try {
             
             String query="select * from producto ";
             ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            //limpiar la lista
//            lista_diario.clear();
//            
//            
//             CallableStatement query=BaseConexion.getprepareCall("{call "
//                + "sp_reporte_semana_paciente_atendido_1()}");
//            query.execute();
//            ResultSet resul=query.getResultSet();
//            
//            CallableStatement query2=BaseConexion.getprepareCall("{call "
//                + "sp_reporte_semana_paciente_atendido_2()}");
//            query2.execute();
//            ResultSet resul2=query2.getResultSet();
//            
            while(resul.next()){
                Producto obj_=new Producto();
                obj_.setId_producto(resul.getInt(1));
                obj_.setCodigo_producto(resul.getString(2));
                obj_.setId_categoria(resul.getInt(3));            
//                // si esta activo no eliminado
//                Pojo_Reporte_Diario_aten_paciente obj_facul=new Pojo_Reporte_Diario_aten_paciente();
//                obj_facul.setNombre_persona(resul.getString(1)+ " " +resul.getString(2));
//                
//                obj_facul.setApellido_persona(resul.getString(2));
//                obj_facul.setNumero_historia(resul.getString(3));
//                
//                resul2.next();
//                obj_facul.setNombre_doctor(resul2.getString(1)+" "+ resul2.getString(2) );
//                obj_facul.setApellido_doctor(resul2.getString(2));
//                obj_facul.setEspecialidad_doctor(resul2.getString(3));
//                
                lista_diario.add(obj_);
            }
//            resul.close();
//            resul2.close();
             try {
                 setRuta(getApplicationPath() + "reportes");
                 JasperCompileManager.compileReportToFile(
                         getApplicationPath() + "/reportes/reportusuario.jrxml",
                         getApplicationPath() + "/reportes/reportusuario.jasper");
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

//    public List<Pojo_Reporte_Diario_aten_paciente> getLista_diario() {
//        return lista_diario;
//    }
//
//    public void setLista_diario(List<Pojo_Reporte_Diario_aten_paciente> lista_diario) {
//        this.lista_diario = lista_diario;
//    }

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

    public List<Producto> getLista_diario() {
        return lista_diario;
    }

    public void setLista_diario(List<Producto> lista_diario) {
        this.lista_diario = lista_diario;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
}
