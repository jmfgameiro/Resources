package pt.jmfgameiro.resources.core.random;

public final class RandomNumber {
	
	/***** PUBLIC *****/
	public static final double randomDouble( double min, double max ) {
	    return min + ( double )( Math.random() * ( ( max - min ) + 1 ) );
	}
	
	
}
