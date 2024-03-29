package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.filter.wrapper.MemberVOWrapper;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberDelete.do")
@Controller
public class MemberDeleteController  {
	MemberService service = new MemberServiceImpl();
	
	
	@RequestMapping(value =  "/member/memberDelete.do", method = RequestMethod.POST)
	public String doPost(
			HttpSession session
			, MemberVOWrapper principal
			,@RequestParam("password")String password
			)  {

		//누가 탈퇴? 아이디, 비밀번호
		String memId = principal.getName(); //userid 존재

		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(password);
		
		Map<String, List<String>>errors = new HashMap<>();
		boolean valid = ValidationUtils.validate(inputData, errors, DeleteGroup.class); //검증 별도의 유틸리티로
		String viewName = null;
		if(valid) {
			ServiceResult result= service.removeMember(inputData);
			switch (result) {
			case INVALIDPASSWORD:
				viewName = "redirect:/mypage";
				//리다이렉트 -> 세션으로 에러 메세지 표현 
				session.setAttribute("message", "비밀번호 오류");  //flash attribute
				break;
			case OK:
				//성공시 웰컴페이지
				viewName = "redirect:/";
				session.invalidate();
				break;

			default:
				viewName = "redirect:/mypage";
				//리다이렉트 -> 세션으로 에러 메세지 표현 
				session.setAttribute("message", "서버 오류");  //flash attribute
				break;
			}
		}else {
			//검증 불통과 비밀번호 누락  - 마이페이지로 보내기 , dispath
			viewName="redirect:/mypage";
			session.setAttribute("message", "비밀번호 누락"); //flash attribute
		}
	
		return viewName;
		
		
	}
	

}
