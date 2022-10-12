package com.hanul.middle;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("HOME");
		return "home";
	}
	
	@RequestMapping("/kym")
	public void test() {
		System.out.println("KYM");
	}
	
	// 바로 HTML BODY를 이용한 응답 처리 	@ResponseBody
	// ArrayList<E> list ; => String , DataFormat
	
	@ResponseBody
	@RequestMapping("/str")
	public String str(String a) {
		System.out.println(a);
		
		
		ArrayList<TempDTO> list = new ArrayList<>();
		list.add(new TempDTO(1, "A"));
		list.add(new TempDTO(2, "B"));
		list.add(new TempDTO(3, "C"));
		//
		Gson gson = new Gson();
		
		System.out.println( gson.toJson(list) );
		
		return  gson.toJson(list) ;// ???
	}
	
	@ResponseBody
	@RequestMapping("/dto")
	public String dtos() {
		TempDTO dto = new TempDTO(1 , "ABCD");
		return new Gson().toJson(dto);
	}
	
	
	public class TempDTO{
		int data ;
		String dataStr ;
		public TempDTO(int data, String dataStr) {
			super();
			this.data = data;
			this.dataStr = dataStr;
		}
		
		
	}
	
	
	
}
