package pt.jmfgameiro.resources.core.random;


import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


enum NameLast {
	
	A( "Azevedo" ),
	B( "Barbosa" ),
	C( "Cardoso" ),
	D( "Díaz" ),
	E( "Eriksson" ),
	F( "Fournier" ),
	G( "Gurakuqi" ),
	H( "Hansson" ),
	I( "Iglesias" ),
	J( "Jensen" ),
	K( "Karlsson" ),
	L( "Lynch" ),
	M( "Martínez" ),
	N( "Navarro" ),
	O( "Ortega" ),
	P( "Pinto" ),
	Q( "Quinn" ),
	R( "Rocha" ),
	S( "Silva" ),
	T( "Thill" ),
	U( "Urbonas" ),
	V( "Vasiliauskas" ),
	W( "Weiss" ),
	X( "Xavez" ),
	Y( "Yilmaz" ),
	Z( "Zukic" );
	
	
	/***** CONSTANTS *****/
	private final String value;
	
	
	/***** CONSTRUCTOR *****/
	private NameLast( String value ) {
		this.value = value;
	}
	
	
	/***** PUBLIC *****/
	public static final String randomValue() {
		List< NameLast > values = Arrays.asList( NameLast.values() );
		Random random = new SecureRandom();
		return values.get( random.nextInt( values.size() ) ).getValue();
	}
	
	
	/***** GETTER *****/
	public final String getValue() {
		return value;
	}
	
	
}
