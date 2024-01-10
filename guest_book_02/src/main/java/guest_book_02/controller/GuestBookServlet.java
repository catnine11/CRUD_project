package guest_book_02.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
		req.setAttribute("books", list); //앞: 어떻게 부를지, 뒤: 어떤걸 보낼건지 //리스트정보를 books 이름으로 보낼거임
		RequestDispatcher rd = req.getRequestDispatcher("view/guestbook.jsp"); //어디에 전달할지
		rd.forward(req, resp);
		
		// 2. 목록 조회 dao
		// 3. 결과 파싱 service
		// 4. view 전달
	}

	//Override vs Overload
	//Override : (기술)상속(extends)받을 때 부모가 만들어둔 메소드를 자식이 쓰는 것
	//Overload : (행위)두 메소드가 똑같은 이름을 가지고 매개변수만 다를때   =>> service에 예시 적어둠
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. form이 보낸 정보 받아오기
		req.setCharacterEncoding("UTF-8");
		String author = req.getParameter("author"); //form 태그의 name을 기준으로 가지고 옴
		String content = req.getParameter("content"); 
		
		//	***********sysout 대신 디버그 모드로 테스트 할 때 =>> 보내지지 않고 뭐라고 쳤는지만 뜸 
//		=>> 아래를 서버로 돌리고 확인하는거랑 똑같음
//		System.out.println(author);
//		System.out.println(content);
		
		//2. 데이터베이스 insert
		//controller에서 먼저 시키고 그 다음에 서비스에 insert 함수를 만들어보자  (서비스의 함수를 컨트롤러에서 호출함)
		// =>doPost가 가진 어떤 정보를 service에게 보내줄지 알려줘야 함  =>> 매개변수에 정보를 적어주자
		new GuestBookService().insertOne(author, content);
		
		//3. 목록으로 돌아가기
		resp.sendRedirect("/book");
		
	}
	
}
