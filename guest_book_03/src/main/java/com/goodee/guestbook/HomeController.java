package com.goodee.guestbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	//	log 남길거임(log4j)
	// 한 번 로깅을 적으면 바꿀 필요 없기때문에 static, 상수(final)
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String home() {
		logger.info("[HomeController] home();"); //homecontroller 안에 있는 home 메소드가 동작하고 있음
		//trace: 추적, debug: 오류생겼는지, info: 정보성, warn: 경고/에러만 나옴
		// 실무에서는 log가 높으면 과부하가 걸릴 수 있어서 log레벨을 높이기도 함
		
		return "home";
	}
	
	
}
