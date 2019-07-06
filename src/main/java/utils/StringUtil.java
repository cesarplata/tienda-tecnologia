package utils;

public final class StringUtil {
	
	private StringUtil() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * funcion que revisa cuantas vocales tiene un string
	 * 
	 * @author Cesar Plata 
	 * @date Jan 15, 2018 
	 * @HU_CU_REQ RQ9HU395
	 * @return Integer (Cantidad de vocales de la cadena)
	 */
	public static int cantidadVocalesString(String cadena){
		int vocales = 0;
		
		String cadenaLowerCase = cadena.toLowerCase();
		for (int i = 0; i < cadenaLowerCase.length(); i++) {
			char caracter = cadenaLowerCase.charAt(i);
			if(caracter == 'a' || caracter == 'e' 
				|| caracter == 'i' || caracter == 'o' || caracter == 'u') {
	                ++vocales;
	        }
		}
		return vocales;
	}
}
