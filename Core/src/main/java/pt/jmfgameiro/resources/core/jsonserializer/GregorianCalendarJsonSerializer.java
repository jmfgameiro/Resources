package pt.jmfgameiro.resources.core.jsonserializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author João Gameiro
 *
 */
public final class GregorianCalendarJsonSerializer implements JsonSerializer< Calendar > {
	
	/***** CONSTANTS *****/
	private static final String CALENDAR_FULL_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS z";
	
	
	/***** PUBLIC *****/
	/* (non-Javadoc)
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize( Calendar calendar, Type typeOfSrc, JsonSerializationContext context ) {
		SimpleDateFormat dateFormat = new SimpleDateFormat( CALENDAR_FULL_FORMAT );
		return new JsonPrimitive( dateFormat.format( calendar.getTime() ) );
	}
	
	
}
