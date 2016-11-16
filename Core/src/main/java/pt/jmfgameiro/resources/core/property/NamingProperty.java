package pt.jmfgameiro.resources.core.property;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import pt.jmfgameiro.resources.core.validator.StringValidator;

/**
 * @author João Gameiro
 *
 * @param <T>
 */
public final class NamingProperty< T > implements Property< T > {
	
	/***** CONSTANTS *****/
	private final String propertyName;
	private final T property;
	
	
	/***** CONSTRUCTOR *****/
	/**
	 * @param propertyName
	 * @throws NamingException 
	 */
	@SuppressWarnings( "unchecked" )
	public NamingProperty( String propertyName ) {
		if( StringValidator.isNullOrEmpty( propertyName ) )
			throw new IllegalArgumentException( "The propertyName cannot be null or empty." );
		
		this.propertyName = propertyName;
		
		Context context = null;
		try {
			context = new InitialContext();
			Object object = context.lookup( propertyName );
			if( object == null )
				throw new IllegalArgumentException( "There is no JNDI property with name: " + propertyName + "." );
			else
				this.property = ( T )object;
		} catch( ClassCastException | NamingException ex ) {
			throw new IllegalArgumentException( "There was a problem getting the JNDI property: " + propertyName + ". Exception: " + ex.getMessage(), ex );
		} finally {
			try {
				if( context != null )
					context.close();
			} catch( NamingException e ) {}
		}
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
