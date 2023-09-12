package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login/loginProcess.do")
public class LoginProcessControllerServlet extends HttpServlet {
	private boolean validate(String memId, String memPass) {
		boolean valid = true;
		valid &= memId != null && !memId.trim().isEmpty();  
		valid &= memPass != null && !memPass.trim().isEmpty();//&= 다 통과해야지 true	
		return valid;
		
	}
	private boolean authenticated(String memId, String memPass) {
		return memId.equals(memPass);  //로그인성공
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 아이디 패스워드 누락된 경우,  Bad Request 상태코드:400 ! 파라미터 받기
		//2. 인증 성공 - 웰컴페이지로 이동  -> 이후에 성공한 사람의 "a001"님 로그인 성공이라는 메세지를 웰컴페이지에 alert창으로 출력
		//3. 인증 실패 - 로그인 폼으로 이동 -> "아이디나 비밀번호 오류" 라는 메세지를 로그인폼에서 alert창으로 출력 
		
//		1. request body 영역에 대한 디코딩 설정
			req.setCharacterEncoding("utf-8");
//		2. 파라미터 획득
			String memId = req.getParameter("memId");
			String memPass = req.getParameter("memPass");
//		3. 요청 검증
			boolean valid= validate(memId, memPass);
			int sc = 200;
			String goPage = null;
			if(valid) {
//				4-1 검증 통과
//				5-1 인증 여부 판단
			boolean authenticated=authenticated(memId, memPass);
			HttpSession session= req.getSession();  //session 은 클라이언트
			
			if(authenticated) {
//				6-1 인증 성공
//				   -웰컴페이지로 이동
				goPage ="redirect:/"; //redirect는 req.setAttribute() 사용할수 없음! session사용하기
				session.setAttribute("authId", memId);
			}else {
//				6-2 인증 실패
//		 			- 로그인 폼으로 이동
				goPage = "redirect:/login/loginForm.jsp";  //Dispatcher
				session.setAttribute("message", "아이디나 비밀번호 오류");
			}//if(authenticated) end
			
		}else {
//				4-2 검증 불통과
//					5-2 Bad request 전송
			sc =HttpServletResponse.SC_BAD_REQUEST;
		}//if(valid) end
			
		if(sc==200) {
			// goPage(view)로 이동
			if(goPage.startsWith("redirect:")) { //Redirect
				String location = req.getContextPath() + goPage.substring("redirect:".length());
				resp.sendRedirect(location);
				
			}else {//Dispatcher로 이동
				req.getRequestDispatcher(goPage).forward(req, resp);
			}
			
		}else {
			
			resp.sendError(sc);
		}
	
	}

}
