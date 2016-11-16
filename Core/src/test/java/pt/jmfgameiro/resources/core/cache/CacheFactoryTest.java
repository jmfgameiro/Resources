package pt.jmfgameiro.resources.core.cache;

import static org.junit.Assert.assertEquals;

import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.junit.Test;

/**
 * @author João Gameiro
 *
 */
public class CacheFactoryTest {
	
	/***** CONSTANTS *****/
	private static final String _CACHE = "created";
	private static final Long _KEY = 456L;
	private static final String _VALUE = "My Created Cache Value!";
	
	
	/***** STATIC *****/
	static {
		Cache< Long, String > myCache = CacheFactory.MANAGER.createCache( _CACHE, 
				CacheConfigurationBuilder.newCacheConfigurationBuilder( Long.class, String.class, ResourcePoolsBuilder.heap( 10 ) ) );
		myCache.put( _KEY, _VALUE );
	}
	
	
	/***** TEST *****/
	/**
	 * 
	 */
	@Test
	public void test() {
		Cache< Long, String > myCache = CacheFactory.MANAGER.getCache( _CACHE, Long.class, String.class );
		String actualValue = myCache.get( _KEY );
		assertEquals( _VALUE, actualValue );
		System.out.println( "ACTUAL VALUE: " + actualValue );
	}
	
	
}
