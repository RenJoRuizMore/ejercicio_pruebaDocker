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


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import conexion.BaseConexion;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
public class RAlmacen_FaturaComprasMes extends ActionSupport {
    public ResultSet rs = null;
    public String submit = null;
    public InputStream fileInputStream;
    public String jasperPath = "";
    public String pdfName = "";
    public String rpt = "";
    public static int num_mes;
       @Override
	public String execute() throws Exception {
		try {
                                
				String query="SELECT fact.numero_factura,prov.nombre_proveedor,\n" +
                                            "fact.monto_factura,fact.fecha_factura,usu.nombre_usuario FROM \n" +
                                            "factura fact INNER JOIN proveedor prov on prov.id_proveedor = fact.id_proveedor\n" +
                                            "INNER JOIN usuario usu on usu.id_usuario = fact.id_usuario \n" +
                                            "where estado_factura= 1 and concat(fact.numero_factura,' ',prov.nombre_proveedor) and month(fecha_factura) = "+ getNum_mes()+ " \n" +
                                            "order by fact.id_factura desc ;";
                                ResultSet rs=BaseConexion.getStatement().executeQuery(query);
                               
				jasperPath = ServletActionContext.getServletContext().getRealPath("/reportes");
				pdfName = "ReporteFacturaComprasMes";
				rpt = "ReporteFacturaCompraMes.jrxml";
				JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
                                
				HashMap<String, Object> pm = new HashMap<String, Object>();
//				String logo = jasperPath + "/ws.jpg";
//				pm.put("logo", logo);
                               pm.put("ruta", jasperPath);
				JasperReport jr = JasperCompileManager.compileReport(jasperPath + "/" + rpt);
                              
				JasperPrint jp = JasperFillManager.fillReport(jr, pm, resultSetDataSource);
                              
				JasperExportManager.exportReportToPdfFile(jp, jasperPath + pdfName + ".pdf");
                                 
				fileInputStream = new FileInputStream(new File(jasperPath + pdfName + ".pdf"));
                          
			
		} catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"puto error");
			e.printStackTrace();
		}
		return "SUCCESS";

	}

   
    public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

         public int getNum_mes() {
        return num_mes;
    }

    public void setNum_mes(int num_mes) {
        this.num_mes = num_mes;
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
