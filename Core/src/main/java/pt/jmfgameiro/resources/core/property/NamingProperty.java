package pt.jmfgameiro.resources.core.property;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public final class NamingProperty< T > implements Property< T > {
	
	/***** CONSTANTS *****/
	private final String propertyName;
	private final T property;
	
	
	/***** CONSTRUCTOR *****/
	@SuppressWarnings( "unchecked" )
	public NamingProperty( String propertyName ) {
		if( propertyName == null || propertyName.trim().equals( "" ) )
			throw new IllegalArgumentException( "The propertyName cannot be null or empty." );
		
		this.propertyName = propertyName;
		
		Context context = null;
		try {
			context = new InitialContext();
			Object object = context.lookup( propertyName );
			if( object == null )
				throw new IllegalArgumentException( "There is no JNDI property with name: " + propertyName + "." );
			else {
				try {
					this.property = ( T )object;
				}
				catch( ClassCastException cce ) {
					throw new IllegalArgumentException( "The property is not of the type provided." );
				}
			}
		}
		catch( NamingException ne ) {
			throw new IllegalArgumentException( "There was an NamingException while getting the JNDI property: " + propertyName + ". Exception: " + ne.getMessage() );
		}
		finally {
			try {
				context.close();
			}
			catch( NamingException ne ) {} // Nothing you can do about it
		}
	}
	
	
	/***** GETTERS *****/
	public String getPropertyName() {
		return propertyName;
	}
	public T getProperty() {
		return property;
	}
	
}
