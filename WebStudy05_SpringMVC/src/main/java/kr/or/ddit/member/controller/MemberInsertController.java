package kr.or.ddit.member.controller;

import java.io.IOException;


import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.MemberVO;

//@MultipartConfig
//@WebServlet("/member/memberInsert.do")
@Controller
public class MemberInsertController {

	@Inject
	private MemberService service;
	
	
	@ModelAttribute("member")
	public MemberVO member() {
		return new MemberVO();
	} //post에서 재활용 가능

	@GetMapping("/member/memberInsert.do")
	public String doGet(){
		// ui
		return "member/memberInsert";

		}

	@PostMapping(value = "/member/memberInsert.do")
	public String doPost(
			@Validated(InsertGroup.class)@ModelAttribute("member")MemberVO member,
			BindingResult errors,
			Model model
			
			) throws IOException {

//		Map<String, List<String>> errors = new HashMap<>(); //에러메세지
//		req.setAttribute("errors", errors);	
		
		boolean valid = !errors.hasErrors();
		String viewName = null;

		if (valid) {
			ServiceResult result = service.CreateMember(member);
			switch (result) {
			case PKDUPLICATED:
				// 1)PKDUPLICATED (PK중복) -->
//					memberInesrt으로 이동(기존 입력 데이터, 메시지, dispatch)
				model.addAttribute("message", "아이디 중복");
				viewName = "member/memberInsert";

				break;
			case OK:
				// 2) OK
//					welcome page로 이동 (redirect)
				viewName ="redirect:/";
				
				break;

			default:
				// 3) FAIL
//					memberInesrt으로 이동(기존 입력 데이터, 메시지, dispatch)		
				model.addAttribute("message", "서버오류, 조금 이따 다시 해보세요");
				viewName = "member/memberInsert";
				break;
			}
			
		} else {
			// 검증 불통
//						
			viewName = "member/memberInsert";

		}

	return viewName;
	}

	
}
