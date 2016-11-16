package pt.jmfgameiro.resources.restclient.resources;

import pt.jmfgameiro.generator.number.DoubleRandomizer;
import pt.jmfgameiro.generator.text.Text;

public final class ServiceConstants {
	
	/***** CONSTANTS *****/
	public static final int PORT = 9000;
	public static final String SERVICE = "/test";
	
	public static final String DELETE_PATH = "/delete";
	public static final String GET_PATH = "/get";
	public static final String POST_PATH = "/post";
	public static final String PUT_PATH = "/put";
	
	public static final String PARAMETER_KEY = "PARAM";
	public static final String PARAMETER_VALUE = "Hello World";
	
	public static final String CONTENT_TYPE = "application/json";
	
	public static final long ID_VALUE = 42L;
	public static final double MIN_RAND = 10.0;
	public static final double MAX_RAND = 10.0;
	
	public static final ServiceObject SERVICE_OBJECT = new ServiceObject( ID_VALUE, Text.name(), DoubleRandomizer.random( MIN_RAND, MAX_RAND ) );
	
	
}
