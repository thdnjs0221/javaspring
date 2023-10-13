package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.filter.wrapper.DummyHttpServletRequestWrapper;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

//@WebServlet("/member/memberView.do")
@Slf4j
@Controller
public class MemberViewController{
	private MemberService service = new MemberServiceImpl();
	
	
	@RequestMapping("/member/memberView.do")
	public String memberView(
			@RequestParam("who") String memId
			,HttpServletRequest req) throws IOException {
		
		MemberVO member = service.retrieveMember(memId);
		
		req.setAttribute("member", member);

		// 뷰이동
		return "member/ajax/memberView";
	
	}
}
