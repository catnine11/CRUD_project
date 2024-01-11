package com.goodee.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.guestbook.model.service.GuestBookService;
import com.goodee.guestbook.model.vo.GuestBookVo;

@Controller
@RequestMapping("/book")
public class GuestBookController {

	//의존성 주입을 위해
	@Autowired
	GuestBookService service;
	
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
//		List<GuestBookVo> list = GuestBookService().selectBookList();  //를 의존성 주입으로 바꿀거임
		List<GuestBookVo> list = service.selectBookList();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("listup");
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String guestBookAdd() {
		//1. 데이터 추가
		//2. 화면 이동
		service.insertBookOne();
		//request로 정보 받아오는 법
		//service, dao에 전달하는 법
	}
	
}
