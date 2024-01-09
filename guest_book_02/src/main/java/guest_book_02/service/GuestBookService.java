package guest_book_02.service;

import java.sql.Connection;
import java.util.List;

import guest_book_02.model.dao.GuestBookDao;
import guest_book_02.model.vo.GuestBookVo;
import static guest_book_02.util.JDBCTemplate.getConnection;

public class GuestBookService {
	
	public List<GuestBookVo> selectList() {
		Connection conn = getConnection();
		List<GuestBookVo> list = new GuestBookDao().selectList(conn);
		return list;
	}

}
