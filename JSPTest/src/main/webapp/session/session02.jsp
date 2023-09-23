<%@page import="java.util.Enumeration"%>
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

String name;
String value;

//set구조랑 비슷
Enumeration en= session.getAttributeNames();
int i=0;

while (en.hasMoreElements()){
	name = en.nextElement().toString();
	value = session.getAttribute(name).toString();
	out.println("설정된 세션이 속성이름 ["+i+"] : " + name +"<br/>");
	out.println("설정된 세션이 속성값 ["+i+"] : " + value +"<br/>");
	i++;
	
}
%>
</body>
</html>