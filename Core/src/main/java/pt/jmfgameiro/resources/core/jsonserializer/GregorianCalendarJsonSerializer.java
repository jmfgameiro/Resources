package pt.jmfgameiro.resources.core.jsonserializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GregorianCalendarJsonSerializer implements JsonSerializer< Calendar > {
	
	
	@Override
	public JsonElement serialize( Calendar calendar, Type typeOfSrc, JsonSerializationContext context ) {
		SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss.SSS z" );
		return new JsonPrimitive( dateFormat.format( calendar.getTime() ) );
	}
	
	
}
