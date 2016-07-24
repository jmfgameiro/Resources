package pt.jmfgameiro.resources.core.random;


import java.security.SecureRandom;
import java.util.List;
import java.util.Random;


public final class RandomString {
	
	/***** PUBLIC *****/
	public static final List< String > listRandomSingleWords() {
		Random random = new SecureRandom();
		int amount = random.nextInt( 3 ) + 3;
		return SingleWords.randomValues( amount );
	}
	
	public static final String randomText() {
		return SingleParagraphText.randomValue();
	}
	
	public static final String randomName() {
		return NameFirst.randomValue() + " " + NameLast.randomValue();
	}
	
	
}
