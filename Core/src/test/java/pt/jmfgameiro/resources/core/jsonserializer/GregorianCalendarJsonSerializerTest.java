package pt.jmfgameiro.resources.core.jsonserializer;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author João Gameiro
 *
 */
public final class GregorianCalendarJsonSerializerTest {
	
	/***** CONSTANTS *****/
	private static final Gson GSONFORMATTER;
	
	
	/***** STATIC *****/
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter( byte[].class, new GregorianCalendarJsonSerializer() );
		GSONFORMATTER = gsonBuilder.create();
	}
	
	
	/***** TESTS *****/
	/**
	 * 
	 */
	@Test
	public void test() {
		Calendar calendar = Calendar.getInstance();
		String calendarStr = GSONFORMATTER.toJson( calendar );
		assertEquals( calendarStr, 
				"{\"year\":" + calendar.get( Calendar.YEAR ) +
				",\"month\":" + calendar.get( Calendar.MONTH ) +
				",\"dayOfMonth\":" + calendar.get( Calendar.DAY_OF_MONTH ) +
				",\"hourOfDay\":" + calendar.get( Calendar.HOUR_OF_DAY ) +
				",\"minute\":" + calendar.get( Calendar.MINUTE ) +
				",\"second\":" + calendar.get( Calendar.SECOND ) +
				"}" );
		System.out.println( calendarStr );
	}
	
	
}
