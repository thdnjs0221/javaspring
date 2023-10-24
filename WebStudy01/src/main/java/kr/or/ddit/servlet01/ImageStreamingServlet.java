package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageStreamingServlet
 * @WebServlet 어노테이션으로 등록하는 경우, servlet-name은 CoC()에 따라 클래스의 심플네임이 사용됨
 * CoC (Convention Over Configuration)  : 명시적인 설정이 없는 경우 일반적이 관행이 코드에 반영됨.
 * 
 * 
 */
@WebServlet(loadOnStartup = 2,value= "/image.do",initParams = {@WebInitParam(name = "imageFolder", value="D:/01.medias/images")}) //가상의 논리주소(클라이언트 사용 가능)
public class ImageStreamingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private File imageFolder;
	private ServletContext application; //싱글톤  현재 컨텍스트 정보 있음
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);//super를 쓰는 이유는 ServletContext 객체가 생성되기 때문에!
		
		application= getServletContext();
		System.out.printf("application hash code: %d \n", application.hashCode());
		
		//String value= config.getInitParameter("imageFolder");
		
		String value=application.getInitParameter("imageFolder");
		
		this.imageFolder= new File (value);
		
		System.out.println(MessageFormat.format("{0} 서블릿 초기화 완료", this.getClass().getSimpleName()));
	}
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageName=request.getParameter("image");
		//이미지는 바이트단위 사용
		
		if(imageName==null || imageName.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		
		File imageFile=new File (imageFolder,imageName);
		
		if(!imageFile.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,MessageFormat.format("{0} 이미지 파일이 없음", imageName));
			return;
		}
		
		//쿠키생성
		Cookie imgcookie = new Cookie("imgCookie",imageName); 
		imgcookie.setMaxAge(60*60*24*7);
		imgcookie.setPath(request.getContextPath());
		response.addCookie(imgcookie);
			
		//톰캣과 대화할때getServletContext  사용 MIME type 
		ServletContext application = getServletContext();
		String mime = application.getMimeType(imageFile.getName());
		
		response.setContentType(mime);
		response.setContentLengthLong(imageFile.length());
		
		FileInputStream fis = new FileInputStream(imageFile); //물리주소 사용해서 객체 생성	
		//response통해서 출력스트림 만들기!! 스트림 카피
		ServletOutputStream sos= response.getOutputStream();
		
		//종료조건 -1 /EOF(End of File) 문자를 만날때까지 반복 작업
		byte[] buffer = new byte[1024]; //전송 효율을 높이기 위해 buffer 사용
		int length = -1;
		while((length=fis.read(buffer)) != -1) {
			sos.write(buffer,0,length); //buffer에 데이터가 존재
		}
		
		sos.flush();
		fis.close();
		sos.close();
		
		//http://localhost/WebStudy01/image.do
		//http://localhost/WebStudy01/01/sample.html
	}

}
