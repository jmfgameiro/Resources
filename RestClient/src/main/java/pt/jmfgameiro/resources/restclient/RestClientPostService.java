package pt.jmfgameiro.resources.restclient;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

/**
 * @author João Gameiro
 *
 */
final class RestClientPostService {
	
	/***** STATIC *****/
	/**
	 * Call a POST service based on a URI.
	 * @param uri
	 * @param entity
	 * @return Response
	 * @throws Exception
	 */
	static final Response post( URI uri, Object entity ) throws Exception {
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
			return target.request().post( Entity.entity( entity, MediaType.APPLICATION_JSON ) );
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
	 * Call a POST service, for a specific object to return, based on a URI.
	 * @param uri
	 * @param entity
	 * @param clazz
	 * @return Object of Class clazz
	 * @throws Exception
	 */
	static final < T > T post( URI uri, Object entity, Class< T > clazz ) throws Exception {
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
			response = target.request().post( Entity.entity( entity, MediaType.APPLICATION_JSON ) );
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
