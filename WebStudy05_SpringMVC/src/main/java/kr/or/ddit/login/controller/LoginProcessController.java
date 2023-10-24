package kr.or.ddit.login.controller;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.login.service.AthenticateServiceImpl;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/Login/loginProcess.do")
@Controller
public class LoginProcessController {

	@Inject
	private AuthenticateService service;



	@RequestMapping(value = "/Login/loginProcess.do", method = RequestMethod.POST)
	public String Login(
			@RequestParam("memId") String memId
			,@RequestParam("memPass")String memPass
			,HttpSession session) throws IOException {

//		String memId = req.getParameter("memId");
//		String memPass = req.getParameter("memPass");
//		String idSave = req.getParameter("idSave");

		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(memPass);
		
		String viewName = null;
		ServiceResult result = service.authenticate(inputData);

//         5-1. 인증 여부 판단
		
			switch (result) {
			case OK:
				// 인증 성공
				// 6-1. 인증 성공
//            - 웰컴페이지 이동
				viewName = "redirect:/";
				session.setAttribute("authMember", inputData);

				break;
			case INVALIDPASSWORD:
				//
				// 6-2. 인증 실패
//           - loginForm으로 이동
				viewName = "redirect:/login/loginForm.jsp";
				session.setAttribute("message", "비밀번호 오류");

				break;

			default:
				// 그런 사람 없음
				viewName = "redirect:/login/loginForm.jsp";
				session.setAttribute("message", "가입 안했거니 이미 탈퇴한 회원");
				break;
			}
		return viewName;
	}
}

/*
 * 쿠키 사용X // 1. 아이디나 비밀번호가 누락된 경우, Bad Request 전송 // 2. 인증 성공 : 웰컴 페이지로 이동 ->
 * "a001" 님 로그인 성공이라는 메시지를 웰컴페이지에 alert창으로 출력. // 3. 인증 실패 : 로그인 폼으로 이동 ->
 * "아이디나 비밀번호 오류" 라는 메시지를 로그인 폼에서 alert 창으로 출력.
 * 
 * // 1. request body 영역에 대한 디코딩 설정. req.setCharacterEncoding("UTF-8");
 * 
 * // 2. 파라미터 획득 String memId = req.getParameter("memId"); String memPass =
 * req.getParameter("memPass");
 * 
 * // 3. 요청 검증 boolean valid = validate(memId, memPass); HttpSession session =
 * req.getSession(); int sc = 200; String goPage = null; if(valid) { // 4-1. 검증
 * 통과 // 5-1. 인증 여부 판단 boolean authenticated = authenticated(memId, memPass);
 * if(authenticated) { // 6-1. 인증 성공 // - 웰컴페이지 이동 goPage = "redirect:/";
 * session.setAttribute("authId", memId); }else { // 6-2. 인증 실패 // - loginForm으로
 * 이동 goPage = "redirect:/login/loginForm.jsp"; session.setAttribute("message",
 * "아이디나 비밀번호 오류"); } // if(authenticated) end
 * 
 * }else { // 4-2. 검증 불통과 // 5-2. Bad request 전송 sc =
 * HttpServletResponse.SC_BAD_REQUEST; } // if(valid) end
 * 
 * if(sc == 200) { // goPage로 이동 if(goPage.startsWith("redirect:")) {
 * 
 * String location = req.getContextPath() +
 * goPage.substring("redirect:".length()); resp.sendRedirect(location); }else {
 * req.getRequestDispatcher(goPage).forward(req, resp); } }else {
 * resp.sendError(sc); }
 */

/*
 * 내코드 String memId = req.getParameter("memId"); String memPass =
 * req.getParameter("memPass");
 * 
 * if(memId.equals("")||memPass.equals("")) { int sc
 * =HttpServletResponse.SC_BAD_REQUEST; resp.sendError(sc); return; }
 * 
 * if(authenticated(memId, memPass)) { req.setAttribute("memId", memId);
 * req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
 * }else { String message = "아이디나 비밀번호 오류"; req.setAttribute("message",
 * message); req.getRequestDispatcher("/login/loginForm.jsp").forward(req,
 * resp); }
 */
