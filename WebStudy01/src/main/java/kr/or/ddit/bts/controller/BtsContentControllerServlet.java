package kr.or.ddit.bts.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bts/*")
public class BtsContentControllerServlet extends HttpServlet {
	
	
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		application=getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		String reqUri = req.getRequestURI();
		System.out.printf("reqUri uri : %s\n",reqUri);
		
		int lastIdx = reqUri.lastIndexOf("/");
		String memCode = reqUri.substring(lastIdx + 1);
		System.out.printf("selected member: %s\n",memCode);
	
		
		//검증
		if(memCode==null || memCode.trim().isEmpty()) {
			resp.sendError(400,"멤버 코드가 없음");			
			return;
		}
		
		Map<String, String[]>btsMap = (Map)application.getAttribute("btsMap");
		if(!btsMap.containsKey(memCode)) {
			resp.sendError(404,String.format("%s에 해당하는 멤버는 없음", memCode));
			return;
		}
		
		String[] memRec =  btsMap.get(memCode);
		
		//모델를 btsLayout.jsp로 넘겨주기
		req.setAttribute("member", memRec);  //이름과 주소 다 들어있는
		
		String goPage = "/WEB-INF/views/bts/btsLayout.jsp";  //jsp주소
		//String goPage = memRec[1];  //jsp주소
		
		
		if(goPage.startsWith("redirect:")) { //Redirect
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
			
		}else {//Dispatcher로 이동
			req.getRequestDispatcher(goPage).forward(req, resp); //!
			
		}
		
	}
}
