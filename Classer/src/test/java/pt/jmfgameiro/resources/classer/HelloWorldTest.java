package pt.jmfgameiro.resources.classer;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtNewMethod;
import javassist.Loader;
import javassist.NotFoundException;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.jmfgameiro.resources.core.random.RandomString;

public class HelloWorldTest {
	
	/***** CONSTANTS *****/
	private static final ClassPool POOL = ClassPool.getDefault();
	private static final Loader LOADER = new Loader( POOL );
	private static final String CLASS_NAME = "HelloWorld";
	private static final String CLASS_FIELD_NAME_VALUE = RandomString.randomName();
	
	
	/***** BEFORE *****/
	@BeforeClass
	public static void beforeClass() {
		try {
			POOL.getCtClass( CLASS_NAME );
		}
		catch( NotFoundException nfe ) {
			try {
				CtClass clazz = POOL.makeClass( CLASS_NAME );
				clazz.addField( CtField.make( "private String name;", clazz ) );
				clazz.addMethod( CtNewMethod.make( "public String getName() { return this.name; }", clazz ) );
				clazz.addMethod( CtNewMethod.make( "public void setName( String name ) { this.name = name; }", clazz ) );
			}
			catch( Exception e ) {
				e.printStackTrace();
				fail( e.getMessage() );
			}
		}
	}
	
	
	/***** TEST *****/
	@Test
	public void helloWorld() {
		try {
			Class< ? > clazz = LOADER.loadClass( CLASS_NAME );
			Object hello = clazz.newInstance();
			
			// SET
			Class< ? >[] formalParams = new Class< ? >[] { String.class };
			Method method = clazz.getDeclaredMethod( "setName", formalParams );
			
			Object[] actualParams = new Object[] { CLASS_FIELD_NAME_VALUE };
			method.invoke( hello, actualParams );
			
			// GET
			formalParams = new Class[] {};
			method = clazz.getDeclaredMethod( "getName", formalParams );
			
			actualParams = new Object[] {};
			String actual = ( String )method.invoke( hello, actualParams );
			
			// TEST
			assertEquals( CLASS_FIELD_NAME_VALUE, actual );
			System.out.println( "HelloWorldTest - helloWorld: " + actual );
			
		} catch( Exception e ) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	
}
