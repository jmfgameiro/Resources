package pt.jmfgameiro.resources.classer.factory;

import java.util.ArrayList;
import java.util.List;

import javassist.Modifier;

public final class ClasserBuilderMethod {
	
	/***** CONSTANTS *****/
	private final String name;
	private final Class< ? > returnClazz;
	private final int modifier;
	private final List< ClasserBuilderAnnotation > annotations = new ArrayList< ClasserBuilderAnnotation >();
	private final List< ClasserBuilderField > parameters = new ArrayList< ClasserBuilderField >();
	private final String body;
	
	
	/***** CONSTRUCTOR *****/
	public ClasserBuilderMethod( String name, String body ) {
		this.name = name;
		this.returnClazz = null;
		this.modifier = Modifier.PUBLIC;
		this.body = body;
	}
	public ClasserBuilderMethod( String name, int modifier, String body ) {
		this.name = name;
		this.returnClazz = null;
		this.modifier = modifier;
		this.body = body;
	}
	public ClasserBuilderMethod( String name, Class< ? > returnClazz, String body ) {
		this.name = name;
		this.returnClazz = returnClazz;
		this.modifier = Modifier.PUBLIC;
		this.body = body;
	}
	public ClasserBuilderMethod( String name, Class< ? > returnClazz, int modifier, String body ) {
		this.name = name;
		this.returnClazz = returnClazz;
		this.modifier = modifier;
		this.body = body;
	}
	
	
	/***** PUBLIC *****/
	public ClasserBuilderMethod addAnnotation( ClasserBuilderAnnotation annotation ) {
		this.annotations.add( annotation );
		return this;
	}
	public ClasserBuilderMethod removeAnnotation( ClasserBuilderAnnotation annotation ) {
		this.annotations.remove( annotation );
		return this;
	}
	public ClasserBuilderMethod addParameter( ClasserBuilderField parameter ) {
		this.parameters.add( parameter );
		return this;
	}
	public ClasserBuilderMethod removeParameter( ClasserBuilderField parameter ) {
		this.parameters.remove( parameter );
		return this;
	}
	
	
	/***** GETTERS *****/
	public String getName() {
		return this.name;
	}
	public Class< ? > getReturnClazz() {
		return returnClazz;
	}
	public int getModifier() {
		return modifier;
	}
	public List< ClasserBuilderAnnotation > getAnnotations() {
		return this.annotations;
	}
	public List< ClasserBuilderField > getParameters() {
		return parameters;
	}
	public String getBody() {
		return body;
	}
	
	
}
