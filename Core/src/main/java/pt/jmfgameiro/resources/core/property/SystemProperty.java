package pt.jmfgameiro.resources.core.property;

public final class SystemProperty< T > {
	
	/***** CONSTANTS *****/
	private final String propertyName;
	private final T property;
	
	
	/***** CONSTRUCTOR *****/
	@SuppressWarnings( "unchecked" )
	public SystemProperty( String propertyName ) {
		if( propertyName == null || propertyName.trim().equals( "" ) )
			throw new IllegalArgumentException( "The propertyName cannot be null or empty." );
		
		this.propertyName = propertyName;
		
		try {
			this.property = ( T )System.getProperty( propertyName );
		}
		catch( ClassCastException cce ) {
			throw new IllegalArgumentException( "The property is not of the type provided." );
		}
		
		if( this.property == null )
			throw new IllegalArgumentException( "There is no System Property with name: " + propertyName + "." );
	}
	
	
	/***** GETTERS *****/
	public String getPropertyName() {
		return propertyName;
	}
	public T getProperty() {
		return property;
	}
	
	
}
