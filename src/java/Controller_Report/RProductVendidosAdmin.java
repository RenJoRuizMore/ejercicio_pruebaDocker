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

import Logica_Negocio.pojos.Productos_report;
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


public class RProductVendidosAdmin extends ActionSupport{
     public ResultSet rs = null;
    public String submit = null;
    public InputStream fileInputStream;
    public String jasperPath = "";
    public String pdfName = "";
    public String rpt = "";
    public static  String txt_fechaf;
    public static int cbx_productos;
    public  static String txt_fechai;;
       @Override
	public String execute() throws Exception {
	 try {
            String fini = getTxt_fechai();
            String ffin = getTxt_fechaf();            
            String idpro = "";
            if(getCbx_productos() != 0)
            idpro = " and pro.id_producto = '"+getCbx_productos()+"' ";
            
            
            String query =  " SELECT \n" +
                            "	pro.descripcion_producto,\n" +
                            "    pro.stock_a_producto,\n" +
                            "    pro.stock_m_producto,    \n" +
                            "	( select sum(pp.cantidad_pedidoproducto) FROM pedido_producto pp WHERE pp.id_producto = pro.id_producto) as 			kantidad , \n" +
                            "    pro.precio_producto    \n" +
                            "FROM \n" +
                            "    producto pro\n" +
                            "    INNER JOIN pedido_producto pp ON pp.id_producto = pro.id_producto\n" +
                            "    INNER JOIN pedido ped ON ped.id_pedido = pp.id_pedido \n" +
                            "WHERE  \n" +
                            "	ped.fecha_pedido BETWEEN '"+ fini +"' and '"+ ffin +"'  "+idpro +"  GROUP by pp.id_producto ";
            ResultSet rs=BaseConexion.getStatement().executeQuery(query);
            jasperPath = ServletActionContext.getServletContext().getRealPath("/reportes");
            pdfName = "ReporteCompraAdmin";
            rpt = "RComprasAdminn.jrxml";
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

    public String getTxt_fechai() {
        return txt_fechai;
    }

    public void setTxt_fechai(String txt_fechai) {
        this.txt_fechai = txt_fechai;
    }

    public String getTxt_fechaf() {
        return txt_fechaf;
    }

    public void setTxt_fechaf(String txt_fechaf) {
        this.txt_fechaf = txt_fechaf;
    }

    public int getCbx_productos() {
        return cbx_productos;
    }

    public void setCbx_productos(int cbx_productos) {
        this.cbx_productos = cbx_productos;
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
