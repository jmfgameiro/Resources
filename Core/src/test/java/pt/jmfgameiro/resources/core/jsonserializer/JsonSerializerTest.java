package pt.jmfgameiro.resources.core.jsonserializer;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author João Gameiro
 *
 */
public final class JsonSerializerTest {
	
	/***** CONSTANTS *****/
	private static final Gson GSONFORMATTER;
	
	
	/***** STATIC *****/
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();
		GSONFORMATTER = gsonBuilder.create();
	}
	
	
	/***** TEST *****/
	/**
	 * 
	 */
	@Test
	public void test() {
		String nullStr = GSONFORMATTER.toJson( null );
		assertEquals( "null", nullStr );
		System.out.println( nullStr );
	}
	
	
}
