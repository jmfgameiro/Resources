package pt.jmfgameiro.resources.core.jsonserializer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class JsonSerializerTest {
	
	/***** CONSTANTS *****/
	private static Gson gsonFormater;
	
	
	/***** BEFORE *****/
	@Before
	public void init() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();
		gsonFormater = gsonBuilder.create();
	}
	
	
	/***** TEST *****/
	@Test
	public void test() {
		String nullStr = gsonFormater.toJson( null );
		assertEquals( "null", nullStr );
		System.out.println( nullStr );
	}
	
	
}
