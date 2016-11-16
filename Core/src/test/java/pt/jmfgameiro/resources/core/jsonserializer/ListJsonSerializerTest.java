package pt.jmfgameiro.resources.core.jsonserializer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pt.jmfgameiro.generator.number.DoubleRandomizer;
import pt.jmfgameiro.generator.text.Text;

public final class ListJsonSerializerTest {
	
	/***** CONSTANTS *****/
	private static final Gson GSONFORMATTER;
	private static final List< Double > LISTDOUBLES = new ArrayList< Double >();
	private static final List< String > LISTNAMES = new ArrayList< String >();
	private static final int LIST_SIZE = 5;
	private static final double MIN_SIZE = 10.0;
	private static final double MAX_SIZE = 10.0;
	
	
	/***** STATIC *****/
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter( List.class, new ListJsonSerializer() );
		GSONFORMATTER = gsonBuilder.create();
		for( int i = 0; i < LIST_SIZE; i++ ) {
			LISTDOUBLES.add( DoubleRandomizer.random( MIN_SIZE, MAX_SIZE ) );
			LISTNAMES.add( Text.name() );
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
