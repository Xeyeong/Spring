package com.hanul.smart;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import visual.VisualServiceImpl;

@Controller
public class VisusalizationController {
	@Autowired private VisualServiceImpl service;
	
	//년도/월별 채용인원수 조회 요청
	@ResponseBody @RequestMapping("/visual/hirement/year")
	public Object hirement_year() {
		return service.hirement_year();
	}
	
	@ResponseBody @RequestMapping("/visual/hirement/month")
	public Object hirement_month() {
		return service.hirement_month();
	}
	
	
	//부서별 사원수 조회 요청
	@ResponseBody @RequestMapping("/visual/department")
	public Object department() {
		//부서별 사원수를 DB에서 조회해와 ajax 로 데이터를 보낸다
		return service.department();
	}
	
	//시각화 목록화면 요청
	@RequestMapping("/list.vi")
	public String list(HttpSession session) {
		session.setAttribute("category", "vi");
		return "visual/list";
	}
}
