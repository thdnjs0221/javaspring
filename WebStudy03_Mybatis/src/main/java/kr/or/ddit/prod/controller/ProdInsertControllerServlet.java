package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.enumpkg.ServiceResult;
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
public class ProdInsertControllerServlet extends HttpServlet {

	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersdao = new OthersDAOImpl();

	private void addRequestAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersdao.selectLprodList());
		req.setAttribute("buyerList", othersdao.selectBuyerList(null));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ui
		req.setCharacterEncoding("utf-8");
		addRequestAttribute(req);

		String viewName = "prod/prodForm";
		new ViewResolverComposite().resolveView(viewName, req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addRequestAttribute(req);
		//1.디코딩
		req.setCharacterEncoding("utf-8");

		
		//2.파라미터 확보
		ProdVO prod = new ProdVO();

		req.setAttribute("prod", prod);
		Map<String, String[]> paramterMap = req.getParameterMap();

		// 중복코드 해결하기 위한
		PopulateUtils.populate(prod, paramterMap);

		Map<String, List<String>> errors = new HashMap<>(); // 에러메세지
		req.setAttribute("errors", errors);
		// 3. 검증 (대상:MemberVO)
		boolean valid = ValidationUtils.validate(prod, errors, InsertGroup.class); // 검증 별도의 유틸리티로

		String viewName = null;

		if (valid) {
			// 검증 통과
			// 4.createMember 등록 처리
			ServiceResult result = service.createProd(prod);
			switch (result) {

			case OK:
				// 2) OK
//							welcome page로 이동 (redirect)
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();

				break;

			default:
				// 3) FAIL
//							memberInesrt으로 이동(기존 입력 데이터, 메시지, dispatch)		
				req.setAttribute("message", "서버오류, 조금 이따 다시 해보세요");
				viewName = "prod/prodForm";
				break;
			}

		} else {
			// 검증 불통
//					memberInesrt으로 이동(기존 입력 데이터, 검증의 결과 메시지들..., dispatch)	
			viewName = "prod/prodForm";

		}
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}

}
