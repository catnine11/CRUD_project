package com.goodee.guestbook.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.guestbook.model.dao.GuestBookDao;
import com.goodee.guestbook.model.vo.GuestBookVo;

@Service
public class GuestBookService {

	//의존성 주입
	@Autowired
	GuestBookDao dao;
	
	private static final Logger logger = LoggerFactory.getLogger(GuestBookService.class);
	
	public List<GuestBookVo> selectBookList(){
		logger.info("[GuestBookService] selectBookList();"); //앞: 클래스 뒤: 메소드
//		List<GuestBookVo> list = new GuestBookDao().selectBookAll();
		List<GuestBookVo> list = dao.selectBookAll();
			
		return list;
	}
	
	//1. 2. requestParam
//	public int insertBookOne() {
//		//서비스가 dao에게 쿼리를 날려달라고 요청
//		int result = dao.insertGuestBook();
//		
//		return result;
//	}
		
	//3. vo로 할 때
	public int insertBookOne(GuestBookVo vo) {
		logger.info("[GuestBookService] insertBookOne();");
		//서비스가 dao에게 쿼리를 날려달라고 요청
		int result = dao.insertGuestBook(vo);
		
		return result;
	}
	
}
