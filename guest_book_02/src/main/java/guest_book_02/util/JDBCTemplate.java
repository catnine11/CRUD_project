package guest_book_02.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTemplate {

	//connection 객체가 우리의 로그인 정보를 가지고 있도록 만들거임
	public static Connection getConnection() { 
		//어디서든 쓸 수 있게 만들기 위해 public
		//한 번 만들면 계속 쓸 수 있게(?) 정적인 static
		Connection conn = null;
		try {
			String url = "jdbc:mariadb://127.0.0.1:3306/guest_book";
			String user = "root";
			String pw = "1234";
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pw); //어디서든 우리 connection객체에 접근할 수 있도록?
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
