package kr.or.ddit.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocaleServelet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//data-> information-> content	
		
		//data
		Locale clientLocale = req.getLocale();
		Locale serverLocale = Locale.getDefault();
		
		
		//information
		String clientLocaleText = clientLocale.getDisplayName(clientLocale);
		String serverLocaleText = serverLocale.getDisplayName(clientLocale);		
		//최종 소비자가 client이니까 clientLocale로 사용해주기
		
		//content
		StringBuilder content = new StringBuilder();
		content.append("<html>");
		content.append("<body>");
		
		//MessageFormat.format("<h4>client side locale: {0} </h4>", clientLocaleText);  //최종문자열 만들고싶은
		content.append(MessageFormat.format("<h4>client side locale: {0} </h4>", clientLocaleText));
		content.append(MessageFormat.format("<h4>server side locale: {0} </h4>", serverLocaleText));
		
		content.append("</body>");
		content.append("</html>");
		
		String mime ="text/html;charset=utf-8";
		
		resp.setContentType(mime);
		
		//응답으로 content 내보낸다
		//문자열 ->character!! getWriter()써주기
		PrintWriter out = resp.getWriter();
		out.print(content);
		out.close();
	}

}
