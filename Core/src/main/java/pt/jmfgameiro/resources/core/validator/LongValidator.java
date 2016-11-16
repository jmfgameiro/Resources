package pt.jmfgameiro.resources.core.validator;

/**
 * @author João Gameiro
 *
 */
public final class LongValidator {
	
	/***** CONSTRUCTOR *****/
	/**
	 * 
	 */
	private LongValidator() {}
	
	
	/***** PUBLIC *****/
	/**
	 * @param value
	 * @return
	 */
	public static final boolean isNullOrEqualsToZero( Long value ) {
		if( value == null || value == 0L )
			return true;
		return false;
	}
	/**
	 * @param value
	 * @return
	 */
	public static final boolean isNullOrSmallerOrEqualsToZero( Long value ) {
		if( value == null || value <= 0L )
			return true;
		return false;
	}
	/**
	 * @param value
	 * @return
	 */
	public static final boolean isNullOrBiggerOrEqualsToZero( Long value ) {
		if( value == null || value >= 0L )
			return true;
		return false;
	}
	
}
