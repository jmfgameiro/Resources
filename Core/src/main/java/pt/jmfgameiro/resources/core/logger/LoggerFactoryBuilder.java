package pt.jmfgameiro.resources.core.logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import pt.jmfgameiro.resources.core.factory.FactoryTimePolicy;
import pt.jmfgameiro.resources.core.property.Property;
import pt.jmfgameiro.resources.core.cache.CacheFactoryBuilder;

/**
 * @author João Gameiro
 *
 */
public final class LoggerFactoryBuilder extends CacheFactoryBuilder {
	
	/***** CONSTANTS *****/
	private static final String RESOURCE = "/";
	private static final String PROPERTY = "os.name";
	private static final String PROPERTY_CONTAINS = "indow";
	private final String logName;
	
	
	/***** VARIABLES *****/
	private String patternLayout = "%-5level {%d{HH:mm:ss.SSS}} [%logger] - %msg%n";
	private Level level = Level.INFO;
	
	private boolean fileAppender = false;
	private Path filePath;
	private String fileExtension = "log";
	private FactoryTimePolicy timePolicy = FactoryTimePolicy.DAILY;
	private int maxHistory = 90;
	
	private boolean consoleAppender = false;
	
	private List< AppenderBase< ILoggingEvent > > customAppenders = new ArrayList<>();
	
	
	/***** CONSTRUCTOR *****/
	/**
	 * @param logName
	 */
	public LoggerFactoryBuilder( String logName ) {
		super();
		String path = this.getClass().getResource( RESOURCE ).getPath();
		this.filePath = Paths.get( System.getProperty( PROPERTY ).contains( PROPERTY_CONTAINS ) ? path.substring( 1 ): path );
		this.logName = logName;
	}
	/**
	 * @param logName
	 * @param cacheSizeValue
	 * @param cacheSizeUnit
	 * @param cacheExpirationValue
	 * @param cacheExpirationUnit
	 */
	public LoggerFactoryBuilder( String logName, Long cacheSizeValue, String cacheSizeUnit, Long cacheExpirationValue, String cacheExpirationUnit ) {
		super( cacheSizeValue, cacheSizeUnit, cacheExpirationValue, cacheExpirationUnit );
		String path = this.getClass().getResource( RESOURCE ).getPath();
		this.filePath = Paths.get( System.getProperty( PROPERTY ).contains( PROPERTY_CONTAINS ) ? path.substring( 1 ): path );
		this.logName = logName;
	}
	/**
	 * @param logName
	 * @param cacheSizeValueProperty
	 * @param cacheSizeUnitProperty
	 * @param cacheExpirationValueProperty
	 * @param cacheExpirationUnitProperty
	 */
	public LoggerFactoryBuilder( String logName, Property< Long > cacheSizeValueProperty, Property< String > cacheSizeUnitProperty, Property< Long > cacheExpirationValueProperty, Property< String > cacheExpirationUnitProperty ) {
		super( cacheSizeValueProperty, cacheSizeUnitProperty, cacheExpirationValueProperty, cacheExpirationUnitProperty );
		String path = this.getClass().getResource( RESOURCE ).getPath();
		this.filePath = Paths.get( System.getProperty( PROPERTY ).contains( PROPERTY_CONTAINS ) ? path.substring( 1 ): path );
		this.logName = logName;
	}
	
	
	/***** PUBLIC *****/
	/**
	 * @return
	 */
	public LoggerFactoryBuilder cleanCustomAppender() {
		this.customAppenders = new ArrayList<>();
		return this;
	}
	/**
	 * @param appender
	 * @return
	 */
	public LoggerFactoryBuilder addCustomAppender( AppenderBase< ILoggingEvent > appender ) {
		this.customAppenders.add( appender );
		return this;
	}
	
	
	/***** GETTERS/SETTERS *****/
	/**
	 * @return
	 */
	public final String getLogName() {
		return logName;
	}
	
	// Pattern Layout Encoder
	/**
	 * @return
	 */
	public final String getPatternLayout() {
		return patternLayout;
	}
	/**
	 * @param patternLayout
	 * @return
	 */
	public final LoggerFactoryBuilder setPatternLayout( String patternLayout ) {
		this.patternLayout = patternLayout;
		return this;
	}
	/**
	 * @param patternLayoutProperty
	 * @return
	 */
	public final LoggerFactoryBuilder setPatternLayout( Property< String > patternLayoutProperty ) {
		if( patternLayoutProperty == null )
			throw new IllegalArgumentException( "The patternLayoutProperty cannot be null." );
		this.patternLayout = patternLayoutProperty.getProperty();
		return this;
	}
	
	// Level
	/**
	 * @return
	 */
	public final Level getLevel() {
		return level;
	}
	/**
	 * @param level
	 * @return
	 */
	public final LoggerFactoryBuilder setLevel( Level level ) {
		this.level = level;
		return this;
	}
	/**
	 * @param levelProperty
	 * @return
	 */
	public final LoggerFactoryBuilder setLevel( Property< String > levelProperty ) {
		if( levelProperty == null )
			throw new IllegalArgumentException( "The levelProperty cannot be null." );
		Level temporaryLevel = Level.toLevel( levelProperty.getProperty() );
		if( !temporaryLevel.toString().equalsIgnoreCase( levelProperty.getProperty() ) )
			throw new IllegalArgumentException( "The level is not a valid Level." );
		this.level = temporaryLevel;
		return this;
	}
	
	// File Appender
	/**
	 * @return
	 */
	public final boolean isFileAppender() {
		return fileAppender;
	}
	/**
	 * @param fileAppender
	 * @return
	 */
	public final LoggerFactoryBuilder setFileAppender( boolean fileAppender ) {
		this.fileAppender = fileAppender;
		return this;
	}
	/**
	 * @param fileAppenderProperty
	 * @return
	 */
	public final LoggerFactoryBuilder setFileAppender( Property< Boolean > fileAppenderProperty ) {
		if( fileAppenderProperty == null )
			throw new IllegalArgumentException( "The fileAppenderProperty cannot be null." );
		this.fileAppender = fileAppenderProperty.getProperty();
		return this;
	}
	
	// -> File Path
	/**
	 * @return
	 */
	public final Path getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath
	 * @return
	 */
	public final LoggerFactoryBuilder setFilePath( Path filePath ) {
		this.filePath = filePath;
		return this;
	}
	/**
	 * @param filePathProperty
	 * @return
	 */
	public final LoggerFactoryBuilder setFilePath( Property< String > filePathProperty ) {
		if( filePathProperty == null )
			throw new IllegalArgumentException( "The filePathProperty cannot be null." );
		this.filePath = Paths.get( filePathProperty.getProperty() );
		return this;
	}
	
	// -> File Extension
	/**
	 * @return
	 */
	public final String getFileExtension() {
		return fileExtension;
	}
	/**
	 * @param fileExtension
	 * @return
	 */
	public final LoggerFactoryBuilder setFileExtension( String fileExtension ) {
		this.fileExtension = fileExtension;
		return this;
	}
	/**
	 * @param fileExtensionProperty
	 * @return
	 */
	public final LoggerFactoryBuilder setFileExtension( Property< String > fileExtensionProperty ) {
		if( fileExtensionProperty == null )
			throw new IllegalArgumentException( "The fileExtensionProperty cannot be null." );
		this.fileExtension = fileExtensionProperty.getProperty();
		return this;
	}
	
	// -> File Time Policy
	/**
	 * @return
	 */
	public final FactoryTimePolicy getTimePolicy() {
		return timePolicy;
	}
	/**
	 * @param timePolicy
	 * @return
	 */
	public final LoggerFactoryBuilder setTimePolicy( FactoryTimePolicy timePolicy ) {
		this.timePolicy = timePolicy;
		return this;
	}
	/**
	 * @param timePolicyProperty
	 * @return
	 */
	public final LoggerFactoryBuilder setTimePolicy( Property< String > timePolicyProperty ) {
		if( timePolicyProperty == null )
			throw new IllegalArgumentException( "The timePolicyProperty cannot be null." );
		try {
			this.timePolicy = FactoryTimePolicy.valueOf( timePolicyProperty.getProperty() );
		}
		catch( IllegalArgumentException iae ) {
			throw new IllegalArgumentException( "The timePolicy is not a valid TimePolicy." );
		}
		return this;
	}
	
	// -> File Max History
	/**
	 * @return
	 */
	public final int getMaxHistory() {
		return maxHistory;
	}
	/**
	 * @param maxHistory
	 * @return
	 */
	public final LoggerFactoryBuilder setMaxHistory( int maxHistory ) {
		this.maxHistory = maxHistory;
		return this;
	}
	/**
	 * @param maxHistoryProperty
	 * @return
	 */
	public final LoggerFactoryBuilder setMaxHistory( Property< Integer > maxHistoryProperty ) {
		if( maxHistoryProperty == null )
			throw new IllegalArgumentException( "The maxHistoryProperty cannot be null." );
		else if( maxHistoryProperty.getProperty() < 0 )
			throw new IllegalArgumentException( "The maxHistoryProperty cannot be less than zero." );
		this.maxHistory = maxHistoryProperty.getProperty();
		return this;
	}
	
	// Console Appender
	/**
	 * @return
	 */
	public final boolean isConsoleAppender() {
		return consoleAppender;
	}
	/**
	 * @param consoleAppender
	 * @return
	 */
	public final LoggerFactoryBuilder setConsoleAppender( boolean consoleAppender ) {
		this.consoleAppender = consoleAppender;
		return this;
	}
	/**
	 * @param consoleAppenderProperty
	 * @return
	 */
	public final LoggerFactoryBuilder setConsoleAppender( Property< Boolean > consoleAppenderProperty ) {
		if( consoleAppenderProperty == null )
			throw new IllegalArgumentException( "The consoleAppenderProperty cannot be null." );
		this.consoleAppender = consoleAppenderProperty.getProperty();
		return this;
	}
	
	// Custom Appenders
	/**
	 * @return
	 */
	public final List< AppenderBase< ILoggingEvent > > getCustomAppenders() {
		return customAppenders;
	}
	
	
}
