package pt.jmfgameiro.resources.logger;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;

import pt.jmfgameiro.resources.core.jsonserializer.ByteArrayJsonSerializer;
import pt.jmfgameiro.resources.core.jsonserializer.GregorianCalendarJsonSerializer;
import pt.jmfgameiro.resources.core.jsonserializer.LocalDateJsonSerializer;
import pt.jmfgameiro.resources.core.jsonserializer.LocalDateTimeJsonSerializer;
import ch.qos.logback.classic.spi.ThrowableProxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

final class LogFormatter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	/***** CONSTANTS *****/
	private final String beginModifier = "<CALL>";
	private final String endModifier = "<RETURN>";
	private final String exceptionModifier = "<EXCEPTION>";
	private final String exceptionIdenter = "------: ";
	private final String simpleMessageModifier = "<MESSAGE>";
	private final String dateFormat = "dd-MM-yyyy HH:mm:ss.SSS z";
	private final ThrowableRenderer throwableRenderer;
	
	
	/***** CONSTRUCTOR *****/
	LogFormatter() {
		this.throwableRenderer = new ThrowableRenderer( this.exceptionIdenter );
		throwableRenderer.start();
	}
	
	
	/***** DEFAULT *****/
	String formatParameters( Object ... attributes ) {
		return formatParametersAux( "", attributes );
	}
	
	String formatMethodCall( String code, String methodName, Object ... attributes ) {
		StringBuilder stringBuilder = new StringBuilder( code + " " + this.beginModifier + " " );
		formatMethodCallBody( stringBuilder, methodName, this.formatParametersAux( " ", attributes ) );
		return stringBuilder.toString();
	}
	
	String formatMethodReturn( String code, String methodName, Object attribute ) {
		StringBuilder stringBuilder = new StringBuilder( code + " " + this.endModifier + " " );
		formatMethodReturnBody( stringBuilder, methodName, attribute );
		return stringBuilder.toString();
	}
	
	String formatMethodReturn( String code, String methodName ) {
		StringBuilder stringBuilder = new StringBuilder( code + " " + this.endModifier + " " );
		formatMethodReturnBody( stringBuilder, methodName );
		return stringBuilder.toString();
	}
	
	String formatThrowable( String code, String message, Throwable throwable ) {
		ThrowableProxy throwableProxy = new ThrowableProxy( throwable );
		throwableProxy.calculatePackagingData();
		StringBuilder stringBuilder = new StringBuilder( code + " " + this.exceptionModifier + " " + message + "\n" );
		stringBuilder.append( this.throwableRenderer.throwableProxyToString( throwableProxy ) );
		return stringBuilder.toString();
	}
	
	String formatMessage( String code, String message, Object ... attributes ) {
		StringBuilder stringBuilder = new StringBuilder( code + " " + this.simpleMessageModifier + " " + message );
		String parameters = this.formatParametersAux( "", attributes );
		if( parameters.isEmpty() == false )
			stringBuilder.append( " : " ).append( parameters );
		return stringBuilder.toString();
	}
	
	
	/***** PRIVATE *****/
	private String formatParametersAux( String spaces, Object ... attributes ) {
		StringBuilder stringBuilder = new StringBuilder();
		if( attributes != null && attributes.length != 0 ) {
			stringBuilder.append( spaces + "{" );
			for( int i = 0; i < attributes.length; i += 2 ) {
				formatAttribute( stringBuilder, ( String )attributes[ i ], attributes[ i + 1 ] );
				stringBuilder.append( "," );
			}
			stringBuilder.replace( stringBuilder.length() - 1, stringBuilder.length(), "" );
			stringBuilder.append( "}" + spaces );
		}
		return stringBuilder.toString();
	}
	
	private void formatMethodCallBody( StringBuilder stringBuilder, String methodName, String parameters ) {
		stringBuilder.append( methodName + "(" + parameters + ")" );
	}
	
	private void formatMethodReturnBody( StringBuilder stringBuilder, String methodName, Object attribute ) {
		Gson gsonFormatter = gsonFormatter();
		stringBuilder.append( methodName + "( {\"return\":" + gsonFormatter.toJson( attribute ) + "} )" );
	}
	
	private void formatMethodReturnBody( StringBuilder stringBuilder, String methodName ) {
		stringBuilder.append( methodName + "( void )" );
	}
	
	private void formatAttribute( StringBuilder stringBuilder, String attributeName, Object attribute ) {
		Gson gsonFormatter = gsonFormatter();
		stringBuilder.append( gsonFormatter.toJson( attributeName ) + ":" + gsonFormatter.toJson( attribute ) );
	}
	
	private Gson gsonFormatter() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls().setDateFormat( dateFormat );
		gsonBuilder.registerTypeAdapter( GregorianCalendar.class, new GregorianCalendarJsonSerializer() );
		gsonBuilder.registerTypeAdapter( LocalDateTime.class, new LocalDateTimeJsonSerializer() );
		gsonBuilder.registerTypeAdapter( LocalDate.class, new LocalDateJsonSerializer() );
		gsonBuilder.registerTypeAdapter( byte[].class, new ByteArrayJsonSerializer() );
		return gsonBuilder.create();
	}
	
	
}
