package pt.jmfgameiro.resources.core.jsonserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author João Gameiro
 *
 */
public final class ByteArrayJsonSerializer implements JsonSerializer< byte[] > {
	
	/***** CONSTANTS *****/
	private static final int GB_LENGTH = 1_000_000_000;
	private static final float GB_CALC = 1_073_741_824f;
	private static final String GB_STR = "%.2f GB";
	
	private static final int MB_LENGTH = 1_000_000;
	private static final float MB_CALC = 1_048_576f;
	private static final String MB_STR = "%.2f MB";
	
	private static final int KB_LENGTH = 1_000;
	private static final float KB_CALC = 1_024f;
	private static final String KB_STR = "%.2f KB";
	
	private static final String B_STR = " B";
	
	
	/***** PUBLIC *****/
	/* (non-Javadoc)
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize( byte[] byteArray, Type typeOfSrc, JsonSerializationContext context ) {
		if( byteArray.length > GB_LENGTH )
			return new JsonPrimitive( String.format( GB_STR, byteArray.length / GB_CALC ) );
		else if( byteArray.length > MB_LENGTH )
			return new JsonPrimitive( String.format( MB_STR, byteArray.length / MB_CALC ) );
		else if( byteArray.length > KB_LENGTH )
			return new JsonPrimitive( String.format( KB_STR, byteArray.length / KB_CALC ) );
		else
			return new JsonPrimitive( byteArray.length + B_STR );
	}
	
	
}
