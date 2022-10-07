package com.hanul.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	@RequestMapping("/member")
	public String member() {
		return "member/join";
	}
	
	
	@RequestMapping("/third")
	public ModelAndView view() {
		//비지니스로직
		//오늘 날짜 시각 정보를 view화면에 출력할 수 있도록 한다
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(new Date());
		
		ModelAndView model = new ModelAndView();
		model.addObject("time", now);
		model.setViewName("view");
		return model;
	}
	
	
	@RequestMapping("/second")
	public String second( Model model ) {
		//비지니스로직
		//현재 시각을 화면에 출력할 수 있도록 Model에 attribute로 담는다
		SimpleDateFormat sdf = new SimpleDateFormat("a hh시 mm분 ss초");
		String now = sdf.format( new Date() );
		model.addAttribute("now", now);
		
		//응답화면 연결
		return "view";
	}
	
	
	@RequestMapping("/first")
	public String first(Model model) {
		//비지니스로직
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		String formattedToday = sdf.format(today);
		//화면에서 출력할 데이터를 Model에 attribute로 담는다
		model.addAttribute("today", formattedToday);
		
		//응답화면 연결
		return "view";
	}

}
