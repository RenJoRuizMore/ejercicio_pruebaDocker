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
public class RAlmacen_SalidasAnio extends ActionSupport{
    public ResultSet rs = null;
    public String submit = null;
    public InputStream fileInputStream;
    public String jasperPath = "";
    public String pdfName = "";
    public String rpt = "";
    public static int num_anio;
       @Override
	public String execute() throws Exception {
		try {
                                
				String query="select s.numero_salida,p.descripcion_producto,\n" +
                                            "p.precio_producto,cantidad_detallesalida\n" +
                                            "from salida s inner join detalle_salida d on \n" +
                                            "s.id_salida= d.id_salida\n" +
                                            "inner join producto p on p.id_producto=d.id_producto\n" +
                                            "where year(fecha_salida)= "+ getNum_anio()+ "  \n" +
                                            "and estado_salida=1;";
                                ResultSet rs=BaseConexion.getStatement().executeQuery(query);
				jasperPath = ServletActionContext.getServletContext().getRealPath("/reportes");
				pdfName = "RSalidaAnio";
				rpt = "RSalidaAnio.jrxml";
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

    public int getNum_anio() {
        return num_anio;
    }

    public void setNum_anio(int num_mes) {
        this.num_anio = num_mes;
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
