

import com.mongodb.*;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.Date;

import org.apache.jmeter.samplers.SampleResult;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
public class Q {
    public static void main(String[] args){
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date date = sdf.parse("1993-03-03 03:03:03");
            ServerAddress serverAddress = new ServerAddress("IP",3717);
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();
            addrs.add(serverAddress);
            MongoCredential credential = MongoCredential.createScramSha1Credential("username", "db_name", "passwd".toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);
            MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
            DB db = mongoClient.getDB("fms_system");
            Date startDate = new Date();
            System.out.println(startDate);
            System.out.println("数据库连接成功");
            DBCollection dbCollection = db.getCollection("FMS_LEAVE");
            System.out.println("集合连接成功");
            /*
            Random r = new Random();
            int randNum = r.nextInt(5);
            BasicDBObject query;
            String s = "{\"Staff_ID\": \"1\", \"Leave_Type\": 4}}";
            String s2 = "{ Staff_ID : \"9651\",\"Bank_Info.bank_num\":4}";
            String s3 = "{ Staff_ID : \"9651\",\"Field9\":/李四王五/}";
            String s4 = "{ Staff_ID : \"9651\",\"Field9\":/李四王五/,\"Field4\":{\"$gte\":1,\"$lte\":100},\"Flow_State\":false,\"Bank_Info.bank_num\":4}";
            String s5 = "{ Staff_ID : \"9651\"}";
  
            if(randNum==1) {	
            	query = BasicDBObject.parse(s3);
            }else if (randNum==2){
            	query = BasicDBObject.parse(s3);
            }else if(randNum==3){
            	query = BasicDBObject.parse(s3);
            }else if (randNum==4){
            	query = BasicDBObject.parse(s3);
            }else if(randNum==5){
            	query = BasicDBObject.parse(s3);
            }else {
            	query = BasicDBObject.parse(s3);
            }
            */
            /*
            if(randNum==1) {
            	String s = "{\"$gte\":ISODate(\"2019-10-12T16:00:00\"),\"$lte\":ISODate(\"2019-11-20T16:00:00\")}";
            	Object o = (Object)s;
            	
            	query  = new BasicDBObject("Staff_ID","9651");
                query.append("Created_Time", s);
            }else if(randNum==2) {
            	query  = new BasicDBObject("Staff_ID","1");
            	String s = "{\"$gte\":ISODate(\"2019-10-12T16:00:00\"),\"$lte\":ISODate(\"2019-11-20T16:00:00\")}";
            	Object o = (Object)s;
                query.append("Created_Time", s);
                query.append("Leave_Type","4");
            }else if(randNum==3) {
            	 query  = new BasicDBObject("Staff_ID","1");
            	 String s = "{\"$gte\":ISODate(\"2019-10-12T16:00:00\"),\"$lte\":ISODate(\"2019-11-20T16:00:00\")}";
             	Object o = (Object)s;
                query.append("Created_Time", s);
                query.append("Bank_Info.bank_num","4");
            }else if(randNum==4) {
            	 query  = new BasicDBObject("Staff_ID","1");
            	 String s = "{\"$gte\":ISODate(\"2019-10-12T16:00:00\"),\"$lte\":ISODate(\"2019-11-20T16:00:00\")}";
              	Object o = (Object)s;
                 query.append("Created_Time", s);
                query.append("Bank_Info.bank_num","4");
                query.append("Field9","/b/");
                query.append("Field4","{\"$gte\":1,\"$lte\":100}");
            }else if(randNum==5) {
            	 query  = new BasicDBObject("Staff_ID","1");
            	 String s = "{\"$gte\":ISODate(\"2019-10-12T16:00:00\"),\"$lte\":ISODate(\"2019-11-20T16:00:00\")}";
              	Object o = (Object)s;
                 query.append("Created_Time", s);
                query.append("Field9","张三李四王五riwk7swqg9c6j5ndsdezol3uojslpj");
            }else {
            	 query  = new BasicDBObject("Staff_ID","9651");
            }
            */
            Map map = new HashMap();
            map.put("Staff_ID", "1");
            map.put("Leave_Type", 0);
            JSONObject js = new JSONObject(map);
            
            
            
            BasicDBObject query = new BasicDBObject(map);

            DBCursor cursor = dbCollection.find(query);
            StringBuilder resultBuilder = new StringBuilder(); // initialize StringBuilder to construct response

            while (cursor.hasNext()) {             // while there is a DBObject available
             DBObject result = cursor.next();     // get the current DBObject
             resultBuilder.append(result.toString()); // append it to result builder
            }
            Date endDate = new Date();
            System.out.println(endDate);
            long time = endDate.getTime() - startDate.getTime();
            System.out.println(time);


            //SampleResult.setResponseData(resultBuilder.toString().getBytes());
            //System.out.println(result.toString());
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}