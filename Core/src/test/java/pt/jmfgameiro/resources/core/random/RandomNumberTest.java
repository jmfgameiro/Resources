package pt.jmfgameiro.resources.core.random;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomNumberTest {
	
	/***** CONSTANTS *****/
	private static final double MIN = 10.0, MAX =  15.0;
	
	
	/***** TEST *****/
	@Test
	public void randomDouble() {
		double rand = RandomNumber.randomDouble( MIN, MAX );
		assertTrue( rand >= MIN );
		assertTrue( rand <= MAX );
	}
	
	
}
