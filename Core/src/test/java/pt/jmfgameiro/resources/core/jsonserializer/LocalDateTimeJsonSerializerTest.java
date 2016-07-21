package pt.jmfgameiro.resources.core.jsonserializer;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class LocalDateTimeJsonSerializerTest {
	
	/***** CONSTANTS *****/
	private static Gson gsonFormater;
	
	
	/***** BEFORE *****/
	@Before
	public void init() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter( byte[].class, new LocalDateTimeJsonSerializer() );
		gsonFormater = gsonBuilder.create();
	}
	
	
	/***** TESTS *****/
	@Test
	public void test() {
		LocalDateTime localDateTime = LocalDateTime.now();
		String localDateTimeStr = gsonFormater.toJson( localDateTime );
		assertEquals( localDateTimeStr, 
				"{\"date\":{\"year\":" + localDateTime.getYear() +
					",\"month\":" + localDateTime.getMonthValue() +
					",\"day\":" + localDateTime.getDayOfMonth() +
				"},\"time\":{\"hour\":" + localDateTime.getHour() +
					",\"minute\":" + localDateTime.getMinute() +
					",\"second\":" + localDateTime.getSecond() +
					",\"nano\":" + localDateTime.getNano() +
				"}}" );
		System.out.println( localDateTimeStr );
	}
	
	
}
