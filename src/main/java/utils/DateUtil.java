package utils;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import org.joda.time.DateTime;

public final class DateUtil {
	
	public static final String NO_SE_PUEDEN_EXCLUIR_TODOS = "no se pueden excluir todos los días de la semana";
	
	private DateUtil() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * funcion que calcula una fecha sin tener en cuenta un conjunto de días específico
	 * y si se tiene o no en cuenta el último día como domingo 
	 * @author Cesar Plata 
	 * @param date fecha Inicial
	 * @param days dias a adicionar
	 * @param dayOfWeekSetNoCount Set de días que no se deben tener encuenta
	 * @param countSunday si cae domingo se debe sumar o no
	 * @date Jan 15, 2018 
	 * @return date 
	 */
	public static Date addDays(Date date, int days, Set<DayOfWeek> dayOfWeekSetNoCount, Boolean noCountSunday) {
		
		if(dayOfWeekSetNoCount.size() == 7){
			throw new UnsupportedOperationException(NO_SE_PUEDEN_EXCLUIR_TODOS);
		}
		
	    if (days < 1) {
	        return date;
	    }

	    LocalDate result = Instant.ofEpochMilli(date.getTime())
	    	      .atZone(ZoneId.systemDefault())
	    	      .toLocalDate();
	    int addedDays = 0;
	    while (addedDays < days) {
	        result = result.plusDays(1);
	        if (!(dayOfWeekSetNoCount.contains(result.getDayOfWeek()))) {
	            ++addedDays;
	        }
	    }
	    
	    if(result.getDayOfWeek() == DayOfWeek.SUNDAY && noCountSunday){
	    	result = result.plusDays(1);
	    }

	    return java.util.Date.from(result.atStartOfDay()
	    	      .atZone(ZoneId.systemDefault())
	    	      .toInstant());
	}
	
	public static Date getCurrentDate(){
		return DateTime.now().toDate();
	}
}
