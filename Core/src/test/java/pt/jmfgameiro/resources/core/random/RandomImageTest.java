package pt.jmfgameiro.resources.core.random;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomImageTest {
	
	/***** TEST *****/
	@Test
	public void redImage() {
		byte[] red = RandomImage.generateImageRed();
		assertNotNull( red );
		assertNotEquals( 0, red.length );
	}
	
	
}
