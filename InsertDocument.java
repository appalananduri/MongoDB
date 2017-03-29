package com.venkat.myMongoDB;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class InsertDocument {

	public static void main(String[] args) {
		String myColl = "mytestcol" ;
		String myDB = "test" ;
		MongoCollection<Document> collection = null;
		
		// set default mongo log to sever from info
		Logger mongoLogger = Logger.getLogger("org.mongodb") ;
		mongoLogger.setLevel(Level.SEVERE) ;
		
		// connect to mongo client
		MongoClient mongo = new MongoClient("sd-606f-3f11", 27017) ;

		
		// set database 
		MongoDatabase db = mongo.getDatabase(myDB) ;

	
        if (!isCollectionExists(db, myColl)) {
        	System.out.printf("Colleciton %s exists in %s db\n" , myColl, myDB);
        	collection = db.getCollection(myColl);
        
        	for (int i=1; i<100 ; i++)
        	{
        		Document myDoc = new Document() ;
        		myDoc.put("_id", i) ;
        		myDoc.put("Name", "String value " + i) ;
        		collection.insertOne(myDoc) ;
        	}
        }
        else
        {
        	collection = db.getCollection(myColl);
        	System.out.printf("Colleciton %s does already exists in %s db\n" , myColl, myDB);
        }
        
		
        // print number of document with in the collection
        System.out.printf("Number of documents with in the collection %s is %d", myColl, collection.count() );
		// close client connection
		mongo.close() ;
	} // end of main

	
	static boolean isCollectionExists(MongoDatabase dbName, String myCollection) {
		MongoIterable<String> collectionList = dbName.listCollectionNames() ;
		
		for (String collectionName : collectionList) {
			if (collectionName.toLowerCase().equals(myCollection.toLowerCase()) )
					return true ;
			
		}
		return false ;
	}
	
}
