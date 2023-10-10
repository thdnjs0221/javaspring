package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;

@WebServlet("/prod/ajax/prodListUI.do")
public class ProdListController_Case2_Servlet extends HttpServlet {
	// spring의 AOP 방법론으로 해결
	private OthersDAO othersdao = new OthersDAOImpl();
	
	private void addAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersdao.selectLprodList());
		req.setAttribute("buyerList", othersdao.selectBuyerList(null));
	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);
		
		// ui
		String viewName = "prod/prodListUI";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}

}
