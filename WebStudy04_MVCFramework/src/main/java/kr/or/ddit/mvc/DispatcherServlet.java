package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.controller.ProdListController;
import kr.or.ddit.prod.controller.ProdListController_Case2;
import kr.or.ddit.prod.controller.ProdViewController;


/**
 * 
 * Front Controller Patter 구조의 front controller command handler에 대해 사전 작업과 사후
 * 작업을 처리할 수 있는 front
 * 
 */
public class DispatcherServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		uri = uri.split(";")[0];

		
		
		String viewName;
		if ("/prod/prodList.do".equals(uri)) {
			// 상품 목록 요청
			ProdListController controller = new ProdListController();
			viewName = controller.proList(req, resp); // 사전작업
		}else if("/prod/prodView.do".equals(uri)) {
			ProdViewController controller  = new ProdViewController();
			viewName = controller.prodView(req, resp);
			
		}else if("/prod/ajax/prodListUI.do".equals(uri)) {
			ProdListController_Case2 controller  = new ProdListController_Case2();
			viewName = controller.listUI(req, resp);
			
		}else {
			//요청을 처리할 커맨드 핸들러가 없는 경우 
			resp.sendError(400,"처리할 수 없는 요청이다");
			return;
		}
		if(viewName!=null) {
			new ViewResolverComposite().resolveView(viewName, req, resp);
			
		}else {
			//응답데이터가 결정이 되었는가?
			if(!resp.isCommitted()) {
				resp.sendError(500,"logical view name 이 결정되지 않았음");
			}
			
		}

	}
}
