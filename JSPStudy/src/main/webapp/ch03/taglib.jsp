<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h5>1. 변수 k가 1부터 10까지 1씩 증가하도록 JSTL의 Core 태그 반복문을 작성</h5>
	<c:forEach var="k" begin="2" end="10" step="2">
		<c:out value="${k}"></c:out>
	</c:forEach>
	<br />

	<p># 짝수 일때만 출력</p>
	<c:forEach var="j" begin="1" end="10" step="1">
		<c:if test="${j%2 ==0 }">
			<c:out value="${j}"></c:out>
		</c:if>
	</c:forEach>

	<br />
	<h5>2. 스크립틀릿과 표현문/ JSTL을 통해 str 출력</h5>
	<p>#스클립틀릿을 선언</p>
	<%
	String str = "스클립틀릿을으로 선언한 개똥이";
	%>
	<%=str%>
	<br />
	<p>#JSTL의 Core태가 out으로 선언</p>
	<c:out value="Core태그 out으로 선언" />
	<br />
	<c:out value="<%=str%>" />
	<br />

	<c:set value="<%=str%>" var="text" />
	<!-- setter랑 비슷 var는 변수 / var 선언은 무조건 el표현 -->
	<c:out value="${text}" />
	<br />

	<h5>3. 스크립틀릿과 표현문/JSTL을 통해 Collection출력</h5>
	<p>스크립틀릿으로 선언</p>
	<%
	List<String> list = new ArrayList<>();

	list.add("개똥이");
	list.add("김철수 ");
	list.add("메뚜기");
	%>
	<%=list%>

	<p>#JSTL을통해 Collection 출력</p>
	<c:forEach items="<%=list%>" var="str">
   		이름:	<c:out value="${str}" />
	</c:forEach>
	<br />

	<c:forEach items="<%=list%>" var="str1" varStatus="stat">
		<c:out value="${stat.index}" /> | <c:out value="${stat.count}" />
		
	</c:forEach>
	<br />
<%--if ..else if.. else  <c:choose>를 무조건 붙여주기 --%>
	<h5>4. JSTL을 통해 범위 내 값을 출력</h5> 
	<c:set value="5000" var="money"/>
	<c:choose>
		<c:when test="${money <= 0 }">
			<c:out value="무일푼"/> </br>

		</c:when>
		<c:when test="${money >= 5000 && money<=10000 } ">
				<c:out value="커피한잔의 여유"/>

		</c:when>
		<c:otherwise>
			<c:out value="커피말고 밥 먹자!!"/>
		</c:otherwise>
	</c:choose>


</body>
</html>