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


public class ProdViewController  {
	private ProdService service = new ProdServiceImpl();

	
	public String prodView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String prodId = req.getParameter("what");
		
		
		// 필수 파라미터 누락시 400 에러
		if(StringUtils.isAllBlank(prodId)) {
			resp.sendError(400,"필수파라미터 누락");
			return null;
		}

		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		
		

		// 뷰이동
		return "prod/prodView";
		
		
	}

}
