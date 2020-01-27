/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciongenerico;

import com.opensymphony.xwork2.ActionSupport;
import Logica_Negocio.pojos.Usuario;
import conexion.BaseConexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Rene Jose
 */
public class AccionLoguear extends ActionSupport{
    
    public static Usuario obj_user=new Usuario();
    private String txt_user;
    private String txt_contrasenia;

    public String getTxt_user() {
        return txt_user;
    }

    public void setTxt_user(String txt_user) {
        this.txt_user = txt_user;
    }

    public String getTxt_contrasenia() {
        return txt_contrasenia;
    }

    public void setTxt_contrasenia(String txt_contrasenia) {
        this.txt_contrasenia = txt_contrasenia;
    }

    public Usuario getObj_user() {
        return obj_user;
    }

    public void setObj_user(Usuario obj_user) {
        this.obj_user = obj_user;
    }
    
    
    public String Logueo(){
        
        String valor=validacionloguear(getTxt_user(),getTxt_contrasenia());
        
        if (valor.equalsIgnoreCase("exitoso")) {
            switch(obj_user.getId_tipousuario()){
                // administrador
                 case 1:
                    return "administrador";
                  case 2:
                    return "vendedor";
                  case 3:
                    return "cajero";
                  case 4:
                    return "almacenero";
                  default:
                    return "noencontro";  
            }
        }
        return "error";
    
    }
    
    
    // vamos a poner nuestro metodo de logica de negocio
    public String validacionloguear(String user, String pass){
        String valor="";
        try{
            
         String sql="{call sp_general_logueo(?,?)};";
         CallableStatement st=BaseConexion.getprepareCall(sql);
            st.setString(1,user);
            st.setString(2,pass);
         st.execute();
            ResultSet resultado=st.getResultSet();
            while(resultado.next()){
                  obj_user.setId_usuario(resultado.getInt(1));   
                  obj_user.setId_tipousuario(resultado.getInt(2));
                  obj_user.setNombre_persona(resultado.getString(3));
                  obj_user.setCodigo_sesion(resultado.getString(4));   
                  obj_user.setDescripcion_tipousuario(resultado.getString(5));
                  obj_user.setTurno(resultado.getString(6));
            }
           resultado.close();
        }
        catch(Exception e){
           
            return "erroneo";
        }
       return "exitoso"; 
    }
    
    public String ruteador(){
        return "success";
    }
    
    
    
}
