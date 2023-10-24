package kr.or.ddit.adrs.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import kr.or.ddit.adrs.service.AddressService;
import kr.or.ddit.adrs.service.AddressServiceImpl;
import kr.or.ddit.vo.AddressVO;

//@WebServlet({ "/adrs/address", "/adrs/address/*" }) // 주소록 하나만을 표현 -> crud 중 어떤걸 할지는 메소드로
/**
 *  /adrs/address (GET)  주소록 조회 
 *  /adrs/address (POST)  주소록 추가  
 *  /adrs/address/23 (GET)  주소록 23번 주소록 조회  
 *  /adrs/address/23 (PUT)  주소록 23번 주소록 수정 
 *  /adrs/address/23 (DELETE)  주소록 23번 주소록 삭제 
 *  
 *
 */
@Controller("/adrs/address")
public class AddressDataController {
	
	@Inject
	private AddressService service ;
	
	@GetMapping("/adrs/address")
	public String adrsList(Principal principal, Model model) {
		
		String memId = principal.getName();
		List<AddressVO> adrsList = service.retriveAddressList(memId);
		
		model.addAttribute("adrsList", adrsList);
		
		return "jsonView";
	}
	
//    private ObjectMapper mapper = new ObjectMapper(); 이제 사용 안함
	
	@PostMapping(value="/adrs/address")
	public String doPost(@Valid @RequestBody AddressVO vo, Errors errors ,Model model, Principal principal) {
//		try(
//			InputStream is = req.getInputStream();  //원문 데이터 꺼내기 위한 (req에 담겨서 온)
//		){
//			AddressVO vo = mapper.readValue(is, AddressVO.class); //언마샬링 단계 
			
		//언마샬링 직접 안하고 vo에서..request 에 있는 바디에 있는걸 언마살링@RequestBody로 대신!!!
			
			model.addAttribute("originalData", vo);
			
//			String authId = (String) req.getSession().getAttribute("authId");
			
			vo.setMemId(principal.getName());
			
			
			boolean valid = !errors.hasErrors();
			
			boolean success = false;
			String message = null;
			if(valid) {
				if(service.createAddress(vo)) {
					success = true;
				}else {
					message = "등록 실패";
				}
			}else {
				message = "필수파라미터 누락";
			}
			
			model.addAttribute("success", success);
			model.addAttribute("message", message);
		
		
		return "jsonView";
	}
	
//	/adrs/address/23 path...로 경로변수 처리
	// 경로 변수가 없으면 requestparam 이라고 생각함 
	public String doDelete(@PathVariable int adrsNo, Model model) throws ServletException, IOException {
	      boolean success = service.removeAddress(adrsNo);
	      model.addAttribute("success", success);
	      if(!success) {
	         model.addAttribute("message", "삭제 실패");
	      }
	      
	      return "jsonView";
	   }
}

