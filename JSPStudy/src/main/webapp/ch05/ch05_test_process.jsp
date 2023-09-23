<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>
<!--
	 넘겨받은 데이터 모두 taglib를 이용하여 출력해주세요 
	 
	 아이디: a001
	 비밀번호 : 1234
	 이름: 홍길동
	 성별: 남자
	 핸드폰번호: 010-1234-1234
	 주소: 대전 중구 오류동 대덕인재개발원
	 
	 출력을 완료했으면 5초뒤에 www.naver.com 으로 이동해주세요
  -->
  <%
  String id =request.getParameter("id");  
  String pw =request.getParameter("pw");  
  String name =request.getParameter("name");  
  String gen =request.getParameter("gen");  
  String phone =request.getParameter("phone");  
  String phone1 =request.getParameter("phone1");  
  String phone2 =request.getParameter("phone2");  
  String add = request.getParameter("add");

  %>
 
 <c:set value="<%=id%>" var="id" />
	아이디:<c:out value="${id}" /><br/>
	
	<c:set value="<%=pw%>" var="pw" />
	비밀번호:<c:out value="${pw}" /><br/>
	
	<c:set value="<%=name%>" var="name" />
	이름:<c:out value="${name}" /><br/>
	
	<c:set value="<%=gen%>" var="gen" />
	성별:<c:out value="${gen}" /><br/>
	
	<c:set value="<%=phone%>" var="phone" />
	번호:<c:out value="${phone}" />
	<c:set value="<%=phone1%>" var="phone1" />
	-<c:out value="${phone1}" />
	<c:set value="<%=phone2%>" var="phone2" />
	-<c:out value="${phone2}" /><br/>
	
	
	<c:set value="<%=add%>" var="add" />
	주소:<c:out value="${add}" /><br/>
 
 <script type="text/javascript">
 
	location.href = "https://www.naver.com";
 
 </script>


</body>
</html>