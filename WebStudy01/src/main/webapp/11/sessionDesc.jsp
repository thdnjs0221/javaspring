<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/sessionDesc.jsp</title>
</head>
<body>
<h4>세션 (HttpSession)</h4>
<pre>
세션
	◆시간(ConnectLess) : 클라이언트가 web 어플리케이션을 사용하고 있는 기간.
		최초의 요청(세션아이디가 없을때)이 발생하면 세션이 생성되고, 사용 종료 이벤트가 발생하면 세션이 제거됨.
		사용 종료 이벤트 종류
		1. log out (제일 명확한 종료)
		2. timeout 이내에 새로운 요청이 발생하지 않으면, 종료로 간주함
		3. session tracking mode에  따라 세션 아이디가 재전송되지 않을때.
			ex) 클라이언트 측에서 쿠키를 제거함
		4. 브라우저 종료
		
		2-3번은 쓰레기 세션
	◆통로(ConnectFull) : connection open(==session create)~ connection close(==session destroy)
							ex)마이바티스 , 통로는 주로 데이터베이스에서 사용
	
	◆세션 유지 방법(세션 식별 방법)
	- 세션이 유지되기 위해 세션 식별자가 클라이언트에서 서버로 재전송되는 방법론: tracking mode
	세션의 생성 시점: <%=new Date(session.getCreationTime()) %>
	세션 아이디: <%=session.getId() %>
	현재 세션 내에서 발생한 마지막 요청 시점: <%=new Date( session.getLastAccessedTime()) %>
		1. Cookie  : request/response의 헤더를 통해 세션 아이디를 공유하는 방식
		2. URL : <a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">세션파라미터를 통한 세션유지</a>			
					request line을 통해 세션아이디를 공유하는 방식(취약성 존재, 보안 때문에 사용 권장x)
		3. SSL(Secure Socket Layer) : 암호화 채널을 이용한 세션 아이디 공유
									오고 가는 모든 내용을 암호화 함 (https)
									ngrok(방화벽 넘어서 외부에서 로컬에 접속 가능하게 하는 터널 프로그램)
	
	***쿠키와 세션의 다른 점: 저장위치가 다름! 세션은 서버(헤더)에 있고 쿠키는 클라이언트에 있음
	
	세션 단점 
	1. 보유 기간의 한정
	2. 불특정 클라이언트의 데이터를 서버1개 저장하면 부담 
</pre>
</body>
</html>