package guest_book_01;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/book") // url 잡아줌(annotation 웹 서블릿이 필요함)
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuestBookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 데이터베이스에 접근
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String url = "jdbc:mariadb://127.0.0.1:3306/guest_book"; //계정주소
			String user = "root";
			String pw = "1234";
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pw);
			
			String sql = "SELECT * FROM content_list";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			// 2. 데이터 파싱(원하는 데이터 형태로 데이트를 담음?)
			List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>(); //map은 순서가 없음
				map.put("author", rs.getString("g_author"));
				map.put("content", rs.getString("g_content"));
				map.put("date", rs.getDate("g_reg_date"));
				list.add(map); //0번째 인덱스에 맵이 들어감
			}
			
//			List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
//			Map<String, Object> map = new HashMap<String, Object>(); // map은 이미 같은 값이 있다면 덮어쓰기됨
//																	=>while 밖에 있어도 문제x but, 그때그때 써주는게 더 가독성 좋음
//			
//			while(rs.next()) {
//				map.put("author", rs.getString("g_author"));
//				map.put("content", rs.getString("g_content"));
//				map.put("date", rs.getDate("g_reg_date"));
//				list.add(map); //0번째 인덱스에 맵이 들어감
//			}
			
//			System.out.println(list.get(0).get("author"));
//			System.out.println(list.get(0).get("content"));
//			System.out.println(list.get(0).get("date"));
			
			// 3. view에 list 전달
			request.setAttribute("books", list); //앞: 어떤 이름으로 뒤: 어떤 애들을 전달할 건지
//			request.getRequestDispatcher("view/guestbook.jsp"); //어디에 전달할 건지 (String 형태로 적어줌)
			RequestDispatcher rd = request.getRequestDispatcher("view/guestbook.jsp"); //
			rd.forward(request, response); // RequestDispatcher가 forward 메소드로 보내줌
			
			// list를 읽어 옴
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(req, resp);
		
		//1. form 정보 받아오기
		request.setCharacterEncoding("UTF-8"); //한국어 받아와서 쓸 수 있게
		String author = request.getParameter("author"); //여러번 써야하기때문에 변수에 담음
		String content = request.getParameter("content");
//		System.out.println(author);
//		System.out.println(content);
		//syso 대신에 디버그 모드로 확인해보자!!
		
		//2. 데이터베이스에 연결
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String url = "jdbc:mariadb://127.0.0.1:3306/guest_book"; //127.0.0.1:3306 로컬호스트 같은거
			String user = "root"; //계정명
			String pw = "1234"; //비번
			Class.forName("org.mariadb.jdbc.Driver"); //이렇게 부르면서 쓰겠다
			conn = DriverManager.getConnection(url, user, pw); //connection 함수에 우리가 지정한 드라이버를 넣어줌
			
			//3. 정보 insert 
			String sql = "INSERT INTO content_list(g_author, g_content, g_reg_date) VALUES(?,?, NOW())";
			pstmt = conn.prepareStatement(sql); //pstmt 에 sql을 넣어줌
			pstmt.setString(1, author); //첫번째 ? 위치에 author 넣어줌
			pstmt.setString(2, content); //두번째 ? 위치에 content 넣어줌
			
			rs = pstmt.executeQuery();
			
			//4. 목록 조회(사용자가 자신이 날린 쿼리 잘 되는지 확인하기 위해서)
			response.sendRedirect("/book");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
