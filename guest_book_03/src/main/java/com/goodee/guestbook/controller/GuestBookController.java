package com.goodee.guestbook.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.guestbook.model.service.GuestBookService;
import com.goodee.guestbook.model.vo.GuestBookVo;

@Controller
@RequestMapping("/book")
public class GuestBookController {

	//의존성 주입을 위해
	@Autowired
	GuestBookService service;
	
	private static final Logger logger = LoggerFactory.getLogger(GuestBookController.class); //log 확인
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView guestBookList() {
//		public ModelAndView guestBookList(ModelAndView vav) {			//밑에 new 안쓰고 매개변수로 넣을 수 있음
		// 차이점 구분
//		 1. ModelLAndView
//		request.setAttribute 로 전달할 정보도 있고 view(보여줄 화면)도 있을 때
//		 2. Model
//		정보만 있을때
//		 3. String
//		view만 있을때
		
		//1. 목록
		//2. 화면
		logger.info("[GuestBookController] guestBookList();");
//		List<GuestBookVo> list = GuestBookService().selectBookList();  //를 의존성 주입으로 바꿀거임
		List<GuestBookVo> list = service.selectBookList();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("listup");
		mav.addObject("list", list);
		return mav;
	}
	
	//1. RequestParam으로 각각의 정보 가져오기(닉네임 바꿀 때 등 그거 하나만 필요할 때)
//	@RequestMapping(method = RequestMethod.POST)
//	public String guestBookAdd(@RequestParam("author") String author, 
//			@RequestParam("content") String content) {
//		//1. 데이터 추가
//		//2. 화면 이동
//		service.insertBookOne();
//		//request로 정보 받아오는 법
//		//service, dao에 전달하는 법
//		
//		return "";
//	}
	
	//2. ReauestParam으로 모든 정보 Map으로 가져오기(정보가 여러 개 있거나 키를 각각으로 가지고 올 때)
//	@RequestMapping(method = RequestMethod.POST)
//	public String guestBookAdd(@RequestParam Map<String, String> param) {
//		System.out.println(param.get("author")); //param.get 하고 get 안에 키 값을 넣어줌
//		System.out.println(param.get("content"));
//		service.insertBookOne();
//		
//		return "";
//	}
	
	//3. Vo로 가져오기
	@RequestMapping(method = RequestMethod.POST)
	public String guestBookAdd(GuestBookVo vo) {
		logger.info("[GuestBookController] guestBookAdd();");
		service.insertBookOne(vo);
		
		return "redirect:/book";
	}
	
	
}
