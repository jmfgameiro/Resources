package pt.jmfgameiro.resources.restclient;

import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

/**
 * @author Jo�o Gameiro
 *
 */
final class RestClientDeleteService {
	
	/***** STATIC *****/
	/**
	 * Call a DELETE service based on a URI.
	 * @param uri
	 * @return Response
	 * @throws Exception
	 */
	static final Response delete( URI uri ) throws Exception {
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
			return target.request().delete();
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
	 * Call a DELETE service, for a specific object to return, based on a URI.
	 * @param uri
	 * @param clazz
	 * @return Object
	 * @throws Exception
	 */
	static final < T > T delete( URI uri, Class< T > clazz ) throws Exception {
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
			response = target.request().delete();
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
	 * Call a DELETE service, with query parameters, based on a URI.
	 * @param uri
	 * @param queryParams
	 * @return Response
	 * @throws Exception
	 */
	static final Response delete( URI uri, Map< String, Object > queryParams ) throws Exception {
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
			return target.request().delete();
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
	 * Call a DELETE service, with query parameters and for a specific object to return, based on a URI.
	 * @param uri
	 * @param clazz
	 * @param queryParams
	 * @return Object
	 * @throws Exception
	 */
	static final < T > T delete( URI uri, Class< T > clazz, Map< String, Object > queryParams ) throws Exception {
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
			response = target.request().delete();
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
