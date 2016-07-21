package pt.jmfgameiro.resources.core.property;

import static org.junit.Assert.*;

import javax.naming.Context;

import org.junit.Before;
import org.junit.Test;

public final class NamingPropertyTest {
	
	/***** CONSTANTS *****/
	private static final String TESTBINDNAME = "test:/Bind";
	private static final String TESTBINDVALUE = "myBindValue";
	private static final String TESTBINDERR1 = "The property value is null!";
	private static final String TESTBINDERR2 = "The property value is different than the given value!";
	private static final String TESTBINDMSG = "Property Value: ";
	
	
	/***** BEFORE *****/
	@Before
	public void init() {
		// References: http://en.newinstance.it/2009/03/27/mocking-jndi/
		System.setProperty( Context.INITIAL_CONTEXT_FACTORY, MockInitialContextFactory.class.getName() );
		MockInitialContextFactory.bind( TESTBINDNAME, TESTBINDVALUE );
	}
	
	
	/***** TEST *****/
	@Test
	public void test() {
		NamingProperty< String > test = new NamingProperty<String>( TESTBINDNAME );
		assertNotNull( TESTBINDERR1, test.getProperty() );
		assertEquals( TESTBINDERR2, TESTBINDVALUE, test.getProperty() );
		System.out.println( TESTBINDMSG + test.getProperty() );
	}
	
	
}
