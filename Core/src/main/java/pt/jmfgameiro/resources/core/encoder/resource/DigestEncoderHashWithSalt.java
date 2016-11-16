package pt.jmfgameiro.resources.core.encoder.resource;

/**
 * @author João Gameiro
 *
 */
public final class DigestEncoderHashWithSalt {
	
	/***** CONSTANTS *****/
	private final String hash;
	private final String salt;
	
	
	/***** CONSTRUCTOR *****/
	/**
	 * @param hash
	 * @param salt
	 */
	public DigestEncoderHashWithSalt( String hash, String salt ) {
		this.hash = hash;
		this.salt = salt;
	}
	
	
	/***** GET *****/
	/**
	 * @return
	 */
	public final String getHash() {
		return hash;
	}
	/**
	 * @return
	 */
	public final String getSalt() {
		return salt;
	}
	
	
}
