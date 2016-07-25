package pt.jmfgameiro.resources.core.jsonserializer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pt.jmfgameiro.resources.core.random.RandomNumber;
import pt.jmfgameiro.resources.core.random.RandomString;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class ListJsonSerializerTest {
	
	/***** CONSTANTS *****/
	private static final Gson GSONFORMATTER;
	private static final List< Double > LISTDOUBLES = new ArrayList< Double >();
	private static final List< String > LISTNAMES = new ArrayList< String >();
	private static final int LIST_SIZE = 5;
	
	
	/***** STATIC *****/
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter( List.class, new ListJsonSerializer() );
		GSONFORMATTER = gsonBuilder.create();
		
		for( int i = 0; i < LIST_SIZE; i++ ) {
			LISTDOUBLES.add( RandomNumber.randomDouble( 10.0, 30.0 ) );
			LISTNAMES.add( RandomString.randomName() );
		}
	}
	
	
	/***** TESTS *****/
	@Test
	public void testDoubles() {
		String actual = GSONFORMATTER.toJson( LISTDOUBLES );
		
		StringBuilder expected = new StringBuilder( "[" );
		for( int i = 0; i < LISTDOUBLES.size(); i++ ) {
			if( i > 0 )
				expected.append( "," );
			expected.append( LISTDOUBLES.get( i ) );
		}
		expected.append( "]" );
		
		assertEquals( expected.toString(), actual );
		System.out.println( actual );
	}
	@Test
	public void testNames() {
		String actual = GSONFORMATTER.toJson( LISTNAMES );
		
		StringBuilder expected = new StringBuilder( "[" );
		for( int i = 0; i < LISTNAMES.size(); i++ ) {
			if( i > 0 )
				expected.append( "," );
			expected.append( "\"" + LISTNAMES.get( i ) + "\"" );
		}
		expected.append( "]" );
		
		assertEquals( expected.toString(), actual );
		System.out.println( actual );
	}
	
	
}
