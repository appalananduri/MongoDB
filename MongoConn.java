package com.venkat.myMongoDB;

import java.net.UnknownHostException;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;


public class MongoConn {

	public static void main(String[] args) throws UnknownHostException {
		
		
		MongoCollection<Document> collection = null ;
		String myCollection = "testcol" ;
		MongoClient mongo = new MongoClient( "sd-606f-3f11.nam.nsroot.net" , 27017 );

		
		MongoIterable<String> dbs = mongo.listDatabaseNames();
		System.out.println(" *** List of databases *** ");
		for(String dbName : dbs){
			System.out.println(dbName);
		}

		MongoDatabase db = mongo.getDatabase("test") ;
		System.out.println("Data base is " + db.getName()); 

		
        if (CheckCollectionExists(myCollection, db))
        {
        	System.out.println("Collection " + myCollection + " Exists ...");
        	collection = db.getCollection(myCollection);
        }
        else 
        	System.out.println("Collection " + myCollection + " does not exists ...");
        
/*      System.out.println("Inserting using BasicDBObjects...");
        final Document basic1 = new Document();
        basic1.put("_id", "2");
        basic1.put("type", "basic");
        basic1.put("first-name", "Appala");
        basic1.put("last-name", "Nanduri");    
        collection.insertOne(basic1);
        System.out.println("Employee 1: " + basic1.toJson());*/
        
/*       System.out.println("delete using BasicDBObjects...");
        final Document basic1 = new Document();
        basic1.put("_id", "2");
        collection.deleteOne(basic1) ;
*/
        
//		  delete all documents in a given collection
//        collection.deleteMany(new Document() );
        
        
        
/*        String json = "{ '_id': 2, 'name' : 'Shyam' , " +
                "'website' : 'howtodoinjava.com' , " +
                "'address' : { 'addressLine1' : 'Some address' , " +
                              "'addressLine2' : 'Karol Bagh' , " +
                              "'addressLine3' : 'New Delhi, India'}" +
                            "}";
        
        Document myDoc = Document.parse(json) ;
        collection.insertOne(myDoc) ;*/
        
/*        System.out.println("update using BasicDBObjects...");
        collection.updateOne(new Document("_id", 1), 
        	new Document("$set", new Document("name", "Lokesh N")));
        
        		
        
        System.out.println("----[Retrieve All Documents in Collection]----");
        for (Document doc: collection.find() ) {
          System.out.println(doc.toJson());
        }
        
       FindIterable<Document> iterable = db.getCollection(myCollection).find(
		new Document("grades.score", new Document("$gt", 30)));
 */ 
        
        FindIterable<Document> iterable = db.getCollection(myCollection).
        		find(new Document()) ;
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                String Name = (String) document.get("name") ;
                System.out.println("Name is " + Name);
            }
        });
        

		mongo.close() ;
	}

  static boolean CheckCollectionExists(String CollectionName, MongoDatabase MD) {
	  
	  MongoIterable<String> myCollecitons = MD.listCollectionNames()  ;
		for(String c : myCollecitons){
			if (c.equals(CollectionName))
				return true ;
		  }
		return false ;
  }
	
  
  
}
