package pt.jmfgameiro.resources.core.jsonserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ByteArrayJsonSerializer implements JsonSerializer< byte[] > {
	
	
	@Override
	public JsonElement serialize( byte[] byteArray, Type typeOfSrc, JsonSerializationContext context ) {
		if( byteArray.length > 1_000_000_000 )
			return new JsonPrimitive( String.format( "%.2f GB", (byteArray.length / 1_073_741_824f) ) );
		else if( byteArray.length > 1_000_000 )
			return new JsonPrimitive( String.format( "%.2f MB", (byteArray.length / 1_048_576f) ) );
		else if( byteArray.length > 1_000 )
			return new JsonPrimitive( String.format( "%.2f KB", (byteArray.length / 1_024f) ) );
		else
			return new JsonPrimitive( byteArray.length + " B" );
	}
	
	
}
