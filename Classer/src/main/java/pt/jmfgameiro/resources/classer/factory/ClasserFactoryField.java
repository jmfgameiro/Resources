package pt.jmfgameiro.resources.classer.factory;

import java.util.List;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtField;
import javassist.bytecode.AnnotationsAttribute;

final class ClasserFactoryField {
	
	/***** STATIC *****/
	static CtField createField( String modifier, Class< ? > type, String name, CtClass declaring ) throws CannotCompileException {
		return createField( null, modifier, type, name, declaring );
	}
	static CtField createField( List< AnnotationsAttribute > annotations, String modifier, Class< ? > type, String name, CtClass declaring ) throws CannotCompileException {
		// VALIDATIONS
		if( modifier == null )
			throw new CannotCompileException( "Modifier must be defined!" );
		if( type == null )
			throw new CannotCompileException( "Type must be defined!" );
		if( name == null )
			throw new CannotCompileException( "Field name must be defined!" );
		if( declaring == null )
			throw new CannotCompileException( "Declaring must be defined!" );
		
		StringBuilder builder = new StringBuilder();
		
		// MODIFIER
		builder.append( modifier );
		// TYPE
		builder.append( " " ).append( type.getName() );
		// NAME
		builder.append( " " ).append( name ).append( ";" );
		
		// MAKE FIELD
		CtField field = CtField.make( builder.toString(), declaring );
		
		// ANNOTATIONS
		if( annotations != null ) {
			for( AnnotationsAttribute annotation : annotations )
				field.getFieldInfo().addAttribute( annotation );
		}
		
		return field;
	}
	
	
}
