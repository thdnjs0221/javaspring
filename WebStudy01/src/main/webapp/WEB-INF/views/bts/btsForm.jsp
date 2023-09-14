<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<select id="memSel">   <!--파라미터가 없음녀 폼, name 필요없음  -->
<%
Map<String,String[]>btsMap = (Map)application.getAttribute("btsMap");
String options = btsMap.entrySet().stream()
					.map((en)->String.format("<option value='%s'>%s</option>", en.getKey() , en.getValue()[0] ))
					.collect(Collectors.joining("\n"));
%>
<%=options %>


<!--localhost/WebStudy01/bts  -->
</select>
<script >
	$(memSel).on('change',function(event){
		let memCode = $(this).val();
		location.href = location.href + "/"+ memCode; 
	});

</script>
</body>
</html>