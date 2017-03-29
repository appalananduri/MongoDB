Package com.venkat.mymongoDB ;

import java.util.logging.* ;
import com.mongodb.MongdoClient ;
import com.mongodb.client.MongoIterable ;

public class getMongoDBList {

	public static void main(String[] args) {

	// below two lines are used to disable MongoDB Logging
	Logger mongoLogger = Logger.getLogger("org.mongodb") ;
	mongoLogger.setLevel(Level.SEVERE) ;

	

	// Connect to Mongo Client
	MongoClient mongo = new MongoClient("serverName", 27017) ;

	// Get List of DBNames from mongo Client
	MongoIterable<String> dbList = mongo.listDatabaseNames() ;

	System.out.println("List of database Names") ;

	// print DB list 
	for (String db: dbList){
		System.out.println(db) ;
	}
	
	//close clinet connection
	mongo.close() ;
	} // end of main


} // end of getMongoDBList