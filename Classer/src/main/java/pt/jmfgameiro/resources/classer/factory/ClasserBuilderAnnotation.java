package pt.jmfgameiro.resources.classer.factory;

import java.util.HashMap;
import java.util.Map;

public final class ClasserBuilderAnnotation {
	
	/***** CONSTANTS *****/
	private final Class< ? > clazz;
	private final Map< String, Object > values = new HashMap< String, Object >();
	
	
	/***** CONSTRUCTOR *****/
	public ClasserBuilderAnnotation( Class< ? > clazz ) {
		this.clazz = clazz;
	}
	
	
	/***** PUBLIC *****/
	public ClasserBuilderAnnotation addValue( String name, Object value ) {
		this.values.put( name, value );
		return this;
	}
	public ClasserBuilderAnnotation removeValue( String name ) {
		this.values.remove( name );
		return this;
	}
	
	
	/***** GETTERS *****/
	public Class< ? > getClazz() {
		return this.clazz;
	}
	public Map< String, Object > getValues() {
		return values;
	}
	
	
}
