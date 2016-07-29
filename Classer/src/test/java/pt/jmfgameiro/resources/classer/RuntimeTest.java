package pt.jmfgameiro.resources.classer;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import pt.jmfgameiro.resources.classer.factory.ClasserBuilder;
import pt.jmfgameiro.resources.classer.factory.ClasserBuilderAnnotation;
import pt.jmfgameiro.resources.classer.factory.ClasserBuilderMethod;
import pt.jmfgameiro.resources.classer.factory.ClasserFactory;

public class RuntimeTest {
	
	/***** CONSTANTS *****/
	private static final String CLASS_NAME = "CreatedRuntimeTest";
	private static final String METHOD_NAME = "createdTest";
	private static final String METHOD_BODY = "System.out.println( \"" + CLASS_NAME + " - " + METHOD_NAME + "\" );";
	
	
	/***** BEFORE *****/
	@BeforeClass
	public static void beforeClass() throws Exception {
		ClasserBuilder builder = new ClasserBuilder( CLASS_NAME );
		ClasserBuilderMethod method = new ClasserBuilderMethod( METHOD_NAME, METHOD_BODY ).addAnnotation( new ClasserBuilderAnnotation( Test.class ) );
		builder.addMethod( method );
		ClasserFactory.createClasser( builder );
	}
	
	
	/***** TEST *****/
	@Test
	public void launchCompileTest() {
		try {
			JUnitCore.runClasses( HelloWorldTest.class, ClasserFactoryTest.class );
			System.out.println( "=> RuntimeTest - launchCompileTest: OK" );
		}
		catch( Exception e ) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	@Test
	public void launchRuntimeTest() {
		try {
			//TODO Not yet running test from generated class!
			JUnitCore.runClasses( ClasserFactory.getClasser( CLASS_NAME ).getClazz() );
			System.out.println( "=> RuntimeTest - launchRuntimeTest: OK" );
		}
		catch( Exception e ) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	
}
