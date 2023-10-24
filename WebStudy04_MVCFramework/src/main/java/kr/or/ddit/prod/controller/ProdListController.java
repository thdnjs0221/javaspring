package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.paging.BootstrapPaginationRender;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class ProdListController {
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersdao = new OthersDAOImpl();

	private void addRequestAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersdao.selectLprodList());
		req.setAttribute("buyerList", othersdao.selectBuyerList(null));
	}

	@RequestMapping( value = "/prod/prodList.do", method = RequestMethod.GET)
	public String prodList(@ModelAttribute("detailCondition") ProdVO detailCondition
			, @RequestParam(value = "page", required = false, defaultValue = "1") int currentPage							
			, HttpServletRequest req)  {
		addRequestAttribute(req);
		
		//@ModelAttribute이 해준 일 : 
		//ProdVO detailCondition = new ProdVO();
		//PopulateUtils.populate(detailCondition, req.getParameterMap());
		//	req.setAttribute("detailCondition", detailCondition);
		
		//@RequestParam이 해준 일 :
//		String pageParam = req.getParameter("page");
//		int currentPage = 1;
//		if (StringUtils.isNumeric(pageParam)) {
//			currentPage = Integer.parseInt(pageParam);
//		}

		listData(detailCondition, currentPage, req);

		// 뷰이동
		return "prod/prodList";

	}

	@RequestMapping("/prod/ajax/prodListUI.do")
	public String listUI(HttpServletRequest req) {
		addRequestAttribute(req);
		// ui
		return "prod/prodListUI";

	}

	@RequestMapping("/prod/ajax/prodListData.do")
	public String listData(
			@ModelAttribute("detailCondition") ProdVO detailCondition 
			,@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage 
			,HttpServletRequest req
	) {
		PaginationInfo<ProdVO> paging = new PaginationInfo<>(3, 2);
		// paging.setSimpleCondition(simpleCondition); //키워드 검색 조건
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition); // 검색 상세조건
		// 검색 조건, 페이징 완성

		service.retrieveProdList(paging);

		paging.setRenderer(new BootstrapPaginationRender());

		req.setAttribute("paging", paging);
		// 뷰이동
		return "jsonView";

	}
}
