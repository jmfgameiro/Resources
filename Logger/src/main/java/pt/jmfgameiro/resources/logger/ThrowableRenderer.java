package pt.jmfgameiro.resources.logger;

import java.io.Serializable;

import ch.qos.logback.classic.pattern.ExtendedThrowableProxyConverter;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;

final class ThrowableRenderer extends ExtendedThrowableProxyConverter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	/***** CONSTANTS *****/
	private final String exceptionIdenter;
	
	
	/***** CONSTRUCTOR *****/
	ThrowableRenderer( String exceptionIdenter ) {
		this.exceptionIdenter = exceptionIdenter;
	}
	
	
	/***** PROTECTED *****/
	@Override
	protected String throwableProxyToString( IThrowableProxy throwableProxy ) {
		StringBuilder stringBuilder = new StringBuilder( BUILDER_CAPACITY );
		recursiveAppend( stringBuilder, this.exceptionIdenter, ThrowableProxyUtil.REGULAR_EXCEPTION_INDENT, throwableProxy );
		String toReturn = stringBuilder.toString();
		return toReturn.substring( 0, toReturn.length() - 2 );
	}
	
	
	/***** PRIVATE *****/
	private void recursiveAppend( StringBuilder stringBuilder, String prefix, int indent, IThrowableProxy throwableProxy ) {
		if( throwableProxy == null )
			return;
		subjoinFirstLine( stringBuilder, prefix, throwableProxy );
		stringBuilder.append( CoreConstants.LINE_SEPARATOR );
		subjoinSTEPArray( stringBuilder, indent + 1, throwableProxy );
		IThrowableProxy[] suppressed = throwableProxy.getSuppressed();
		if( suppressed != null ) {
			for( IThrowableProxy current : suppressed )
				recursiveAppend( stringBuilder, CoreConstants.SUPPRESSED, indent + ThrowableProxyUtil.SUPPRESSED_EXCEPTION_INDENT, current );
		}
		recursiveAppend( stringBuilder, prefix + CoreConstants.CAUSED_BY, indent, throwableProxy.getCause() );
	}
	
	private void subjoinFirstLine( StringBuilder stringBuilder, String prefix, IThrowableProxy throwableProxy ) {
		stringBuilder.append( prefix );
		subjoinExceptionMessage( stringBuilder, throwableProxy );
	}
	
	private void subjoinExceptionMessage( StringBuilder stringBuilder, IThrowableProxy throwableProxy ) {
		stringBuilder.append( throwableProxy.getClassName() ).append( ": " );
		if( throwableProxy.getMessage() != null ) {
			stringBuilder.append( throwableProxy
					.getMessage()
					.substring( 0, throwableProxy.getMessage().length() )
					.replaceAll( "\n", "\n".concat( this.exceptionIdenter ).concat( this.getSpaces( throwableProxy.getClassName().length() + 2 ) ) ) );
		}
		else
			stringBuilder.append( "null" );
	}
	
	@Override
	protected void subjoinSTEPArray( StringBuilder stringBuilder, int indent, IThrowableProxy throwableProxy ) {
		StringBuilder stringBuilderTemp = new StringBuilder();
		super.subjoinSTEPArray( stringBuilderTemp, indent, throwableProxy );
		stringBuilder.append( stringBuilderTemp.toString().replaceAll( "\t\t", this.exceptionIdenter.concat( "    " ) ) );
	}
	
	private String getSpaces( int n ) {
		StringBuilder sb = new StringBuilder( n );
		for( int x = 0; x < n; x++ )
			sb.append( " " );
		return sb.toString();
	}
	
	
}
