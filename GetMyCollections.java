package com.venkat.myMongoDB;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class GetMyCollections {
	public static void main(String[] args) {

		// disable default log messages for mongo
		Logger mongoLogger = Logger.getLogger("org.mongodb") ;
		mongoLogger.setLevel(Level.SEVERE) ;
		
		// set Mongo client
		MongoClient mongo = new MongoClient("sd-606f-3f11", 27017) ;
		
		//set database 
		MongoDatabase db = mongo.getDatabase("test");
		
		// get collection names for given database 
		MongoIterable<String> myCollections = db.listCollectionNames() ;
		System.out.println("Collections");
		System.out.println("===========");
		for (String collection : myCollections) {
			System.out.println(collection);
		}
				
		mongo.close() ;
	} // end of main
}
