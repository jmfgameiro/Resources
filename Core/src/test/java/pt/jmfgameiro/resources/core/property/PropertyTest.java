package pt.jmfgameiro.resources.core.property;

import static org.junit.Assert.*;

import javax.naming.Context;

import org.junit.Before;
import org.junit.Test;

/**
 * @author João Gameiro
 *
 */
public final class PropertyTest {
	
	/***** CONSTANTS *****/
	private static final String SYSTEM_PRP = "java.version";
	private static final String SYSTEM_ERR = "The property value is null!";
	private static final String SYSTEM_MSG = "Java Version: ";
	private static final String NAMING_NAME = "test:/Bind";
	private static final String NAMING_VALUE = "myBindValue";
	private static final String NAMING_ERR1 = "The property value is null!";
	private static final String NAMING_ERR2 = "The property value is different than the given value!";
	private static final String NAMING_MSG = "Property Value: ";
	
	
	/***** BEFORE *****/
	/**
	 * 
	 */
	@Before
	public void init() {
		// References: http://en.newinstance.it/2009/03/27/mocking-jndi/
		System.setProperty( Context.INITIAL_CONTEXT_FACTORY, MockInitialContextFactory.class.getName() );
		MockInitialContextFactory.bind( NAMING_NAME, NAMING_VALUE );
	}
	
	
	/***** TEST *****/
	/**
	 * 
	 */
	@Test
	public void systemPropertyTest() {
		Property< String > javaVersion = new SystemProperty< String >( SYSTEM_PRP );
		assertNotNull( SYSTEM_ERR, javaVersion.getProperty() );
		System.out.println( SYSTEM_MSG + javaVersion.getProperty() );
	}
	
	/**
	 * 
	 */
	@Test
	public void namingPropertyTest() {
		NamingProperty< String > test = new NamingProperty<String>( NAMING_NAME );
		assertNotNull( NAMING_ERR1, test.getProperty() );
		assertEquals( NAMING_ERR2, NAMING_VALUE, test.getProperty() );
		System.out.println( NAMING_MSG + test.getProperty() );
	}
	
}
