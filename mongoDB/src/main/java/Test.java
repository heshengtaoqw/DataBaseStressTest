		import com.mongodb.*;
		import com.mongodb.DB;
		import com.mongodb.MongoClient;
		//import org.apache.jmeter.protocol.mongodb.config.MongoDBHolder;
		import com.mongodb.DBCollection; 
		import java.util.HashMap;
		import java.util.ArrayList;
		import java.util.Arrays;
		import java.util.Date;
		import java.util.List;
		import java.util.Random;// import DBCollection class
		import org.json.JSONArray;
		import org.json.JSONObject;
		
		
public class Test {
	private static MongoClient mongoClient = new MongoClient("10.31.0.221", 27017);
	public static void main(String[] args) {



		Date startDate = new Date();
		System.out.println(startDate);
		MongoClient mongoClient = new MongoClient("127.0.0.1",27017);  //根据IP、端口连mongo
		DB db = mongoClient.getDB("fms_system"); 
		//List<DBObject> list = new ArrayList<DBObject>();
		//DB db = MongoDBHolder.getDBFromSource("test", "fms_system");
		DBCollection collection = db.getCollection("FMS_LEAVE"); // get "zips" collection
		//long count = collection.getCount();
		//String result = String.valueOf(count);              // convert long to String
		//SampleResult.setResponseData(result.getBytes());

		//BasicDBObject object = new BasicDBObject(); // create empty document structure
		//BasicDBList location = new BasicDBList();   // create array containing latitude and longitude
		//location.add(32.211100D);                   // add double values
		//location.add(44.251500D);                   // for latitude and longitude
		//object.append("rec_id","BABYLON");            // provide "city" value
		////object.append("loc",location);              // provide "loc" value using earlier created array
		//object.put("name","BABYLON");                // provide "pop"
		//object.put("cellphone","13852");                // provide "state"
		//object.put("createdTime","date");
		//object.put("tenant_id","IS");
		//object.put("sex","11");
		//object.put("state","false");
		//object.put("others_long","99.99");
		//WriteResult result = collection.insert(object, WriteConcern.ACKNOWLEDGED);

		/*
				
		            int randNum = r.nextInt(5);
		             BasicDBObject query;
		            String s = "{\"Staff_ID\": \"1\", \"Leave_Type\": 4}";
		            String s2 = "{ Staff_ID : \"9651\",\"Bank_Info.bank_num\":4}";
		            String s3 = "{ Staff_ID : \"9651\",\"Field9\":\"d74xfv6mcfruk2xs0lov0q6057gord\"}";
		            String s4 = "{ Staff_ID : \"9651\",\"Field9\":\"d74xfv6mcfruk2xs0lov0q6057gord\",\"Other_Long\":{20.99},\"Flow_State\":false,\"Bank_Info.bank_num\":4}";
		            String s5 = "{ Staff_ID : \"9651\"}";
		  */

		  
		  		Random r = new Random();
		  		int randNum = r.nextInt(5);
		  		BasicDBObject query;
		            if(randNum==1) {	
		            HashMap map = new HashMap();
		            map.put("Staff_ID", "9651");
		            map.put("Leave_Type", 0);
		             query = new BasicDBObject(map);
		            }else if (randNum==2){
		            HashMap map = new HashMap();
		            map.put("Staff_ID", "9651");
		            map.put("Bank_Info.bank_num",4);
		             query = new BasicDBObject(map);
		            }else if(randNum==3){
		            	 HashMap map = new HashMap();
		            map.put("Staff_ID", "9651");
		            map.put("Field9", "张三李四王五newumi78c9g8y5tn76s7akmuef48yq");
		             query = new BasicDBObject(map);
		            }
		            else if (randNum==4){
		             HashMap map = new HashMap();
		           map.put("Staff_ID", "9651");
		           map.put("Flow_State", false);
		           map.put("Bank_Info.bank_num",4);
		           map.put("Others_Long",20.99d);
		             query = new BasicDBObject(map);
		            }
		            else{
		            	 HashMap map = new HashMap();
		            map.put("Staff_ID", "9651");
		            map.put("Tenant_ID","8" );
		             query = new BasicDBObject(map);
		            }
		            


		DBCursor cursor = collection.find(query);  // execute the query and get DBCursor instance
		StringBuilder resultBuilder = new StringBuilder(); // initialize StringBuilder to construct response
		//
		while (cursor.hasNext()) {             // while there is a DBObject available
		 DBObject result = cursor.next();     // get the current DBObject
		 resultBuilder.append(result.toString()); // append it to result builder
		}

		Date endDate = new Date();
		System.out.println(endDate);
		long time = endDate.getTime() - startDate.getTime();
		System.out.println(time);
		resultBuilder.append(time).toString();
		//
		//
		////list.add(object);
		////collection.insert(object);
		//
		//SampleResult.setResponseData(resultBuilder.toString().getBytes());    
	}
}
