package com.venkat.myMongoDB;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.citi.MongoUtils.isCollectionExists;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

public class DeleteDocument {


	public static void main(String[] args) {

		String myDB = "test" ;
		String myCollection = "mytestcol" ;
		 
		
		isCollectionExists isCollectionFound = new  isCollectionExists();
		
		
		// Set logger level to sever
		Logger mongoLogger = Logger.getLogger("org.mongodb") ;
		mongoLogger.setLevel(Level.SEVERE) ;
		
		// set Mongo clinet
		MongoClient mongo = new MongoClient("sd-606f-3f11", 27017) ;
		
		MongoDatabase db = mongo.getDatabase(myDB) ;
		
		if (!isCollectionFound.findCollection(db, myCollection))
		{
			System.out.printf("Collection %s not found in db %s\n", myCollection, myDB);
			mongo.close() ;
			return ;
		}
		
	//	FindIterable<Document> docs = db.getCollection(myCollection).find(new Document("_id", 10)) ;
		
		long rows = db.getCollection(myCollection).count(new Document("_id", new Document("$gte", 80))) ;
		System.out.println("number of rows return is : " + rows);
		
		if (rows > 0) {
			DeleteResult deleteResult = db.getCollection(myCollection).deleteMany(new Document("_id", new Document("$gte", 80))) ;
			System.out.println("Number of documents deleted are " + deleteResult.getDeletedCount() ) ;
		}

		mongo.close() ;
	}

}
