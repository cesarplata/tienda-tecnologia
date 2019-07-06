package dominio.integracion;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dominio.Producto;
import dominio.Vendedor;
import dominio.excepcion.GarantiaExtendidaException;
import dominio.repositorio.RepositorioGarantiaExtendida;
import dominio.repositorio.RepositorioProducto;
import persistencia.sistema.SistemaDePersistencia;
import testdatabuilder.ProductoTestDataBuilder;

public class VendedorTest {

	private static final String COMPUTADOR_LENOVO = "Computador Lenovo";
	private static final String CODIGO_TRES_LETRAS = "O01TSA015E";
	private static final String CLIENTE_UNO = "Tino Asprilla";
	private static final double PRECIO_MAYOR = 650000;
	private static final double PRECIO_MENOR = 100000;
	
	private SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
	
	private SistemaDePersistencia sistemaPersistencia;
	
	private RepositorioProducto repositorioProducto;
	private RepositorioGarantiaExtendida repositorioGarantia;

	@Before
	public void setUp() {
		
		sistemaPersistencia = new SistemaDePersistencia();
		
		repositorioProducto = sistemaPersistencia.obtenerRepositorioProductos();
		repositorioGarantia = sistemaPersistencia.obtenerRepositorioGarantia();
		
		sistemaPersistencia.iniciar();
		
		try{
			Date fixedDateTime = DATE_FORMATTER.parse("16/08/2018");
			DateTimeUtils.setCurrentMillisFixed(fixedDateTime.getTime());
		}catch (Exception e) {
			e.printStackTrace();
		}
	     
	}
	

	@After
	public void tearDown() {
		sistemaPersistencia.terminar();
		DateTimeUtils.setCurrentMillisSystem();
	}

	@Test
	public void generarGarantiaTest() {

		// arrange
		Producto producto = new ProductoTestDataBuilder().conNombre(COMPUTADOR_LENOVO).build();
		repositorioProducto.agregar(producto);
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);

		// act
		vendedor.generarGarantia(producto.getCodigo(), CLIENTE_UNO);

		// assert
		Assert.assertTrue(vendedor.tieneGarantia(producto.getCodigo(), CLIENTE_UNO));
		Assert.assertNotNull(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(producto.getCodigo()));

	}

	@Test
	public void productoYaTieneGarantiaTest() {

		// arrange
		Producto producto = new ProductoTestDataBuilder().conNombre(COMPUTADOR_LENOVO).build();
		
		repositorioProducto.agregar(producto);
		
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);

		// act
		vendedor.generarGarantia(producto.getCodigo(), CLIENTE_UNO);
		try {
			
			vendedor.generarGarantia(producto.getCodigo(), CLIENTE_UNO);
			fail();
			
		} catch (GarantiaExtendidaException e) {
			// assert
			Assert.assertEquals(Vendedor.EL_PRODUCTO_TIENE_GARANTIA, e.getMessage());
		}
	}	
	
	@Test
	public void productoTresVocalesTest() {

		// arrange
		Producto producto = new ProductoTestDataBuilder().conCodigo(CODIGO_TRES_LETRAS).build();
		
		repositorioProducto.agregar(producto);
		
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);

		// act
		try {			
			vendedor.generarGarantia(producto.getCodigo(), CLIENTE_UNO);		
		} catch (GarantiaExtendidaException e) {
			// assert
			Assert.assertEquals(Vendedor.EL_PRODUCTO_NO_CUENTA_GARANTIA, e.getMessage());
		}
	}
	
	@Test	
	public void productoCostoMayor() {

		// arrange
		Producto producto = new ProductoTestDataBuilder().conPrecio(PRECIO_MAYOR).build();
		
		repositorioProducto.agregar(producto);
		
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);

		// act
		vendedor.generarGarantia(producto.getCodigo(), CLIENTE_UNO);
			
		// assert
		try {			
			Assert.assertTrue(PRECIO_MAYOR*Vendedor.PORCENTAJE_MAYOR_LIMITE==repositorioGarantia.obtener(producto.getCodigo(), CLIENTE_UNO).getPrecioGarantia());
		}catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	@Test
	public void productoCostoMenorIgual() {

		// arrange
		Producto producto = new ProductoTestDataBuilder().conPrecio(PRECIO_MENOR).build();
		
		repositorioProducto.agregar(producto);
		
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);

		// act
		vendedor.generarGarantia(producto.getCodigo(), CLIENTE_UNO);
			
		// assert
		try {			
			Assert.assertTrue((producto.getPrecio()*Vendedor.PORCENTAJE_MENOR_LIMITE)==repositorioGarantia.obtener(producto.getCodigo(), CLIENTE_UNO).getPrecioGarantia());
		}catch (Exception e) {
			 e.printStackTrace();
		}
	}
}
