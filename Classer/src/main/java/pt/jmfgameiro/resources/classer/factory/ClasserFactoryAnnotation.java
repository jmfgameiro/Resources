package pt.jmfgameiro.resources.classer.factory;

import java.util.Map.Entry;

import javassist.CannotCompileException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ByteMemberValue;
import javassist.bytecode.annotation.ClassMemberValue;
import javassist.bytecode.annotation.DoubleMemberValue;
import javassist.bytecode.annotation.FloatMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.LongMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.ShortMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

final class ClasserFactoryAnnotation {
	
	/***** STATIC *****/
	static AnnotationsAttribute createAnnotation( ConstPool constPool, ClasserBuilderAnnotation builder ) throws CannotCompileException {
		AnnotationsAttribute attr = new AnnotationsAttribute( constPool, AnnotationsAttribute.visibleTag );
		Annotation annot = new Annotation( builder.getClazz().getName(), constPool );
		
		for( Entry< String, Object > entry : builder.getValues().entrySet() ) {
			MemberValue memberValue = getMemberValue( constPool,  entry.getValue() );
			annot.addMemberValue( entry.getKey(), memberValue );
		}
		
		attr.setAnnotation( annot );
		return attr;
	}
	
	
	/***** PRIVATE *****/
	private static MemberValue getMemberValue( ConstPool constPool, Object value ) throws CannotCompileException {
		MemberValue memberValue = null;
		
		if( value instanceof Byte )
			memberValue = new ByteMemberValue( ( Byte )value, constPool );
		else if( value instanceof Short )
			memberValue = new ShortMemberValue( ( Short )value, constPool );
		else if( value instanceof Integer )
			memberValue = new IntegerMemberValue( constPool, ( Integer ) value );
		else if( value instanceof Float )
			memberValue = new FloatMemberValue( ( Float )value, constPool );
		else if( value instanceof Long )
			memberValue = new LongMemberValue( ( Long )value, constPool );
		else if( value instanceof Double )
			memberValue = new DoubleMemberValue( ( Double )value, constPool );
		else if( value instanceof String )
			memberValue = new StringMemberValue( ( String )value, constPool );
		else if( value instanceof Class )
			memberValue = new ClassMemberValue( ( ( Class< ? > ) value).getName(), constPool );
		else
			throw new CannotCompileException( "The provided object cannot be instantiated in a MemberValue class!" );
		
		return memberValue;
	}
	
	
}
