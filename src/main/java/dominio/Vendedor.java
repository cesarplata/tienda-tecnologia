package dominio;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import dominio.excepcion.GarantiaExtendidaException;
import dominio.repositorio.RepositorioGarantiaExtendida;
import dominio.repositorio.RepositorioProducto;
import utils.DateUtil;
import utils.StringUtil;

public class Vendedor {

    public static final String EL_PRODUCTO_TIENE_GARANTIA = "El producto ya cuenta con una garantia extendida";
    public static final String EL_PRODUCTO_NO_CUENTA_GARANTIA = "Este producto no cuenta con garantía extendida";
    public static final double LIMITE = 500000;
    public static final double PORCENTAJE_MAYOR_LIMITE = 0.2;
    public static final double PORCENTAJE_MENOR_LIMITE = 0.1;
    public static final int DIAS_MAYOR_LIMITE = 200;
    public static final int DIAS_MENOR_LIMITE = 100;
    

    private RepositorioProducto repositorioProducto;
    private RepositorioGarantiaExtendida repositorioGarantia;

    public Vendedor(RepositorioProducto repositorioProducto, RepositorioGarantiaExtendida repositorioGarantia) {
        this.repositorioProducto = repositorioProducto;
        this.repositorioGarantia = repositorioGarantia;

    }

    public void generarGarantia(String codigo, String nombreCliente) {
    	
    	if(StringUtil.cantidadVocalesString(codigo) == 3){
    		throw new GarantiaExtendidaException(EL_PRODUCTO_NO_CUENTA_GARANTIA);
    	}    	
    	
    	if(tieneGarantia(codigo, nombreCliente)){
    		throw new GarantiaExtendidaException(EL_PRODUCTO_TIENE_GARANTIA);
    	}
    	
		Producto producto = repositorioProducto.obtenerPorCodigo(codigo);
		
		GarantiaExtendida garantiaExtendida = null;
		Date fechaSolicitud = DateUtil.getCurrentDate();
		Set<DayOfWeek> dayOfWeekSetNoCount = new HashSet<>();
		if(producto.getPrecio() > LIMITE){
			dayOfWeekSetNoCount.add(DayOfWeek.MONDAY);    			
			
			garantiaExtendida = new GarantiaExtendida(producto,fechaSolicitud,
					DateUtil.addDays(fechaSolicitud, DIAS_MAYOR_LIMITE, dayOfWeekSetNoCount, false),
					producto.getPrecio()*PORCENTAJE_MAYOR_LIMITE, nombreCliente);
		}else{
			garantiaExtendida = new GarantiaExtendida(producto,fechaSolicitud,
					DateUtil.addDays(fechaSolicitud, DIAS_MENOR_LIMITE, dayOfWeekSetNoCount, true),
					producto.getPrecio()*PORCENTAJE_MENOR_LIMITE, nombreCliente);
		}    		
		repositorioGarantia.agregar(garantiaExtendida);
    }

    public boolean tieneGarantia(String codigo, String nombreCliente) {
    	Producto producto = repositorioGarantia.obtenerProductoConGarantiaPorCodigoNombreCliente(codigo, nombreCliente);
    	return null != producto;
    }   
}
