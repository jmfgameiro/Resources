package pt.jmfgameiro.resources.core.encoder;

/**
 * @author João Gameiro
 *
 */
public enum DigestEncoderAlgorithm {
	MD2( "MD2" ),
	MD5( "MD5" ),
	SHA( "SHA-1" ),
	SHA256( "SHA-256" ),
	SHA384( "SHA-384" ),
	SHA512( "SHA-512" );
	
	
	/***** CONSTANTS *****/
	private final String name;
	
	
	/***** CONSTRUCTOR *****/
	/**
	 * @param name
	 */
	private DigestEncoderAlgorithm( String name ) {
		this.name = name;
	}
	
	
	/***** GETTER *****/
	/**
	 * @return
	 */
	public final String getName() {
		return this.name;
	}
	
	
}
