package pt.jmfgameiro.resources.core.encoder;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import pt.jmfgameiro.resources.core.encoder.resource.DigestEncoderHashWithSalt;

/**
 * @author João Gameiro
 *
 */
public final class DigestEncoder {
	
	/***** CONSTRUCTOR *****/
	/**
	 * 
	 */
	private DigestEncoder() {}
	
	
	/***** PUBLIC *****/
	/**
	 * @param algorithm
	 * @param charset
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static final String hash( DigestEncoderAlgorithm algorithm, Charset charset, String str ) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance( algorithm.getName() );
		digest.update( str.getBytes( charset ) );
		byte[] converted = Base64.getEncoder().encode( digest.digest() );
		return new StringBuilder( new String( converted ) ).toString();
	}
	/**
	 * @param algorithm
	 * @param charset
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static final DigestEncoderHashWithSalt hashWithSalt( DigestEncoderAlgorithm algorithm, Charset charset, String str ) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance( algorithm.getName() );
		digest.update( str.getBytes( charset ) );
		
		// Generate salt
		SecureRandom sr = new SecureRandom();
		byte[] saltGen = new byte[ 32 ];
		sr.nextBytes( saltGen );
		String salt = Arrays.toString( saltGen );
		
		// Digest the salt
		byte[] converted = Base64.getEncoder().encode( digest.digest( salt.getBytes() ) );
		return new DigestEncoderHashWithSalt( new StringBuilder( new String( converted ) ).toString(), salt );
	}
	/**
	 * @param algorithm
	 * @param charset
	 * @param str
	 * @param hash
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static final boolean validHash( DigestEncoderAlgorithm algorithm, Charset charset, String str, String hash ) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance( algorithm.getName() );
		digest.update( str.getBytes( charset ) );
		byte[] converted = Base64.getEncoder().encode( digest.digest() );
		String providedHash = new StringBuilder( new String( converted ) ).toString();
		return providedHash.equals( hash );
	}
	/**
	 * @param algorithm
	 * @param charset
	 * @param str
	 * @param hashWithSalt
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static final boolean validHash( DigestEncoderAlgorithm algorithm, Charset charset, String str, DigestEncoderHashWithSalt hashWithSalt ) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance( algorithm.getName() );
		digest.update( str.getBytes( charset ) );
		byte[] converted = Base64.getEncoder().encode( digest.digest( hashWithSalt.getSalt().getBytes() ) );
		String providedHash = new StringBuilder( new String( converted ) ).toString();
		return providedHash.equals( hashWithSalt.getHash() );
	}
	
	
}
