package kr.or.ddit.servlet04;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/08/serverTime")
public class ServerTimeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept= req.getHeader("accept");  //클라이언트가 요청하는 컨텐츠에 따라..? request header 보기
		String contentType= "text/html; charset=utf-8";
		if(accept.contains("json")) {
			contentType = "application/json; charset=utf-8";
		}
		LocalDateTime now =  LocalDateTime.now();
		
		Object content = null;
		if(accept.contains("json")) {
			//marshalling: native-> common, unMarshalling: common->native
			String ptrn= "{\"now\":\"%s\"}";
			content = String.format(ptrn, now.toString());
		}else {//특정지지 않은 컨텐츠일 경우
			content = now;
		}
		resp.setContentType(contentType);
		resp.setIntHeader("Refresh", 1); //1초마다 자동 갱신 
		
		resp.getWriter().print(content);
	}
}
