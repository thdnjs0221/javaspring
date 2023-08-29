package kr.or.ddit.servlet01;

import java.io.File;
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

@WebServlet("/imageForm.do")
public class ImageFormServlet extends HttpServlet {
	
	private File imageFolder;
	private ServletContext application;
	//ServletContext는 웹 애플리케이션의 컨텍스트 정보를 담고 있으며, 
	//서블릿들 간의 정보 공유나 애플리케이션 수준의 설정 등을 처리하는 데 사용됩니다.
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);//super를 쓰는 이유는 ServletContext 객체가 생성되기 때문에!
		application = getServletContext();
		String value=application.getInitParameter("imageFolder");	
		imageFolder = new File(value);
		//web.xml 파일이나 어노테이션을 통해 설정한 초기화 매개변수를 읽어오는 것입니다.
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] imageFileNames= imageFolder.list();
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
				//requestsubmit() 폼 제출하는 메서드! 서버로 전송되어 해당 URL로 요청이 보내짐
				content.append(options);
				content.append("</select>");
				content.append("<input type='submit' value='전송'>");
				content.append("</form>");
				content.append("<script>                           ");
				content.append("	function submitHandler(event) {"); //event 매개변수를 통해 이벤트 정의
				content.append("		event.preventDefault();    ");  //기본 이벤트 동작을 취소(여기서는 폼 제출 동작 막음)
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
				PrintWriter out= resp.getWriter(); 
				out.println(content);
				out.close();
				
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
		
		
	
