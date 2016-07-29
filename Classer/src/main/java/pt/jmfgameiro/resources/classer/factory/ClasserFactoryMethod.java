package pt.jmfgameiro.resources.classer.factory;

import java.util.List;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.bytecode.AnnotationsAttribute;

final class ClasserFactoryMethod {
	
	/***** STATIC *****/
	static CtMethod createMethod( String modifier, String name, String body, CtClass declaring ) throws CannotCompileException {
		return createMethod( null, modifier, null, name, null, body, declaring );
	}
	static CtMethod createMethod( String modifier, String name, List < ClasserBuilderField > methodParameters, String body, CtClass declaring ) throws CannotCompileException {
		return createMethod( null, modifier, null, name, methodParameters, body, declaring );
	}
	static CtMethod createMethod( String modifier, Class< ? > returnType, String name, String body, CtClass declaring ) throws CannotCompileException {
		return createMethod( null, modifier, returnType, name, null, body, declaring );
	}
	static CtMethod createMethod( String modifier, Class< ? > returnType, String name, List < ClasserBuilderField > methodParameters, String body, CtClass declaring ) throws CannotCompileException {
		return createMethod( null, modifier, returnType, name, methodParameters, body, declaring );
	}
	static CtMethod createMethod( List< AnnotationsAttribute > annotations, String modifier, String name, String body, CtClass declaring ) throws CannotCompileException {
		return createMethod( annotations, modifier, null, name, null, body, declaring );
	}
	static CtMethod createMethod( List< AnnotationsAttribute > annotations, String modifier, String name, List < ClasserBuilderField > methodParameters, String body, CtClass declaring ) throws CannotCompileException {
		return createMethod( annotations, modifier, null, name, methodParameters, body, declaring );
	}
	static CtMethod createMethod( List< AnnotationsAttribute > annotations, String modifier, Class< ? > returnType, String name, String body, CtClass declaring ) throws CannotCompileException {
		return createMethod( annotations, modifier, returnType, name, null, body, declaring );
	}
	static CtMethod createMethod( List< AnnotationsAttribute > annotations, String modifier, Class< ? > returnType, String name, List < ClasserBuilderField > methodParameters, String body, CtClass declaring ) throws CannotCompileException {
		// VALIDATIONS
		if( modifier == null )
			throw new CannotCompileException( "Modifier must be defined!" );
		if( name == null )
			throw new CannotCompileException( "Method name must be defined!" );
		if( body == null )
			throw new CannotCompileException( "Body must be defined!" );
		if( declaring == null )
			throw new CannotCompileException( "Declaring must be defined!" );
		
		
		StringBuilder builder = new StringBuilder();
		
		// MODIFIER
		builder.append( modifier );
		// RETURN TYPE
		builder.append( " " ).append( returnType == null ? "void" : returnType.getName() );
		// NAME
		builder.append( " " ).append( name ).append( "(" );
		// PARAMETERS
		if( methodParameters != null ) {
			int i = 0;
			for( ClasserBuilderField parameter : methodParameters ) {
				if( i > 0 )
					builder.append( "," );
				
				//TODO ANNOTATIONS
				/*if( parameter.getAnnotations() != null ) {
					for( ClasserBuilderAnnotation annotation : parameter.getAnnotations() )
						builder.append( " @" ).append( annotation.getClazz().getName() );
				}*/
				// TYPE
				builder.append( " " ).append( parameter.getClazz().getName() );
				// NAME
				builder.append( " " ).append( parameter.getName() );
				
				i++;
			}
			if( i > 0 )
				builder.append( " " );
		}
		// BODY
		builder.append( ") { " ).append( body ).append( " }" );
		CtMethod method = CtNewMethod.make( builder.toString(), declaring );
		
		// METHOD ANNOTATIONS
		if( annotations != null ) {
			for( AnnotationsAttribute annotation: annotations )
				method.getMethodInfo().addAttribute( annotation );
		}
		
		
		// RETURN
		return method;
	}
	
	
	
}
