package mongoDB;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class FMS_USER {

    //Mongo db 批量插入数据
    private static MongoClient mongoClient = new MongoClient("10.31.0.221", 27017);
    private static long count = 100000;
    private static String TenantID;
    private static boolean state = false;
    private static double others = 20.99;
    private static String GUID;
    
    public static MongoClient getClient(){
        return  mongoClient;
    }
    
    public static List setTenantID() {
    	List list = new ArrayList(); 
    	for(int i=1; i<3001;i++) {
    		list.add(i);
    	}
    	
    	return list;
    }
    
    public static String getTenantID(List a) {
    	Random r = new Random();
    	return a.get(r.nextInt(a.size())).toString();
    }
    
    public static String getSex() {
    	Random r = new Random();
    	if( r.nextInt(2) == 0) {
    		return "男";
    	}else {
    		return "女";
    	}
    }
    
//    public static String getTenantID() {
//    	Random random = new Random();
//    	
//    	DBCollection collection = mongoClient.getDB("fms_system").getCollection("FMS_TENANT");
//    	BasicDBObject condition=new BasicDBObject();
//    	BasicDBObject key=new BasicDBObject("_id",1);
//    	DBCursor dbCursor = collection.find(condition,key);
//    	List list = dbCursor.toArray();
//    	System.out.println(list.get(random.nextInt(list.size())));
//    	return (String) list.get(random.nextInt(list.size()));
//    }
    

    public static void  main(String[] args){
    	
        List<DBObject> list = new ArrayList<DBObject>();
        DBCollection collection = mongoClient.getDB("fms_system").getCollection("FMS_USER");
        
        for (int i = 1; i < 100001; i++){
            DBObject object = new BasicDBObject(); 
            Date date = new Date();
            
            count++;        
            
            
            object.put("rec_id",String.valueOf(i));
            object.put("tenant_id", getTenantID(setTenantID()));
            object.put("name","张" + String.valueOf(count));
            object.put("cellphone","13852" + Integer.parseInt(String.valueOf(count)));
            object.put("createdTime", date);
            object.put("sex",getSex());
            object.put("state", state);
            object.put("others_long", Double.parseDouble(String.valueOf(others)));
         
            list.add(object);
            if (i != 0 && i %1000 == 0){
                System.out.println("插入" + " " + i + "数据");
                collection.insert(list);
                list.clear();
            }
        }
    }
}