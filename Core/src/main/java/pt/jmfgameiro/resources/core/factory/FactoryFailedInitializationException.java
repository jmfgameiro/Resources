package pt.jmfgameiro.resources.core.factory;

public class FactoryFailedInitializationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public FactoryFailedInitializationException( Throwable cause ) {
		super( cause );
	}
	
}
