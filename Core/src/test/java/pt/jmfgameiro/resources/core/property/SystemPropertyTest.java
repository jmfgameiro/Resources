package pt.jmfgameiro.resources.core.property;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author João Gameiro
 *
 */
public final class SystemPropertyTest {
	
	/***** CONSTANTS *****/
	private static final String JAVAVERSIONPRP = "java.version";
	private static final String JAVAVERSIONERR = "The property value is null!";
	private static final String JAVAVERSIONMSG = "Java Version: ";
	
	
	/***** TEST *****/
	/**
	 * 
	 */
	@Test
	public void javaVersion() {
		SystemProperty< String > javaVersion = new SystemProperty< String >( JAVAVERSIONPRP );
		assertNotNull( JAVAVERSIONERR, javaVersion.getProperty() );
		System.out.println( JAVAVERSIONMSG + javaVersion.getProperty() );
	}
	
	
}
