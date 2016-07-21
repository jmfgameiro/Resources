package pt.jmfgameiro.resources.core.property;

import static org.junit.Assert.*;

import org.junit.Test;

public final class SystemPropertyTest {
	
	/***** CONSTANTS *****/
	private static final String javaVersionPrp = "java.version";
	private static final String javaVersionErr = "The property value is null!";
	private static final String javaVersionMsg = "Java Version: ";
	
	
	/***** TEST *****/
	@Test
	public void javaVersion() {
		SystemProperty< String > javaVersion = new SystemProperty< String >( javaVersionPrp );
		assertNotNull( javaVersionErr, javaVersion.getProperty() );
		System.out.println( javaVersionMsg + javaVersion.getProperty() );
	}
	
	
}
