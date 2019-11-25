package mysql;
 
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
 
public class InsertMysql {
	public static PreparedStatement stmt;
	public static Connection conn;
	static BigDecimal b = new BigDecimal("10");
	
    public static int randomNum(int size)
    {
    	Random r = new Random();
    	return r.nextInt(size);
    }
		
	   public static int getStaffID() {   	
	    	return randomNum(100000);
	    }
	   
	    
	    public static String getSex() {
	    	Random r = new Random();
	    	if( r.nextInt(2) == 0) {
	    		return "男";
	    	}else {
	    		return "女";
	    	}
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

	    
	    public static void getTimeStamp() throws ParseException {  
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
	         sdf.format(randomDate());
	         
	      }  
	    
	    
	
	public static void main(String[] args) throws ParseException, SQLException{
		
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/stress_test?useSSL=false";
		String user = "root";
		String password = "123456";
		String sql = "insert into fms_leave values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false);
		stmt = (PreparedStatement) conn.prepareStatement(sql);
		
//		Staff_ID (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
//		Tenant_ID
//		Created_Time
//		Flow_State
//		Leave_Type
//		Others_Long
//		Bank_Info
//		Update_Time
//		Business_Time
//		Field1
//		Field2
//		Field3
//		Field4
//		Field5
//		Field6
//		Field7
//		Field8
//		Field9
//		Field10
//		Field11
//		Field12


		
			try {
				//java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
				//Class.forName(driver);				
				for(int i=1;i<101;i++) {
					stmt.setString(1,String.valueOf(randomNum(100000)));
					stmt.setString(2, String.valueOf(randomNum(5)));
					stmt.setDate(3, new java.sql.Date(randomDate().getTime()));
					stmt.setInt(4,randomNum(2));
					stmt.setInt(5, randomNum(2));
					stmt.setDouble(6, randomNum(555)*0.11);
					stmt.setString(7, randomString(10));
					stmt.setDate(8, new java.sql.Date(randomDate().getTime()));
					stmt.setDate(9, new java.sql.Date(randomDate().getTime()));
					stmt.setString(10, randomString(10));
					stmt.setInt(11,randomNum(2));
					stmt.setInt(12,randomNum(2));
					stmt.setFloat(13, randomNum(555)*0.12f);
					stmt.setString(14, randomString(10));
					stmt.setString(15, randomString(10));
					stmt.setBigDecimal(16, b);
					stmt.setString(17, randomString(10));
					stmt.setString(18, randomString(10));
					stmt.setDate(19, new java.sql.Date(randomDate().getTime()));
					stmt.setBigDecimal(20, b);
					stmt.setBigDecimal(21, b);					
					stmt.addBatch();
					System.out.println("成功插入第"+i+"数据");
				}
				
				
				stmt.executeBatch();
				conn.commit();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}finally
		    {
			      
			    }
		
		if(stmt!= null) 
	        stmt.close(); 		
	      if(conn!= null) 
	        conn.close(); 
		
	}
 
}
