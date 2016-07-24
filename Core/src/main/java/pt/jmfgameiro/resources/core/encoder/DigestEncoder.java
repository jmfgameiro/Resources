package pt.jmfgameiro.resources.core.encoder;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import pt.jmfgameiro.resources.core.encoder.resource.DigestEncoderHashWithSalt;

public final class DigestEncoder {
	
	/***** DEFAULT *****/
	public static String hash( DigestEncoderAlgorithm algorithm, Charset charset, String str ) throws Exception {
		MessageDigest digest = MessageDigest.getInstance( algorithm.getName() );
		digest.update( str.getBytes( charset ) );
		byte[] converted = Base64.getEncoder().encode( digest.digest() );
		return new StringBuilder( new String( converted ) ).toString();
	}
	public static DigestEncoderHashWithSalt hashWithSalt( DigestEncoderAlgorithm algorithm, Charset charset, String str ) throws Exception {
		MessageDigest digest = MessageDigest.getInstance( algorithm.getName() );
		digest.update( str.getBytes( charset ) );
		String salt = generateSalt();
		digest.digest( salt.getBytes() );
		byte[] converted = Base64.getEncoder().encode( digest.digest() );
		return new DigestEncoderHashWithSalt( new StringBuilder( new String( converted ) ).toString(), salt );
	}
	public static boolean validHash( DigestEncoderAlgorithm algorithm, Charset charset, String str, String hash ) throws Exception {
		MessageDigest digest = MessageDigest.getInstance( algorithm.getName() );
		digest.update( str.getBytes( charset ) );
		byte[] converted = Base64.getEncoder().encode( digest.digest() );
		String providedHash = new StringBuilder( new String( converted ) ).toString();
		return providedHash.equals( hash );
	}
	public static boolean validHash( DigestEncoderAlgorithm algorithm, Charset charset, String str, DigestEncoderHashWithSalt hashWithSalt ) throws Exception {
		MessageDigest digest = MessageDigest.getInstance( algorithm.getName() );
		digest.update( str.getBytes( charset ) );
		digest.digest( hashWithSalt.getSalt().getBytes() );
		byte[] converted = Base64.getEncoder().encode( digest.digest() );
		String providedHash = new StringBuilder( new String( converted ) ).toString();
		return providedHash.equals( hashWithSalt.getHash() );
	}
	
	
	/***** PRIVATE *****/
	private static String generateSalt() throws Exception {
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[ 32 ];
		sr.nextBytes( salt );
		return salt.toString();
	}
	
	
}
