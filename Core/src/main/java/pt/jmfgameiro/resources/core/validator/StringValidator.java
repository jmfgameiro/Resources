package pt.jmfgameiro.resources.core.validator;

/**
 * @author João Gameiro
 *
 */
public final class StringValidator {
	
	/***** CONSTRUCTOR *****/
	/**
	 * 
	 */
	private StringValidator() {}
	
	
	/***** PUBLIC *****/
	/**
	 * @param str
	 * @return
	 */
	public static final boolean isNullOrEmpty( String str ) {
		if( str == null || str.isEmpty() )
			return true;
		return false;
	}
	
	
}
