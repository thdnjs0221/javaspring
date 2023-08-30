package kr.or.ddit.c35;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.c35") //등록한다 / 
public class C35Servlet extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		System.out.printf("%s 서블릿 초기화 완료\n",this.getClass().getSimpleName());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		//service:doGet doPost 상관없이 서비스 가능
		
		String servletpath=req.getServletPath();
		System.out.printf("=====servlet path: %s======\n",servletpath);
		String realpath= getServletContext().getRealPath(servletpath); //파라미터로 가상의 논리주소를 주면 RealPath 주겠다
		System.out.printf("=====real path: %s======\n",realpath); //톰캣의 의해서 서비스되는 진짜 물리 주소
	
		File c35File = new File(realpath);
		//java 8v
		String templateSource= Files.readAllLines(c35File.toPath())
							.stream()
							.collect(Collectors.joining());
		
		//(serverNow.c35)#now# --> 파싱 필요 
		//정규식 사용하기 '\\w'-영문이나 숫자? 한글자 
		Pattern regex= Pattern.compile("#(\\w+)#");
		Matcher matcher =regex.matcher(templateSource);
		
		StringBuffer content = new StringBuffer();
		
		while(matcher.find()) {
			String name = matcher.group(1); //now 식별자
			
			String repalceText= Optional.ofNullable(req.getAttribute(name))
					.map((v)->v.toString())
					.orElse("");
					
			
			matcher.appendReplacement(content, repalceText); 
		}
		matcher.appendTail(content); //남아있는 소스 출력
		
		//mime text 세팅
		resp.setContentType("text/html; charset=utf-8");
		//출력 스트림! 문자열이니때문에 getWriter /
		try(
				PrintWriter out= resp.getWriter();
		){
			out.println(content);
		}
		
		
	}
	
	

}
