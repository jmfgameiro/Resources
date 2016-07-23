package pt.jmfgameiro.resources.logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.qos.logback.classic.spi.ILoggingEvent;

public final class LoggerMongodbAppenderTest {
	
	/***** CONSTANTS *****/
	private final Logger LOG = LoggerFactory.getLogger( LoggerMongodbAppenderTest.class );
	private static final String INFO_MSG = "Info MongoDB Message";
	private static final String INFO_PARAM_NAME = "Parameter";
	private static final Boolean INFO_PARAM_VALUE = null;
	
	
	/***** BEFORE *****/
	@BeforeClass
	public static void init() throws Exception {
		LoggerFactoryBuilder builder = new LoggerFactoryBuilder( "LOGGER_MONGODB_TEST" );
		builder.addCustomAppender( new LoggerMongodbAppender< ILoggingEvent >() );
		LoggerFactory.build( builder );
	}
	
	
	/***** TESTS *****/
	@Test
	public void info() {
		System.out.println( "Before Insert" );
		LOG.info( INFO_MSG );
		System.out.println( "After Insert" );
	}
	@Test
	public void infoWithParams() {
		System.out.println( "Before Insert" );
		LOG.info( INFO_MSG, INFO_PARAM_NAME, INFO_PARAM_VALUE );
		System.out.println( "After Insert" );
	}
	
	
	/***** AFTER *****/
	@AfterClass
	public static void end() throws Exception {
		LoggerFactory.destroy();
	}
	

}
