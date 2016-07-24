package pt.jmfgameiro.resources.core.jsonserializer;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class LocalDateJsonSerializerTest {
	
	/***** CONSTANTS *****/
	private static final Gson GSONFORMATTER;
	
	
	/***** STATIC *****/
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter( byte[].class, new LocalDateJsonSerializer() );
		GSONFORMATTER = gsonBuilder.create();
	}
	
	
	/***** TESTS *****/
	@Test
	public void test() {
		LocalDate localDate = LocalDate.now();
		String localDateStr = GSONFORMATTER.toJson( localDate );
		assertEquals( localDateStr, 
				"{\"year\":" + localDate.getYear() +
				",\"month\":" + localDate.getMonthValue() +
				",\"day\":" + localDate.getDayOfMonth() +
				"}" );
		System.out.println( localDateStr );
	}
	
	
}
