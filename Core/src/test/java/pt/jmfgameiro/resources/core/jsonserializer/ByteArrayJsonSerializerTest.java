package pt.jmfgameiro.resources.core.jsonserializer;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author João Gameiro
 *
 */
public final class ByteArrayJsonSerializerTest {
	
	/***** CONSTANTS *****/
	private static final byte[] MEGABYTES = new byte[ 1_500_000 ];
	private static final String MEGABYTES_RESULT = "\"1.43 MB\"";
	private static final byte[] KILOBYTES = new byte[ 1_500 ];
	private static final String KILOBYTES_RESULT = "\"1.46 KB\"";
	private static final byte[] BYTES = new byte[ 500 ];
	private static final String BYTES_RESULT = "\"500 B\"";
	private static final Gson GSONFORMATTER;
	
	
	/***** STATIC *****/
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter( byte[].class, new ByteArrayJsonSerializer() );
		GSONFORMATTER = gsonBuilder.create();
	}
	
	
	/***** TESTS *****/
	/**
	 * 
	 */
	@Test
	public void megaBytesTest() {
		String megaBytesStr = GSONFORMATTER.toJson( MEGABYTES );
		assertEquals( megaBytesStr, MEGABYTES_RESULT );
		System.out.println( megaBytesStr );
	}
	/**
	 * 
	 */
	@Test
	public void kiloBytesTest() {
		String kiloBytesStr = GSONFORMATTER.toJson( KILOBYTES );
		assertEquals( kiloBytesStr, KILOBYTES_RESULT );
		System.out.println( kiloBytesStr );
	}
	/**
	 * 
	 */
	@Test
	public void bytesTest() {
		String bytesStr = GSONFORMATTER.toJson( BYTES );
		assertEquals( bytesStr, BYTES_RESULT );
		System.out.println( bytesStr );
	}
	
	
}
