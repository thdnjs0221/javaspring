package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.common.enumpkg.ServiceResult;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

//@MultipartConfig
//@WebServlet("/member/memberUpdate.do")
@Controller
public class MemberUpdateController{
	
	@Inject
	private MemberService service;

	
	@RequestMapping("/member/memberUpdate.do")
	public String updateForm(
			 Principal principal
			,HttpServletRequest req) {
	
		String memId = principal.getName(); // userid 존재 (보호자원)

		MemberVO member = service.retrieveMember(memId);

		req.setAttribute("member", member);

		return "member/memberInsert";
	
	}

	@PostMapping(value = "/member/memberUpdate.do")
	public String updateProcess(
			@Validated(UpdateGroup.class)@ModelAttribute("member")MemberVO member
			, Errors errors
			,Model model
			) throws IOException {

		
//		if (req instanceof StandardMultipartHttpServletRequest) {
//			MultipartFile memImage = ((StandardMultipartHttpServletRequest) req).getFile("memImage");
//			if (memImage != null && !memImage.isEmpty()) {
//				member.setMemImg(memImage.getBytes()); // 이미지 업로드
//			}
//		}

//		Map<String, List<String>> errors = new HashMap<>();
//		req.setAttribute("errors", errors);
//		3. 검증 (대상 : MemberVO)
		boolean valid = !errors.hasErrors();
		String viewName = null;
		
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
//					1) INVALIDPASSWORD 
//						memberForm 으로 이동 (기존 입력 데이터, 메시지, dispatch)
				model.addAttribute("message", "비밀번호 오류");
				viewName = "member/memberInsert";

				break;
			case OK:
//					2) OK 
//						/mypage 로 이동 (redirect)
				viewName = "redirect:/mypage";
				break;
			default:
//					3) FAIL
//						memberForm 으로 이동 (기존 입력 데이터, 메시지, dispatch)
				model.addAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "member/memberInsert";
				break;
			}
		
//				memberForm 으로 이동 (기존 입력 데이터, 검증 결과 메시지들.., dispatch)
			

		return viewName;
	}

}
