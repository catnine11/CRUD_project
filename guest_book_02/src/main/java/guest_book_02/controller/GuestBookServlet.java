package guest_book_02.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guest_book_02.model.vo.GuestBookVo;
import guest_book_02.service.GuestBookService;

@WebServlet("/book")
public class GuestBookServlet extends HttpServlet {
	/**
	 *  직렬화 해주기 위해 시리얼 id 추가
	 */
	private static final long serialVersionUID = 1L;
	
	public GuestBookServlet() {
		super(); //생성자 생성
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException { //따로 exception 처리하지 않고 두겠다
		// 1.데이터베이스 접근 class
		List<GuestBookVo> list = new GuestBookService().selectList();
		
		// 2. 목록 조회 dao
		// 3. 결과 파싱 service
		// 4. view 전달
		
	}
	
	
}
