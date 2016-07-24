package pt.jmfgameiro.resources.core.random;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.util.Random;

import javax.imageio.ImageIO;

public final class RandomImage {
	
	/***** PUBLIC *****/
	public static final byte[] generateImageRed() {
		int grid = 4;
		int multiplier = 10;
		int width = 200;
		int height = 200;
		
		BufferedImage img = new BufferedImage( width, height, BufferedImage.TYPE_INT_ARGB );
		
		boolean[] imagensFields = positions( grid * grid );
		int row = 0;
		for( int y = 0; y < height; y++ ) {
			if( y % ( grid * multiplier ) == 0 )
				row++;
			//
			int column = 0;
			for( int x = 0; x < width; x++ ) {
				if( x % ( grid * multiplier ) == 0 )
					column++;
				//
				int[] color;
				if( imagensFields[ row + column ] )
					color = red();
				else
					color = white();
				
				int p = ( color[ 0 ] << 24 ) | ( color[ 1 ] << 16 ) | ( color[ 2 ] << 8 ) | color[ 3 ];
				img.setRGB( x, y, p );
			}
		}
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( img, "jpg", baos );
			baos.flush();
			byte[] newImage = baos.toByteArray();
			baos.close();
			return newImage;
		} catch( Exception e ) {
			return null;
		}
	}
	
	
	/***** PRIVATE *****/
	private static final int[] white() {
		int[] color = new int[ 4 ];
		color[ 0 ] = 0;		//alpha
		color[ 0 ] = 256;	//red
		color[ 0 ] = 256;	//green
		color[ 0 ] = 256;	//blue
		return color;
	}
	private static final int[] red() {
		int[] color = new int[ 4 ];
		color[ 0 ] = 0;		//alpha
		color[ 0 ] = 256;	//red
		color[ 0 ] = 0;		//green
		color[ 0 ] = 0;		//blue
		return color;
	}
	private static final boolean[] positions( int size ) {
		Random rnd = new SecureRandom();
		boolean[] positions = new boolean[ size ];
		for( int i = 0; i < size; i++ )
			positions[ i ] = rnd.nextBoolean();
		return positions;
	}
	
	
}
