package pt.jmfgameiro.resources.core.jsonserializer;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GregorianCalendarJsonSerializerTest {
	
	/***** CONSTANTS *****/
	private static Gson gsonFormater;
	
	
	/***** BEFORE *****/
	@Before
	public void init() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter( byte[].class, new GregorianCalendarJsonSerializer() );
		gsonFormater = gsonBuilder.create();
	}
	
	
	/***** TESTS *****/
	@Test
	public void test() {
		Calendar calendar = Calendar.getInstance();
		String calendarStr = gsonFormater.toJson( calendar );
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
