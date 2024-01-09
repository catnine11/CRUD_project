package guest_book_02.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import guest_book_02.model.vo.GuestBookVo;

import static guest_book_02.util.JDBCTemplate.getConnection;

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
		}
		
		return list;
	}
	
}
