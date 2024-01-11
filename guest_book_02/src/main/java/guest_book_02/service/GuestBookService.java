package guest_book_02.service;

import static guest_book_02.util.JDBCTemplate.getConnection;
import static guest_book_02.util.JDBCTemplate.close;

import java.sql.Connection;
import java.util.List;

import guest_book_02.model.dao.GuestBookDao;
import guest_book_02.model.vo.GuestBookVo;

public class GuestBookService {
	
	//새로운 객체를 만들어서 이용하는 방법(메모리 사용 증가, new를 어디에 썼는지 기억해야 함)
	public List<GuestBookVo> selectList() {
		Connection conn = getConnection();
		List<GuestBookVo> list = new GuestBookDao().selectList(conn);
		close(conn);
		return list;
	}
	
	//의존성 주입방법(메모리 사용을 줄일 수 있음, 어디에서 어떤 객체를 사용했는지 확인 가능) 
	//	=> dao가 있어야 service를 쓸 수 있음 => 의존성이 있다
//	public List<GuestBookVo> selectList(GuestBookDao dao) {
//		Connection conn = getConnection();
//		List<GuestBookVo> list = dao.selectList(conn);
//		close(conn);
//		return list;
//	}
	
	/*
	 * overload 예시
	 */
//	public List<GuestBookVo> selectList(String condition) {
//		Connection conn = getConnection();
//		List<GuestBookVo> list = new GuestBookDao().selectList(conn);
//		return list;
//	}
	
	public void insertOne(String author, String content) {
		Connection conn = getConnection();
		new GuestBookDao().insertOne(conn, author, content);  //dao에 insertOne 함수 만들어야함
		close(conn);
	}

}
