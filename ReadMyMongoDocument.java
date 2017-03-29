package com.venkat.myMongoDB;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.citi.MongoUtils.isCollectionExists;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class ReadMyMongoDocument {

	public static void main(String[] args) {
		
		String myDB = "test" ;
		String myCollection = "mytestcol" ;
		//MongoCollection<Document> myDocs ;
		
		isCollectionExists result = new isCollectionExists() ;
		
		// set Mongo log level to server to supress any INFO log messages
		Logger mongoLogger = Logger.getLogger("org.mongodb") ;
		mongoLogger.setLevel(Level.SEVERE) ;
		
		
		// set Mongo client
		MongoClient mongo = new MongoClient ("sd-606f-3f11", 27017) ;
		
		// set mongo DB
		MongoDatabase db = mongo.getDatabase(myDB) ;
		
		// Check if the collection exists or not
		boolean isFound= result.findCollection(db, myCollection) ;
		
		if (!isFound) {
			System.out.println("Collection not found ");
			mongo.close() ;
			return ;
		}
		else
		{
			System.out.println("Collection found");
		}
		
		
		System.out.println("*** Find single Document ***");
		System.out.println("-----------------------------");
		// Find document with _id = 10
		FindIterable<Document> resultOneDoc = db.getCollection(myCollection).
				find(new Document("_id", 10)) ;
		
		resultOneDoc.forEach(new Block<Document>()  {
			@Override
			public void apply(Document myDoc) {
				String name = (String) myDoc.get("Name") ;
				System.out.println(name) ;
			}
			
		}) ;
		
		System.out.println("\n*** Find multiple Documents ***");
		System.out.println("----------------------------------");
		// find result set where _id > 90
		FindIterable<Document> resultsMany = db.getCollection(myCollection).
				find(new Document("_id", new Document("$gt", 90))
				    ) ;
		
		resultsMany.forEach(new Block<Document>()  {
			@Override
			public void apply(Document myDoc) {
				String name = (String) myDoc.get("Name") ;
				System.out.println(name) ;
			}
			
		}) ;
		
		System.out.println("\n*** Find Document(s) with AND condition ***");
		System.out.println("--------------------------------------------");
		// find result set where _id > 90
		FindIterable<Document> resultsManywithAnd = db.getCollection(myCollection).
				find(new Document("_id", new Document("$gt", 90))
				.append("Name", "String value 96")
				    ) ;
		
		resultsManywithAnd.forEach(new Block<Document>()  {
			@Override
			public void apply(Document myDoc) {
				String name = (String) myDoc.get("Name") ;
				System.out.println(name) ;
			}
			
		}) ;
		
		

		System.out.println("\n*** Find Document(s) with OR condition ***");
		System.out.println("--------------------------------------------");
		// find result set where _id > 90
		FindIterable<Document> resultsManywithOr = db.getCollection(myCollection).find(
				new Document("$or", 
						Arrays.asList(new Document("_id", 90), new Document("Name", "String value 10")
				       )
				 )) ;
		
		resultsManywithOr.forEach(new Block<Document>()  {
			@Override
			public void apply(Document myDoc) {
				String name = (String) myDoc.get("Name") ;
				System.out.println(name) ;
			}
			
		}) ;
		
		System.out.println("\n*** Find Document(s) with AND /  OR condition ***");
		System.out.println("--------------------------------------------");
		// find result set where _id > 90
		FindIterable<Document> resultsManywithANDOr = db.getCollection(myCollection).find(
				(new Document("$or", 
						Arrays.asList(new Document("_id", 94), new Document("Name", "String value 99")
				                     )
				             )
				).append("_id", new Document("$gt", 90))
				 ) ;
		
		resultsManywithANDOr.forEach(new Block<Document>()  {
			@Override
			public void apply(Document myDoc) {
				String name = (String) myDoc.get("Name") ;
				System.out.println(name) ;
			}
			
		}) ;
		
		mongo.close() ;
		
		
		
	}

}
