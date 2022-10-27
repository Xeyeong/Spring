package com.hanul.smart;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DataController {

	//공공데이터 목록화면 요청
	@RequestMapping("/list.da")
	public String list(HttpSession session) {
		session.setAttribute("category", "da");
		
		return "data/list";
	}
}
