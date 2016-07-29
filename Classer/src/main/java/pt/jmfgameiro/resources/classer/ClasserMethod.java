package pt.jmfgameiro.resources.classer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ClasserMethod {
	
	/***** CONSTANTS *****/
	private final Class< ? > returnClazz;
	private final int modifier;
	private final List< ClasserField > parameters;
	
	
	/***** CONSTRUCTOR *****/
	public ClasserMethod( Class< ? > returnClazz, int modifier, List< ClasserField > parameters ) {
		this.returnClazz = returnClazz;
		this.modifier = modifier;
		this.parameters = Collections.unmodifiableList( new ArrayList< ClasserField >( parameters ) );
	}
	
	
	/***** GETTERS *****/
	public Class< ? > getReturnClazz() {
		return returnClazz;
	}
	public int getModifier() {
		return modifier;
	}
	public List< ClasserField > getParameters() {
		return new ArrayList< ClasserField >( parameters );
	}


	
	
}
