package pt.jmfgameiro.resources.ehcache;

import static org.junit.Assert.*;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.junit.After;
import org.junit.Test;

public class FirstManagedCacheTest {
	
	/***** CONSTANTS *****/
	private static final CacheManager MANAGER;
	private static final String _CACHE = "created";
	private static final Long _KEY = 456L;
	private static final String _VALUE = "My Created Cache Value!";
	
	
	/***** STATIC *****/
	static {
		MANAGER = CacheManagerBuilder.newCacheManagerBuilder().build();
		MANAGER.init();
		Cache< Long, String > myCache = MANAGER.createCache( _CACHE,
				CacheConfigurationBuilder.newCacheConfigurationBuilder( Long.class, String.class, ResourcePoolsBuilder.heap( 10 ) ).build() );
		myCache.put( _KEY, _VALUE );
	}
	
	
	/***** TESTS *****/
	@Test
	public void created() {
		Cache< Long, String > myCache = MANAGER.getCache( _CACHE, Long.class, String.class );
		String actualValue = myCache.get( _KEY );
		assertEquals( _VALUE, actualValue );
		System.out.println( "ACTUAL VALUE: " + actualValue );
	}
	
	
	/***** AFTER *****/
	@After
	public void end() {
		MANAGER.removeCache( _CACHE );
		MANAGER.close();
	}
	
	
}
