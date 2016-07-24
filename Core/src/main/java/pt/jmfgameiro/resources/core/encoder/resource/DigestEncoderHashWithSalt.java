package pt.jmfgameiro.resources.core.encoder.resource;

public final class DigestEncoderHashWithSalt {
	
	/***** CONSTANTS *****/
	private final String hash;
	private final String salt;
	
	
	/***** CONSTRUCTOR *****/
	public DigestEncoderHashWithSalt( String hash, String salt ) {
		this.hash = hash;
		this.salt = salt;
	}
	
	
	/***** GET *****/
	public String getHash() {
		return hash;
	}
	public String getSalt() {
		return salt;
	}
	
	
}
