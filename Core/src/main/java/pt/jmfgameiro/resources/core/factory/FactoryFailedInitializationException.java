package pt.jmfgameiro.resources.core.factory;

/**
 * @author João Gameiro
 *
 */
public final class FactoryFailedInitializationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param cause
	 */
	public FactoryFailedInitializationException( Throwable cause ) {
		super( cause );
	}
	
}
