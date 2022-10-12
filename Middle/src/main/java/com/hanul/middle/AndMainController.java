package com.hanul.middle;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import dto.AndMemberDTO;

// Spring 프레임워크 버전을 높여주기. pom.xml ==> (5.3.20)
// 
@RestController
public class AndMainController {
	@Autowired  SqlSession sql;
	
	@RequestMapping("/andLogin")
	public String login(String email , String pw) {
		// DTO를 return 해서 크롬에 찍히게 해보기.
		// 1DTO == 1JsonObject
		// 1Array == 1JsonArray
		AndMemberDTO dto = null;
		System.out.println(email);
		System.out.println(pw);
		System.out.println(sql.selectOne("test.test"));
		if(email.equals("admin@naver.com") && pw.equals("admin1234")) {
			dto = new AndMemberDTO(email, pw);
		}
		
		
		return new Gson().toJson(dto); // to ( String json으로 바꿀때사용 )
	}
}
