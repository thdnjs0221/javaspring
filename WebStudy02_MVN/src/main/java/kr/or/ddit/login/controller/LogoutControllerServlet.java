package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout.do")
public class LogoutControllerServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
	
		// 검증 코드!!!!!!
		HttpSession session = req.getSession(false); // flase ->null 
		if( session==null ||  session.isNew()) { //최초의 요청일때 (정상적인 로그아웃이 아닐때 )
			resp.sendError(400,"로그인 하지도 않았는데!!");
			return;
		}
		
		
		// 1.authid 속성 아이디 지우고
		//session.removeAttribute("authId");
		
		//2.세션 만료 시키고
		session.invalidate();  //invalidate를 사용하면 알아서 authid 속성 아이디 지워짐
		
		//3.클라이언트에게 보여줌 (welcome page redirection으로) 
		String goPage ="redirect:/";
				
		if(goPage.startsWith("redirect:")) { //Redirect
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
			
		}else {//Dispatcher로 이동
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
		
		
		
	}
}
