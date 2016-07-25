package pt.jmfgameiro.resources.core.jsonserializer;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@SuppressWarnings( "rawtypes" )
public final class ListJsonSerializer implements JsonSerializer< List > {
	
	
	@Override
	public JsonElement serialize( List src, Type typeOfSrc, JsonSerializationContext context ) {
		Gson gson = new Gson();
		return gson.toJsonTree( src, typeOfSrc );
	}
	
	
}
