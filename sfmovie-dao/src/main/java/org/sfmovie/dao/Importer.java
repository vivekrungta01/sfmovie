package org.sfmovie.dao;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;

public class Importer 
{
    public static void main( String[] args )
    {
    	JSONParser parser = new JSONParser();
    	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://vivek:vivek@localhost/sfmovie") );
    	try {	
            Object obj = parser.parse(new FileReader(
                    "src/main/resources/sfmovie-data.json"));
            JSONArray jsonArray = (JSONArray) obj;
            List<Document> docList = new ArrayList<Document>();
            int i=0;
            MongoCollection<Document> mongoColl=mongoClient.getDatabase("sfmovie").getCollection("movie_location");
            for(Object object:jsonArray.toArray()) {
            	JSONObject jsonObject = (JSONObject) object;
            	Document doc = Document.parse(jsonObject.toJSONString());
            	GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyAhinD9vSOZq5Bd7iRSloBUqASc1wcYMks");
            	GeocodingResult[] results =  GeocodingApi.geocode(context,
            		    (String)doc.get("locations")+" San Francisco, CA").await();
            	if(results!=null && results.length>0 && results[0].geometry!=null) {
            		doc.append("lat",results[0].geometry.location.lat);
            		doc.append("lng",results[0].geometry.location.lng);
            	}
            	docList.add(doc);
            	i++;
            	if(i%100==0) {
            		mongoColl.insertMany(docList);
            		docList = new ArrayList<Document>();
            	}
            } 
            if (docList.size()>0) { 
            	mongoColl.insertMany(docList);
            }
    	} catch(Exception e) {
    		System.err.println(e);
    	} finally {
			mongoClient.close();
		}
    }
}
