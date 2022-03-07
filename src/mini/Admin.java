package mini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Admin {
	public static boolean admin(String id, String pwd) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web";
		String password = "1234";

		boolean flag = false;

		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		String sql = "select pwd from admin where id=?";
		String getPass = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// 쿼리 결과값과 뷰에서 받아온 pwd값을 비교
			if (rs.next()) {
				getPass = rs.getString("pwd");
				if (getPass.equals(pwd)) {
					flag = true;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

			// 쿼리 실행 후 모두 닫아줌
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	

}
