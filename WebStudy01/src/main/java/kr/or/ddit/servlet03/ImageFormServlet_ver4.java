package kr.or.ddit.servlet03;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
자바8버전!
0829 스프링 과제

여백활용해서 이미지 출력하기 
이벤트- sumbit 

이미지가 아닌 파일 제외하고 옵션 16개 만들기
 imageFolder.list(); -> 필터네임 람다문법으로 활용하기!!!!

 * */
/**
 *Model 2  아키텍처 기반의 책임의 분리 구조.
 *
 *Model 1: request와 response가 하나의 객체(서블릿,JSP)에 의해 처리되는 구조.
 *Model 2: request(servlet, controller)처리 객체와 response(servlet/jsp, view) 처리 객체가 분리된 구조.
 *			Model: content의 원형이 되는 information. -> MVC pattern
 *
 *Controller의 역할
 *1. 요청 접수 
 *2. 요청을 검증/분석
 *3. Model(informaion) 생성
 *4. scope 를 통해 model 공유
 *5. view 선택
 *6. view 로 이동
 *
 *View 의 역할 
 *model로 부터 content를 생성해 응답으로 전송.
 */

//controller
@WebServlet("/ver4/imageForm.do")
public class ImageFormServlet_ver4 extends HttpServlet {
	
	private File imageFolder;
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);//super를 쓰는 이유는 ServletContext 객체가 생성되기 때문에!
		application = getServletContext();
		String value=application.getInitParameter("imageFolder");	
		imageFolder = new File(value);
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//람다식: 코드를 줄이는 //람다 한 문장일 경우 바디,return 생략가능!
		String[] imageFileNames= imageFolder.list(((d,n)-> Optional.ofNullable(application.getMimeType(n)) //nullpoint 피할수있음
															.orElse("").startsWith("image/")));
		
		String options= Stream.of(imageFileNames)
				.map((in)-> MessageFormat.format("<option>{0}</option>", in))
				.collect(Collectors.joining("\n"));  
		
		
		//Stream 이용하기
		//StringBuilder 대신 사용하기 collect..
		//String options= Stream.of(imageFileNames)
		//	.map((in)-> MessageFormat.format("<option>{0}</option>", in))
		//	.collect(Collectors.joining("\n"));  //option들을 하나로 모으는 작업
		
	
		
		String Cpath=req.getContextPath();
		
		req.setAttribute("cPath", Cpath);
		req.setAttribute("options", options);
		
		String viewName= "/WEB-INF/views/04/imageForm.jsp";
		
		req.getRequestDispatcher(viewName).forward(req, resp);
		
		
		
		
			
			}
		}
		

		
	
