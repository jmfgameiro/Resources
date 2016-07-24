package pt.jmfgameiro.resources.core.random;


import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


enum NameFirst {
	
	A( "Abaddon" ),
	B( "Barachiel" ),
	C( "Cassiel" ),
	D( "Daniel" ),
	E( "Eremiel" ),
	F( "Fred" ),
	G( "Gabriel" ),
	H( "Harut" ),
	I( "Israfil" ),
	J( "Jerahmeel" ),
	K( "Kushiel" ),
	L( "Lucifer" ),
	M( "Marut" ),
	N( "Nakir" ),
	O( "Osiris" ),
	P( "Phanuel" ),
	Q( "Qaphsiel" ),
	R( "Raziel" ),
	S( "Sariel" ),
	T( "Tennin" ),
	U( "Uzziel" ),
	V( "Virtues" ),
	W( "Wormwood" ),
	X( "Xerxes" ),
	Y( "Yaegar" ),
	Z( "Zachariel" );
	
	
	/***** CONSTANTS *****/
	private final String value;
	
	
	/***** CONSTRUCTOR *****/
	private NameFirst( String value ) {
		this.value = value;
	}
	
	
	/***** PUBLIC *****/
	public static final String randomValue() {
		List< NameFirst > values = Arrays.asList( NameFirst.values() );
		Random random = new SecureRandom();
		return values.get( random.nextInt( values.size() ) ).getValue();
	}
	
	
	/***** GETTER *****/
	public final String getValue() {
		return value;
	}
	
	
}
