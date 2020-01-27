/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Report;

/**
 *
 * @author Rene Jose
 * 
 */
import Logica_Negocio.PojosPersonalizados.Factura;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import conexion.BaseConexion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
public class RComprasAdmin extends ActionSupport{
    
      public ResultSet rs = null;
    public String submit = null;
    public InputStream fileInputStream;
    public String jasperPath = "";
    public String pdfName = "";
    public String rpt = "";
    public static int cbx_provee;
    public static String txt_fechai;
    public static int cbx_opcion;
    public static String txt_anio;
    public static String txt_fechaf;
    public static int cbx_mes;
   
       @Override
	public String execute() throws Exception {
		try {
            String fini = "";
            String ffin= "";            
            String idpro = "";
            if(getCbx_provee() != 0)
            idpro = " and fac.id_proveedor = '"+getCbx_provee()+"' ";
            if(getCbx_opcion() == 1){//dia
                fini = getTxt_fechai();
                ffin = getTxt_fechai();
            }else if(getCbx_opcion() == 2 ){//mes
                fini = getTxt_anio()+"-"+getCbx_mes()+"-01";
                ffin = getTxt_anio()+"-"+getCbx_mes()+"-31";
            }else if(getCbx_opcion() == 3 ){//anio
                fini = getTxt_anio()+"-01-01";
                ffin = getTxt_anio()+"-12-31";                
            }else{//fechas                
                fini = getTxt_fechai();
                ffin = getTxt_fechaf();
            }            
          
            
            String query =  " SELECT \n" +
                            "	  fac.numero_factura,\n" +
                            "     prov.nombre_proveedor,\n" +
                            "     fac.fecha_factura,\n" +
                            "     fac.monto_factura,\n" +
                            "     usu.nombre_usuario\n" +
                            "FROM \n" +
                            "	factura fac\n" +
                            "INNER JOIN proveedor prov on prov.id_proveedor = fac.id_proveedor    \n" +
                            "INNER JOIN usuario usu on usu.id_usuario = fac.id_usuario \n" +
                            "WHERE  \n" +
                            "	fac.fecha_factura BETWEEN '"+ fini +"' and '"+ ffin +"' and fac.estado_factura = 1 "+idpro;
            ResultSet rs=BaseConexion.getStatement().executeQuery(query);
            jasperPath = ServletActionContext.getServletContext().getRealPath("/reportes");
            pdfName = "ReporteCompraBusqueda";
            rpt = "RComprasAdmin.jrxml";
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
            HashMap<String, Object> pm = new HashMap<String, Object>();
//				String logo = jasperPath + "/ws.jpg";
	    pm.put("ruta", jasperPath);

            JasperReport jr = JasperCompileManager.compileReport(jasperPath + "/" + rpt);

            JasperPrint jp = JasperFillManager.fillReport(jr, pm, resultSetDataSource);

            JasperExportManager.exportReportToPdfFile(jp, jasperPath + pdfName + ".pdf");

            fileInputStream = new FileInputStream(new File(jasperPath + pdfName + ".pdf"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "puto error");
            e.printStackTrace();
        }
        return "SUCCESS";

	}

    public int getCbx_mes() {
        return cbx_mes;
    }

    public void setCbx_mes(int cbx_mes) {
        this.cbx_mes = cbx_mes;
    }

        
        
        
    public String getTxt_anio() {
        return txt_anio;
    }

    public void setTxt_anio(String txt_anio) {
        this.txt_anio = txt_anio;
    }

    public String getTxt_fechaf() {
        return txt_fechaf;
    }

    public void setTxt_fechaf(String txt_fechaf) {
        this.txt_fechaf = txt_fechaf;
    }

        
        
    public int getCbx_provee() {
        return cbx_provee;
    }

    public void setCbx_provee(int cbx_provee) {
        this.cbx_provee = cbx_provee;
    }

    public String getTxt_fechai() {
        return txt_fechai;
    }

    public void setTxt_fechai(String txt_fechai) {
        this.txt_fechai = txt_fechai;
    }

    public int getCbx_opcion() {
        return cbx_opcion;
    }

    public void setCbx_opcion(int cbx_opcion) {
        this.cbx_opcion = cbx_opcion;
    }

   
     
    
    public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public String getJasperPath() {
		return jasperPath;
	}

	public void setJasperPath(String jasperPath) {
		this.jasperPath = jasperPath;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public String getRpt() {
		return rpt;
	}

	public void setRpt(String rpt) {
		this.rpt = rpt;
	}  
    
    
    
    
}
