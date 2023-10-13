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
public class ProdUpdateController  {
	
	@Value("/resources/prodImages")
	private String pridImagesUrl;
	
	@Value("/resources/prodImages")
	private Resource prodImages;
	
	private File saveFolder;
	
	@PostConstruct  //생성자 이후에 실행
	public void init() throws IOException {
		saveFolder =  prodImages.getFile();
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
	
	@RequestMapping("/prod/prodUpdate.do")
	public String Prod(Model model
			,@RequestParam(value = "what", required = true) String prodId ){
	

		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);

		// ui
		return "prod/prodEdit";
		
	}

	@RequestMapping(value ="/prod/prodUpdate.do" , method = RequestMethod.POST )
	public String prodUpdate(@ModelAttribute("prod") ProdVO prod,
			MultipartFile prodImage,
			Model model, HttpServletResponse resp) throws IOException{
		
	
			// 업로드된 이미지가 있는지 검증!
			if (!prodImage.isEmpty()) {

				String filename = UUID.randomUUID().toString();

				// String filename = prodImage.getOriginalFilename();
				File saveFile = new File(saveFolder, filename);
				// 상품이미지의 2진 데이터 저장
				prodImage.transferTo(saveFile); // 이미지 저장
				prod.setProdImg(filename); // 메타데이터는 디비에 저장
			}
		
			Map<String, List<String>> errors = new HashMap<>(); // 에러메세지
			model.addAttribute("errors", errors);
			// 3. 검증
			boolean valid = ValidationUtils.validate(prod, errors, UpdateGroup.class); // 검증 별도의 유틸리티로
			String viewName = null;

			if (valid) {
				// 검증 통과
				ServiceResult result = service.modifyProd(prod);
				switch (result) {
				case OK:
					// 2) OK
					viewName = "redirect:/prod/prodView.do?what"+prod.getProdId();

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

