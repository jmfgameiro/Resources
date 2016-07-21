package pt.jmfgameiro.resources.ehcache;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;

public final class CacheFactory {
	
	
	public static final CacheManager MANAGER = CacheManagerBuilder.newCacheManagerBuilder().build( true );
	
	
}
