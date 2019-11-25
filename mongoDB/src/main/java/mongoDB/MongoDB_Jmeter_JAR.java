package mongoDB;


import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.session.ClientSession;

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

public class MongoDB_Jmeter_JAR {

    //Mongo db 批量插入数据
    private static MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
    private static long count = 100000;
    private static String TenantID;
    private static boolean state = false;
    private static double others = 20.99;
    private static String GUID;
    int r1;
    int r2;
    int r3;
    int r4;
    int r5;
    
    public static MongoClient getClient(){
        return  mongoClient;
    }
    
    public static Date randomDate() throws ParseException {
    	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         //定义开始时间
         Date start = format.parse("2019-10-01");
         //定义结束时间
         Date end = format.parse("2019-11-30");
         long date = random(start.getTime(),end.getTime());
         return new Date(date);   	
    } 
    
    private static long random(long begin,long end){

        long rtn = begin + (long)(Math.random() * (end - begin));
        //如果返回的是开始时间和结束时间，通过递归调用本函数查找随机值
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
     }
    
    public static int randomNum(int size)
    {
    	Random r = new Random();
    	return r.nextInt(size);
    }
    
    public static boolean randomBoolean() {
    	if(randomNum(2)==1) {
    		return false;
    	}else 
    		return true;
    }
    
    public static String randomString(int size)
    {
    	String s = "abcdefghijklmnopqrstuvwxyz12345677890";
    	StringBuffer sb = new StringBuffer();
    	for(int i=0;i<size;i++) {
    		int index = (int) Math.floor(Math.random() * s.length());
    		sb.append(s.charAt(index));
    	}
    	return sb.toString();
    }
    
    public static int getStaffID() {   	
    	return randomNum(10000);
    }
    
    public static int getTenantID() {
    	return randomNum(30);
    }
    
    public static int getLeaveType() {
    	return randomNum(6);
    }
    
    public static int getBankNumber() {
    	return randomNum(7);
    }
        
    public static List getList(int size) {
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	
    	for(int i=0;i<=size;i++) {
    		Map<String,Object> m = new HashMap<String,Object>();
    		m.put("bank_num", getBankNumber());
    		m.put("count", randomNum(555)*0.11);
    		m.put("address", randomString(7));
    		m.put("active", randomBoolean());
    		list.add(m);
    	}
    	return list;
    }
    
    public void run() {
    	System.out.println("开始插入...:" + new Date().getTime());
    	//ClientSession clientSession = mongoClient.startSession();
    	//mongoClient.startSession().startTransaction();
    	List<DBObject> list = new ArrayList<DBObject>();
        DBCollection collection = mongoClient.getDB("fms_system").getCollection("FMS_INSERT");
        
       // ConnectionString connectionString = new ConnectionString("mongodb://address1:27017,address2:27018,address3:27019/?replicaSet=rs0");
       // MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();

       
           
       Random r = new Random();

      for (int i = 1; i < 100001; i++){
    	    DBObject object = new BasicDBObject(); 
            Date date = new Date();
	        r1 = r.nextInt(5);
	        r2 = r.nextInt(5); 
	        r3 = r.nextInt(5);
	        r4 = r.nextInt(5);
	        r5 = r.nextInt(5);
	        object.put("String1",String.valueOf(r1));
	        object.put("String2", String.valueOf(r2));
	        object.put("String3", String.valueOf(r3));
	        object.put("String4", String.valueOf(r4));
	        object.put("String5", String.valueOf(r5));
	        object.put("Int1", r1);
	        object.put("Int2", r2);
	        object.put("Int3", r3);
	        object.put("Int4", r4);
	        object.put("Int5", r5);
	        object.put("Double1", r1*3.00d);
	        object.put("Double2", r2*3.00d);
	        object.put("Double3", r3*3.00d);
	        object.put("Double4", r4*3.00d);
	        object.put("Double5", r5*3.00d);
	        object.put("Date1", date);
	        object.put("Date2", date);
	        object.put("Date3", date);
	        object.put("Date4", date);
	        object.put("Date5", date);	   
	        list.add(object);
	        collection.insert(list);
	        list.clear();

      }
     
   
	            System.out.println("成功插入第1000001数据:" + new Date().getTime()); 
     // mongoClient.startSession().commitTransaction();

    }
     
    public static void  main(String[] args) throws ParseException{
    	//MongoDB_Jmeter_JAR m = new MongoDB_Jmeter_JAR();
    	//m.run();
    }
}
