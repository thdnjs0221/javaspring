package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.filter.utils.StandardMultipartHttpServletRequest;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

//@MultipartConfig
//@WebServlet("/prod/prodUpdate.do")
@Controller
public class ProdUpdateController  {
	private String pridImagesUrl = "/resources/prodImages";

	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersdao = new OthersDAOImpl();

	private void addRequestAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersdao.selectLprodList());
		req.setAttribute("buyerList", othersdao.selectBuyerList(null));
	}

	@RequestMapping("/prod/prodUpdate.do")
	public String Prod(HttpServletRequest req
			,@RequestParam(value = "what", required = true) String prodId ){
		addRequestAttribute(req);

		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);

		// ui
		return "prod/prodEdit";
		
	}

	@RequestMapping(value ="/prod/prodUpdate.do" , method = RequestMethod.POST )
	public String prodUpdate(@ModelAttribute("prod") ProdVO prod, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		addRequestAttribute(req);
		
		//mutipart
		if (req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile prodImage = ((StandardMultipartHttpServletRequest) req).getFile("prodImage");

			// 업로드된 이미지가 있는지 검증!
			if (!prodImage.isEmpty()) {

				String realPath = req.getServletContext().getRealPath(pridImagesUrl);
				File saveFolder = new File(realPath);

				String filename = UUID.randomUUID().toString();

				// String filename = prodImage.getOriginalFilename();
				File saveFile = new File(saveFolder, filename);
				// 상품이미지의 2진 데이터 저장
				prodImage.transferTo(saveFile); // 이미지 저장
				prod.setProdImg(filename); // 메타데이터는 디비에 저장
			}
		}
			Map<String, List<String>> errors = new HashMap<>(); // 에러메세지
			req.setAttribute("errors", errors);
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
					req.setAttribute("message", "서버오류, 조금 이따 다시 해보세요");
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

