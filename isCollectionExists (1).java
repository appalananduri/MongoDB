package com.venkat.MongoUtils;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class isCollectionExists {
    

	public boolean findCollection( MongoDatabase dbName, String myCollection) {
		MongoIterable<String> collectionList = dbName.listCollectionNames() ;
		
		for (String collectionName : collectionList) {
			if (collectionName.toLowerCase().equals(myCollection.toLowerCase()) )
					return true ;
			
		}
		return false ;
	}
	
	
}
