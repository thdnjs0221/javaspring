package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.filter.wrapper.MemberVOWrapper;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/mypage")
@Controller
public class MyPageController{
	private MemberService service = new MemberServiceImpl();

	@RequestMapping("/mypage" )
	public String myPage(MemberVOWrapper principal, HttpServletRequest req){

		String memId = principal.getName(); // userid 존재
		MemberVO member = service.retrieveMember(memId);

		req.setAttribute("member", member);
		
		return "member/myPage";
		
	}

}
