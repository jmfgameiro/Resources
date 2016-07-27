package pt.jmfgameiro.resources.logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import pt.jmfgameiro.resources.core.factory.FactoryTimePolicy;
import pt.jmfgameiro.resources.core.property.Property;
import pt.jmfgameiro.resources.ehcache.CacheFactoryBuilder;

public final class LoggerFactoryBuilder extends CacheFactoryBuilder {
	
	/***** CONSTANTS *****/
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
	
	private List< AppenderBase< ILoggingEvent > > customAppenders = new ArrayList< AppenderBase< ILoggingEvent > >();
	
	
	/***** CONSTRUCTOR *****/
	public LoggerFactoryBuilder( String logName ) {
		super();
		String filePath = this.getClass().getResource( "/" ).getPath();
		this.filePath = Paths.get( System.getProperty( "os.name" ).contains( "indow" ) ? filePath.substring( 1 ): filePath );
		this.logName = logName;
	}
	public LoggerFactoryBuilder( String logName, Long cacheSizeValue, String cacheSizeUnit, Long cacheExpirationValue, String cacheExpirationUnit ) {
		super( cacheSizeValue, cacheSizeUnit, cacheExpirationValue, cacheExpirationUnit );
		String filePath = this.getClass().getResource( "/" ).getPath();
		this.filePath = Paths.get( System.getProperty( "os.name" ).contains( "indow" ) ? filePath.substring( 1 ): filePath );
		this.logName = logName;
	}
	public LoggerFactoryBuilder( String logName, Property< Long > cacheSizeValueProperty, Property< String > cacheSizeUnitProperty, Property< Long > cacheExpirationValueProperty, Property< String > cacheExpirationUnitProperty ) {
		super( cacheSizeValueProperty, cacheSizeUnitProperty, cacheExpirationValueProperty, cacheExpirationUnitProperty );
		String filePath = this.getClass().getResource( "/" ).getPath();
		this.filePath = Paths.get( System.getProperty( "os.name" ).contains( "indow" ) ? filePath.substring( 1 ): filePath );
		this.logName = logName;
	}
	
	
	/***** PUBLIC *****/
	public LoggerFactoryBuilder cleanCustomAppender() {
		this.customAppenders = new ArrayList< AppenderBase< ILoggingEvent > >();
		return this;
	}
	public LoggerFactoryBuilder addCustomAppender( AppenderBase< ILoggingEvent > appender ) {
		this.customAppenders.add( appender );
		return this;
	}
	
	
	/***** GETTERS/SETTERS *****/
	public String getLogName() {
		return logName;
	}
	
	// Pattern Layout Encoder
	public String getPatternLayout() {
		return patternLayout;
	}
	public LoggerFactoryBuilder setPatternLayout( String patternLayout ) {
		this.patternLayout = patternLayout;
		return this;
	}
	public LoggerFactoryBuilder setPatternLayout( Property< String > patternLayoutProperty ) {
		if( patternLayoutProperty == null )
			throw new IllegalArgumentException( "The patternLayoutProperty cannot be null." );
		this.patternLayout = patternLayoutProperty.getProperty();
		return this;
	}
	
	// Level
	public Level getLevel() {
		return level;
	}
	public LoggerFactoryBuilder setLevel( Level level ) {
		this.level = level;
		return this;
	}
	public LoggerFactoryBuilder setLevel( Property< String > levelProperty ) {
		if( levelProperty == null )
			throw new IllegalArgumentException( "The levelProperty cannot be null." );
		Level temporaryLevel = Level.toLevel( levelProperty.getProperty() );
		if( !temporaryLevel.toString().equalsIgnoreCase( levelProperty.getProperty() ) )
			throw new IllegalArgumentException( "The level is not a valid Level." );
		this.level = temporaryLevel;
		return this;
	}
	
	// File Appender
	public boolean isFileAppender() {
		return fileAppender;
	}
	public LoggerFactoryBuilder setFileAppender( boolean fileAppender ) {
		this.fileAppender = fileAppender;
		return this;
	}
	public LoggerFactoryBuilder setFileAppender( Property< Boolean > fileAppenderProperty ) {
		if( fileAppenderProperty == null )
			throw new IllegalArgumentException( "The fileAppenderProperty cannot be null." );
		this.fileAppender = fileAppenderProperty.getProperty();
		return this;
	}
	
	// -> File Path
	public Path getFilePath() {
		return filePath;
	}
	public LoggerFactoryBuilder setFilePath( Path filePath ) {
		this.filePath = filePath;
		return this;
	}
	public LoggerFactoryBuilder setFilePath( Property< String > filePathProperty ) {
		if( filePathProperty == null )
			throw new IllegalArgumentException( "The filePathProperty cannot be null." );
		this.filePath = Paths.get( filePathProperty.getProperty() );
		return this;
	}
	
	// -> File Extension
	public String getFileExtension() {
		return fileExtension;
	}
	public LoggerFactoryBuilder setFileExtension( String fileExtension ) {
		this.fileExtension = fileExtension;
		return this;
	}
	public LoggerFactoryBuilder setFileExtension( Property< String > fileExtensionProperty ) {
		if( fileExtensionProperty == null )
			throw new IllegalArgumentException( "The fileExtensionProperty cannot be null." );
		this.fileExtension = fileExtensionProperty.getProperty();
		return this;
	}
	
	// -> File Time Policy
	public FactoryTimePolicy getTimePolicy() {
		return timePolicy;
	}
	public LoggerFactoryBuilder setTimePolicy( FactoryTimePolicy timePolicy ) {
		this.timePolicy = timePolicy;
		return this;
	}
	public LoggerFactoryBuilder setTimePolicy( Property< String > timePolicyProperty ) {
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
	public int getMaxHistory() {
		return maxHistory;
	}
	public LoggerFactoryBuilder setMaxHistory( int maxHistory ) {
		this.maxHistory = maxHistory;
		return this;
	}
	public LoggerFactoryBuilder setMaxHistory( Property< Integer > maxHistoryProperty ) {
		if( maxHistoryProperty == null )
			throw new IllegalArgumentException( "The maxHistoryProperty cannot be null." );
		else if( maxHistoryProperty.getProperty() < 0 )
			throw new IllegalArgumentException( "The maxHistoryProperty cannot be less than zero." );
		this.maxHistory = maxHistoryProperty.getProperty();
		return this;
	}
	
	// Console Appender
	public boolean isConsoleAppender() {
		return consoleAppender;
	}
	public LoggerFactoryBuilder setConsoleAppender( boolean consoleAppender ) {
		this.consoleAppender = consoleAppender;
		return this;
	}
	public LoggerFactoryBuilder setConsoleAppender( Property< Boolean > consoleAppenderProperty ) {
		if( consoleAppenderProperty == null )
			throw new IllegalArgumentException( "The consoleAppenderProperty cannot be null." );
		this.consoleAppender = consoleAppenderProperty.getProperty();
		return this;
	}
	
	// Custom Appenders
	public List< AppenderBase< ILoggingEvent > > getCustomAppenders() {
		return customAppenders;
	}
	
	
}
