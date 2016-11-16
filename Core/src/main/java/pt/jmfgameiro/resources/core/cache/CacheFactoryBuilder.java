package pt.jmfgameiro.resources.core.cache;

import java.util.concurrent.TimeUnit;

import org.ehcache.config.units.MemoryUnit;

import pt.jmfgameiro.resources.core.property.Property;

/**
 * @author João Gameiro
 *
 */
public class CacheFactoryBuilder {
	
	
	/***** CONSTANTS *****/
	private final Long cacheSizeValue;
	private final MemoryUnit cacheSizeUnit;
	private final Long cacheExpirationValue;
	private final TimeUnit cacheExpirationUnit;
	
	
	/***** CONSTRUCTOR *****/
	/**
	 * 
	 */
	public CacheFactoryBuilder() {
		this.cacheSizeValue = 5L;
		this.cacheSizeUnit = MemoryUnit.MB;
		this.cacheExpirationValue = 10L;
		this.cacheExpirationUnit = TimeUnit.MINUTES;
	}
	/**
	 * @param cacheSizeValue
	 * @param cacheSizeUnit
	 * @param cacheExpirationValue
	 * @param cacheExpirationUnit
	 */
	public CacheFactoryBuilder( Long cacheSizeValue, String cacheSizeUnit, Long cacheExpirationValue, String cacheExpirationUnit ) {
		validateCache( cacheSizeValue, cacheSizeUnit, cacheExpirationValue, cacheExpirationUnit );
		this.cacheSizeValue = cacheSizeValue;
		this.cacheSizeUnit = MemoryUnit.valueOf( cacheSizeUnit );
		this.cacheExpirationValue = cacheExpirationValue;
		this.cacheExpirationUnit = TimeUnit.valueOf( cacheExpirationUnit );
	}
	/**
	 * @param cacheSizeValueProperty
	 * @param cacheSizeUnitProperty
	 * @param cacheExpirationValueProperty
	 * @param cacheExpirationUnitProperty
	 */
	public CacheFactoryBuilder( Property< Long > cacheSizeValueProperty, Property< String > cacheSizeUnitProperty, Property< Long > cacheExpirationValueProperty, Property< String > cacheExpirationUnitProperty ) {
		validateCache( cacheSizeValueProperty, cacheSizeUnitProperty, cacheExpirationValueProperty, cacheExpirationUnitProperty );
		this.cacheSizeValue = cacheSizeValueProperty.getProperty();
		this.cacheSizeUnit = MemoryUnit.valueOf( cacheSizeUnitProperty.getProperty() );
		this.cacheExpirationValue = cacheExpirationValueProperty.getProperty();
		this.cacheExpirationUnit = TimeUnit.valueOf( cacheExpirationUnitProperty.getProperty() );
	}
	
	
	/***** PRIVATE *****/
	private void validateCache( Long cacheSizeValue, String cacheSizeUnit, Long cacheExpirationValue, String cacheExpirationUnit ) {
		if( cacheSizeValue == null )
			throw new IllegalArgumentException( "The cacheSizeValue cannot be null" );
		if( cacheSizeValue <= 0 )
			throw new IllegalArgumentException( "The cacheSizeValue cannot be less than zero" );
		if( cacheSizeUnit == null )
			throw new IllegalArgumentException( "The cacheSizeUnit cannot be null" );
		if( cacheExpirationValue == null )
			throw new IllegalArgumentException( "The cacheExpirationValue cannot be null" );
		if( cacheExpirationValue <= 0 )
			throw new IllegalArgumentException( "The cacheExpirationValue cannot be less than zero" );
		if( cacheExpirationUnit == null )
			throw new IllegalArgumentException( "The cacheExpirationUnit cannot be null" );
	}
	private void validateCache( Property< Long > cacheSizeValueProperty, Property< String > cacheSizeUnitProperty, Property< Long > cacheExpirationValueProperty, Property< String > cacheExpirationUnitProperty ) {
		if( cacheSizeValueProperty == null )
			throw new IllegalArgumentException( "The cacheSizeValueProperty cannot be null" );
		if( cacheSizeValueProperty.getProperty() <= 0 )
			throw new IllegalArgumentException( "The cacheSizeValueProperty cannot be less than zero" );
		if( cacheSizeUnitProperty == null )
			throw new IllegalArgumentException( "The cacheSizeUnitProperty cannot be null" );
		if( cacheExpirationValueProperty == null )
			throw new IllegalArgumentException( "The cacheExpirationValueProperty cannot be null" );
		if( cacheExpirationValueProperty.getProperty() <= 0 )
			throw new IllegalArgumentException( "The cacheExpirationValueProperty cannot be less than zero" );
		if( cacheExpirationUnitProperty == null )
			throw new IllegalArgumentException( "The cacheExpirationUnitProperty cannot be null" );
	}
	
	
	/***** GETTERS *****/
	public Long getCacheSizeValue() {
		return cacheSizeValue;
	}
	public MemoryUnit getCacheSizeUnit() {
		return cacheSizeUnit;
	}
	public Long getCacheExpirationValue() {
		return cacheExpirationValue;
	}
	public TimeUnit getCacheExpirationUnit() {
		return cacheExpirationUnit;
	}
	
	
}
