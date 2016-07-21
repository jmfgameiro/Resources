package pt.jmfgameiro.resources.core.jsonserializer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class ByteArrayJsonSerializerTest {
	
	/***** CONSTANTS *****/
	private static final byte[] megaBytes = new byte[ 1_500_000 ];
	private static final byte[] kiloBytes = new byte[ 1_500 ];
	private static final byte[] bytes = new byte[ 500 ];
	private static Gson gsonFormater;
	
	
	/***** BEFORE *****/
	@Before
	public void init() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter( byte[].class, new ByteArrayJsonSerializer() );
		gsonFormater = gsonBuilder.create();
	}
	
	
	/***** TESTS *****/
	@Test
	public void megaBytesTest() {
		String megaBytesStr = gsonFormater.toJson( megaBytes );
		assertEquals( megaBytesStr, "\"1.43 MB\"" );
		System.out.println( megaBytesStr );
	}
	@Test
	public void kiloBytesTest() {
		String kiloBytesStr = gsonFormater.toJson( kiloBytes );
		assertEquals( kiloBytesStr, "\"1.46 KB\"" );
		System.out.println( kiloBytesStr );
	}
	@Test
	public void bytesTest() {
		String bytesStr = gsonFormater.toJson( bytes );
		assertEquals( bytesStr, "\"500 B\"" );
		System.out.println( bytesStr );
	}
	
	
}
