package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
자바8버전 이전
0829 스프링 과제

여백활용해서 이미지 출력하기 
이벤트- sumbit 

이미지가 아닌 파일 제외하고 옵션 16개 만들기
 imageFolder.list(); -> 필터네임 람다문법으로 활용하기!!!!

 * */
@WebServlet("/ver1/imageForm.do")
public class ImageFormServlet_ver1 extends HttpServlet {
	
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
		String[] imageFileNames= imageFolder.list(new FilenameFilter() {
			
			
			@Override
			//이미지가 아닌 파일 거르기(FilenameFilter)
			public boolean accept(File dir, String name) {
				String mime= application.getMimeType(name); //톰캣과 커뮤니케이션 필요
				return mime!=null &&  mime.startsWith("image/"); //mime!=null(hwp는 mime db에 없으므로)
			}
		});
		StringBuilder options = new StringBuilder();

		for(String imageName : imageFileNames) {
			options.append(
					
			MessageFormat.format("<option>{0}</option>", imageName)
			
					);
		}
			StringBuilder content = new StringBuilder();
				content.append("<html>");
				content.append("<body>");
				content.append(String.format("<form onsubmit='submitHandler(event);' action='%s/image.do'>", req.getContextPath()));
				content.append("<select name='image' onchange='this.form.requestsubmit();' >");
				content.append(options);
				content.append("</select>");
				content.append("<input type='submit' value='전송'>");
				content.append("</form>");
				content.append("<script>                           ");
				content.append("	function submitHandler(event) {");
				content.append("		event.preventDefault();    ");
				content.append("<img >  ");
				
				content.append("		                           ");
				content.append("		                           ");
				content.append("	}                              ");
				content.append("                                   ");
				content.append("                                   ");
				content.append("</script>                          ");
				
				
				
				content.append("</body>");
				content.append("</html>");
				
				
				resp.setContentType("text/html; charset=utf-8"); //출력스트림 개방하기 전에  mime써주기
				
				PrintWriter out=null;
				try {
					out= resp.getWriter(); 
					out.println(content);
				}finally {
					if(out!=null) 
						out.close();
				}
			}
		}
		
		//imageFolder.list(); //
		
		
//		<html>
//		<body>
//		<form action='/WebStudy01/image.do'>
//		<select>
//		<option>cat1.jpg</option>
//		</select>
//		<input type='submit' value='전송'>
//		</form>
//		</body>
//		</html>
		
		
	
