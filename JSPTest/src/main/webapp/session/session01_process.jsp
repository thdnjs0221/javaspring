<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String userId = request.getParameter("id");
	String userPw = request.getParameter("pw");

	if(userId.equals("admin") && userPw.equals("1234")){
		session.setAttribute("userId", userId);
		session.setAttribute("userPw", userPw);
		out.print("세션 설정 성공!");
		out.print(userId+"님 환영합니다 !");
	}else{
		out.print("세션 설정 실패!");
	}

%>
</body>
</html>