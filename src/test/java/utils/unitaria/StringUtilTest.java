package utils.unitaria;

import org.junit.Assert;
import org.junit.Test;

import utils.StringUtil;

public class StringUtilTest {
	
	public static final String VOCALES = "AEIOU";
	public static final String CONSONANTES = "BCDFH";
	
	@Test
	public void cantidadVocalesStringTest() {		
		Assert.assertEquals(5,StringUtil.cantidadVocalesString(VOCALES));
		Assert.assertEquals(0,StringUtil.cantidadVocalesString(CONSONANTES));
	}
	
}
