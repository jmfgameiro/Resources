package pt.jmfgameiro.resources.restclient;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import pt.jmfgameiro.resources.restclient.resources.ServiceDelete;
import pt.jmfgameiro.resources.restclient.resources.ServiceConstants;
import pt.jmfgameiro.resources.restclient.resources.ServiceGet;
import pt.jmfgameiro.resources.restclient.resources.ServiceObject;
import pt.jmfgameiro.resources.restclient.resources.ServicePost;
import pt.jmfgameiro.resources.restclient.resources.ServicePut;

public class ClientServiceTest {
	
	/***** CONSTANTS *****/
	private static final Server SERVER = new Server( ServiceConstants.PORT );
	private static final ClientService CLIENT = new ClientService( "localhost", ServiceConstants.PORT , ServiceConstants.SERVICE );
	
	
	/***** BEFORE *****/
	@BeforeClass
	public static void before() throws Exception {
		ServletContextHandler handler = new ServletContextHandler( SERVER, ServiceConstants.SERVICE );
		handler.addServlet( ServiceDelete.class, ServiceConstants.DELETE_PATH );
		handler.addServlet( ServiceGet.class, ServiceConstants.GET_PATH );
		handler.addServlet( ServicePost.class, ServiceConstants.POST_PATH );
		handler.addServlet( ServicePut.class, ServiceConstants.PUT_PATH );
		SERVER.start();
	}
	
	
	/***** TESTS *****/
	// DELETE SERVICES
	@Test
	public void delete() throws Exception {
		Response response = CLIENT.delete( ServiceConstants.DELETE_PATH );
		assertEquals( Status.ACCEPTED, ( Status )response.getStatusInfo() );
		
		JsonElement entity = new Gson().fromJson( response.readEntity( String.class ), JsonElement.class );
		assertEquals( ServiceObject.toJson( ServiceConstants.SERVICE_OBJECT ), entity.getAsString() );
	}
	@Test
	public void deleteClass() throws Exception {
		ServiceObject response = CLIENT.delete( ServiceConstants.DELETE_PATH, ServiceObject.class );
		assertEquals( ServiceConstants.SERVICE_OBJECT, response );
	}
	@Test
	public void deleteQueryParameters() throws Exception {
		Response response = CLIENT.delete( ServiceConstants.DELETE_PATH, ServiceObject.toQuery( ServiceConstants.SERVICE_OBJECT ) );
		assertEquals( Status.ACCEPTED, ( Status )response.getStatusInfo() );
		
		JsonElement entity = new Gson().fromJson( response.readEntity( String.class ), JsonElement.class );
		assertEquals( ServiceObject.toJson( ServiceConstants.SERVICE_OBJECT ), entity.getAsString() );
	}
	@Test
	public void deleteQueryParametersClass() throws Exception {
		ServiceObject response = CLIENT.delete( ServiceConstants.DELETE_PATH, ServiceObject.class, ServiceObject.toQuery( ServiceConstants.SERVICE_OBJECT ) );
		assertEquals( ServiceConstants.SERVICE_OBJECT, response );
	}
	
	// GET SERVICES
	@Test
	public void get() throws Exception {
		Response response = CLIENT.get( ServiceConstants.GET_PATH );
		assertEquals( Status.OK, ( Status )response.getStatusInfo() );
		
		JsonElement entity = new Gson().fromJson( response.readEntity( String.class ), JsonElement.class );
		assertEquals( ServiceObject.toJson( ServiceConstants.SERVICE_OBJECT ), entity.getAsString() );
	}
	@Test
	public void getWithClass() throws Exception {
		ServiceObject response = CLIENT.get( ServiceConstants.GET_PATH, ServiceObject.class );
		assertEquals( ServiceConstants.SERVICE_OBJECT, response );
	}
	@Test
	public void getQueryParameters() throws Exception {
		Response response = CLIENT.get( ServiceConstants.GET_PATH, ServiceObject.toQuery( ServiceConstants.SERVICE_OBJECT ) );
		assertEquals( Status.OK, ( Status )response.getStatusInfo() );
		
		JsonElement entity = new Gson().fromJson( response.readEntity( String.class ), JsonElement.class );
		assertEquals( ServiceObject.toJson( ServiceConstants.SERVICE_OBJECT ), entity.getAsString() );
	}
	@Test
	public void getQueryParametersWithClass() throws Exception {
		ServiceObject response = CLIENT.get( ServiceConstants.GET_PATH, ServiceObject.class, ServiceObject.toQuery( ServiceConstants.SERVICE_OBJECT ) );
		assertEquals( ServiceConstants.SERVICE_OBJECT, response );
	}
	
	// POST SERVICES
	@Test
	public void post() throws Exception {
		Response response = CLIENT.post( ServiceConstants.POST_PATH, ServiceConstants.SERVICE_OBJECT );
		assertEquals( Status.ACCEPTED, ( Status )response.getStatusInfo() );
		
		JsonElement entity = new Gson().fromJson( response.readEntity( String.class ), JsonElement.class );
		assertEquals( ServiceObject.toJson( ServiceConstants.SERVICE_OBJECT ), entity.getAsString() );
	}
	@Test
	public void postWithClass() throws Exception {
		ServiceObject response = CLIENT.post( ServiceConstants.POST_PATH, ServiceConstants.SERVICE_OBJECT, ServiceObject.class );
		assertEquals( ServiceConstants.SERVICE_OBJECT, response );
	}
	
	// PUT SERVICES
	@Test
	public void put() throws Exception {
		Response response = CLIENT.put( ServiceConstants.PUT_PATH, ServiceConstants.SERVICE_OBJECT );
		assertEquals( Status.CREATED, ( Status )response.getStatusInfo() );
		
		JsonElement entity = new Gson().fromJson( response.readEntity( String.class ), JsonElement.class );
		assertEquals( ServiceObject.toJson( ServiceConstants.SERVICE_OBJECT ), entity.getAsString() );
	}
	@Test
	public void putWithClass() throws Exception {
		ServiceObject response = CLIENT.put( ServiceConstants.PUT_PATH, ServiceConstants.SERVICE_OBJECT, ServiceObject.class );
		assertEquals( ServiceConstants.SERVICE_OBJECT, response );
	}
	
	
	/***** AFTER *****/
	@AfterClass
	public static void after() throws Exception {
		SERVER.stop();
	}
	
	
}
