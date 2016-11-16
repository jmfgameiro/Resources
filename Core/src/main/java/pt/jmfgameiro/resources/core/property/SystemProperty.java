package pt.jmfgameiro.resources.core.property;

import pt.jmfgameiro.resources.core.validator.StringValidator;

/**
 * @author João Gameiro
 *
 * @param <T>
 */
public final class SystemProperty< T > implements Property< T > {
	
	/***** CONSTANTS *****/
	private final String propertyName;
	private final T property;
	
	
	/***** CONSTRUCTOR *****/
	/**
	 * @param propertyName
	 */
	@SuppressWarnings( "unchecked" )
	public SystemProperty( String propertyName ) {
		if( StringValidator.isNullOrEmpty( propertyName ) )
			throw new IllegalArgumentException( "The propertyName cannot be null or empty." );
		
		this.propertyName = propertyName;
		
		try {
			this.property = ( T )System.getProperty( propertyName );
		}
		catch( ClassCastException cce ) {
			throw new IllegalArgumentException( "The property is not of the type provided.", cce );
		}
		
		if( this.property == null )
			throw new IllegalArgumentException( "There is no System Property with name: " + propertyName + "." );
	}
	
	
	/***** GETTERS *****/
	/**
	 * @return
	 */
	public String getPropertyName() {
		return propertyName;
	}
	/* (non-Javadoc)
	 * @see pt.jmfgameiro.resources.core.property.Property#getProperty()
	 */
	@Override
	public T getProperty() {
		return property;
	}
	
	
}
