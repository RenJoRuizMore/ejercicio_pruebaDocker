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
import Logica_Negocio.PojosPersonalizados.RImprimirFacturaP;

public class RImprimirFactura extends ActionSupport{
    // crear las variables que interactuan acon el reporte
    private List<RImprimirFacturaP>lista_diario=new ArrayList<>();
    //private String formato="XLS";
    private String formato="PDF";
    private String tipo_contenido="application/pdf";
    private String nombre_archivo="document.pdf";
    private String archivo_jasper="FacturaVentas.jasper";
    private HashMap Parametros_Reporte=new HashMap();
    static String numero_pedido_e;
    private String ruta;
    
    public String execute_hola() throws Exception{
        // el obejto con el pojo 
         try {
            
             String query=" select p.numero_pedido,nombre_cliente,direccion_cliente,"
                     + "documento_cliente,p.fecha_pedido,\n" +
                            " pp.cantidad_pedidoproducto,descripcion_producto,precio_producto,importe_pedidoproducto,\n" +
                            " monto_total_pedido,igv_pedido,monto_t_producto\n" +
                            " from pedido p inner join\n" +
                            " pedido_producto pp on p.id_pedido=pp.id_pedido \n" +
                            " inner join producto pro on pro.id_producto=pp.id_producto \n" +
                            " inner join cliente c on p.id_cliente=c.id_cliente\n" +
                            " where numero_pedido = '"+getNumero_pedido_e() +"'";
             ResultSet resul=BaseConexion.getStatement().executeQuery(query);
            //limpiar la lista
            lista_diario.clear();
            while(resul.next()){
                RImprimirFacturaP obj=new  RImprimirFacturaP();
                obj.setNumero_pedido(resul.getString(1));
                obj.setNombre_cliente(resul.getString(2));
                obj.setDireccion_cliente(resul.getString(3)); 
                obj.setDocumento_cliente(resul.getString(4));
                obj.setFecha_pedido(resul.getDate(5));
                obj.setCantidad_pedidoproducto(resul.getInt(6));
                obj.setDescripcion_producto(resul.getString(7));
                obj.setPrecio_producto(resul.getDouble(8));
                obj.setImporte_pedidoproducto(resul.getDouble(9));
                obj.setMonto_total_pedido(resul.getDouble(10));
                obj.setIgv_pedido(resul.getDouble(11));
                obj.setMonto_t_producto(resul.getDouble(12));
                lista_diario.add(obj);
            }
               resul.close();
             try {
                 setRuta(getApplicationPath() + "reportes");
                 JasperCompileManager.compileReportToFile(
                         getApplicationPath() + "/reportes/FacturaVentas.jrxml",
                         getApplicationPath() + "/reportes/FacturaVentas.jasper");
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

    
    
    public List<RImprimirFacturaP> getLista_diario() {
        return lista_diario;
    }

    public void setLista_diario(List<RImprimirFacturaP> lista_diario) {
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
    
}
