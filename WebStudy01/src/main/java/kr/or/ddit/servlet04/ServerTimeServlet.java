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
	//!!autoRequest.jsp
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept= req.getHeader("accept");  // 클라이언트가 요청하는 컨텐츠 타입을 확인하기 위해 request header에서 accept 값을 가져온다
		String contentType= "text/html; charset=utf-8";
		if(accept.contains("json")) { // 클라이언트가 JSON 형식의 응답을 원할 경우(특정 문자열 포함)
			contentType = "application/json; charset=utf-8";  // 컨텐츠 타입을 JSON 형식으로 변경
		}
		LocalDateTime now =  LocalDateTime.now(); 
		
		Object content = null;
		if(accept.contains("json")) { 
			//marshalling: native-> common, unMarshalling: common->native
			String ptrn= "{\"now\":\"%s\"}";  //json형식의 문자열   {"now":"%s"}  " 앞에 써주기
			content = String.format(ptrn, now.toString());  // 서버 시간을 문자열로 변환하여 content 변수에 저장
		}else {//특정지지 않은 컨텐츠일 경우
			content = now; // 서버 시간을 그대로 content 변수에 저장
		}
		resp.setContentType(contentType); // 응답 헤더에 컨텐츠 타입을 설정
		resp.setIntHeader("Refresh", 1);  //1초마다 자동 갱신 
		
		resp.getWriter().print(content);  // 응답으로 content 변수의 값을 클라이언트에게 출력
	}
	//--------------------------------------------------------------------------------
}


