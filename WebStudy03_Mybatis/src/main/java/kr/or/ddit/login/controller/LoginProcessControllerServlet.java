package kr.or.ddit.login.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.login.service.AthenticateServiceImpl;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/Login/loginProcess.do")
public class LoginProcessControllerServlet extends HttpServlet {
   
   private AuthenticateService service = new AthenticateServiceImpl();
   
   private boolean validate(MemberVO member) {
      boolean valid = true;
      valid &= StringUtils.isNotBlank(member.getMemId());
      valid &= StringUtils.isNotBlank(member.getMemPass());
      return valid;
   }
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      req.setCharacterEncoding("utf-8");

      String memId = req.getParameter("memId");
      String memPass = req.getParameter("memPass");
      String idSave = req.getParameter("idSave");
      
      MemberVO inputData = new MemberVO();
      inputData.setMemId(memId);
      inputData.setMemPass(memPass);
      
//      3. 요청 검증
      boolean valid = validate(inputData);
      
      Cookie idCookie = null;
   
      
      int sc = 200;
      String viewName = null;
      if(valid) {
//      4-1. 검증 통과
//         5-1. 인증 여부 판단
         ServiceResult result = service.authenticate(inputData);
         HttpSession session = req.getSession();
         switch (result) {
		case OK:
			//인증 성공
		    //  6-1. 인증 성공
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
			//그런 사람 없음
			 viewName = "redirect:/login/loginForm.jsp";
		        session.setAttribute("message", "가입 안했거니 이미 탈퇴한 회원");
			break;
		}

         
      }else {   
//      4-2. 검증 불통과
//         5-2. Bad request 전송
         sc = HttpServletResponse.SC_BAD_REQUEST;
      } // if(valid) end
      
      if(sc == 200) {
         // goPage로 이동
  		//뷰이동
  		
  		new ViewResolverComposite().resolveView(viewName, req, resp);
      }else {
         resp.sendError(sc);
      }
      
   /* 쿠키 사용X
      // 1. 아이디나 비밀번호가 누락된 경우, Bad Request 전송
      // 2. 인증 성공 : 웰컴 페이지로 이동 -> "a001" 님 로그인 성공이라는 메시지를 웰컴페이지에 alert창으로 출력.
      // 3. 인증 실패 : 로그인 폼으로 이동 -> "아이디나 비밀번호 오류" 라는 메시지를 로그인 폼에서 alert 창으로 출력.

//      1. request body 영역에 대한 디코딩 설정.
      req.setCharacterEncoding("UTF-8");

//      2. 파라미터 획득
      String memId = req.getParameter("memId");
      String memPass = req.getParameter("memPass");
      
//      3. 요청 검증
      boolean valid = validate(memId, memPass);
      HttpSession session = req.getSession();
      int sc = 200;
      String goPage = null;
      if(valid) {
//      4-1. 검증 통과
//         5-1. 인증 여부 판단
         boolean authenticated = authenticated(memId, memPass);
         if(authenticated) {
//            6-1. 인증 성공
//               - 웰컴페이지 이동
            goPage = "redirect:/";
            session.setAttribute("authId", memId);
         }else {
//            6-2. 인증 실패
//               - loginForm으로 이동
            goPage = "redirect:/login/loginForm.jsp";
            session.setAttribute("message", "아이디나 비밀번호 오류");
         } // if(authenticated) end
         
      }else {   
//      4-2. 검증 불통과
//         5-2. Bad request 전송
         sc = HttpServletResponse.SC_BAD_REQUEST;
      } // if(valid) end
      
      if(sc == 200) {
         // goPage로 이동
         if(goPage.startsWith("redirect:")) {
            
            String location = req.getContextPath() + goPage.substring("redirect:".length());
            resp.sendRedirect(location);
         }else {
            req.getRequestDispatcher(goPage).forward(req, resp);
         }
      }else {
         resp.sendError(sc);
      }
      */
      
      
      
      /* 내코드
      String memId = req.getParameter("memId");
      String memPass = req.getParameter("memPass");
      
      if(memId.equals("")||memPass.equals("")) {
         int sc =HttpServletResponse.SC_BAD_REQUEST;
         resp.sendError(sc);
         return;
      }
      
      if(authenticated(memId, memPass)) {
         req.setAttribute("memId", memId);
         req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
      }else {
         String message = "아이디나 비밀번호 오류";
         req.setAttribute("message", message);
         req.getRequestDispatcher("/login/loginForm.jsp").forward(req, resp);
      }
      */
   }
}