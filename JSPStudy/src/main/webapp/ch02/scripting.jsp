<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
	//선언문 태그를 사용하여 자바 변수와 메소드 정의 
	int count =3; //자바변수를 전역으로 선언
	
	
	//메소드를 전역으로 선언
	String makeItLower(String data){
		return data.toLowerCase();
	}

%>
<%
	for(int i=0; i<=count; i++){
		out.println("Java Server Pages"+i+"<br/>");		
	}

%>
<%= makeItLower("Hello World!") %>
</body>
</html>