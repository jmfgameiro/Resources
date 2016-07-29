package pt.jmfgameiro.resources.classer.factory;

import java.util.ArrayList;
import java.util.List;

import javassist.Modifier;

public final class ClasserBuilderField {
	
	/***** CONSTANTS *****/
	private final String name;
	private final Class< ? > clazz;
	private final int modifier;
	private final List< ClasserBuilderAnnotation > annotations = new ArrayList< ClasserBuilderAnnotation >();
	
	
	/***** CONSTRUCTOR *****/
	public ClasserBuilderField( String name, Class< ? > clazz ) {
		this.name = name;
		this.clazz = clazz;
		this.modifier = Modifier.PRIVATE;
	}
	
	
	/***** PUBLIC *****/
	public ClasserBuilderField addAnnotation( ClasserBuilderAnnotation annotation ) {
		this.annotations.add( annotation );
		return this;
	}
	public ClasserBuilderField removeAnnotation( ClasserBuilderAnnotation annotation ) {
		this.annotations.remove( annotation );
		return this;
	}
	
	
	/***** GETTERS *****/
	public String getName() {
		return this.name;
	}
	public List< ClasserBuilderAnnotation > getAnnotations() {
		return this.annotations;
	}
	public Class< ? > getClazz() {
		return this.clazz;
	}
	public int getModifier() {
		return this.modifier;
	}
	
	
}
