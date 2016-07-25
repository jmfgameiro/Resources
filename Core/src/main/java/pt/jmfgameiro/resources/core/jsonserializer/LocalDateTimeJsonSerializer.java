package pt.jmfgameiro.resources.core.jsonserializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public final class LocalDateTimeJsonSerializer implements JsonSerializer< LocalDateTime > {
	
	
	@Override
	public JsonElement serialize( LocalDateTime localDateTime, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive( localDateTime.toString() );
	}
	
	
}
