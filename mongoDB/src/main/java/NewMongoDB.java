
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NewMongoDB {

    //Mongo db 批量插入数据
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
        for(int j=15476;j< 20001;j++) {
        	String DBname = "system" + String.valueOf(j);
        	String TableName = DBname + "_table1";
        	String TableName2 = DBname + "_table2";
        	
	        DBCollection collection = mongoClient.getDB(DBname).getCollection(TableName);
	        //DBCollection collection2 = mongoClient.getDB(DBname).getCollection(TableName2);
	        
	        for (int i = 1; i < 101; i++){
	            DBObject object = new BasicDBObject(); 
	            Date date = new Date();
	            
	            count++;        
	            
	            Random r = new Random();
	            int randNum = r.nextInt(5);
	            
	            if(randNum==1) {	
	                Map map = new HashMap();
	                map.put("Staff_ID", "9651");
	                map.put("Leave_Type", 0);
	                BasicDBObject query = new BasicDBObject(map);
	                }else if (randNum==2){
	                Map map = new HashMap();
	                map.put("Staff_ID", "9651");
	                map.put("Bank_Info.bank_num",4);
	                BasicDBObject query = new BasicDBObject(map);
	                }else if(randNum==3){
	                	 Map map = new HashMap();
	                map.put("Staff_ID", "9651");
	                map.put("Field9", "张三李四王五newumi78c9g8y5tn76s7akmuef48yq");
	                BasicDBObject query = new BasicDBObject(map);
	                }
	                else if (randNum==4){
	                 Map map = new HashMap();
	               map.put("Staff_ID", "9651");
	               map.put("Flow_State", false);
	               map.put("Bank_Info.bank_num",4);
	               map.put("Other_Long",20.99);
	                BasicDBObject query = new BasicDBObject(map);
	                }
	                else{
	                	 Map map = new HashMap();
	                map.put("Staff_ID", "9651");
	                map.put("Tenant_ID","8" );
	                BasicDBObject query = new BasicDBObject(map);
	                }
	                       
	            object.put("rec_id",String.valueOf(i));
	            object.put("tenant_id", getTenantID(setTenantID()));
	            object.put("name","张" + String.valueOf(count));
	            object.put("cellphone","13852" + Integer.parseInt(String.valueOf(count)));
	            object.put("createdTime", date);
	            object.put("sex",getSex());
	            object.put("state", state);
	            object.put("others_long", Double.parseDouble(String.valueOf(others)));
	         
	            list.add(object);
	            if (i != 0 && i %10 == 0){
	                System.out.println("现在是第" + j +"个数据库，  " + "插入" + " " + i + "数据");
	                collection.insert(list);
	               // collection2.insert(list);
	                list.clear();
	            }
	        }
	    }
    }
}