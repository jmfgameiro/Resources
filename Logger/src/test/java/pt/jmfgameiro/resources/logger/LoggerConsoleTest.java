package pt.jmfgameiro.resources.logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public final class LoggerConsoleTest {
	
	/***** CONSTANTS *****/
	private final Logger LOG = LoggerFactory.getLogger( LoggerConsoleTest.class );
	private static final String INFO_MSG = "Info Console Message";
	private static final String INFO_PARAM_NAME = "Parameter";
	private static final Boolean INFO_PARAM_VALUE = true;
	
	
	/***** BEFORE *****/
	@BeforeClass
	public static void init() throws Exception {
		LoggerFactoryBuilder builder = new LoggerFactoryBuilder( "LOGGER_CONSOLE_TEST" );
		builder.setConsoleAppender( true );
		LoggerFactory.build( builder );
	}
	
	
	/***** TESTS *****/
	@Test
	public void info() {
		LOG.info( INFO_MSG );
	}
	@Test
	public void infoWithParams() {
		LOG.info( INFO_MSG, INFO_PARAM_NAME, INFO_PARAM_VALUE );
	}
	
	
	/***** AFTER *****/
	@AfterClass
	public static void end() throws Exception {
		LoggerFactory.destroy();
	}
	

}
