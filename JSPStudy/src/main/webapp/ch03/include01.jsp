<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>include01 페이지입니다</p>

	<!--헤더 정보를 넣어주세요  -->
	<%@ include file="include01_header.jsp"%>
	<h4>Content 영역입니다</h4>
	<!--푸터 정보를 넣어주세요  -->
	<%@ include file="include01_fotter.jsp"%>
</body>
</html>