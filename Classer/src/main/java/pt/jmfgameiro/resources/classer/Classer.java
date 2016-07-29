package pt.jmfgameiro.resources.classer;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.Modifier;

public final class Classer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	/***** CONSTANTS *****/
	private final Class< ? > clazz;
	private final Map< String, ClasserField > fields;
	private final Map< String, ClasserMethod > methods;
	
	
	/***** CONSTRUCTOR *****/
	public Classer( Class< ? > clazz, Map< String, ClasserField > fields, Map< String, ClasserMethod > methods ) {
		this.clazz = clazz;
		this.fields = Collections.unmodifiableMap( new HashMap<>( fields ) );
		this.methods = Collections.unmodifiableMap( new HashMap<>( methods ) );
	}
	
	
	/***** PUBLIC *****/
	public Object newObject() throws Exception {
		return this.clazz.newInstance();
	}
	
	public Object getField( Object obj, String fieldName ) throws Exception {
		String methodName = "get" + fieldName.substring( 0, 1 ).toUpperCase() + fieldName.substring( 1 );
		Class< ? >[] formalParams = new Class< ? >[] {};
		Method method = this.clazz.getDeclaredMethod( methodName, formalParams );
		//
		Object[] actualParams = new Object[] {};
		return method.invoke( obj, actualParams );
	}
	public void setField( Object obj, String fieldName, Object value ) throws Exception {
		ClasserField field = this.fields.get( fieldName );
		String methodName = "set" + fieldName.substring( 0, 1 ).toUpperCase() + fieldName.substring( 1 );
		Class< ? >[] formalParams = new Class< ? >[] { field.getClazz() };
		Method method = this.clazz.getDeclaredMethod( methodName, formalParams );
		//
		Object[] actualParams = new Object[] { value };
		method.invoke( obj, actualParams );
	}
	
	public Object getMethod( Object obj, String methodName, List< Object > parameters ) throws Exception {
		ClasserMethod methodClasser = this.methods.get( methodName );
		if( methodClasser.getModifier() != Modifier.PUBLIC )
			throw new IllegalArgumentException( "The requested method is not PUBLIC!" );
		//
		Class< ? >[] formalParamsList;
		Object[] actualParamsList;
		if( parameters != null ) {
			formalParamsList = new Class< ? >[ parameters.size() ];
			actualParamsList = new Object[ parameters.size() ];
			for( int i = 0; i < parameters.size(); i++ ) {
				formalParamsList[ i ] = parameters.get( i ).getClass();
				actualParamsList[ i ] = parameters.get( i );
			}
		}
		else {
			formalParamsList = new Class< ? >[] {};
			actualParamsList = new Object[] {};
		}
		//
		Method method = this.clazz.getDeclaredMethod( methodName, formalParamsList );
		return method.invoke( obj, actualParamsList );
	}
	
	
	/***** GETTERS *****/
	public Class< ? > getClazz() {
		return clazz;
	}
	public List< String > getFields() {
		return new ArrayList< String >( this.fields.keySet() );
	}
	public List< String > getMethods() {
		return new ArrayList< String >( this.methods.keySet() );
	}
	
	
}
