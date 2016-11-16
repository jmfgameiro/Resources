package pt.jmfgameiro.resources.classer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javassist.CannotCompileException;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.jmfgameiro.generator.text.Text;
import pt.jmfgameiro.resources.classer.Classer;
import pt.jmfgameiro.resources.classer.factory.ClasserBuilder;
import pt.jmfgameiro.resources.classer.factory.ClasserBuilderField;
import pt.jmfgameiro.resources.classer.factory.ClasserBuilderMethod;
import pt.jmfgameiro.resources.classer.factory.ClasserFactory;

public class ClasserFactoryTest {
	
	/***** CONSTANTS *****/
	private static final String CLASS_NAME = "FactoryTest";
	// FIELD TEST
	private static final String FIELD_NAME = "name";
	private static final String FIELD_VALUE = Text.name();
	private static final String FIELD_VALUE2 = Text.name();
	// METHOD TEST
	private static final String METHOD_NAME = "returnConstant";
	private static final String METHOD_VALUE = Text.name();
	private static final String METHOD_BODY = "return \"" + METHOD_VALUE + "\";";
	// METHOD PARAMETER TEST
	private static final String METHOD2_NAME = "returnParameter";
	private static final String METHOD2_PARAM_NAME = "myParameter";
	private static final String METHOD2_VALUE = Text.name();
	private static final String METHOD2_BODY = "return " + METHOD2_PARAM_NAME + ";";
	
	
	/***** BEFORE *****/
	@BeforeClass
	public static void beforeClass() {
		try {
			ClasserFactory.getClasser( CLASS_NAME );
		}
		catch( ClassNotFoundException cnfe ) {
			try {
				ClasserBuilder builder = new ClasserBuilder( CLASS_NAME );
				builder.addField( new ClasserBuilderField( FIELD_NAME, FIELD_VALUE.getClass() ) );
				builder.addMethod( new ClasserBuilderMethod( METHOD_NAME, METHOD_VALUE.getClass(), METHOD_BODY ) );
				builder.addMethod( new ClasserBuilderMethod( METHOD2_NAME, METHOD2_VALUE.getClass(), METHOD2_BODY ).addParameter( new ClasserBuilderField( METHOD2_PARAM_NAME, METHOD2_VALUE.getClass() ) ) );
				ClasserFactory.createClasser( builder );
			}
			catch( CannotCompileException e ) {
				e.printStackTrace();
				fail( e.getMessage() );
			}
		}
	}
	
	
	/***** TEST *****/
	@Test
	public void testField() {
		try {
			Classer clazz = ClasserFactory.getClasser( CLASS_NAME );
			Object obj = clazz.newObject();
			
			// SET
			clazz.setField( obj, FIELD_NAME, FIELD_VALUE );
			
			// GET
			String actual = ( String )clazz.getField( obj, FIELD_NAME );
			
			// TEST
			assertEquals( FIELD_VALUE, actual );
			System.out.println( "ClasserFactoryTest - testField: " + actual );
		}
		catch( Exception e ) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	@Test
	public void testField2() {
		try {
			Classer clazz = ClasserFactory.getClasser( CLASS_NAME );
			Object obj = clazz.newObject();
			
			// SET
			clazz.setField( obj, FIELD_NAME, FIELD_VALUE2 );
			
			// GET
			String actual = ( String )clazz.getField( obj, FIELD_NAME );
			
			// TEST
			assertEquals( FIELD_VALUE2, actual );
			System.out.println( "ClasserFactoryTest - testField2: " + actual );
		}
		catch( Exception e ) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	@Test
	public void testMethod() {
		try {
			Classer clazz = ClasserFactory.getClasser( CLASS_NAME );
			Object obj = clazz.newObject();
			
			String actual = ( String )clazz.getMethod( obj, METHOD_NAME, null );
			
			// TEST
			assertEquals( METHOD_VALUE, actual );
			System.out.println( "ClasserFactoryTest - testMethod: " + actual );
		}
		catch( Exception e ) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	@Test
	public void testMethodParameter() {
		try {
			Classer clazz = ClasserFactory.getClasser( CLASS_NAME );
			Object obj = clazz.newObject();
			
			List< Object > parameters = new ArrayList< Object >();
			parameters.add( METHOD2_VALUE );
			String actual = ( String )clazz.getMethod( obj, METHOD2_NAME, parameters );
			
			// TEST
			assertEquals( METHOD2_VALUE, actual );
			System.out.println( "ClasserFactoryTest - testMethodParameter: " + actual );
		}
		catch( Exception e ) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	
}
