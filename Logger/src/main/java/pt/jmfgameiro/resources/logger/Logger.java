package pt.jmfgameiro.resources.logger;

public final class Logger {
	
	/***** CONSTANTS *****/
	private final String code;
	private final org.slf4j.Logger slf4jLogger;
	private final LogFormatter formatter;
	
	
	/***** CONSTRUCTOR *****/
	Logger( String code, org.slf4j.Logger slf4jLogger, LogFormatter formatter ) {
		this.code = code;
		this.slf4jLogger = slf4jLogger;
		this.formatter = formatter;
	}
	
	
	/***** PUBLIC *****/
	public void logOperationCallAsDebug( String operationName, Object ... parameters ) {
		this.slf4jLogger.debug( this.formatter.formatMethodCall( this.code, operationName, parameters ) );
	}
	
	public void logOperationCallAsInfo( String operationName, Object ... parameters ) {
		this.slf4jLogger.info( this.formatter.formatMethodCall( this.code, operationName, parameters ) );
	}
	
	public void logOperationReturnAsDebug( String operationName, Object returnValue ) {
		this.slf4jLogger.debug( this.formatter.formatMethodReturn( this.code, operationName, returnValue ) );
	}
	
	public void logOperationReturnAsInfo( String operationName, Object returnValue ) {
		this.slf4jLogger.info( this.formatter.formatMethodReturn( this.code, operationName, returnValue ) );
	}
	
	public void logOperationReturnAsDebug( String operationName ) {
		this.slf4jLogger.debug( this.formatter.formatMethodReturn( this.code, operationName ) );
	}
	
	public void logOperationReturnAsInfo( String operationName ) {
		this.slf4jLogger.info( this.formatter.formatMethodReturn( this.code, operationName ) );
	}
	
	public void logThrowableAsDebug( String message, Throwable throwable ) {
		this.slf4jLogger.debug( this.formatter.formatThrowable( this.code, message, throwable ) );
	}
	
	public void logThrowableAsWarn( String message, Throwable throwable ) {
		this.slf4jLogger.warn( this.formatter.formatThrowable( this.code, message, throwable ) );
	}
	
	public void logThrowableAsError( String message, Throwable throwable ) {
		this.slf4jLogger.error( this.formatter.formatThrowable( this.code, message, throwable ) );
	}
	
	public void debug( String message ) {
		this.slf4jLogger.debug( this.formatter.formatMessage( this.code, message ) );
	}
	
	public void debug( String message, Object ... parameters ) {
		this.slf4jLogger.debug( this.formatter.formatMessage( this.code, message, parameters ) );
	}
	
	public void info( String message ) {
		this.slf4jLogger.info( this.formatter.formatMessage( this.code, message ) );
	}
	
	public void info( String message, Object ... parameters ) {
		this.slf4jLogger.info( this.formatter.formatMessage( this.code, message, parameters ) );
	}
	
}
