/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accion.almacenero;

import com.opensymphony.xwork2.Action;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
/**
 *
 * @author Rene Jose
 * se maneja el objeto HTTPServeltResponse , se obtiene del contexto que se recibe 
 * atraves del protocolo http 
 */
public class JSONConfiguracion {
    // objeto htttpServletRequest
    static HttpServletRequest request;
    
    // method for request convert String for angular js 
    public static String extraer_data_json(){
        // ServletACTIONcONTEXT ::> Class of struts 2 para recibir los datos 
         
       request= ServletActionContext.getRequest();
       // ponerlo en un String , para que este en un IOUtils y recibir el request ocmo inputStream 
        try {
           
            String cadena_json=IOUtils.toString(request.getInputStream());
            
            return cadena_json;
        } catch (IOException ex) {
            System.out.println(""+ ex);
        }
      return Action.SUCCESS;
    }
    
}
