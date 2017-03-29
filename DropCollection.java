package com.venkat.myMongoDB;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import com.citi.MongoUtils.*;

public class DropCollection {

	public static void main(String[] args) {
		
		String myDB = "test" ;
		String myCollection = "mytestcol" ;

		isCollectionExists isCollectionFound = new isCollectionExists() ;
		
		// Set log level to severe to suppress INFO messages
		Logger mongoLogger = Logger.getLogger("org.mongodb") ;
		mongoLogger.setLevel(Level.SEVERE) ;
		
		// set Mongo Clinet
		MongoClient mongo = new MongoClient("sd-606f-3f11", 27017) ;
		
		MongoDatabase mongoDB = mongo.getDatabase(myDB) ;
		
		 if (!isCollectionFound.findCollection(mongoDB, myCollection)) {
			 System.out.println("collection not found");
			 mongo.close() ;
			 return ;
		 }
		
		 mongoDB.getCollection(myCollection).drop() ;
		 
		 if (!isCollectionFound.findCollection(mongoDB, myCollection)) {
			 System.out.println("collection successfully dropped");
		 }
		 else
			 System.out.println("error in drop collection ....");
			 

		 
		 mongo.close() ;
	}

}
