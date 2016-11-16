package pt.jmfgameiro.resources.core.logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author João Gameiro
 *
 */
public final class LoggerMultiAppenderTest {
	
	/***** CONSTANTS *****/
	private static final String LOG_NAME = "LOGGER_TEST";
	private static final String INFO_MSG = "Info File Message";
	private static final String INFO_PARAM_NAME = "Parameter";
	private static final Boolean INFO_PARAM_VALUE = false;
	private final Logger LOG = LoggerFactory.getLogger( LoggerMultiAppenderTest.class );
	
	
	/***** BEFORE *****/
	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void init() throws Exception {
		LoggerFactoryBuilder builder = new LoggerFactoryBuilder( LOG_NAME );
		builder.setConsoleAppender( true );
		builder.setFileAppender( true );
		builder.addCustomAppender( new LoggerMongodbAppender< ILoggingEvent >() );
		LoggerFactory.build( builder );
	}
	
	
	/***** TESTS *****/
	/**
	 * 
	 */
	@Test
	public void info() {
		LOG.info( INFO_MSG );
	}
	/**
	 * 
	 */
	@Test
	public void infoWithParams() {
		LOG.info( INFO_MSG, INFO_PARAM_NAME, INFO_PARAM_VALUE );
	}
	
	
	/***** AFTER *****/
	/**
	 * @throws Exception
	 */
	@AfterClass
	public static void end() throws Exception {
		LoggerFactory.destroy();
	}
	

}
