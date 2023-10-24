package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

//@MultipartConfig
//@WebServlet("/prod/prodUpdate.do")
@Controller
@RequiredArgsConstructor
public class ProdUpdateController {



	private final ProdService service;

	@Inject
	private OthersDAO othersdao;

	@ModelAttribute("lprodList")
	public List<LprodVO> lprodList() {
		return othersdao.selectLprodList();
	}

	@ModelAttribute("buyerList")
	public List<BuyerVO> buyerList() {
		return othersdao.selectBuyerList(null);

	}

	@RequestMapping("/prod/prodUpdate.do")
	public String Prod(
			Model model, @RequestParam(value = "what", required = true) String prodId
			) {

		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);

		return "prod/prodEdit";

	}

	/**
	 * 스프링을 이용한 객체 검증 (H/V + Spring)
	 * 1. 검증 대상이 되는 command object 에 @Valid(그룹힌트 못줌) / @Validated(그룹힌트 가능)  를 사용
	 * 2. command object 바로 다음 파라미터 검증 결과(Errors/ BindingResult) 객체 선언.
	 * 3. errors.hasErrors 로 검증 통과 여부 확인
	 * 4. 검증 결과 메시지 출력시 , form:errors 커스텀 태그 활용.
	 * 
	 */
	@RequestMapping(value = "/prod/prodUpdate.do", method = RequestMethod.POST)
	public String prodUpdate(
			@Validated(UpdateGroup.class)@ModelAttribute("prod") ProdVO prod,
			Errors errors,
			Model model 
	) throws IOException {

//		prod.saveTo(saveFolder);

//		Map<String, List<String>> errors = new HashMap<>(); // 에러메세지
//		model.addAttribute("errors", errors);
//		
//		// 3. 검증
		boolean valid = !errors.hasErrors(); 
		String viewName = null;

		if (valid) {
			// 검증 통과
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
			case OK:
				// 2) OK
				viewName = "redirect:/prod/prodView.do?what=" + prod.getProdId();

				break;

			default:
				// 3) FAIL
				model.addAttribute("message", "서버오류, 조금 이따 다시 해보세요");
				viewName = "prod/prodEdit";
				break;
			}

		} else {
			// 검증 불통
			viewName = "prod/prodEdit";

		}
		return viewName;
	}
}
