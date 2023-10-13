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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.ValidationUtils;
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
	@Value("/resources/prodImages")
	private String pridImagesUrl;
	
	@Value("/resources/prodImages")
	private Resource prodImages;
	
	private File saveFolder;
	
	@PostConstruct
	public void init() throws IOException { 
		saveFolder =  prodImages.getFile();
		log.info("상품 이미지 저장 위치: {}",saveFolder.getCanonicalPath());
	}

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


	@RequestMapping("/prod/prodInsert.do") // get 생략
	public String prodForm() {
		// ui

		return "prod/prodForm";

	}

	@RequestMapping(value = "/prod/prodInsert.do", method = RequestMethod.POST)
	public String prodInsert(MultipartFile prodImage,Model model,
			@ModelAttribute("prod") ProdVO prod) throws IOException {
	
			// 업로드된 이미지가 있는지 검증!
			if (!prodImage.isEmpty()) {
			

				//String realPath = req.getServletContext().getRealPath(pridImagesUrl);
				
				
				//File saveFolder = new File(realPath);

				String filename = UUID.randomUUID().toString();

				// String filename = prodImage.getOriginalFilename();
				File saveFile = new File(saveFolder, filename);
				// 상품이미지의 2진 데이터 저장
				prodImage.transferTo(saveFile); // 이미지 저장
				prod.setProdImg(filename); // 메타데이터는 디비에 저장
			}

		

		Map<String, List<String>> errors = new HashMap<>(); // 에러메세지
		model.addAttribute("errors", errors);

		// 3. 검증 (대상:ProdVO)
		boolean valid = ValidationUtils.validate(prod, errors, InsertGroup.class); // 검증 별도의 유틸리티로

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
