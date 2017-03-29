package com.venkat.myMongoDB;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

public class updateDocument {


	public static void main(String[] args) {
	
		// define variables and required objects
		String myDB = "test" ;
		String myCollection = "mytestcol" ;
		UpdateResult myUpdateResult ;
		
		
		// set Logger to severe to suppress Mongo INFO message
		Logger mongoLogger = Logger.getLogger("org.mongodb") ;
		mongoLogger.setLevel(Level.SEVERE) ;
		
		// Set MongoDB Client
		MongoClient mongo = new MongoClient("sd-606f-3f11", 27017) ;
		
		// check if the collection exists or not, before udate document
		MongoDatabase mongoDB = mongo.getDatabase(myDB) ;
		
		
		// update document based on the condition, return status
		myUpdateResult = mongoDB.getCollection(myCollection).updateOne(new Document("_id",  1),
				new Document("$set", new Document("Name", "Set Name 01"))) ;
		
		System.out.println("Number of rows updated " + myUpdateResult.getMatchedCount() ) ;
		
		// close MongoDB client connection
		mongo.close() ;
	}

}
