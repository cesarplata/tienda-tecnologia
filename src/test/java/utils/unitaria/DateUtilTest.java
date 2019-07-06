package utils.unitaria;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utils.DateUtil;

public class DateUtilTest {
	
	public static final int DIAS_MAYOR_LIMITE = 200;
	
	private SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

	@Before
	public void setUp() {	
		
		try{
			Date fixedDateTime = DATE_FORMATTER.parse("16/08/2018");
			DateTimeUtils.setCurrentMillisFixed(fixedDateTime.getTime());
		}catch (Exception e) {
			e.printStackTrace();
		}
	     
	}
	
	@After
	public void tearDown() {
		DateTimeUtils.setCurrentMillisSystem();
	}
	
	@Test
	public void addDaysCountTest() {		
		// arrange
		Set<DayOfWeek> dayOfWeekSetNoCount = new HashSet<>();
		dayOfWeekSetNoCount.add(DayOfWeek.MONDAY);
		
		// act
		Date fechaInicial = DateUtil.getCurrentDate();
		Date fechaFinal =  DateUtil.addDays(fechaInicial, DIAS_MAYOR_LIMITE, dayOfWeekSetNoCount, true);

		// assert
		try{
			Assert.assertEquals(0, DATE_FORMATTER.parse("06/04/2019").compareTo(fechaFinal));
		}catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	@Test
	public void addDaysCountSundayTest() {		
		// arrange
		Set<DayOfWeek> dayOfWeekSetNoCount = new HashSet<>();		
		
		// act
		Date fechaInicial = DateUtil.getCurrentDate();
		Date fechaFinal =  DateUtil.addDays(fechaInicial, 3, dayOfWeekSetNoCount, false);

		// assert
		try{
			Assert.assertEquals(0, DATE_FORMATTER.parse("19/08/2018").compareTo(fechaFinal));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	@Test
	public void addDaysNoDaysTest() {		
		// arrange
		Set<DayOfWeek> dayOfWeekSetNoCount = new HashSet<>();
		
		// act
		Date fechaInicial = DateUtil.getCurrentDate();
		Date fechaFinal =  DateUtil.addDays(fechaInicial, 0, dayOfWeekSetNoCount, true);

		// assert
		try{
			Assert.assertEquals(0, fechaInicial.compareTo(fechaFinal));
		}catch (Exception e) {
			 e.printStackTrace();
		}
	}

	@Test
	public void addDaysAllWeekTest() {		
		// arrange
		Set<DayOfWeek> dayOfWeekSetNoCount = new HashSet<>();
		dayOfWeekSetNoCount.add(DayOfWeek.MONDAY);
		dayOfWeekSetNoCount.add(DayOfWeek.TUESDAY);
		dayOfWeekSetNoCount.add(DayOfWeek.WEDNESDAY);
		dayOfWeekSetNoCount.add(DayOfWeek.THURSDAY);
		dayOfWeekSetNoCount.add(DayOfWeek.FRIDAY);
		dayOfWeekSetNoCount.add(DayOfWeek.SATURDAY);
		dayOfWeekSetNoCount.add(DayOfWeek.SUNDAY);
		
		// act
		Date fechaInicial = DateUtil.getCurrentDate();		

		// assert
		try{
			DateUtil.addDays(fechaInicial, DIAS_MAYOR_LIMITE, dayOfWeekSetNoCount, true);
		}catch (UnsupportedOperationException e) {
			Assert.assertEquals(DateUtil.NO_SE_PUEDEN_EXCLUIR_TODOS, e.getMessage());
		}
	}

	
}
