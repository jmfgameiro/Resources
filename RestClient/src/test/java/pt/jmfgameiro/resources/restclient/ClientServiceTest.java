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
	private static final int PORT = 9000;
	private static final String SERVICE = "/test";
	private static final Server SERVER = new Server( PORT );
	
	private static final String DELETE_PATH = "/delete";
	private static final String GET_PATH = "/get";
	private static final String POST_PATH = "/post";
	private static final String PUT_PATH = "/put";
	
	private static final ClientService CLIENT = new ClientService( "localhost", PORT , SERVICE );
	
	
	/***** BEFORE *****/
	@BeforeClass
	public static void before() throws Exception {
		ServletContextHandler handler = new ServletContextHandler( SERVER, SERVICE );
		handler.addServlet( ServiceDelete.class, DELETE_PATH );
		handler.addServlet( ServiceGet.class, GET_PATH );
		handler.addServlet( ServicePost.class, POST_PATH );
		handler.addServlet( ServicePut.class, PUT_PATH );
		SERVER.start();
	}
	
	
	/***** TESTS *****/
	// DELETE SERVICES
	@Test
	public void delete() throws Exception {
		Response response = CLIENT.delete( DELETE_PATH );
		assertEquals( Status.ACCEPTED, ( Status )response.getStatusInfo() );
		
		JsonElement entity = new Gson().fromJson( response.readEntity( String.class ), JsonElement.class );
		assertEquals( ServiceObject.toJson( ServiceConstants.SERVICE_OBJECT ), entity.getAsString() );
	}
	@Test
	public void deleteClass() throws Exception {
		ServiceObject response = CLIENT.delete( DELETE_PATH, ServiceObject.class );
		assertEquals( ServiceConstants.SERVICE_OBJECT, response );
	}
	@Test
	public void deleteQueryParameters() throws Exception {
		Response response = CLIENT.delete( DELETE_PATH, ServiceObject.toQuery( ServiceConstants.SERVICE_OBJECT ) );
		assertEquals( Status.ACCEPTED, ( Status )response.getStatusInfo() );
		
		JsonElement entity = new Gson().fromJson( response.readEntity( String.class ), JsonElement.class );
		assertEquals( ServiceObject.toJson( ServiceConstants.SERVICE_OBJECT ), entity.getAsString() );
	}
	@Test
	public void deleteQueryParametersClass() throws Exception {
		ServiceObject response = CLIENT.delete( DELETE_PATH, ServiceObject.class, ServiceObject.toQuery( ServiceConstants.SERVICE_OBJECT ) );
		assertEquals( ServiceConstants.SERVICE_OBJECT, response );
	}
	
	// GET SERVICES
	@Test
	public void get() throws Exception {
		Response response = CLIENT.get( GET_PATH );
		assertEquals( Status.OK, ( Status )response.getStatusInfo() );
		
		JsonElement entity = new Gson().fromJson( response.readEntity( String.class ), JsonElement.class );
		assertEquals( ServiceObject.toJson( ServiceConstants.SERVICE_OBJECT ), entity.getAsString() );
	}
	@Test
	public void getWithClass() throws Exception {
		ServiceObject response = CLIENT.get( GET_PATH, ServiceObject.class );
		assertEquals( ServiceConstants.SERVICE_OBJECT, response );
	}
	@Test
	public void getQueryParameters() throws Exception {
		Response response = CLIENT.get( GET_PATH, ServiceObject.toQuery( ServiceConstants.SERVICE_OBJECT ) );
		assertEquals( Status.OK, ( Status )response.getStatusInfo() );
		
		JsonElement entity = new Gson().fromJson( response.readEntity( String.class ), JsonElement.class );
		assertEquals( ServiceObject.toJson( ServiceConstants.SERVICE_OBJECT ), entity.getAsString() );
	}
	@Test
	public void getQueryParametersWithClass() throws Exception {
		ServiceObject response = CLIENT.get( GET_PATH, ServiceObject.class, ServiceObject.toQuery( ServiceConstants.SERVICE_OBJECT ) );
		assertEquals( ServiceConstants.SERVICE_OBJECT, response );
	}
	
	// POST SERVICES
	@Test
	public void post() throws Exception {
		Response response = CLIENT.post( POST_PATH, ServiceConstants.SERVICE_OBJECT );
		assertEquals( Status.ACCEPTED, ( Status )response.getStatusInfo() );
		
		JsonElement entity = new Gson().fromJson( response.readEntity( String.class ), JsonElement.class );
		assertEquals( ServiceObject.toJson( ServiceConstants.SERVICE_OBJECT ), entity.getAsString() );
	}
	@Test
	public void postWithClass() throws Exception {
		ServiceObject response = CLIENT.post( POST_PATH, ServiceConstants.SERVICE_OBJECT, ServiceObject.class );
		assertEquals( ServiceConstants.SERVICE_OBJECT, response );
	}
	
	// PUT SERVICES
	@Test
	public void put() throws Exception {
		Response response = CLIENT.put( PUT_PATH, ServiceConstants.SERVICE_OBJECT );
		assertEquals( Status.CREATED, ( Status )response.getStatusInfo() );
		
		JsonElement entity = new Gson().fromJson( response.readEntity( String.class ), JsonElement.class );
		assertEquals( ServiceObject.toJson( ServiceConstants.SERVICE_OBJECT ), entity.getAsString() );
	}
	@Test
	public void putWithClass() throws Exception {
		ServiceObject response = CLIENT.put( PUT_PATH, ServiceConstants.SERVICE_OBJECT, ServiceObject.class );
		assertEquals( ServiceConstants.SERVICE_OBJECT, response );
	}
	
	
	/***** AFTER *****/
	@AfterClass
	public static void after() throws Exception {
		SERVER.stop();
	}
	
	
}
