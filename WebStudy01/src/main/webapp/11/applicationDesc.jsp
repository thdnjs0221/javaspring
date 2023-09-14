<%@page import="java.io.InputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/sessionDesc.jsp</title>
</head>
<body>
<h4> ServletContext</h4>
<pre>
: 서버와 현재 컨텍스트에 대한 정보를 가진 싱글턴 객체.

1. 서버의 정보 획득
	<%=application.getServerInfo() %>
	<%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
	<%= application.getMimeType("sample.jpg") %>
	<%= application.getMimeType("sample.hwp") %>
2. context parameter 획득 : web.xml 을 통해 등록된 파라미터 획득

	<%
		Enumeration<String>names = application.getInitParameterNames();
		while(names.hasMoreElements()){
			String paramName = names.nextElement();
			String paramVaule= application.getInitParameter(paramName);
			out.println(
					String.format("%s : %s",paramName,paramVaule)
					);	
			}
		
		%>
3. log 기록 - 서버의 콘솔에 출력 서버를 위한 데이터
	<%application.log("샘플 로그 메세지"); %>
	
	
★★★4. 웹 리소스(url) 획득★★★
<%
String url = "/resources/images/cat4.png"; //논리주소
//중간 과정이 필요(톰캣의 정보를 통해서 realPath 가져옴) (웹자원, 클래스패스 자원은 중간 과정이 필요 webapp/07/resourceIdentify.jsp 참고 )
String realPath = application.getRealPath(url);  //물리 주소
File srcfile  =new File(realPath);//파일은 물리 주소가 필요함



String destUrl = "/11/cat4.png";
String destPath = application.getRealPath(destUrl);
File destFile = new File(destPath);

//스트림 사용시 이 문법 사용하기! try java8문법
	try(
		InputStream is = application.getResourceAsStream(url);  //1차 스트림 대신 알아서 데이터 읽어들이는 역할
		//FileInputStream fin = new FileInputStream(srcfile); //1차 스트림
		BufferedInputStream bin = new BufferedInputStream(is); //2차 스트림 (연결형)

		FileOutputStream fout = new FileOutputStream(destFile); //객체 생성
		BufferedOutputStream bout = new BufferedOutputStream(fout);
	){
		
		int length = -1;
		while((length= bin.read()) != -1 ){
			bout.write(length);
		
	}
		bout.flush();

}





%>
srcfile: <%=srcfile.getCanonicalPath() %>

</pre>
<img  src="<%=request.getContextPath() %>/11/cat4.png"> <!--/11/cat4.png 톰캣 서버에서 확인  -->
</body>
</html>