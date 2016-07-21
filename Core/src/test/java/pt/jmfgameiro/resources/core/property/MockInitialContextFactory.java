package pt.jmfgameiro.resources.core.property;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

public final class MockInitialContextFactory implements InitialContextFactory {
	
	/***** CONSTANTS *****/
	private static final Context CONTEXT;
	
	
	/***** STATIC *****/
	static {
		try {
			CONTEXT = new InitialContext( true ) {
				/*****  *****/
				Map< String, Object > bindings = new HashMap< String, Object >();
				
				/*****  *****/
				@Override
				public void bind( String name, Object obj ) throws NamingException {
					bindings.put( name, obj );
				}
				@Override
				public Object lookup( String name ) throws NamingException {
					return bindings.get( name );
				}
			};
		}
		catch( NamingException e ) {
			throw new RuntimeException( e );
		}
	}
	
	
	/***** PUBLIC *****/
	@Override
	public Context getInitialContext( Hashtable<?, ?> environment ) throws NamingException {
		return CONTEXT;
	}
	public static void bind( String name, Object obj ) {
		try {
			CONTEXT.bind( name, obj );
		}
		catch( NamingException e ) {
			throw new RuntimeException( e );
		}
	}
	

}
