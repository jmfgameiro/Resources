package pt.jmfgameiro.resources.core.property;

import static org.junit.Assert.*;

import javax.naming.Context;

import org.junit.Before;
import org.junit.Test;

public final class NamingPropertyTest {
	
	/***** CONSTANTS *****/
	private static final String testBindName = "test:/Bind";
	private static final String testBindValue = "myBindValue";
	private static final String testBindErr1 = "The property value is null!";
	private static final String testBindErr2 = "The property value is different than the given value!";
	private static final String testBindMsg = "Property Value: ";
	
	
	/***** BEFORE *****/
	@Before
	public void init() {
		// References: http://en.newinstance.it/2009/03/27/mocking-jndi/
		System.setProperty( Context.INITIAL_CONTEXT_FACTORY, MockInitialContextFactory.class.getName() );
		MockInitialContextFactory.bind( testBindName, testBindValue );
	}
	
	
	/***** TEST *****/
	@Test
	public void test() {
		NamingProperty< String > test = new NamingProperty<String>( testBindName );
		assertNotNull( testBindErr1, test.getProperty() );
		assertEquals( testBindErr2, testBindValue, test.getProperty() );
		System.out.println( testBindMsg + test.getProperty() );
	}
	
	
}
