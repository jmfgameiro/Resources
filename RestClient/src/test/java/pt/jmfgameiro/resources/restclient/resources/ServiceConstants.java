package pt.jmfgameiro.resources.restclient.resources;

import pt.jmfgameiro.resources.core.random.RandomNumber;
import pt.jmfgameiro.resources.core.random.RandomString;

public final class ServiceConstants {
	
	
	/*****  *****/
	public static final String PARAMETER_KEY = "PARAM";
	public static final String PARAMETER_VALUE = "Hello World";
	
	public static final String CONTENT_TYPE = "application/json";
	
	public static final ServiceObject SERVICE_OBJECT = new ServiceObject( 42L, RandomString.randomName(), RandomNumber.randomDouble( 10.0, 50.0 ) );
	
	
}
