package com.goodee.guestbook.model.vo;

import java.util.Date;

public class GuestBookVo {
	//mvc에서 썼던거랑 같은 방법
	
	private String author;
	private String content;
	private Date reg_date;
//	private Date reg_date = new Date(); //시간 부여하는걸 maria DB에서 하지 자바로 받아오지 않음
//	(자바로 받으면 form태그가 서버에게 정보를 넘겨올 때의 시간이 찍히는데 우리가 관리하고 싶은건 데이터베이스에 정보를 등록하는 시간이기 때문에 db로)
	
	public GuestBookVo() {
		super();
	}
	
	public GuestBookVo(String author, String content, Date reg_date) {
		this.author = author;
		this.content = content;
		this.reg_date = reg_date; 
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getContent() {
		return content;
	}
	
	public Date getReg_date() {
		return reg_date;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	

}
