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
		HttpSession session = req.getSession(false);  
		//이것은 새로운 세션을 만들지 말고, 현재 요청과 관련된 세션이 이미 존재하는 경우 해당 세션을 반환하거나, 
		//세션이 없는 경우 null을 반환하라는 의미
		
		
		// 1.authid 속성 아이디 지우고
		//session.removeAttribute("authId");
		
		//2.세션 만료 시키고
		session.invalidate();  //invalidate를 사용하면 알아서 authid 속성 아이디 지워짐
		
		//3.클라이언트에게 보여줌 (welcome page redirection으로) 
		String goPage ="redirect:/";
				
		if(goPage.startsWith("redirect:")) { //Redirect
			String location = req.getContextPath() + goPage.substring("redirect:".length()); 
			//redirect:" 부분을 제외한 나머지 부분을 가져옴. 이 부분은 실제로 리다이렉션할 페이지의 경로
			resp.sendRedirect(location);
			
		}else {//Dispatcher로 이동
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
		
		
		
	}
}
