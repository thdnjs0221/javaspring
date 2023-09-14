<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<%=request.getHeader("user-agent") %>
<br />

멤버 컨텐츠 페이지<!-- 페이지 모듈화 include -->

<%
String[] memRec = (String[])request.getAttribute("member");

%>
<jsp:include page="<%=memRec[1] %>"/>
</body>
</html>