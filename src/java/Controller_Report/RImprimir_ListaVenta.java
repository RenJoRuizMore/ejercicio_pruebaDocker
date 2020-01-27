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
public class RImprimir_ListaVenta extends ActionSupport{
     public ResultSet rs = null;
    public String submit = null;
    public InputStream fileInputStream;
    public String jasperPath = "";
    public String pdfName = "";
    public String rpt = "";
    private static int id_usuario_venta;
       @Override
	public String execute() throws Exception {
		try {
			  
				String query="select numero_pedido,pago_con,igv_pedido,vuelto,monto_total_pedido,estado_pedido\n" +
                                "from pedido where  fecha_pedido= curdate() and id_usuario= "+ getId_usuario_venta();
                                ResultSet rs=BaseConexion.getStatement().executeQuery(query);
				jasperPath = ServletActionContext.getServletContext().getRealPath("/reportes");
				pdfName = "ReporteImprimirListVenta";
				rpt = "ReporteImprimirListVenta.jrxml";
				JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
				HashMap<String, Object> pm = new HashMap<String, Object>();
//				String logo = jasperPath + "/ws.jpg";
				pm.put("ruta", jasperPath);
                               
				JasperReport jr = JasperCompileManager.compileReport(jasperPath + "/" + rpt);
                                
				JasperPrint jp = JasperFillManager.fillReport(jr, pm, resultSetDataSource);
                            
				JasperExportManager.exportReportToPdfFile(jp, jasperPath + pdfName + ".pdf");
                                 
				fileInputStream = new FileInputStream(new File(jasperPath + pdfName + ".pdf"));
                          
			
		} catch (Exception e) {
                   
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

    public int getId_usuario_venta() {
        return id_usuario_venta;
    }

    public void setId_usuario_venta(int id_usuario_venta) {
        this.id_usuario_venta = id_usuario_venta;
    }
        
        
    
}
