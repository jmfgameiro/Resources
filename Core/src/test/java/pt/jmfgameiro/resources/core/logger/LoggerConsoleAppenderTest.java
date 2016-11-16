package pt.jmfgameiro.resources.core.logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author João Gameiro
 *
 */
public final class LoggerConsoleAppenderTest {
	
	/***** CONSTANTS *****/
	private static final String LOG_NAME = "LOGGER_CONSOLE_TEST";
	private static final String INFO_MSG = "Info Console Message";
	private static final String INFO_PARAM_NAME = "Parameter";
	private static final Boolean INFO_PARAM_VALUE = true;
	private final Logger LOG = LoggerFactory.getLogger( LoggerConsoleAppenderTest.class );
	
	
	/***** BEFORE *****/
	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void init() throws Exception {
		LoggerFactoryBuilder builder = new LoggerFactoryBuilder( LOG_NAME );
		builder.setConsoleAppender( true );
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
