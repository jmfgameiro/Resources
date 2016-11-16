package pt.jmfgameiro.resources.core.logger;

import java.io.Serializable;

/**
 * @author João Gameiro
 *
 */
public final class Logger implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	/***** CONSTANTS *****/
	private final String code;
	private final org.slf4j.Logger slf4jLogger;
	private final LogFormatter formatter;
	
	
	/***** CONSTRUCTOR *****/
	/**
	 * @param code
	 * @param slf4jLogger
	 * @param formatter
	 */
	Logger( String code, org.slf4j.Logger slf4jLogger, LogFormatter formatter ) {
		this.code = code;
		this.slf4jLogger = slf4jLogger;
		this.formatter = formatter;
	}
	
	
	/***** PUBLIC *****/
	/**
	 * @param operationName
	 * @param parameters
	 */
	public void logOperationCallAsDebug( String operationName, Object ... parameters ) {
		this.slf4jLogger.debug( this.formatter.formatMethodCall( this.code, operationName, parameters ) );
	}
	
	/**
	 * @param operationName
	 * @param parameters
	 */
	public void logOperationCallAsInfo( String operationName, Object ... parameters ) {
		this.slf4jLogger.info( this.formatter.formatMethodCall( this.code, operationName, parameters ) );
	}
	
	/**
	 * @param operationName
	 * @param returnValue
	 */
	public void logOperationReturnAsDebug( String operationName, Object returnValue ) {
		this.slf4jLogger.debug( this.formatter.formatMethodReturn( this.code, operationName, returnValue ) );
	}
	
	/**
	 * @param operationName
	 * @param returnValue
	 */
	public void logOperationReturnAsInfo( String operationName, Object returnValue ) {
		this.slf4jLogger.info( this.formatter.formatMethodReturn( this.code, operationName, returnValue ) );
	}
	
	/**
	 * @param operationName
	 */
	public void logOperationReturnAsDebug( String operationName ) {
		this.slf4jLogger.debug( this.formatter.formatMethodReturn( this.code, operationName ) );
	}
	
	/**
	 * @param operationName
	 */
	public void logOperationReturnAsInfo( String operationName ) {
		this.slf4jLogger.info( this.formatter.formatMethodReturn( this.code, operationName ) );
	}
	
	/**
	 * @param message
	 * @param throwable
	 */
	public void logThrowableAsDebug( String message, Throwable throwable ) {
		this.slf4jLogger.debug( this.formatter.formatThrowable( this.code, message, throwable ) );
	}
	
	/**
	 * @param message
	 * @param throwable
	 */
	public void logThrowableAsWarn( String message, Throwable throwable ) {
		this.slf4jLogger.warn( this.formatter.formatThrowable( this.code, message, throwable ) );
	}
	
	/**
	 * @param message
	 * @param throwable
	 */
	public void logThrowableAsError( String message, Throwable throwable ) {
		this.slf4jLogger.error( this.formatter.formatThrowable( this.code, message, throwable ) );
	}
	
	/**
	 * @param message
	 */
	public void debug( String message ) {
		this.slf4jLogger.debug( this.formatter.formatMessage( this.code, message ) );
	}
	
	/**
	 * @param message
	 * @param parameters
	 */
	public void debug( String message, Object ... parameters ) {
		this.slf4jLogger.debug( this.formatter.formatMessage( this.code, message, parameters ) );
	}
	
	/**
	 * @param message
	 */
	public void info( String message ) {
		this.slf4jLogger.info( this.formatter.formatMessage( this.code, message ) );
	}
	
	/**
	 * @param message
	 * @param parameters
	 */
	public void info( String message, Object ... parameters ) {
		this.slf4jLogger.info( this.formatter.formatMessage( this.code, message, parameters ) );
	}
	
	
}
