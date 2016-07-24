package pt.jmfgameiro.resources.core.random;


import java.security.SecureRandom;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


enum SingleWords {
	
	A( "hello" ),
	B( "world" ),
	C( "alfa" ),
	D( "manga" ),
	E( "raspberry" ),
	F( "android" ),
	G( "party" ),
	H( "crazy" ),
	I( "stuff" ),
	J( "iamawesomeinthesky" ),
	K( "notinmyname" ),
	L( "forcefield" ),
	M( "peace" ),
	N( "war" ),
	O( "craziness" ),
	P( "hashstuff" ),
	Q( "beach" ),
	R( "bitch" ),
	S( "football" ),
	T( "soccer" ),
	U( "benfica" ),
	V( "champion" ),
	W( "elasticsearch" ),
	X( "openbro" ),
	Y( "buzzbee" ),
	Z( "equalrights" );
	
	
	/***** CONSTANTS *****/
	private final String value;
	
	
	/***** CONSTRUCTOR *****/
	private SingleWords( String value ) {
		this.value = value;
	}
	
	
	/***** PUBLIC *****/
	public static final List< String > randomValues( int amount ) {
		List< SingleWords > values = Arrays.asList( SingleWords.values() );
		
		List< Integer > positions = new LinkedList< Integer >();
		List< String > strings = new LinkedList< String >();
		Random random = new SecureRandom();
		while( positions.size() < amount ) {
			int current = random.nextInt( values.size() );
			
			if( positions.contains( current ) )
				continue;
			
			positions.add( current );
			strings.add( values.get( current ).getValue() );
		}
		
		return strings;
	}
	
	
	/***** GETTER *****/
	public final String getValue() {
		return value;
	}
	
	
}
