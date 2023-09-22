<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body data-context-path="<%=request.getContextPath()%>">
<form id="adrsForm" action="<%=request.getContextPath()%>/adrs/address" method="post">
	<input type="text" name="adrsName" placeholder="이름" required/> 
	<input type="text" name="adrsHp" placeholder="휴대폰" required/> 
	<input type="text" name="adrsAdd" placeholder="주소" /> 
	<input type="submit" value="등록"/>
</form>
	<table>
		<thead>
			<tr>
				<th>이름</th>
				<th>휴대폰</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody id="listBody"> 
		

		</tbody>
	</table>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/app/address.js">




</script>
</body>
</html>