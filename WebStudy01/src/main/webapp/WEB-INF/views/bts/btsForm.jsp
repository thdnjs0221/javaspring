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
	<option value>멤버선택</option>
<%
Map<String,String[]>btsMap = (Map)application.getAttribute("btsMap");
String options = btsMap.entrySet().stream()
					.map((en)->String.format("<option value='%s'>%s</option>", en.getKey() , en.getValue()[0] ))
					.collect(Collectors.joining("\n"));
String savedMemCode = (String)request.getAttribute("savedMemCode");
%>
<%=options %>


<!--localhost/WebStudy01/bts  -->
</select>
<div id="resultArea"></div>
<script >
	$(memSel).on('change',function(event){
		let memCode = $(this).val();
	//	location.href = location.href + "/"+ memCode; //동기 요청
		let settings = {
					url:`\${location.href}/\${memCode}`, // $ -> el기호로 사용되기 때문에 이스케이프 문자 써주기 
					dataType:"html" //Accept request header : Content-Type response header
							, 
					success:function(resp){
						$(resultArea).html(resp)
								
							},
					error: function(jqxhr,status,error){
								console.log("jqxhr:",jqxhr);
								console.log("status:",status);
								console.log("error:",error);
							}
			};
			$.ajax(settings);
	}).val("<%=savedMemCode%>")  //memSel의 객체
	.trigger("change");   //특정이벤트 강제 발생 trigger()

</script>
</body>
</html>