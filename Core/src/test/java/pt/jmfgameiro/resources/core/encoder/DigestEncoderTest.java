package pt.jmfgameiro.resources.core.encoder;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

import pt.jmfgameiro.resources.core.encoder.resource.DigestEncoderHashWithSalt;

public final class DigestEncoderTest {
	
	/*****  *****/
	private static final String PARAM = "password";
	
	
	/*****  *****/
	@Test
	public void md2() {
		testHash( DigestEncoderAlgorithm.MD2 );
	}
	@Test
	public void md2WithSalt() {
		testHashWithSalt( DigestEncoderAlgorithm.MD2 );
	}
	
	@Test
	public void md5() {
		testHash( DigestEncoderAlgorithm.MD5 );
	}
	@Test
	public void md5WithSalt() {
		testHashWithSalt( DigestEncoderAlgorithm.MD5 );
	}
	
	@Test
	public void sha() {
		testHash( DigestEncoderAlgorithm.SHA );
	}
	@Test
	public void shaWithSalt() {
		testHashWithSalt( DigestEncoderAlgorithm.SHA );
	}
	
	@Test
	public void sha256() {
		testHash( DigestEncoderAlgorithm.SHA256 );
	}
	@Test
	public void sha256WithSalt() {
		testHashWithSalt( DigestEncoderAlgorithm.SHA256 );
	}
	
	@Test
	public void sha384() {
		testHash( DigestEncoderAlgorithm.SHA384 );
	}
	@Test
	public void sha384WithSalt() {
		testHashWithSalt( DigestEncoderAlgorithm.SHA384 );
	}
	
	@Test
	public void sha512() {
		testHash( DigestEncoderAlgorithm.SHA512 );
	}
	@Test
	public void sha512WithSalt() {
		testHashWithSalt( DigestEncoderAlgorithm.SHA512 );
	}
	
	
	/***** PRIVATE *****/
	private void testHash( DigestEncoderAlgorithm algorithm ) {
		try {
			String hash = DigestEncoder.hash( algorithm, StandardCharsets.UTF_8, PARAM );
			assertNotNull( hash );
			assertEquals( true, DigestEncoder.validHash( algorithm, StandardCharsets.UTF_8, PARAM, hash ) );
			System.out.println( "PARAM: " + PARAM + " -> " + hash );
		}
		catch( Exception e ) {
			fail( "[" + algorithm.getName() + "] EXCEPTION: " + e.getMessage() );
		}
	}
	private void testHashWithSalt( DigestEncoderAlgorithm algorithm ) {
		try {
			DigestEncoderHashWithSalt hashWithSalt = DigestEncoder.hashWithSalt( algorithm, StandardCharsets.UTF_8, PARAM );
			assertNotNull( hashWithSalt );
			assertEquals( true, DigestEncoder.validHash( algorithm, StandardCharsets.UTF_8, PARAM, hashWithSalt ) );
			System.out.println( "PARAM: " + PARAM + " -> " + hashWithSalt.getHash() + " | " + hashWithSalt.getSalt() );
		}
		catch( Exception e ) {
			fail( "[" + algorithm.getName() + "] EXCEPTION: " + e.getMessage() );
		}
	}
	
	
}
