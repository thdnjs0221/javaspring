package kr.or.ddit.interceptors;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationCheckInterceptor implements HandlerInterceptor {
	
	//로그인 페이지의 경로가 변경할 수 있으니까
	private String loginPage ;
	
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Principal principal = request.getUserPrincipal();
		boolean pass = principal != null;
		
		// 회원은 보호자원(insert만 제외시키기)
		//인증안되어있는데 비정상인 요청으로 올때 
		if(!pass) {
			//로그인 폼으로 이동
			
			response.sendRedirect(request.getContextPath()+loginPage);
			
			
		}
		return pass;
	}

}
