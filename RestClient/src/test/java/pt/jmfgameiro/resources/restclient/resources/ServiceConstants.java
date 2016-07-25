package pt.jmfgameiro.resources.restclient.resources;

import pt.jmfgameiro.resources.core.random.RandomNumber;
import pt.jmfgameiro.resources.core.random.RandomString;

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
	
	public static final ServiceObject SERVICE_OBJECT = new ServiceObject( 42L, RandomString.randomName(), RandomNumber.randomDouble( 10.0, 50.0 ) );
	
	
}
