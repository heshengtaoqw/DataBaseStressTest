package mongoDB;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FMS_TENANT {

    //Mongo db 批量插入数据
    private static MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
    private static long count = 0;
    private static boolean state = false;
    private static double others = 20.99;
    
    public static MongoClient getClient(){
        return  mongoClient;
    }

    public static void  main(String[] args){
        List<DBObject> list = new ArrayList<DBObject>();
        DBCollection collection = mongoClient.getDB("fms_system").getCollection("FMS_TENANT");

        for (int i = 1; i < 3001; i++){
            DBObject object = new BasicDBObject(); 
            Date date = new Date();
            
            count++;        
            object.put("name","租户" + String.valueOf(count));
            object.put("cellphone",Integer.parseInt(String.valueOf(count)));
            object.put("createdTime", date);
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