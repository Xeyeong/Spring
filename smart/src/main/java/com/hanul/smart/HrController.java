package com.hanul.smart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.EmployeeVO;
import hr.HrServiceImpl;

@Controller
public class HrController {
	@Autowired private HrServiceImpl service;
	
	//사원목록화면 요청
	@RequestMapping("/list.hr")
	public String list() {
		//DB에서 사원목록을 조회해와
		List<EmployeeVO> list = service.employee_list();
		//화면에서 출력할 수 있도록 Model에 attribute 로 데이터를 담는다
		//응답화면연결
		return "hr/list";
	}
}
