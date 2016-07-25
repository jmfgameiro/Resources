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

import pt.jmfgameiro.resources.restclient.resources.ServiceGeneric;
import pt.jmfgameiro.resources.restclient.resources.ServiceObject;
import pt.jmfgameiro.resources.restclient.resources.ServicePost;

public class ClientPostServiceTest {
	
	/***** CONSTANTS *****/
	private static final int PORT = 9000;
	private static final String SERVICE = "/test";
	private static final Server SERVER = new Server( PORT );
	private static final String POST_PATH = "/post";
	private static final ClientService CLIENT = new ClientService( "localhost", PORT , SERVICE );
	
	
	/***** BEFORE *****/
	@BeforeClass
	public static void before() throws Exception {
		ServletContextHandler handler = new ServletContextHandler( SERVER, SERVICE );
		handler.addServlet( ServicePost.class, POST_PATH );
		SERVER.start();
	}
	
	
	/***** TESTS *****/
	@Test
	public void post() throws Exception {
		Response response = CLIENT.post( POST_PATH, ServiceGeneric.SERVICE_OBJECT );
		assertEquals( Status.ACCEPTED, ( Status )response.getStatusInfo() );
		
		JsonElement entity = new Gson().fromJson( response.readEntity( String.class ), JsonElement.class );
		assertEquals( ServiceObject.toJson( ServiceGeneric.SERVICE_OBJECT ), entity.getAsString() );
	}
	@Test
	public void postWithClass() throws Exception {
		ServiceObject response = CLIENT.post( POST_PATH, ServiceGeneric.SERVICE_OBJECT, ServiceObject.class );
		assertEquals( ServiceGeneric.SERVICE_OBJECT, response );
	}
	
	
	/***** AFTER *****/
	@AfterClass
	public static void after() throws Exception {
		SERVER.stop();
	}
	
	
}
