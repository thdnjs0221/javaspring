<%@page import="kr.or.ddit.servlet01.DescriptionServlet"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/resourceIdentify.jsp</title>
<style type="text/css">
	img{
		width: 100px;
		height: 100px;
	
	}


</style>
</head>
<body>
<h4>자원의 종류와 식별 방법</h4>
<pre>
** 자원을 식별할때는 변경되는 경로는 사용하지 않는다.
1. file system resource : 자원의 실제 파일 시스템상의 경로(물리주소)를 그대로 사용해서 식별함 ex)D:\01.medias\images\cute1.PNG
						//클라이언트는 접근 불가능. 서버는 가능(물리주소 사용)
						<%				
							File res1 = new File("D:\\01.medias\\images\\cute1.PNG");					
						%>
						파일 크기 : <%=res1.length() %>
2. class path resource: classpath 이후의 논리주소 형태로 자원을 식별함. //클라이언트는 접근 불가능.
						ex) /kr/or/ddit/images/cat1.jpg
						<%
							ClassLoader loader = DescriptionServlet.class.getClassLoader();
							URL url = loader.getResource("kr/or/ddit/images/cat1.jpg");  //가짜경로
							if (url!=null){
							String realPath = url.getFile(); //진짜 경로
							File res2 = new File(realPath);
						
						
						%>
						파일 크기 : <%=res2.length() %>
						파일의 물리 주소:<%=realPath %>
						
						<%
							}
						%>
3. *****web resource(context resource):  URL 형태의 식별자 체게로 네트워크 반대편의 클라이언트가 접근 할 수 있는 자원
						//webapp파일 아래 있는건 모두 web resource , 논리주소로 접근
	ex) http://localhost/WebStudy01/resources/images/cat4.png (URL)
	<%
		String logical = "/resources/images/cat4.png";  //**기억하기같은 쪽에서 접근하는 파일이라 앞에 생략
		String physical = application.getRealPath(logical); //서버를 통해서 접근(톰캣)
		File res3 = new File(physical);
	%>
	파일 크기 : <%=res3.length() %>
	파일의 물리 주소:<%=physical %>
	
	
* 웹 자원의 식별자
	URI(Uniform Resource Identifiter, 통합 자원 식별자):네트워크 자원을 식별하는 체계
	-URN(Uniform Resource Naming):
	-URC(Uniform Resource Content):
	-URL(Uniform Resource Locator):

	case 1 -  http://localhost/WebStudy01/resources/images/cat4.png(URL)
	case 2 -  http://localhost/WebStudy01/ver4/imageForm.do(URI)

	URL 표기방식
	protocol://IP[domain]:port/context/depth.../resource_name
	
	
	(클라이언트 입장)
	절대경로 (자원을 접근할때는 절대 경로 사용해주기)
		1) http://localhost/WebStudy01/resources/images/cat4.png
		2) //localhost/WebStudy01/resources/images/cat4.png
		3) client side - <%=request.getContextPath()%>/resources/images/cat4.png (**)  ->도메인 바뀌어도 소스를 건드릴 필요 없으므로 3번의 방식을 쓴다
		4) server side - /resources/images/cat4.png
	상대경로: 현재 페이지 출처를 기준으로 경로를 표기함.(상대경로는 잘 이용하지 않는다 )
		../resources/images/cat4.png

</pre>
<img src="http://localhost/WebStudy01/resources/images/cat4.png"/><!--진짜 주소  -->
<img src="//localhost/WebStudy01/resources/images/cat4.png"/> <!--location이 가지고 있는 주소는 생략가능이긴 하나  -->
<img src=" <%=request.getContextPath()%>/resources/images/cat4.png"/> <!--  -->
<img src="../resources/images/cat4.png"/> 

</body>
</html>