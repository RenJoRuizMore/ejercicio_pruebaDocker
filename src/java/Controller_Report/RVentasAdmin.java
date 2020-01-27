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

public class RVentasAdmin extends ActionSupport{
     public ResultSet rs = null;
    public String submit = null;
    public InputStream fileInputStream;
    public String jasperPath = "";
    public String pdfName = "";
    public String rpt = "";
   
    public static int cbx_vende;
    public static int cbx_tipo;
    public static int cbx_turno;
    public static int cbx_opcion;
    public static String txt_fechai;
    public static String txt_anio;
    public static String txt_fechaf;
    public static int cbx_mes;
   
       @Override
	public String execute() throws Exception {
		try {
            String fini = "";
            String ffin = "";
            String idven = "";
            String tipo = "";
            String turno = "";
            if (getCbx_vende() != 0) {
                idven = " and ped.id_usuario = '" + getCbx_vende() + "' ";
            }
            if (getCbx_tipo() != 0) {
                tipo = " and ped.tipo_documento = '" + getCbx_tipo() + "' ";
            }
            if (getCbx_turno() != 0) {
                if (getCbx_turno() == 1) {
                    turno = " and ped.hora_pedido BETWEEN  '08:00:00' and  '16:00:00' ";
                } else {
                    turno = " and ped.hora_pedido BETWEEN  '16:00:01' and  '22:00:00' ";
                }
            }

            if (getCbx_opcion() == 1) {//dia
                fini = getTxt_fechai();
                ffin = getTxt_fechai();
            } else if (getCbx_opcion() == 2) {//mes
                fini = getTxt_anio() + "-" + getCbx_mes() + "-01";
                ffin = getTxt_anio() + "-" + getCbx_mes() + "-31";
            } else if (getCbx_opcion() == 3) {//anio
                fini = getTxt_anio() + "-01-01";
                ffin = getTxt_anio() + "-12-31";
            } else {//fechas                
                fini = getTxt_fechai();
                ffin = getTxt_fechaf();
            }

          
                String query = " SELECT \n"
                    + " (case ped.tipo_documento when 1 then 'Boleta' when  2 then 'Factura' end) as tipo_documento,\n"
                    + "    usu.nombre_usuario,\n"
                    + "    ped.fecha_pedido,\n"
                    + "    ped.numero_pedido,\n"
                    + "   (case ped.tipo_pago when 1 then 'Efectivo' when  2 then 'Tarjeta' end) as tipo_pago,\n"
                    + "    ped.monto_total_pedido\n"
                    + "FROM \n"
                    + "	pedido ped\n"
                    + "    INNER JOIN usuario usu ON usu.id_usuario = ped.id_usuario\n"
                    + "WHERE \n"
                    + "	ped.fecha_pedido BETWEEN '" + fini + "' and '" + ffin + "' " + idven + " " + tipo + turno;
           
            
            rs = BaseConexion.getStatement().executeQuery(query);
            jasperPath = ServletActionContext.getServletContext().getRealPath("/reportes");
            pdfName = "ReporteVentaBusqueda";
            rpt = "RVentasFacturasAdMIN.jrxml";
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

    public  int getCbx_vende() {
        return cbx_vende;
    }

    public  void setCbx_vende(int cbx_vende) {
        this.cbx_vende = cbx_vende;
    }

    public  int getCbx_tipo() {
        return cbx_tipo;
    }

    public  void setCbx_tipo(int cbx_tipo) {
        this.cbx_tipo = cbx_tipo;
    }

    public int getCbx_turno() {
        return cbx_turno;
    }

    public void setCbx_turno(int cbx_turno) {
        this.cbx_turno = cbx_turno;
    }

        
  public int getCbx_opcion() {
        return cbx_opcion;
    }

    public void setCbx_opcion(int cbx_opcion) {
        this.cbx_opcion = cbx_opcion;
    }

    public String getTxt_fechai() {
        return txt_fechai;
    }

    public void setTxt_fechai(String txt_fechai) {
        this.txt_fechai = txt_fechai;
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

    public void setTxt_fechaf(String rxt_fechaf) {
        this.txt_fechaf = rxt_fechaf;
    }

    public int getCbx_mes() {
        return cbx_mes;
    }

    public void setCbx_mes(int cbx_mes) {
        this.cbx_mes = cbx_mes;
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
