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


public class RVendedor_ReporteProducto extends ActionSupport {
    public ResultSet rs = null;
	public String submit = null;
	public InputStream fileInputStream;
        public String jasperPath = "";
	public String pdfName = "";
	public String rpt = "";
        public static String turno_e;
        public static int id_usuario_venta_e;
       @Override
	public String execute() throws Exception {
		try {
			 
				String query="select p.numero_pedido,codigo_producto,descripcion_producto,\n" +
                                                "cantidad_pedidoproducto,importe_pedidoproducto\n" +
                                                " from pedido p inner join usuario us on us.id_usuario= p.id_usuario\n" +
                                                " inner join sesion ses on us.id_usuario=ses.id_usuario \n" +
                                                " inner join pedido_producto ped_pro on p.id_pedido=ped_pro.id_pedido\n" +
                                                "inner join producto pr on ped_pro.id_producto =pr.id_producto \n" +
                                                "where ses.turno_sesion = '" + getTurno_e() + "' and p.fecha_pedido=curdate()\n" +
                                                "and us.id_usuario= " + getId_usuario_venta_e() + " group by \n" +
                                                "pr.id_producto,p.numero_pedido order by p.id_pedido ;";
                                ResultSet rs=BaseConexion.getStatement().executeQuery(query);
                                
				jasperPath = ServletActionContext.getServletContext().getRealPath("/reportes");
				pdfName = "Reporte Ventas";
				rpt = "ReporteVentasProductos.jrxml";
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

    public String getTurno_e() {
        return turno_e;
    }

    public void setTurno_e(String turno_e) {
        this.turno_e = turno_e;
    }

    public int getId_usuario_venta_e() {
        return id_usuario_venta_e;
    }

    public void setId_usuario_venta_e(int id_usuario_venta_e) {
        this.id_usuario_venta_e = id_usuario_venta_e;
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
