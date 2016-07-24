package pt.jmfgameiro.resources.core.random;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class RandomStringTest {
	
	/***** TESTS *****/
	@Test
	public void singleWordsTest() {
		List< String > words = RandomString.listRandomSingleWords();
		assertNotNull( words );
		assertTrue( words.size() >= 3 );
		assertTrue( words.size() <= 5 );
	}
	@Test
	public void randomTextTest() {
		String text = RandomString.randomText();
		assertNotNull( text );
		assertNotEquals( "", text );
	}
	@Test
	public void randomNameTest() {
		String name = RandomString.randomName();
		assertNotNull( name );
		assertNotEquals( "", name );
		assertTrue( name.contains( " " ) );
	}
	
	
}
