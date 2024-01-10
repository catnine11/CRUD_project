package guest_book_02.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import guest_book_02.model.vo.GuestBookVo;
import static guest_book_02.util.JDBCTemplate.close;

public class GuestBookDao {

	public List<GuestBookVo> selectList(Connection conn) {
//		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		
		try {
			String sql = "SELECT * FROM content_list";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				GuestBookVo vo = new GuestBookVo();
				vo.setAuthor(rs.getString("g_author"));
				vo.setContent(rs.getString("g_content"));
				vo.setReg_date(rs.getDate("g_reg_date"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//연 방향 반대로 닫아줘야 해서 rs먼저 닫아주기
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public void insertOne(Connection conn, String author, String content) {
		//커넥션은 서비스로부터 받아와서 이미 써줬기때문에 preparedStatement랑 ResultSet만 있으면 됨
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO content_list(g_author, g_content, g_reg_date) VALUES(?,?, NOW())";
			pstmt = conn.prepareStatement(sql);
			//pstmt 는 시작점이 1이라 1부터 시작
			pstmt.setString(1, author);
			pstmt.setString(2, content);
			rs = pstmt.executeQuery(); //rs꼭 안 적어도 되긴함(rs는 insert 구문을 가져오는 것인데 
//							우리는 void고 insert 결과로 특정행위(rs>1)를 하지 않기때문에 필요없지만 확장성을 위해 적어줌)
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//연 방향 반대로 닫아줘야 해서 rs먼저 닫아주기
			close(rs);
			close(pstmt);
		}
	}
	
	
}
