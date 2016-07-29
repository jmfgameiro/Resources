package pt.jmfgameiro.resources.classer.factory;

import java.util.ArrayList;
import java.util.List;

public final class ClasserBuilder {
	
	/***** CONSTANTS *****/
	private final String name;
	private final List< ClasserBuilderAnnotation > annotations = new ArrayList< ClasserBuilderAnnotation >();
	private final List< ClasserBuilderField > fields = new ArrayList< ClasserBuilderField >();
	private final List< ClasserBuilderMethod > methods = new ArrayList< ClasserBuilderMethod >();
	
	
	/***** CONSTRUCTOR *****/
	public ClasserBuilder( String name ) {
		this.name = name;
	}
	
	
	/***** PUBLIC *****/
	public ClasserBuilder addAnnotation( ClasserBuilderAnnotation annotation ) {
		this.annotations.add( annotation );
		return this;
	}
	public ClasserBuilder addField( ClasserBuilderField field ) {
		this.fields.add( field );
		return this;
	}
	public ClasserBuilder addMethod( ClasserBuilderMethod method ) {
		this.methods.add( method );
		return this;
	}
	
	
	/***** GETTERS *****/
	public String getName() {
		return this.name;
	}
	public List< ClasserBuilderAnnotation > getAnnotations() {
		return this.annotations;
	}
	public List< ClasserBuilderField > getFields() {
		return this.fields;
	}
	public List< ClasserBuilderMethod > getMethods() {
		return methods;
	}
	
	
}
