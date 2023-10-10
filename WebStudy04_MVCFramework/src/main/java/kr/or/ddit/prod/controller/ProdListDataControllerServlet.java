package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.paging.BootstrapPaginationRender;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;

@WebServlet("/prod/ajax/prodListData.do")
public class ProdListDataControllerServlet extends HttpServlet {
	private ProdService service = new ProdServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ProdVO detailCondition = new ProdVO();
		PopulateUtils.populate(detailCondition, req.getParameterMap());

		String pageParam = req.getParameter("page");
		int currentPage = 1;
		if (StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}

		PaginationInfo<ProdVO> paging = new PaginationInfo<>(3, 2);
		// paging.setSimpleCondition(simpleCondition); //키워드 검색 조건
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition); // 검색 상세조건
		// 검색 조건, 페이징 완성

		service.retrieveProdList(paging);

		paging.setRenderer(new BootstrapPaginationRender());

		req.setAttribute("paging", paging);

		// 뷰이동
		String viewName = "jsonView";
		new ViewResolverComposite().resolveView(viewName, req, resp);

	}
}
