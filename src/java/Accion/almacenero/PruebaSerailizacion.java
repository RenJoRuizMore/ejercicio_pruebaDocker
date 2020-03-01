/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accion.almacenero;

import Logica_Negocio.pojos.Producto_VentaSerializable;
import Logica_Negocio.pojos.VentaSerializable;
import com.opensymphony.xwork2.Action;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import PatronBuilder.Director;
import PatronBuilder.BuilderCarManual;
import PatronBuilder.CarManual;
/**
 *
 * @author Rene Jose
 */
public class PruebaSerailizacion {
    
    
    public String ver_datos_serializables(){
        JSONParser parseador= new JSONParser();
        String valores_json=JSONConfiguracion.extraer_data_json();
        // creatinf ob ject for serialization
       
        VentaSerializable obj_sales=new VentaSerializable();
        // parse 
        Object obj_string_venta;
        try {
          
            obj_string_venta =  parseador.parse(valores_json);
            JSONObject obj_jsonn=(JSONObject) obj_string_venta; 
            obj_sales.setDni(obj_jsonn.get("dni").toString());
            obj_sales.setTipo_comprobante(Integer.parseInt(obj_jsonn.get("tipo_documento").toString()));
            obj_sales.setNombre_cliente(obj_jsonn.get("nombre_cliente").toString());
             
            obj_sales.setCelular(obj_jsonn.get("celular_cliente").toString());
            obj_sales.setSubtotal_todo(Double.parseDouble(obj_jsonn.get("sub_total").toString()));
            obj_sales.setIgv(Double.parseDouble(obj_jsonn.get("igv").toString()));
             
            //===========================================================================00 
            
            obj_sales.setTotal_todo(Double.parseDouble(obj_jsonn.get("total").toString()));
            
            obj_sales.setNumero_pedido(obj_jsonn.get("numero_pedido").toString());
             
            //obj_jsonn.get("products").toString();
            // array of object
            
            JSONArray arreglo_sales= (JSONArray) obj_jsonn.get("products");
            obj_sales.setLst_productos_venta(arreglo_sales);
            // converting 
            JOptionPane.showMessageDialog(null,"si paso en el array ");
            for(Object obj_producto :arreglo_sales){
                 
                JSONObject obj_product_json= (JSONObject) obj_producto;
                // creating objct and add in the arrylist of objec
                Producto_VentaSerializable obj_prod_seria = new Producto_VentaSerializable();
                obj_prod_seria.setId_producto(Integer.parseInt(obj_product_json.get("product_idproducto").toString()));
                // falta el id-cetegoria
                obj_prod_seria.setCodigo_producto(obj_product_json.get("product_codigo").toString());
                obj_prod_seria.setNombre_producto(obj_product_json.get("product_nombre").toString() );
                obj_prod_seria.setCantidad_llevar(Integer.parseInt(obj_product_json.get("product_cantidad").toString()));
                obj_prod_seria.setPrecio(Double.parseDouble(obj_product_json.get("product_precio").toString()));
                obj_prod_seria.setTotal_por_producto(Double.parseDouble(obj_product_json.get("product_total").toString())); 
                // guardar el en disco el objeto 
                obj_sales.add_producto_venta(obj_prod_seria);
                
                JOptionPane.showMessageDialog(null,"grupo humano "+obj_product_json.get("product_total").toString());
                
            }
            
            obj_sales.grabar_serialization();
            JOptionPane.showMessageDialog(null,"si pasoo");
        }
        catch (ParseException ex) {
            JOptionPane.showMessageDialog(null,"hubo un error de parseo "+ ex);
        }
      return Action.SUCCESS;
    }
    
    public String restaurar_serialization(){
        try {
            FileInputStream file_input= new FileInputStream("sales.txt");
            ObjectInputStream input=new ObjectInputStream(file_input);
            VentaSerializable objeto=(VentaSerializable)input.readObject();
           
            List<Producto_VentaSerializable> lst_product_ventas=objeto.getLst_productos_venta();
            
             for (int i = 0; i < lst_product_ventas.size(); i++) {
                 Object objeto_e=(Object) lst_product_ventas.get(i);
                 JSONObject obj_product_json= (JSONObject) objeto_e;
                // creating objct and add in the arrylist of objec
//                Producto_VentaSerializable obj_prod_seria = new Producto_VentaSerializable();
//                obj_prod_seria.setId_producto(Integer.parseInt(obj_product_json.get("product_idproducto").toString()));
//                // falta el id-cetegoria
//                obj_prod_seria.setCodigo_producto(obj_product_json.get("product_codigo").toString());
                JOptionPane.showMessageDialog(null,obj_product_json.get("product_nombre").toString());
//                obj_prod_seria.setCantidad_llevar(Integer.parseInt(obj_product_json.get("product_cantidad").toString()));
//                obj_prod_seria.setPrecio(Double.parseDouble(obj_product_json.get("product_precio").toString()));
//                obj_prod_seria.setTotal_por_producto(Double.parseDouble(obj_product_json.get("product_total").toString()));
            }
   




            
            return Action.SUCCESS;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"problema cpon el archivo "+ ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"problemas con el input" + ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"con la transforamcion problemas "+ ex);
        }
    
         return Action.SUCCESS;
    }
    
     
    public String probar_DesginPatter(){
       Director obj_director= new Director();
       // crear un 
       BuilderCarManual carro_manual= new BuilderCarManual();
       obj_director.construir_CarroDeportivo(carro_manual);
       CarManual carr =carro_manual.getResult();
       
       JOptionPane.showMessageDialog(null,carr.getinformacion()); 
        
      return Action.SUCCESS;
    } 
     
     
}
