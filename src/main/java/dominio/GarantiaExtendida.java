package dominio;

import java.util.Date;

public class GarantiaExtendida {

    private Producto producto;
    private Date fechaSolicitudGarantia;
    private Date fechaFinGarantia;
    private double precioGarantia;
    private String nombreCliente;

    public GarantiaExtendida(Producto producto) {
        this.fechaSolicitudGarantia = new Date();
        this.producto = producto;
    }

    public GarantiaExtendida(Producto producto, Date fechaSolicitudGarantia, Date fechaFinGarantia,
            double precioGarantia, String nombreCliente) {

        this.producto = producto;        
        this.fechaSolicitudGarantia = fechaSolicitudGarantia != null ? (new Date(fechaSolicitudGarantia.getTime())) : null;;
        this.fechaFinGarantia = fechaFinGarantia != null ? (new Date(fechaFinGarantia.getTime())) : null;
        this.precioGarantia = precioGarantia;
        this.nombreCliente = nombreCliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public Date getFechaSolicitudGarantia() {
    	return fechaSolicitudGarantia != null ? (new Date(fechaSolicitudGarantia.getTime())) : null;
    }

    public Date getFechaFinGarantia() {    	
    	return fechaFinGarantia != null ? (new Date(fechaFinGarantia.getTime())) : null;
    }

    public double getPrecioGarantia() {
        return precioGarantia;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

}
