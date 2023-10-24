package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



//@WebServlet("/login/logout.do")
@Controller
public class LogoutController extends HttpServlet {

	@RequestMapping(value = "/login/logout.do", method = RequestMethod.POST)
	public String logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 검증 코드!!!!!!
		HttpSession session = req.getSession(false); // flase ->null , 여기서 세션을 쓴이유는 내가 검증을 하기 위해서
		if( session==null ||  session.isNew()) { //최초의 요청일때 (정상적인 로그아웃이 아닐때 )
			resp.sendError(400,"로그인 하지도 않았는데!!");
			return null;
		}	
		//2.세션 만료 시키고
		session.invalidate();  //invalidate를 사용하면 알아서 authid 속성 아이디 지워짐
		
		//3.클라이언트에게 보여줌 (welcome page redirection으로) 
		return "redirect:/";  //redirect 새로운 요청 발생 다시 Wrapper를 타게 됨 이제 막 만들어진 세션이라 authMember가 없음
		
		
		
	}
}
