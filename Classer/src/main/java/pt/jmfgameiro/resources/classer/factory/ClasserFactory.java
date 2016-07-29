package pt.jmfgameiro.resources.classer.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.jmfgameiro.resources.classer.Classer;
import pt.jmfgameiro.resources.classer.ClasserField;
import pt.jmfgameiro.resources.classer.ClasserMethod;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.Loader;
import javassist.Modifier;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;

public final class ClasserFactory {
	
	/***** CONSTANTS *****/
	private static final ClassPool POOL = ClassPool.getDefault();
	private static final Loader LOADER = new Loader( POOL );
	
	
	/***** PUBLIC *****/
	public static Classer getClasser( String name ) throws ClassNotFoundException {
		Class< ? > clazz = LOADER.loadClass( name );
		
		// Fetch class fields
		Map< String, ClasserField > fields = new HashMap< String, ClasserField >();
		for( Field clazzField : clazz.getDeclaredFields() )
			fields.put( clazzField.getName(), new ClasserField( clazzField.getType(), clazzField.getModifiers() ) );
		
		// Fetch class methods
		Map< String, ClasserMethod > methods = new HashMap< String, ClasserMethod >();
		for( Method clazzMethod : clazz.getDeclaredMethods() ) {
			if( clazzMethod.getName().contains( "get" ) || clazzMethod.getName().contains( "set" ) )
				continue;
			
			List< ClasserField > parameters = new ArrayList< ClasserField >();
			for( Parameter parameter : clazzMethod.getParameters() )
				parameters.add( new ClasserField( parameter.getClass(), parameter.getModifiers() ) );
			methods.put( clazzMethod.getName(), new ClasserMethod( clazzMethod.getReturnType(), clazzMethod.getModifiers(), parameters ) );
		}
		
		return new Classer( clazz, fields, methods );
	}
	public static Classer createClasser( ClasserBuilder builder ) throws CannotCompileException {
		CtClass clazz = POOL.makeClass( builder.getName() );
		
		ClassFile clazzFile = clazz.getClassFile();
		ConstPool constPool = clazzFile.getConstPool();
		
		// Insert annotations into the Class
		for( ClasserBuilderAnnotation annotation : builder.getAnnotations() )
			clazzFile.addAttribute( ClasserFactoryAnnotation.createAnnotation( constPool, annotation ) );
		
		// Insert fields (with respective getter/setter) into the Class
		Map< String, ClasserField > fields = createFields( constPool, builder.getFields(), clazz );
		
		// Insert methods into the Class
		Map< String, ClasserMethod > methods = createMethods( constPool, builder.getMethods(), clazz );
		
		return new Classer( clazz.toClass(), fields, methods );
	}
	
	
	/***** PRIVATE *****/
	private static Map< String, ClasserField > createFields( ConstPool constPool, List< ClasserBuilderField > fields, CtClass clazz ) throws CannotCompileException {
		Map< String, ClasserField > customFields = new HashMap< String, ClasserField >();
		
		for( ClasserBuilderField field : fields ) {
			// Prepare variables
			Class< ? > fieldClass = field.getClazz();
			String fieldName = field.getName();
			int fieldModifier = field.getModifier();
			
			// Create Field
			List< AnnotationsAttribute > annotations = new ArrayList< AnnotationsAttribute >();
			for( ClasserBuilderAnnotation annotation : field.getAnnotations() )
				annotations.add( ClasserFactoryAnnotation.createAnnotation( constPool, annotation ) );
			CtField clazzField = ClasserFactoryField.createField( annotations, Modifier.toString( fieldModifier ), fieldClass, fieldName, clazz );
			clazz.addField( clazzField );
			
			// Create Field Annotations
			for( ClasserBuilderAnnotation annotation : field.getAnnotations() )
				clazzField.getFieldInfo().addAttribute( ClasserFactoryAnnotation.createAnnotation( constPool, annotation ) );
			
			// Prepare field to be used
			customFields.put( fieldName, new ClasserField( fieldClass, field.getModifier() ) );
			
			// Prepare final variables
			String fieldMethodName = fieldName.substring( 0, 1 ).toUpperCase() + fieldName.substring( 1 );
			int fieldMethodModifier = Modifier.PUBLIC;
			
			// Create Getter
			String body = "return this." + fieldName + ";";
			clazz.addMethod( ClasserFactoryMethod.createMethod( Modifier.toString( fieldMethodModifier ), fieldClass, "get" + fieldMethodName, body, clazz ) );
			
			// Create Setter
			List< ClasserBuilderField > parameters = new ArrayList< ClasserBuilderField >();
			parameters.add( new ClasserBuilderField( fieldName, fieldClass ) );
			body = "this." + fieldName + " = " + fieldName + ";";
			clazz.addMethod( ClasserFactoryMethod.createMethod( Modifier.toString( fieldMethodModifier ), "set" + fieldMethodName, parameters, body, clazz ) );
		}
		
		return customFields;
	}
	private static Map< String, ClasserMethod > createMethods( ConstPool constPool, List< ClasserBuilderMethod > methods, CtClass clazz ) throws CannotCompileException {
		Map< String, ClasserMethod > customMethods = new HashMap< String, ClasserMethod >();
		
		for( ClasserBuilderMethod method : methods ) {
			// Prepare Method Annotations
			List< AnnotationsAttribute > annotations = new ArrayList< AnnotationsAttribute >();
			for( ClasserBuilderAnnotation annotation : method.getAnnotations() )
				annotations.add( ClasserFactoryAnnotation.createAnnotation( constPool, annotation ) );
			
			// Create Method
			clazz.addMethod( ClasserFactoryMethod.createMethod( 
					annotations,
					Modifier.toString( method.getModifier() ),
					method.getReturnClazz(),
					method.getName(),
					method.getParameters(),
					method.getBody(),
					clazz ) );
			
			// Create Reflection Method Values
			List< ClasserField > parameters = new ArrayList< ClasserField >();
			for( ClasserBuilderField parameter : method.getParameters() )
				parameters.add( new ClasserField( parameter.getClazz(), parameter.getModifier() ) );
			customMethods.put( method.getName(), new ClasserMethod( method.getReturnClazz(), method.getModifier(), parameters ) );
		}
		
		return customMethods;
	}
	
	
}
