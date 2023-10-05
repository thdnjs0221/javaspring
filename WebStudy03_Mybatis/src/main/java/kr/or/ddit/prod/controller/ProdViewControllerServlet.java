package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodView.do")
public class ProdViewControllerServlet extends HttpServlet {
	private ProdService service = new ProdServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String prodId = req.getParameter("what");
		
		
		// 필수 파라미터 누락시 400 에러
		if(StringUtils.isAllBlank(prodId)) {
			resp.sendError(400,"필수파라미터 누락");
			return;
		}

		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);

		// 뷰이동
		String viewName = "prod/prodView";
		new ViewResolverComposite().resolveView(viewName, req, resp);

	}

}
