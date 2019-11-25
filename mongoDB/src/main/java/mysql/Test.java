package mysql;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class Test {
	
	public static void main(String[] arg){
		Connection conn;
		PreparedStatement stmt;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/stress_test?useSSL=false";
		String user = "root";
		String password = "123456";
		String sql = "insert into single values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			for(int i=0;i<5;i++) {
			stmt.setString(1,"1");
			stmt.setString(2, "2");
			stmt.setString(3, "3");
			stmt.setString(4, "4");
			stmt.setString(5, "5");
			stmt.setInt(6,6);
			stmt.setInt(7,7);
			stmt.setInt(8,8);
			stmt.setInt(9,9);
			stmt.setInt(10,10);
			stmt.setDouble(11, 0.11);
			stmt.setDouble(12, 0.12);
			stmt.setDouble(13, 0.13);
			stmt.setDouble(14, 0.14);
			stmt.setDouble(15, 0.15);
			stmt.setDate(16, new java.sql.Date(new Date(20171213).getTime()));
			stmt.setDate(17, new java.sql.Date(new Date(20171213).getTime()));
			stmt.setDate(18, new java.sql.Date(new Date(20171213).getTime()));
			stmt.setDate(19, new java.sql.Date(new Date(20171213).getTime()));
			stmt.setDate(20, new java.sql.Date(new Date(20171213).getTime()));
			stmt.executeUpdate();
			System.out.println("done");
			}
		} 
		
		catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}