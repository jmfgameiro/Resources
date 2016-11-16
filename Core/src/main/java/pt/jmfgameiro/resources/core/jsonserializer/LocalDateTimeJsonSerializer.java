package pt.jmfgameiro.resources.core.jsonserializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author João Gameiro
 *
 */
public final class LocalDateTimeJsonSerializer implements JsonSerializer< LocalDateTime > {
	
	
	/***** PUBLIC *****/
	/* (non-Javadoc)
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public final JsonElement serialize( LocalDateTime localDateTime, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive( localDateTime.toString() );
	}
	
	
}
