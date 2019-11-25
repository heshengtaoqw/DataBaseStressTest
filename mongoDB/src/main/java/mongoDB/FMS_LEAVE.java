package mongoDB;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

 public class FMS_LEAVE{
	 
	    private static MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
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
	 
	 
	 
 	 	public static void main(String[] args){
 	 		MongoClient mongoClient = new MongoClient("127.0.0.1",27017);  //根据IP、端口连mongo
			DB db = mongoClient.getDB("fms_system"); 
			DBCollection dbCollection = db.getCollection("col_1");  //选择集合
			List<DBObject> list = new ArrayList<DBObject>();
	        DBCollection collection = mongoClient.getDB("fms_system").getCollection("FMS_LEAVE");
	        DBObject object = new BasicDBObject(); 
            Date date = new Date();
            
            count++;        
            
            
            
            
            object.put("rec_id",String.valueOf(1));
            object.put("tenant_id", getTenantID(setTenantID()));
            object.put("name","张" + String.valueOf(count));
            object.put("cellphone","13852" + Integer.parseInt(String.valueOf(count)));
            object.put("createdTime", date);
            object.put("sex",getSex());
            object.put("state", state);
            object.put("others_long", Double.parseDouble(String.valueOf(others)));
         
            list.add(object);
           
                System.out.println("插入" + " " + 1+ "数据");
                collection.insert(list);
                list.clear();
                System.out.println("end");

 	}
}