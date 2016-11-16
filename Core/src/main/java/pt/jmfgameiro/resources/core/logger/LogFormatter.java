package pt.jmfgameiro.resources.core.logger;

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

/**
 * @author João Gameiro
 *
 */
final class LogFormatter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	/***** CONSTANTS *****/
	private static final String BEGIN_MODIFIER = "<CALL>";
	private static final String END_MODIFIER = "<RETURN>";
	private static final String EXCEPTION_MODIFIER = "<EXCEPTION>";
	private static final String EXCEPTION_IDENTER = "------: ";
	private static final String SIMPLE_MESSAGE_MODIFIER = "<MESSAGE>";
	private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS z";
	private final ThrowableRenderer throwableRenderer;
	
	
	/***** CONSTRUCTOR *****/
	/**
	 * 
	 */
	LogFormatter() {
		this.throwableRenderer = new ThrowableRenderer( EXCEPTION_IDENTER );
		throwableRenderer.start();
	}
	
	
	/***** DEFAULT *****/
	/**
	 * @param attributes
	 * @return
	 */
	String formatParameters( Object ... attributes ) {
		return formatParametersAux( "", attributes );
	}
	
	/**
	 * @param code
	 * @param methodName
	 * @param attributes
	 * @return
	 */
	String formatMethodCall( String code, String methodName, Object ... attributes ) {
		StringBuilder stringBuilder = new StringBuilder( code + " " + BEGIN_MODIFIER + " " );
		formatMethodCallBody( stringBuilder, methodName, this.formatParametersAux( " ", attributes ) );
		return stringBuilder.toString();
	}
	
	/**
	 * @param code
	 * @param methodName
	 * @param attribute
	 * @return
	 */
	String formatMethodReturn( String code, String methodName, Object attribute ) {
		StringBuilder stringBuilder = new StringBuilder( code + " " + END_MODIFIER + " " );
		formatMethodReturnBody( stringBuilder, methodName, attribute );
		return stringBuilder.toString();
	}
	
	/**
	 * @param code
	 * @param methodName
	 * @return
	 */
	String formatMethodReturn( String code, String methodName ) {
		StringBuilder stringBuilder = new StringBuilder( code + " " + END_MODIFIER + " " );
		formatMethodReturnBody( stringBuilder, methodName );
		return stringBuilder.toString();
	}
	
	/**
	 * @param code
	 * @param message
	 * @param throwable
	 * @return
	 */
	String formatThrowable( String code, String message, Throwable throwable ) {
		ThrowableProxy throwableProxy = new ThrowableProxy( throwable );
		throwableProxy.calculatePackagingData();
		StringBuilder stringBuilder = new StringBuilder( code + " " + EXCEPTION_MODIFIER + " " + message + "\n" );
		stringBuilder.append( this.throwableRenderer.throwableProxyToString( throwableProxy ) );
		return stringBuilder.toString();
	}
	
	/**
	 * @param code
	 * @param message
	 * @param attributes
	 * @return
	 */
	String formatMessage( String code, String message, Object ... attributes ) {
		StringBuilder stringBuilder = new StringBuilder( code + " " + SIMPLE_MESSAGE_MODIFIER + " " + message );
		String parameters = this.formatParametersAux( "", attributes );
		if( !parameters.isEmpty() )
			stringBuilder.append( " : " ).append( parameters );
		return stringBuilder.toString();
	}
	
	
	/***** PRIVATE *****/
	/**
	 * @param spaces
	 * @param attributes
	 * @return
	 */
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
	
	/**
	 * @param stringBuilder
	 * @param methodName
	 * @param parameters
	 */
	private void formatMethodCallBody( StringBuilder stringBuilder, String methodName, String parameters ) {
		stringBuilder.append( methodName + "(" + parameters + ")" );
	}
	
	/**
	 * @param stringBuilder
	 * @param methodName
	 * @param attribute
	 */
	private void formatMethodReturnBody( StringBuilder stringBuilder, String methodName, Object attribute ) {
		Gson gsonFormatter = gsonFormatter();
		stringBuilder.append( methodName + "( {\"return\":" + gsonFormatter.toJson( attribute ) + "} )" );
	}
	
	/**
	 * @param stringBuilder
	 * @param methodName
	 */
	private void formatMethodReturnBody( StringBuilder stringBuilder, String methodName ) {
		stringBuilder.append( methodName + "( void )" );
	}
	
	/**
	 * @param stringBuilder
	 * @param attributeName
	 * @param attribute
	 */
	private void formatAttribute( StringBuilder stringBuilder, String attributeName, Object attribute ) {
		Gson gsonFormatter = gsonFormatter();
		stringBuilder.append( gsonFormatter.toJson( attributeName ) + ":" + gsonFormatter.toJson( attribute ) );
	}
	
	/**
	 * @return
	 */
	private Gson gsonFormatter() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls().setDateFormat( DATE_FORMAT );
		gsonBuilder.registerTypeAdapter( GregorianCalendar.class, new GregorianCalendarJsonSerializer() );
		gsonBuilder.registerTypeAdapter( LocalDateTime.class, new LocalDateTimeJsonSerializer() );
		gsonBuilder.registerTypeAdapter( LocalDate.class, new LocalDateJsonSerializer() );
		gsonBuilder.registerTypeAdapter( byte[].class, new ByteArrayJsonSerializer() );
		return gsonBuilder.create();
	}
	
	
}
