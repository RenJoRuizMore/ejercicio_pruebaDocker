 
package Logica_Negocio.PojosPersonalizados;

 
public class Factura {
    
    private int id_factura;
    private int id_proveedor;
    private int id_usuario;
    private String numero_factura;
    private String nombre_proveedor;
    private double monto_factura;
    private String fecha_factura;
    private String nombre_usuario;
    private int estado_factura;

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(String numero_factura) {
        this.numero_factura = numero_factura;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public double getMonto_factura() {
        return monto_factura;
    }

    public void setMonto_factura(double monto_factura) {
        this.monto_factura = monto_factura;
    }

    public String getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(String fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public int getEstado_factura() {
        return estado_factura;
    }

    public void setEstado_factura(int estado_factura) {
        this.estado_factura = estado_factura;
    }

    
}
