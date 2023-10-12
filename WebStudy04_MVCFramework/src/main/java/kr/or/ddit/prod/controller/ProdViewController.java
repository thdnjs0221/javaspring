package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdViewController  {
	private ProdService service = new ProdServiceImpl();

	@RequestMapping("/prod/prodView.do")  //get메소드 생략되어있는
	public String prodView(@RequestParam("what") String prodId, HttpServletRequest req) {
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		
		// 뷰이동
		return "prod/prodView";
		
		
	}

}
