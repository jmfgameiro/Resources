package pt.jmfgameiro.resources.core.property;

/**
 * @author João Gameiro
 *
 * @param <T>
 */
@FunctionalInterface
public interface Property< T > {
	
	
	public T getProperty();
	
	
}
