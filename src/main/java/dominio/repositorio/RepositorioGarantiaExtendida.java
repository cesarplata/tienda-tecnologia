package dominio.repositorio;

import dominio.Producto;

import java.util.List;

import dominio.GarantiaExtendida;

public interface RepositorioGarantiaExtendida {

	/**
	 * Permite obtener un producto con garantia extendida dado un codigo
	 * @param codigo
	 * @return
	 */
	Producto obtenerProductoConGarantiaPorCodigo(String codigo);
	
	/**
	 * Permite obtener un producto con garantia extendida dado un codigo
	 * y el nombre del cliente
	 * @param codigo
	 * @param nombreCliente
	 * @return
	 */
	Producto obtenerProductoConGarantiaPorCodigoNombreCliente(String codigo, String nombreCliente);
	
	/**
	 * Permite obtener todas las garantías
	 * @return
	 */
	List obtenerGarantias();
	
	/**
	 * Permite agregar una garantia al repositorio de garantia
	 * @param garantia
	 */
	void agregar(GarantiaExtendida garantia);
	
	/**
	 * Permite obtener una garantia extendida por el codigo del producto
	 * @param codigo
	 */
	GarantiaExtendida obtener(String codigo);
	
	/**
	 * Permite obtener una garantia extendida por el codigo del producto y nombre del cliente
	 * @param codigo
	 * @param nombreCliente
	 */
	GarantiaExtendida obtener(String codigo, String nombreCliente);

}
