package com.hanul.smart;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.CommonUtility;

@Controller
public class DataController {
	private String key = "FPgj2NXbJw46TcGkmAfZEiYFDbxilys7KLjk3KaB7AfeJE00ZhPNM0M8unwbsI69fSmT8SNfVEimE6ZZ2U14hA%3D%3D";
	@Autowired private CommonUtility common;
	
	private String animalURL 
		= "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/";
	
	
	// 유기동물정보조회 요청
//	@ResponseBody @RequestMapping("/data/animal/list")
//	public Object animal_list(@RequestBody HashMap<String, Object> map ) {
	@RequestMapping("/data/animal/list")
	public String animal_list(@RequestBody HashMap<String, Object> map, Model model ) {
		StringBuffer url = new StringBuffer( animalURL );
		url.append( "abandonmentPublic?serviceKey=" ).append(key);
		url.append( "&_type=json" );
		url.append( "&pageNo=" ).append( map.get("pageNo") );
		url.append( "&numOfRows=" ).append( map.get("rows") );
		//return common.requestAPItoMap(url);
		
		model.addAttribute("list", common.requestAPItoMap(url));
		model.addAttribute("pageNo", map.get("pageNo"));
		return "data/animal/animal_" + map.get("viewType");
	}
	
	
	// 약국정보 요청
	@ResponseBody @RequestMapping("/data/pharmacy")
	public Map<String, Object> pharmacy_list(int pageNo, int rows) {
		//공공데이터 포털에 약국정보 데이터를 요청한다
		//http://apis.data.go.kr/B551182/pharmacyInfoService/getParmacyBasisList?ServiceKey=FPgj2NXbJw46TcGkmAfZEiYFD
		StringBuffer url = new StringBuffer(
				"http://apis.data.go.kr/B551182/pharmacyInfoService/getParmacyBasisList");
		url.append("?ServiceKey=").append(key);
		url.append("&_type=json");
		url.append("&pageNo=").append(pageNo);
		url.append("&numOfRows=").append(rows);
//		return common.requestAPI(url);
		return common.requestAPItoMap(url);
	}
	
	//공공데이터 목록화면 요청
	@RequestMapping("/list.da")
	public String list(HttpSession session) {
		session.setAttribute("category", "da");
		
		return "data/list";
	}
}
