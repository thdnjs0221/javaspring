package kr.or.ddit.buyer.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/buyer")
@RequiredArgsConstructor
public class BuyerInsertController {
	
	private final BuyerService service;
	
	
	@Inject
	private OthersDAO othersdao;
	
	@ModelAttribute("lprodList") 
	public List<LprodVO> lprodList(){
		return othersdao.selectLprodList();
	}

	
	@ModelAttribute("buyer")
	public BuyerVO buyer() {
		return new BuyerVO();
	}
	
	@GetMapping("form")
	public String buyerInserForm() {
		return "buyer/buyerForm";
	}
		
	
	//Errors : 검증대상 바로 밑에 써주기
	@PostMapping
	public String buyerInsert(
			@Validated(InsertGroup.class)@ModelAttribute("buyer") BuyerVO buyer,
			Errors errors,
			Model model
			) {
		
				boolean valid = !errors.hasErrors(); // 검증 별도의 유틸리티로

				String viewName = null;

				if (valid) {
					// 검증 통과
					// 4.createProd 등록 처리
					ServiceResult result = service.createBuyer(buyer);
					switch (result) {

					case OK:
						// 2) OK
//							
						viewName = "redirect:/buyer/"+buyer.getBuyerId();  //buyerview로

						break;

					default:
						// 3) FAIL
//							
						model.addAttribute("message", "서버오류, 조금 이따 다시 해보세요");
						viewName = "buyer/buyerForm";
						break;
					}

				} else {
					// 검증 불통
//						
					viewName = "buyer/buyerForm";

				}
	
		return viewName;
		
	}

}
