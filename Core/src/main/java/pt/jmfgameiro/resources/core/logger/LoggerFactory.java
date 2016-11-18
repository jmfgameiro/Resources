package pt.jmfgameiro.resources.core.logger;

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
import pt.jmfgameiro.resources.core.validator.StringValidator;
import pt.jmfgameiro.resources.core.factory.FactoryIsUninitializedException;
import pt.jmfgameiro.resources.core.cache.CacheFactory;

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
	private static FactoryState state = FactoryState.UNINITIALIZED;
	private static RollingFileAppender< ILoggingEvent > rollingFileAppender;
	private static ConsoleAppender< ILoggingEvent > consoleAppender;
	private static List< AppenderBase< ILoggingEvent > > customAppenders;
	private static Level level;
	
	
	/***** CONSTRUCTOR *****/
	/**
	 * 
	 */
	private LoggerFactory() {}
	
	
	/***** PUBLIC *****/
	/**
	 * @param builder
	 */
	public static synchronized void build( LoggerFactoryBuilder builder ) {
		switch( state ) {
			case UNINITIALIZED:
			case FAILED_INITIALIZATION:
				executeBuild( builder );
				break;
			case SUCCESSFUL_INITIALIZATION:
				throw new FactoryHasAlreadyBeenInitializedException();
			default:
				throw new FactoryFailedInitializationException( "The current state of the factory is null!" );
		}
	}
	
	/**
	 * 
	 */
	public static synchronized void destroy() {
		state = FactoryState.UNINITIALIZED;
		rollingFileAppender = null;
		consoleAppender = null;
		customAppenders = null;
		level = null;
		CacheFactory.MANAGER.removeCache( CACHE_NAME );
	}
	
	/**
	 * @param clazz
	 * @return
	 */
	public static Logger getLogger( Class< ? > clazz ) {
		if( state != FactoryState.SUCCESSFUL_INITIALIZATION )
			throw new FactoryIsUninitializedException();
		
		Cache< Long, Logger > cacheLogger = CacheFactory.MANAGER.getCache( CACHE_NAME, Long.class, Logger.class );
		Thread thread = Thread.currentThread();
		if( cacheLogger.containsKey( thread.getId() ) )
			return cacheLogger.get( thread.getId() );
		
		ch.qos.logback.classic.Logger logger = ( ch.qos.logback.classic.Logger )org.slf4j.LoggerFactory.getLogger( clazz );
		
		//
		if( rollingFileAppender != null )
			logger.addAppender( rollingFileAppender );
		if( consoleAppender != null )
			logger.addAppender( consoleAppender );
		if( customAppenders != null ) {
			for( AppenderBase< ILoggingEvent > appender : customAppenders )
				logger.addAppender( appender );
		}
		
		//
		logger.setLevel( level );
		logger.setAdditive( false );
		Logger log = new Logger( generateCode(), logger, FORMATTER );
		cacheLogger.put( thread.getId(), log );
		return log;
	}
	
	
	/***** PRIVATE *****/
	/**
	 * @param builder
	 */
	private static void executeBuild( LoggerFactoryBuilder builder ) {
		if( !builder.isConsoleAppender() && !builder.isFileAppender() && builder.getCustomAppenders().isEmpty() )
			throwBuildException( "No appenders were defined!", null );
		
		try {
			// Logger Level
			level = builder.getLevel();
			
			// Logger Context
			LoggerContext loggerContext = ( LoggerContext )org.slf4j.LoggerFactory.getILoggerFactory();
			
			// File Appender
			if( builder.isFileAppender() )
				buildFileAppender( builder, loggerContext );
			
			// Console Appender
			if( builder.isConsoleAppender() )
				buildConsoleAppender( builder, loggerContext );
			
			// Custom Appenders
			if( !builder.getCustomAppenders().isEmpty() ) {
				customAppenders = new ArrayList<>();
				for( AppenderBase< ILoggingEvent > appender : builder.getCustomAppenders() ) {
					appender.setContext( loggerContext );
					appender.start();
					customAppenders.add( appender );
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
			state = FactoryState.SUCCESSFUL_INITIALIZATION;
		} catch( Exception t ) {
			throwBuildException( null, t );
		}
		return;
	}
	
	/**
	 * @param builder
	 * @param loggerContext
	 */
	private static void buildFileAppender( LoggerFactoryBuilder builder, LoggerContext loggerContext ) {
		// File Appender Pattern Layout
		PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
		patternLayoutEncoder.setPattern( builder.getPatternLayout() );
		patternLayoutEncoder.setContext( loggerContext );
		patternLayoutEncoder.start();
		
		// Time Policy
		TimeBasedRollingPolicy< ILoggingEvent > timeBasedRollingPolicy = new TimeBasedRollingPolicy<>();
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
		rollingFileAppender = new RollingFileAppender<>();
		rollingFileAppender.setName( FILE_APPENDER_NAME );
		rollingFileAppender.setEncoder( patternLayoutEncoder );
		rollingFileAppender.setRollingPolicy( timeBasedRollingPolicy );
		rollingFileAppender.setContext( loggerContext );
		
		// Start Time Policy
		timeBasedRollingPolicy.setParent( rollingFileAppender );
		timeBasedRollingPolicy.start();
		rollingFileAppender.start();
	}
	
	/**
	 * @param builder
	 * @param loggerContext
	 */
	private static void buildConsoleAppender( LoggerFactoryBuilder builder, LoggerContext loggerContext ) {
		// Console Appender Pattern Layout
		PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
		patternLayoutEncoder.setPattern( builder.getPatternLayout() );
		patternLayoutEncoder.setContext( loggerContext );
		patternLayoutEncoder.start();
		
		// Console Appender
		consoleAppender = new ConsoleAppender<>();
		consoleAppender.setName( CONSOLE_APPENDER_NAME );
		consoleAppender.setEncoder( patternLayoutEncoder );
		consoleAppender.setContext( loggerContext );
		consoleAppender.start();
	}
	
	/**
	 * @return
	 */
	private static String generateCode() {
		BigInteger bi = new BigInteger( 40, RANDOM );
		String code = bi.toString( 16 ).toUpperCase();
		if( code.length() > 10 )
			code = code.substring( 0, 10 );
		else if( code.length() < 10 )
			code = code.concat( code ).substring( 0, 10 );
		return code;
	}
	
	/**
	 * @param message
	 * @param e
	 */
	private static void throwBuildException( String message, Exception e ) {
		state = FactoryState.FAILED_INITIALIZATION;
		rollingFileAppender = null;
		consoleAppender = null;
		level = null;
		if( !StringValidator.isNullOrEmpty( message ) )
			throw new FactoryFailedInitializationException( message );
		throw new FactoryFailedInitializationException( e );
	}
	
	
}
