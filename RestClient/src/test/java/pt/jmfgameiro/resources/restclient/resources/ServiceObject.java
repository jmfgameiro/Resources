package pt.jmfgameiro.resources.restclient.resources;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class ServiceObject {
	
	/***** CONSTANTS *****/
	private Long id;
	private String name;
	private Double value;
	
	
	/***** CONSTRUCTOR *****/
	public ServiceObject() {}
	public ServiceObject( Long id, String name, Double value ) {
		this.setId(id);
		this.setName(name);
		this.setValue(value);
	}
	
	
	/***** STATIC *****/
	@JsonCreator
	public static ServiceObject toObject( String jsonString ) {
		ServiceObject obj = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			obj = mapper.readValue( jsonString, ServiceObject.class );
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
		return obj;
	}
	public static String toJson( ServiceObject object ) {
		return new Gson().toJson( object );
	}
	public static Map< String, Object > toQuery( ServiceObject object ) {
		Map< String, Object > map = new HashMap< String, Object >();
		map.put( "id", object.getId() );
		map.put( "name", object.getName() );
		map.put( "value", object.getValue() );
		return map;
	}
	
	/***** PUBLIC *****/
	@Override
	public boolean equals( Object obj ) {
		if( !( obj instanceof ServiceObject ) )
			return false;
		if( obj == this )
			return true;
		
		ServiceObject service = ( ServiceObject )obj;
		
		if( this.id != service.getId() )
			return false;
		if( !( this.name.equals( service.getName() ) ) )
			return false;
		if( !( this.value.equals( service.getValue() ) ) )
			return false;
		
		return true;
	}
	
	
	/***** GETTERS *****/
	public Long getId() {
		return this.id;
	}
	public void setId( Long id ) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName( String name ) {
		this.name = name;
	}
	public Double getValue() {
		return this.value;
	}
	public void setValue( Double value ) {
		this.value = value;
	}
	
	
}
