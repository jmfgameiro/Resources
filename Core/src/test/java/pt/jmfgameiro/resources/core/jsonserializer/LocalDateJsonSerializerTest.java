package pt.jmfgameiro.resources.core.jsonserializer;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class LocalDateJsonSerializerTest {
	
	/***** CONSTANTS *****/
	private static Gson gsonFormater;
	
	
	/***** BEFORE *****/
	@Before
	public void init() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter( byte[].class, new LocalDateJsonSerializer() );
		gsonFormater = gsonBuilder.create();
	}
	
	
	/***** TESTS *****/
	@Test
	public void test() {
		LocalDate localDate = LocalDate.now();
		String localDateStr = gsonFormater.toJson( localDate );
		assertEquals( localDateStr, 
				"{\"year\":" + localDate.getYear() +
				",\"month\":" + localDate.getMonthValue() +
				",\"day\":" + localDate.getDayOfMonth() +
				"}" );
		System.out.println( localDateStr );
	}
	
	
}
