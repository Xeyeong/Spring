package com.hanul.smart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import board.BoardFileVO;
import board.BoardPageVO;
import board.BoardServiceImpl;
import board.BoardVO;
import common.CommonUtility;
import member.MemberServiceImpl;
import member.MemberVO;

@Controller
public class BoardController {
	@Autowired private BoardServiceImpl service;
	
	//신규 방명록 글저장처리 요청
	@RequestMapping("/insert.bo")
	public String insert(BoardVO vo, MultipartFile file[], HttpServletRequest request) {
		
		//첨부파일 저장
		//file 태그가 한 개이면 첨부파일이 없는 경우
		if(file.length > 1) {
			attached_file(vo,file,request);
		}
		//화면에서 입력한 정보를 DB에 신규저장한 후
		service.board_insert(vo);
		//응답화면연결
		return "redirect:list.bo";
	}
	private void attached_file(BoardVO vo, MultipartFile file[], HttpServletRequest request) {
		//첨부파일 정보를 DB에 신규저장
		List<BoardFileVO> list = null;
		for(MultipartFile attached : file) {
			if(attached.isEmpty()) continue;
			if( list == null ) list = new ArrayList<BoardFileVO>();
			BoardFileVO filevo = new BoardFileVO();
			filevo.setFilename(attached.getOriginalFilename());
			filevo.setFilepath(common.fileUpload("board", attached, request));
			list.add(filevo);
			
		}
		vo.setFileList(null);
	}
	
	//신규 방명록 글쓰기화면 요청
	@RequestMapping("/new.bo")
	public String board() {
		//응답화면연결
		return "board/new";
	}
	
	@Autowired private MemberServiceImpl member;
	@Autowired private CommonUtility common;
	
	//방명록 목록화면 요청
		@RequestMapping("/list.bo")
		public String list(HttpSession session, BoardPageVO page, Model model) {
			//임시로 로그인처리를 한다 -----
			String id = "sim2022";
			String pw = "Sim2022";		
			String salt = member.member_salt(id);
			pw = common.getEncrypt(pw, salt);		
			MemberVO vo = member.member_login(id, pw);
			session.setAttribute("loginInfo", vo);
			//------------------------------
			
			session.setAttribute("category", "bo");
			//DB에서 방명록 글 목록을 조회해와 
			//화면에 출력할 수 있도록 Model에 attribute 로 담는다
			model.addAttribute("page",  service.board_list(page) );
			
			//응답화면연결
			return "board/list";
		}
}




