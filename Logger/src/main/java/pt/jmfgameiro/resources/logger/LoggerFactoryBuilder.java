package pt.jmfgameiro.resources.logger;

import java.nio.file.Path;
import java.nio.file.Paths;

import ch.qos.logback.classic.Level;
import pt.jmfgameiro.resources.core.factory.FactoryTimePolicy;
import pt.jmfgameiro.resources.core.property.NamingProperty;
import pt.jmfgameiro.resources.core.property.SystemProperty;

public final class LoggerFactoryBuilder {
	
	/***** CONSTANTS *****/
	private final Path logPath;
	private final String logName;
	private final String logExtension;
	
	
	/***** VARIABLES *****/
	private boolean consoleAppender = false;
	private Level level = Level.INFO;
	private FactoryTimePolicy timePolicy = FactoryTimePolicy.DAILY;
	private int maxHistory = 90;
	
	
	/***** CONSTRUCTORS *****/
	public LoggerFactoryBuilder( Path logPath, String logName, String logExtension ) {
		this.logPath = logPath;
		this.logName = logName;
		this.logExtension = logExtension;
	}
	public LoggerFactoryBuilder( SystemProperty< String > logPathProperty, String logName, String logExtension ) {
		this.logPath = Paths.get( logPathProperty.getProperty() );
		this.logName = logName;
		this.logExtension = logExtension;
	}
	public LoggerFactoryBuilder( NamingProperty< String > logPathProperty, String logName, String logExtension ) {
		this.logPath = Paths.get( logPathProperty.getProperty() );
		this.logName = logName;
		this.logExtension = logExtension;
	}
	
	
	/***** GETTERS/SETTERS *****/
	public Path getLogPath() {
		return logPath;
	}
	public String getLogName() {
		return logName;
	}
	public String getLogExtension() {
		return logExtension;
	}
	
	public boolean isConsoleAppender() {
		return consoleAppender;
	}
	public LoggerFactoryBuilder setConsoleAppender( boolean consoleAppender ) {
		this.consoleAppender = consoleAppender;
		return this;
	}
	public LoggerFactoryBuilder setConsoleAppender( SystemProperty< Boolean > consoleAppenderProperty ) {
		if( consoleAppenderProperty == null )
			throw new IllegalArgumentException( "The consoleAppenderProperty cannot be null." );
		this.consoleAppender = consoleAppenderProperty.getProperty();
		return this;
	}
	public LoggerFactoryBuilder setConsoleAppender( NamingProperty< Boolean > consoleAppenderProperty ) {
		if( consoleAppenderProperty == null )
			throw new IllegalArgumentException( "The consoleAppenderProperty cannot be null." );
		this.consoleAppender = consoleAppenderProperty.getProperty();
		return this;
	}
	
	public Level getLevel() {
		return level;
	}
	public void setLevel( Level level ) {
		this.level = level;
	}
	public LoggerFactoryBuilder setLevel( SystemProperty< String > levelProperty ) {
		if( levelProperty == null )
			throw new IllegalArgumentException( "The levelProperty cannot be null." );
		Level temporaryLevel = Level.toLevel( levelProperty.getProperty() );
		if( !temporaryLevel.toString().equalsIgnoreCase( levelProperty.getProperty() ) )
			throw new IllegalArgumentException( "The level is not a valid Level." );
		this.level = temporaryLevel;
		return this;
	}
	public LoggerFactoryBuilder setLevel( NamingProperty< String > levelProperty ) {
		if( levelProperty == null )
			throw new IllegalArgumentException( "The levelProperty cannot be null." );
		Level temporaryLevel = Level.toLevel( levelProperty.getProperty() );
		if( !temporaryLevel.toString().equalsIgnoreCase( levelProperty.getProperty() ) )
			throw new IllegalArgumentException( "The level is not a valid Level." );
		this.level = temporaryLevel;
		return this;
	}
	
	public FactoryTimePolicy getTimePolicy() {
		return timePolicy;
	}
	public void setTimePolicy( FactoryTimePolicy timePolicy ) {
		this.timePolicy = timePolicy;
	}
	public LoggerFactoryBuilder setTimePolicy( SystemProperty< String > timePolicyProperty ) {
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
	public LoggerFactoryBuilder setTimePolicy( NamingProperty< String > timePolicyProperty ) {
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
	
	public int getMaxHistory() {
		return maxHistory;
	}
	public void setMaxHistory( int maxHistory ) {
		this.maxHistory = maxHistory;
	}
	public LoggerFactoryBuilder setMaxHistory( SystemProperty< Integer > maxHistoryProperty ) {
		if( maxHistoryProperty == null )
			throw new IllegalArgumentException( "The maxHistoryProperty cannot be null." );
		else if( maxHistoryProperty.getProperty() < 0 )
			throw new IllegalArgumentException( "The maxHistoryProperty cannot be less than zero." );
		this.maxHistory = maxHistoryProperty.getProperty();
		return this;
	}
	public LoggerFactoryBuilder setMaxHistory( NamingProperty< Integer > maxHistoryProperty ) {
		if( maxHistoryProperty == null )
			throw new IllegalArgumentException( "The maxHistoryProperty cannot be null." );
		else if( maxHistoryProperty.getProperty() < 0 )
			throw new IllegalArgumentException( "The maxHistoryProperty cannot be less than zero." );
		this.maxHistory = maxHistoryProperty.getProperty();
		return this;
	}
	
	
}
