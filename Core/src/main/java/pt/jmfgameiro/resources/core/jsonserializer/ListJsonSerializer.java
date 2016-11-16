package pt.jmfgameiro.resources.core.jsonserializer;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author João Gameiro
 *
 */
@SuppressWarnings( "rawtypes" )
public final class ListJsonSerializer implements JsonSerializer< List > {
	
	/***** PUBLIC *****/
	/* (non-Javadoc)
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize( List src, Type typeOfSrc, JsonSerializationContext context ) {
		Gson gson = new Gson();
		return gson.toJsonTree( src, typeOfSrc );
	}
	
	
}
