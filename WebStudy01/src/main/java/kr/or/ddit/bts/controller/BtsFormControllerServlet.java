package kr.or.ddit.bts.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.ApplicationBufferHandler;

@WebServlet(value="/bts", loadOnStartup = 1) 
public class BtsFormControllerServlet extends HttpServlet{
	private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//모델 
		Map<String, String[]> btsMap = new LinkedHashMap<>();  //LinkedHashMap 순서가 있는 
		btsMap.put("B001", new String[] {"RM","/WEB-INF/views/bts/rm.jsp"});
		btsMap.put("B002", new String[] {"제이홉","/WEB-INF/views/bts/jhope.jsp"});
		btsMap.put("B003", new String[] {"지민","/WEB-INF/views/bts/jimin.jsp"});
		btsMap.put("B004", new String[] {"진","/WEB-INF/views/bts/jin.jsp"});
		btsMap.put("B005", new String[] {"정국","/WEB-INF/views/bts/jungkuk.jsp"});
		btsMap.put("B006", new String[] {"슈가","/WEB-INF/views/bts/suga.jsp"});
		btsMap.put("B007", new String[] {"뷔","/WEB-INF/views/bts/bui.jsp"});
		application = getServletContext();
		application.setAttribute("btsMap", btsMap); //application는 서버 구동되면 바로 넣어도 된다(init)
		System.out.println("btsMap을 application scope 에 저장했음");
	
	}
	
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	//java8
	String savedMemCode= Optional.ofNullable(req.getCookies())
					.map( (cs)->{
						return Arrays.stream(cs) //쿠키 배열
								.filter((c)->"btsCookie".equals(c.getName()))  //c : 쿠키 하나 꺼내기
								.findFirst()
								.map(fc->fc.getValue())
								.orElse("");   //bts쿠키가 없는 경우 
					}).orElse("");  //쿠키가 아예 하나도 없는 경우 
	
	req.setAttribute("savedMemCode", savedMemCode);
	
	
	//뷰 이동
	String goPage = "/WEB-INF/views/bts/btsForm.jsp";
	if(goPage.startsWith("redirect:")) { //Redirect
		String location = req.getContextPath() + goPage.substring("redirect:".length());
		resp.sendRedirect(location);
		
	}else {//Dispatcher로 이동
		req.getRequestDispatcher(goPage).forward(req, resp); //!
		
	}
	
}
	
}
