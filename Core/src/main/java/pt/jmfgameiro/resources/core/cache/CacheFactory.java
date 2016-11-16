package pt.jmfgameiro.resources.core.cache;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;

/**
 * @author João Gameiro
 *
 */
public final class CacheFactory {
	
	/***** CONSTANTS *****/
	public static final CacheManager MANAGER = CacheManagerBuilder.newCacheManagerBuilder().build( true );
	
	
	/***** CONSTANTS *****/
	private CacheFactory() {}
	
	
}
