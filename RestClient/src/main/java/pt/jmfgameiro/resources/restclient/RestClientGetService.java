package pt.jmfgameiro.resources.restclient;

import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

/**
 * @author João Gameiro
 *
 */
final class RestClientGetService {
	
	/***** STATIC *****/
	/**
	 * Call a GET service based on a URI.
	 * @param uri
	 * @return String
	 * @throws Exception
	 */
	static final String get( URI uri ) throws Exception {
		// Initialize the client object at null
		Client client = null;
		// Initialize the response object at null
		Response response = null;
		// Try to get the response from the service
		try {
			// Initialize the client
			client = new ResteasyClientBuilder().build();
			// Initialize the target
			WebTarget target = client.target( uri );
			// Make the GET request to the service and obtain the response
			response = target.request().get();
			// Return the obtained response
			return response.readEntity( String.class );
		}
		finally {
			// Close response
			if( response != null )
				response.close();
			// Close client
			if( client != null )
				client.close();
		}
	}
	/**
	 * Call a GET service, for a specific object to return, based on a URI.
	 * @param uri
	 * @param clazz
	 * @return Object
	 * @throws Exception
	 */
	static final < T > T get( URI uri, Class< T > clazz ) throws Exception {
		// Initialize the client object at null
		Client client = null;
		// Initialize the response object at null
		Response response = null;
		// Try to get the response from the service
		try {
			// Initialize the client
			client = new ResteasyClientBuilder().build();
			// Initialize the target
			WebTarget target = client.target( uri );
			// Make the GET request to the service and obtain the response
			response = target.request().get();
			// Return the obtained response
			return response.readEntity( clazz );
		}
		finally {
			// Close response
			if( response != null )
				response.close();
			// Close client
			if( client != null )
				client.close();
		}
	}
	/**
	 * Call a GET service, with query parameters, based on a URI.
	 * @param uri
	 * @param queryParams
	 * @return String
	 * @throws Exception
	 */
	static final String get( URI uri, Map< String, Object > queryParams ) throws Exception {
		// Initialize the client object at null
		Client client = null;
		// Initialize the response object at null
		Response response = null;
		// Try to get the response from the service
		try {
			// Initialize the client
			client = new ResteasyClientBuilder().build();
			// Initialize the target
			WebTarget target = client.target( uri );
			// Insert all query parameters into the target
			for( Entry< String, Object > entry : queryParams.entrySet() )
				target.queryParam( entry.getKey(), entry.getValue() );
			// Make the GET request to the service and obtain the response
			response = target.request().get();
			// Return the obtained response
			return response.readEntity( String.class );
		}
		finally {
			// Close response
			if( response != null )
				response.close();
			// Close client
			if( client != null )
				client.close();
		}
	}
	/**
	 * Call a GET service, with query parameters and for a specific object to return, based on a URI.
	 * @param uri
	 * @param clazz
	 * @param queryParams
	 * @return Object
	 * @throws Exception
	 */
	static final < T > T get( URI uri, Class< T > clazz, Map< String, Object > queryParams ) throws Exception {
		// Initialize the client object at null
		Client client = null;
		// Initialize the response object at null
		Response response = null;
		// Try to get the response from the service
		try {
			// Initialize the client
			client = new ResteasyClientBuilder().build();
			// Initialize the target
			WebTarget target = client.target( uri );
			// Insert all query parameters into the target
			for( Entry< String, Object > entry : queryParams.entrySet() )
				target.queryParam( entry.getKey(), entry.getValue() );
			// Make the GET request to the service and obtain the response
			response = target.request().get();
			// Return the obtained response
			return response.readEntity( clazz );
		}
		finally {
			// Close response
			if( response != null )
				response.close();
			// Close client
			if( client != null )
				client.close();
		}
	}
	
	
}
