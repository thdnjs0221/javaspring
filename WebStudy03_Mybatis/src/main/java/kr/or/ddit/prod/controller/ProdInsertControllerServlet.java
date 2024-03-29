package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.filter.utils.StandardMultipartHttpServletRequest;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
@MultipartConfig
public class ProdInsertControllerServlet extends HttpServlet {
	private String pridImagesUrl = "/resources/prodImages";

	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersdao = new OthersDAOImpl();

	private void addRequestAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersdao.selectLprodList());
		req.setAttribute("buyerList", othersdao.selectBuyerList(null));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ui

		addRequestAttribute(req);

		String viewName = "prod/prodForm";
		new ViewResolverComposite().resolveView(viewName, req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addRequestAttribute(req);
		// 1.디코딩(필터)

		// 2.파라미터 확보 ProdVO
		ProdVO prod = new ProdVO();

		req.setAttribute("prod", prod);
		Map<String, String[]> paramterMap = req.getParameterMap();

		// 중복코드 해결하기 위한
		PopulateUtils.populate(prod, paramterMap);

		//multipart 처리!  원본인지 아닌지 판단
		if (req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile prodImage = ((StandardMultipartHttpServletRequest) req).getFile("prodImage"); 
			//(wrapper) .getFile로 업로드된 이미지 꺼내기
			
			// 업로드된 이미지가 있는지 검증!
			if (!prodImage.isEmpty()) {

				String realPath = req.getServletContext().getRealPath(pridImagesUrl);
				File saveFolder = new File(realPath);
				
				String filename = UUID.randomUUID().toString();
				
				//String filename = prodImage.getOriginalFilename();
				File saveFile = new File(saveFolder, filename);
				//상품이미지의 2진 데이터 저장
				prodImage.transferTo(saveFile); // 이미지 저장
				prod.setProdImg(filename);  //메타데이터는 디비에 저장
			}

		}

		Map<String, List<String>> errors = new HashMap<>(); // 에러메세지
		req.setAttribute("errors", errors);

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
				req.setAttribute("message", "서버오류, 조금 이따 다시 해보세요");
				viewName = "prod/prodForm";
				break;
			}

		} else {
			// 검증 불통
//						
			viewName = "prod/prodForm";

		}
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}

}
