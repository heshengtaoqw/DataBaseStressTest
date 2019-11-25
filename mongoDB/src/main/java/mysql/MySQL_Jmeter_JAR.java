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
 
public class MySQL_Jmeter_JAR {
	public static PreparedStatement stmt;
	public static Connection conn;
	public static BigDecimal b = new BigDecimal("10");
	int r1;
	int r2;
	int r3;
	int r4;
	int r5;
	int count;
	
	
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
	    
	    public static long random(long begin,long end){
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
	    
	    
	    public void run() throws SQLException, ClassNotFoundException, ParseException {
	    	System.out.println("开始插入...:" + new Date().getTime());
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/stress_test?useSSL=false";
			String user = "root";
			String password = "123456";
			String sql = "insert into fms_insert values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";		
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);//批量提交
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			int retryCount = 5;
				
					try {	
						Date date = new Date();
						Class.forName(driver);					
						for(int i=1;i<100001;i++) {
							Random r = new Random();
					   	    r1 = r.nextInt(5);
					   	    r2 = r.nextInt(5); 
							r3 = r.nextInt(5);
						    r4 = r.nextInt(5);
						    r5 = r.nextInt(5);
							stmt.setString(1, String.valueOf(r1));
							stmt.setString(2, String.valueOf(r2));
							stmt.setString(3, String.valueOf(r3));
							stmt.setString(4, String.valueOf(r4));
							stmt.setString(5, String.valueOf(r5));
							stmt.setInt(6,r1);
							stmt.setInt(7,r2);
							stmt.setInt(8,r3);
							stmt.setInt(9,r4);
							stmt.setInt(10,r5);
							stmt.setDouble(11, r1*3.00);
							stmt.setDouble(12, r2*3.00);
							stmt.setDouble(13, r3*3.00);
							stmt.setDouble(14, r4*3.00);
							stmt.setDouble(15, r5*3.00);
							stmt.setDate(16, new java.sql.Date(date.getTime()));
							stmt.setDate(17, new java.sql.Date(date.getTime()));
							stmt.setDate(18, new java.sql.Date(date.getTime()));
							stmt.setDate(19, new java.sql.Date(date.getTime()));
							stmt.setDate(20, new java.sql.Date(date.getTime()));
							//stmt.executeUpdate();
							//单条提交
							stmt.addBatch();
							if(i%100==0) {
								System.out.println("提交"+count+"次");
								count++;	//批量提交	
								stmt.executeBatch();//批量提交
								conn.commit();//批量提交
								
							}
							
						}
						
						

					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
						String sqlState = e.getSQLState();
				           // 这个08S01就是这个异常的sql状态。单独处理手动重新链接就可以了。
				            if ("08S01".equals(sqlState) || "40001".equals(sqlState)) {                
				                    retryCount--;            
				                 } else {                
				                     retryCount = 0;            
				                     }        
					}finally{		
						
					}					
//						stmt.close();							
//				        conn.close(); 
				      
		       System.out.println("成功插入第"+10000+"数据:" + new Date().getTime());
		      
	    }
	    
	
	public static void main(String[] args) throws ParseException, SQLException, ClassNotFoundException{
		MySQL_Jmeter_JAR mjj = new MySQL_Jmeter_JAR();
		mjj.run();
	}
 
}
