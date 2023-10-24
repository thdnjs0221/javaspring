package kr.or.ddit.prod.controller;


import java.io.IOException;

import java.util.List;



import javax.inject.Inject;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;


import kr.or.ddit.validate.grouphint.InsertGroup;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;

import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@WebServlet("/prod/prodInsert.do")
//@MultipartConfig
@Slf4j
@Controller
@RequiredArgsConstructor
public class ProdInsertController {
	
//	@Value("#{appInfo.prodImagesUrl}")
//	private String prodImagesUrl;
//	
//	@Value("#{appInfo.prodImagesUrl}")
//	private Resource prodImages;
//	
//	private File saveFolder;
//	
//	@PostConstruct //생성자 이후에 실행
//	public void init() throws IOException { 
//		saveFolder =  prodImages.getFile();
//		log.info("상품 이미지 저장 위치: {}",saveFolder.getCanonicalPath());
//	}

	
	private final ProdService service;
	
	@Inject
	private OthersDAO othersdao;
	
	@ModelAttribute("lprodList") 
	public List<LprodVO> lprodList(){
		return othersdao.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO>buyerList(){
		return othersdao.selectBuyerList(null);
		
	}
	
	@ModelAttribute("prod")  
	//기존의 입력값을 가져오기 위해  /prod/prodInsert.do get메소드 일때 'form' 태그사용하기 위해 꼭 써줘야함!
	//컨트롤러 실행 되기전에 실행 됨 @ModelAttribute쓴 이유는 재활용을 위햐
	public ProdVO prod() {
		return new ProdVO();
	}

	
	// get 밑에 실행 되기 전에  위에 3개가 순차적으로 실행 되고 get, post 로 실행 됨

	@RequestMapping("/prod/prodInsert.do") // get 생략
	public String prodForm() {
		//prodForm.jsp에서 prod 쓰기 위해
		
		// ui
		return "prod/prodForm";

	}

	@RequestMapping(value = "/prod/prodInsert.do", method = RequestMethod.POST)
	public String prodInsert(
			@Validated(InsertGroup.class)@ModelAttribute("prod") ProdVO prod,  //실패했을때 기존 입력값 가져오기 위해
			Errors  errors,  //BindingResult는 Errors와 비슷한..(★★★★검증 대상 바로 뒤에 써주기!!)
			Model model
			) throws IOException {
			
//		prod.saveTo(saveFolder);
//
//		Map<String, List<String>> errors = new HashMap<>(); // 에러메세지
//		model.addAttribute("errors", errors);
		// 3. 검증 (대상:ProdVO)
		
		boolean valid = !errors.hasErrors(); // 검증 별도의 유틸리티로

		String viewName = null;

		if (valid) {
			// 검증 통과
			// 4.createProd 등록 처리
			ServiceResult result = service.createProd(prod);
			switch (result) {

			case OK:
				// 2) OK
//							
				viewName = "redirect:/prod/prodView.do?what=" + prod.getProdId();

				break;

			default:
				// 3) FAIL
//							
				model.addAttribute("message", "서버오류, 조금 이따 다시 해보세요");
				viewName = "prod/prodForm";
				break;
			}

		} else {
			// 검증 불통
//						
			viewName = "prod/prodForm";

		}
		return viewName;
	}

}
