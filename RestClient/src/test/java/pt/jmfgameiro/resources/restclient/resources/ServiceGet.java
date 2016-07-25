package pt.jmfgameiro.resources.restclient.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class ServiceGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader( req.getInputStream() ) );
		
		String json = "";
		if( br != null )
			json = br.readLine();
		
		if( json == null || json.trim().equals( "" ) )
			json = ServiceObject.toJson( ServiceConstants.SERVICE_OBJECT );
		
		resp.setStatus( HttpStatus.OK_200 );
		resp.setContentType( ServiceConstants.CONTENT_TYPE );
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue( resp.getOutputStream(), json );
	}
	

}
