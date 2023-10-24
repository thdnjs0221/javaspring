package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.filter.wrapper.MemberVOWrapper;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDeleteController{
	
	@Inject
	private MemberService service;
	
	@PostMapping(value="/member/memberDelete.do")
	public String doPost(
		MemberVOWrapper principal
		, @RequestParam("password") String password   //required 검증 끝남 (비밀번호 누락) 
		, RedirectAttributes redirectAttributes
		, HttpSession session
	){
		String memId = principal.getName();

		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(password);

		String viewName = null;
		
			ServiceResult result = service.removeMember(inputData);
			switch (result) {
			case INVALIDPASSWORD:
				viewName = "redirect:/mypage";
				redirectAttributes.addFlashAttribute("message", "비밀 번호 오류"); 
				// flash attribute ->  꺼내고 나면 자동 삭제가 된다
				break;
			case OK:
				viewName = "redirect:/";
				session.invalidate();
				break;
			default:
				viewName = "redirect:/mypage";
				redirectAttributes.addFlashAttribute("message", "서버 오류"); // flash attribute
				break;
			}
		
		return viewName;
				
	}
}


















