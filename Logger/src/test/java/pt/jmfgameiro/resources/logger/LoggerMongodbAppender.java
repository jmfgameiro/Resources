package pt.jmfgameiro.resources.logger;

import java.time.LocalDateTime;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

final class LoggerMongodbAppender< E > extends AppenderBase< E > {
	
	/***** CONSTANTS *****/
	private static final String HOST = "localhost";
	private static final Integer PORT = 27017;
	private static final String DATABASE = "test";
	
	
	/***** OVERRIDE *****/
	@Override
	protected void append( E eventObject ) {
		MongoClient client = null;
		try {
			// Connect to MongoDB Instance
			client = new MongoClient( HOST, PORT );
			
			// Get Database
			MongoDatabase database = client.getDatabase( DATABASE );
			
			// Create New Document
			ILoggingEvent loggingEvent = ( ILoggingEvent )eventObject;
			Document document = new Document( "_id", loggingEvent.getThreadName() + "_" + loggingEvent.getTimeStamp() )
					.append( "localdatetime", LocalDateTime.now().toString() )
					.append( "level", loggingEvent.getLevel().toString() )
					.append( "message", loggingEvent.getFormattedMessage() );
			
			// Insert New Document
			MongoCollection< Document > collection = database.getCollection( loggingEvent.getLoggerName() );
			collection.insertOne( document );
		}
		finally {
			// Close MongoDB Connection
			if( client != null )
				client.close();
		}
	}
	

}
