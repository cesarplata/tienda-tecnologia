package utils.unitaria;

import org.junit.Assert;
import org.junit.Test;

import utils.StringUtil;

public class StringUtilTest {
	
	public static final String VOCALES = "AEIOU";
	public static final String CONSONANTES = "BCDFH";
	
	@Test
	public void soloVocalesTest() {		
		
		//arrange
		final int cincoVocales = 5;
		
		//act
		final int cantidadVocalesEnVocales = StringUtil.cantidadVocalesString(VOCALES);
		
		//assert
		Assert.assertEquals(cincoVocales,cantidadVocalesEnVocales);
	}
	
	@Test
	public void soloConsonantesTest() {		
		
		//arrange
		final int ceroVocales = 0;
		
		//act
		final int cantidadVocalesEnConsonantes = StringUtil.cantidadVocalesString(CONSONANTES);
		
		//assert
		Assert.assertEquals(ceroVocales,cantidadVocalesEnConsonantes);
	}
	
}
