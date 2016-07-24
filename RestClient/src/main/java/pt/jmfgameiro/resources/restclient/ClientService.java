package pt.jmfgameiro.resources.restclient;

import java.net.URI;
import java.util.Map;

public class ClientService {
	
	/***** CONSTANTS *****/
	private final String scheme = "http";
	private final String host;
	private final int port;
	private final String service;
	
	
	/*****  *****/
	ClientService( String host, int port, String service ) {
		this.host = host;
		this.port = port;
		this.service = service + "/";
	}
	
	
	/***** PUBLIC *****/
	/**
	 * Call a DELETE service based on a path.
	 * @param path
	 * @return String
	 * @throws Exception
	 */
	public final String delete( String path ) throws Exception {
		return RestClientDeleteService.delete( new URI( scheme, null, host, port, service + path, null, null ) );
	}
	/**
	 * Call a DELETE service, for a specific object to return, based on a path.
	 * @param path
	 * @param clazz
	 * @return Object of Class clazz
	 * @throws Exception
	 */
	public final < T > T delete( String path, Class< T > clazz ) throws Exception {
		return RestClientDeleteService.delete( new URI( scheme, null, host, port, service + path, null, null ), clazz );
	}
	/**
	 * Call a DELETE service, with query parameters, based on a path.
	 * @param path
	 * @param queryParams
	 * @return String
	 * @throws Exception
	 */
	public final String delete( String path, Map< String, Object > queryParams ) throws Exception {
		return RestClientDeleteService.delete( new URI( scheme, null, host, port, service + path, null, null ), queryParams );
	}
	/**
	 * Call a DELETE service, with query parameters and for a specific object to return, based on a path.
	 * @param path
	 * @param clazz
	 * @param queryParams
	 * @return Object of Class clazz
	 * @throws Exception
	 */
	public final < T > T delete( String path, Class< T > clazz, Map< String, Object > queryParams ) throws Exception {
		return RestClientDeleteService.delete( new URI( scheme, null, host, port, service + path, null, null ), clazz, queryParams );
	}
	
	/**
	 * Call a GET service based on a path.
	 * @param path
	 * @return String
	 * @throws Exception
	 */
	public final String get( String path ) throws Exception {
		return RestClientGetService.get( new URI( scheme, null, host, port, service + path, null, null ) );
	}
	/**
	 * Call a GET service, for a specific object to return, based on a path.
	 * @param path
	 * @param clazz
	 * @return Object of Class clazz
	 * @throws Exception
	 */
	public final < T > T get( String path, Class< T > clazz ) throws Exception {
		return RestClientGetService.get( new URI( scheme, null, host, port, service + path, null, null ), clazz );
	}
	/**
	 * Call a GET service, with query parameters, based on a path.
	 * @param path
	 * @param queryParams
	 * @return String
	 * @throws Exception
	 */
	public final String get( String path, Map< String, Object > queryParams ) throws Exception {
		return RestClientGetService.get( new URI( scheme, null, host, port, service + path, null, null ), queryParams );
	}
	/**
	 * Call a GET service, with query parameters and for a specific object to return, based on a path.
	 * @param path
	 * @param clazz
	 * @param queryParams
	 * @return Object of Class clazz
	 * @throws Exception
	 */
	public final < T > T get( String path, Class< T > clazz, Map< String, Object > queryParams ) throws Exception {
		return RestClientGetService.get( new URI( scheme, null, host, port, service + path, null, null ), clazz, queryParams );
	}
	
	/**
	 * Call a POST service based on a path.
	 * @param path
	 * @param entity
	 * @return String
	 * @throws Exception
	 */
	public final String post( String path, Object entity ) throws Exception {
		return RestClientPostService.post( new URI( scheme, null, host, port, service + path, null, null ), entity );
	}
	/**
	 * Call a POST service, for a specific object to return, based on a path.
	 * @param path
	 * @param entity
	 * @param clazz
	 * @return Object of Class clazz
	 * @throws Exception
	 */
	public final < T > T post( String path, Object entity, Class< T > clazz ) throws Exception {
		return RestClientPostService.post( new URI( scheme, null, host, port, service + path, null, null ), entity, clazz );
	}
	
	/**
	 * Call a PUT service based on a path.
	 * @param path
	 * @param entity
	 * @return String
	 * @throws Exception
	 */
	public final String put( String path, Object entity ) throws Exception {
		return RestClientPutService.put( new URI( scheme, null, host, port, service + path, null, null ), entity );
	}
	/**
	 * Call a PUT service, for a specific object to return, based on a path.
	 * @param path
	 * @param entity
	 * @param clazz
	 * @return Object of Class clazz
	 * @throws Exception
	 */
	public final < T > T put( String path, Object entity, Class< T > clazz ) throws Exception {
		return RestClientPutService.put( new URI( scheme, null, host, port, service + path, null, null ), entity, clazz );
	}
	
	
}
