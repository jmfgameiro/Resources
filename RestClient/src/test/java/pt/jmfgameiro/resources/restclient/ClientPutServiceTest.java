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

import pt.jmfgameiro.resources.restclient.resources.ServiceConstants;
import pt.jmfgameiro.resources.restclient.resources.ServiceObject;
import pt.jmfgameiro.resources.restclient.resources.ServicePut;

public class ClientPutServiceTest {
	
	/***** CONSTANTS *****/
	private static final Server SERVER = new Server( ServiceConstants.PORT );
	private static final ClientService CLIENT = new ClientService( "localhost", ServiceConstants.PORT , ServiceConstants.SERVICE );
	
	
	/***** BEFORE *****/
	@BeforeClass
	public static void before() throws Exception {
		ServletContextHandler handler = new ServletContextHandler( SERVER, ServiceConstants.SERVICE );
		handler.addServlet( ServicePut.class, ServiceConstants.PUT_PATH );
		SERVER.start();
	}
	
	
	/***** TESTS *****/
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
