package pt.jmfgameiro.resources.core.jsonserializer;

import java.lang.reflect.Type;
import java.time.LocalDate;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public final class LocalDateJsonSerializer implements JsonSerializer< LocalDate > {
	
	
	@Override
	public JsonElement serialize( LocalDate localDate, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive( localDate.toString() );
	}
	
	
}
