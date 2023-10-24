<%@page import="java.util.Objects"%>
<%@page import="java.util.Map"%>
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
	Map<String, String>errors = (Map)request.getAttribute("errors");
	if(errors!=null && errors.isEmpty()){
		%>
		<div class="error"><%=errors %></div>
		<% 
	}


%>
<!--form 파라미터 넘길때 / required(반드시 입력을 해야함)있어도 서버에서 한번도 검증해야함 /action생략하면 현재 컨트롤러로 메소드로 명령 분리  -->
<form id ="calForm" method="post">
	<input type="number" name="leftOp" required value="${param.leftOp}"/>
	<select name="operator" required>
		<option value>연산자</option>
		<option value="PLUS">+</option>
		<option value="MINUS">-</option>
		<option value="MULTIPLY">*</option>
		<option value="DIVIDE">/</option>
		<option value="MODULAR">%</option>
	</select>
	<input type="number" name="rightOp" required value="${param.rightOp}"/>
	<input type="submit" value="=" />
</form>
<script type="text/javascript">
	calForm.operator.value = "${param.operator}";  //el..없으면 화이트 스페이스
	


</script>
</body>
</html>