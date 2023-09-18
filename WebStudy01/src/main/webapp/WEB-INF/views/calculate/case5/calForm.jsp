<%@page import="java.util.stream.Collectors"%>
<%@page import="java.text.MessageFormat"%>
<%@page import="java.util.Arrays"%>
<%@page import="kr.or.ddit.calculate.NumericOpertorType"%>
<%@page import="java.util.Objects"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app/calculate/calForm_case5.js"></script>

</head>
<body>


<!--form 파라미터 넘길때 / required(반드시 입력을 해야함)있어도 서버에서 한번도 검증해야함 /action생략하면 현재 컨트롤러로 메소드로 명령 분리  -->
<form id ="calForm" method="post">
	<input type="number" name="leftOp" required value="${param.leftOp}"/>
	<select name="operator" required data-init-value="${param.operator} }">
		<option value>연산자</option>
		<%
		//enum상수 ->stream 이용하기
		String options  = Arrays.stream(NumericOpertorType.values())
				.map((n)-> String.format("<option id='cal' value='%s'>%c</option>", n.name(), n.getSign()))
				.collect(Collectors.joining("\n"));
		%>
		<%=options%>
		</select>
		
	<!-- 	<option value="PLUS">+</option>
		<option value="MINUS">-</option>
		<option value="MULTIPLY">*</option>
		<option value="DIVIDE">/</option> -->
		
		
	<input type="number" name="rightOp" required value="${param.rightOp}"/>
	<input type="submit" value="=" />
</form>
<div id="resultArea"></div>


</body>
</html>