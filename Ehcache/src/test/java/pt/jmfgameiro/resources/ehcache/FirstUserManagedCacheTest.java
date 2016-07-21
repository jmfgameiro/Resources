package pt.jmfgameiro.resources.ehcache;

import static org.junit.Assert.*;

import org.ehcache.UserManagedCache;
import org.ehcache.config.builders.UserManagedCacheBuilder;
import org.junit.After;
import org.junit.Test;

public class FirstUserManagedCacheTest {
	
	/***** CONSTANTS *****/
	private static final UserManagedCache< Long, String > MANAGER;
	private static final Long _KEY = 123L;
	private static final String _VALUE = "My User Manager Cache Value!";
	
	
	/***** STATIC *****/
	static {
		MANAGER = UserManagedCacheBuilder.newUserManagedCacheBuilder( Long.class, String.class ).build();
		MANAGER.init();
		MANAGER.put( _KEY, _VALUE );
	}
	
	
	/***** TESTS *****/
	@Test
	public void created() {
		String actualValue = MANAGER.get( _KEY );
		assertEquals( _VALUE, actualValue );
		System.out.println( "ACTUAL VALUE: " + actualValue );
	}
	
	
	/***** AFTER *****/
	@After
	public void end() {
		MANAGER.close();
	}
	
	
}
