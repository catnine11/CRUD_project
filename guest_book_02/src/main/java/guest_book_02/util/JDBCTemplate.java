package guest_book_02.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTemplate {

	//connection 객체가 우리의 로그인 정보를 가지고 있도록 만들거임
	public static Connection getConnection() { 
		//어디서든 쓸 수 있게 만들기 위해 public
		//한 번 만들면 계속 쓸 수 있게(?) 정적인 static
		Connection conn = null;
		//데이터베이스와 관련된 것을 쓸 때는 무조건 try, catch문 사이에 적어줘야 함
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
	
	/*
	 * 메모리를 관리(부하 방지)하기 위해 쿼리(데이터베이스 접근)와 관련된 Connection, pstmt, resultSet을 닫아줘야 함.(오버로드)
	 */
	public static void close(Connection conn) {
		try {
			//데이터베이스(쿼리)와 관련된 것을 쓸 때는 무조건 try, catch문 사이에 적어줘야 함
			if(conn != null && conn.isClosed() == false) { //connection이 null이 아니고(conn~이 있고) 닫히지 않았다면
				conn.close(); //connection 을 닫아줘
			}
			
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null && pstmt.isClosed() == false) { //pstmt가 있고 아직 쓰고 있다면
				pstmt.close(); //pstmt 닫아줘
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			if(rs != null && rs.isClosed() == false) {  //rs가 있고 아직 쓰고 있다면
				rs.close(); ////rs 닫아줘
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
