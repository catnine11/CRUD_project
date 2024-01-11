package com.goodee.guestbook.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.guestbook.model.dao.GuestBookDao;
import com.goodee.guestbook.model.vo.GuestBookVo;

@Service
public class GuestBookService {

	//의존성 주입
	@Autowired
	GuestBookDao dao;
	
	public List<GuestBookVo> selectBookList(){
//		List<GuestBookVo> list = new GuestBookDao().selectBookAll();
		List<GuestBookVo> list = dao.selectBookAll();
			
		return list;
	}
	
	public int insertBookOne() {
		//서비스가 dao에게 쿼리를 날려달라고 요청
		int result = dao.insertGuestBook();
		
		return result;
	}
		
}
