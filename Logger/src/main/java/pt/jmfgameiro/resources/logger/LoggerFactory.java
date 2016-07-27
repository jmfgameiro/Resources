package pt.jmfgameiro.resources.logger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import pt.jmfgameiro.resources.core.factory.FactoryFailedInitializationException;
import pt.jmfgameiro.resources.core.factory.FactoryHasAlreadyBeenInitializedException;
import pt.jmfgameiro.resources.core.factory.FactoryState;
import pt.jmfgameiro.resources.core.factory.FactoryTimePolicy;
import pt.jmfgameiro.resources.core.factory.FactoryIsUninitializedException;
import pt.jmfgameiro.resources.ehcache.CacheFactory;

public final class LoggerFactory {
	
	/***** CONSTANTS *****/
	private static final LogFormatter FORMATTER = new LogFormatter();
	private static final Random RANDOM = new Random();
	private static final String TIME_POLICY_DAILY = ".%d{yyyy.MM.dd}.";
	private static final String TIME_POLICY_HOURLY = ".%d{yyyy.MM.dd'-'H'h'}.";
	private static final String FILE_APPENDER_NAME = "LOGFILE";
	private static final String CONSOLE_APPENDER_NAME = "STDOUT";
	private static final String CACHE_NAME = "LOGGER_CACHE";
	
	
	/***** VARIABLES *****/
	private static FactoryState STATE = FactoryState.UNINITIALIZED;
	private static RollingFileAppender< ILoggingEvent > ROLLING_FILE_APPENDER;
	private static ConsoleAppender< ILoggingEvent > CONSOLE_APPENDER;
	private static List< AppenderBase< ILoggingEvent > > CUSTOM_APPENDERS;
	private static Level LEVEL;
	
	
	/***** PUBLIC *****/
	public synchronized static void build( LoggerFactoryBuilder builder ) {
		switch( STATE ) {
			case UNINITIALIZED:
			case FAILED_INITIALIZATION:
				try {
					if( !builder.isConsoleAppender() && !builder.isFileAppender() && builder.getCustomAppenders().isEmpty() )
						throw new IllegalArgumentException( "No appenders were defined!" );
					
					// Logger Level
					LEVEL = builder.getLevel();
					
					// Logger Context
					LoggerContext loggerContext = ( LoggerContext )org.slf4j.LoggerFactory.getILoggerFactory();
					
					// File Appender
					if( builder.isFileAppender() ) {
						// File Appender Pattern Layout
						PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
						patternLayoutEncoder.setPattern( builder.getPatternLayout() );
						patternLayoutEncoder.setContext( loggerContext );
						patternLayoutEncoder.start();
						
						// Time Policy
						TimeBasedRollingPolicy< ILoggingEvent > timeBasedRollingPolicy = new TimeBasedRollingPolicy< ILoggingEvent >();
						StringBuilder fileNamePattern = new StringBuilder( builder.getFilePath().toString() + "/" + builder.getLogName() );
						if( builder.getTimePolicy() == FactoryTimePolicy.DAILY )
							fileNamePattern.append( TIME_POLICY_DAILY );
						else
							fileNamePattern.append( TIME_POLICY_HOURLY );
						fileNamePattern.append( builder.getFileExtension() );
						timeBasedRollingPolicy.setFileNamePattern( fileNamePattern.toString() );
						timeBasedRollingPolicy.setMaxHistory( builder.getMaxHistory() );
						timeBasedRollingPolicy.setContext( loggerContext );
						
						// Rolling File Appender
						ROLLING_FILE_APPENDER = new RollingFileAppender< ILoggingEvent >();
						ROLLING_FILE_APPENDER.setName( FILE_APPENDER_NAME );
						ROLLING_FILE_APPENDER.setEncoder( patternLayoutEncoder );
						ROLLING_FILE_APPENDER.setRollingPolicy( timeBasedRollingPolicy );
						ROLLING_FILE_APPENDER.setContext( loggerContext );
						
						// Start Time Policy
						timeBasedRollingPolicy.setParent( ROLLING_FILE_APPENDER );
						timeBasedRollingPolicy.start();
						ROLLING_FILE_APPENDER.start();
					}
					
					// Console Appender
					if( builder.isConsoleAppender() ) {
						// Console Appender Pattern Layout
						PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
						patternLayoutEncoder.setPattern( builder.getPatternLayout() );
						patternLayoutEncoder.setContext( loggerContext );
						patternLayoutEncoder.start();
						
						// Console Appender
						CONSOLE_APPENDER = new ConsoleAppender< ILoggingEvent >();
						CONSOLE_APPENDER.setName( CONSOLE_APPENDER_NAME );
						CONSOLE_APPENDER.setEncoder( patternLayoutEncoder );
						CONSOLE_APPENDER.setContext( loggerContext );
						CONSOLE_APPENDER.start();
					}
					
					// Custom Appenders
					if( !builder.getCustomAppenders().isEmpty() ) {
						CUSTOM_APPENDERS = new ArrayList< AppenderBase< ILoggingEvent > >();
						for( AppenderBase< ILoggingEvent > appender : builder.getCustomAppenders() ) {
							appender.setContext( loggerContext );
							appender.start();
							CUSTOM_APPENDERS.add( appender );
						}
					}
					
					// Create Logger Cache
					CacheFactory.MANAGER.createCache( CACHE_NAME, 
							CacheConfigurationBuilder
									.newCacheConfigurationBuilder(
											Long.class,
											Logger.class,
											ResourcePoolsBuilder.newResourcePoolsBuilder().offheap( builder.getCacheSizeValue(), builder.getCacheSizeUnit() ).build() )
									.withExpiry(
											Expirations.timeToLiveExpiration( Duration.of( builder.getCacheExpirationValue(), builder.getCacheExpirationUnit() ) ) ) );
					
					// Factory State
					STATE = FactoryState.SUCCESSFUL_INITIALIZATION;
					return;
				}
				catch( Throwable t ) {
					STATE = FactoryState.FAILED_INITIALIZATION;
					ROLLING_FILE_APPENDER = null;
					CONSOLE_APPENDER = null;
					LEVEL = null;
					throw new FactoryFailedInitializationException( t );
				}
			case SUCCESSFUL_INITIALIZATION:
				throw new FactoryHasAlreadyBeenInitializedException();
		}
	}
	
	public synchronized static void destroy() {
		STATE = FactoryState.UNINITIALIZED;
		ROLLING_FILE_APPENDER = null;
		CONSOLE_APPENDER = null;
		CUSTOM_APPENDERS = null;
		LEVEL = null;
		CacheFactory.MANAGER.removeCache( CACHE_NAME );
	}
	
	public static Logger getLogger( Class< ? > clazz ) {
		if( STATE != FactoryState.SUCCESSFUL_INITIALIZATION )
			throw new FactoryIsUninitializedException();
		
		Cache< Long, Logger > cacheLogger = CacheFactory.MANAGER.getCache( CACHE_NAME, Long.class, Logger.class );
		Thread thread = Thread.currentThread();
		if( cacheLogger.containsKey( thread.getId() ) )
			return cacheLogger.get( thread.getId() );
		
		ch.qos.logback.classic.Logger logger = ( ch.qos.logback.classic.Logger )org.slf4j.LoggerFactory.getLogger( clazz );
		
		//
		if( ROLLING_FILE_APPENDER != null )
			logger.addAppender( ROLLING_FILE_APPENDER );
		if( CONSOLE_APPENDER != null )
			logger.addAppender( CONSOLE_APPENDER );
		if( CUSTOM_APPENDERS != null ) {
			for( AppenderBase< ILoggingEvent > appender : CUSTOM_APPENDERS )
				logger.addAppender( appender );
		}
		
		//
		logger.setLevel( LEVEL );
		logger.setAdditive( false );
		Logger log = new Logger( generateCode(), logger, FORMATTER );
		cacheLogger.put( thread.getId(), log );
		return log;
	}
	
	
	/***** PRIVATE *****/
	private static String generateCode() {
		BigInteger bi = new BigInteger( 40, RANDOM );
		String code = bi.toString( 16 ).toUpperCase();
		if( code.length() > 10 )
			code = code.substring( 0, 10 );
		else if( code.length() < 10 )
			code = code.concat( code ).substring( 0, 10 );
		return code;
	}
	
	
}
