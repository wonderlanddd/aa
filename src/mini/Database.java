package mini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	Connection con = null;
	
	
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web";
		String password = "1234";
		
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("연동 성공");
		} catch (SQLException e) {
			System.out.println("연동 실패");
			e.printStackTrace();
		}
		
	}
}
