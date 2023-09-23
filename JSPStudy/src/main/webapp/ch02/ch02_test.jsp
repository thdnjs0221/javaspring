<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		구구단 1-9단까지 출력해주세요.
		짝수 단인 경우에는 빨간색으로 출력해주세요.
		- 정렬 기준을 2가지로 제공하고 제공된 정렬에 따라 출력을 다르게 해주세요.
			> 가로로 정렬, 세로로 정렬
	 -->
	 <select id="orderSelect">
	 	<option value="">--선택--</option>
	 	<option value="1">가로로정렬</option>
	 	<option value="2">세로로정렬</option>
	 </select>
	 <div id="t1">
	 	<table width="100%">
	 	<%
	 		String color = "";
	 		for(int i = 1; i < 10; i++){
		 		out.println("<tr>");
	 			for(int j = 1; j < 10; j++){
	 				if(i % 2 == 0){
	 					color = "red";
	 				}else{
	 					color = "black";
	 				}
	 				out.println("<td><font color='"+color+"'>" + i + "x" + j + "=" + (i*j) + 
	 						"</font></td>");	
		 		}	
		 		out.println("</tr>");
	 		}
	 	%>
	 	</table>
	 </div>
	 <div id="t2">
	 	<table width="100%">
	 	<%
	 		for(int i = 1; i < 10; i++){
		 		out.println("<tr>");
	 			for(int j = 1; j < 10; j++){
	 				if(j % 2 == 0){
	 					color = "red";
	 				}else{
	 					color = "black";
	 				}
	 				out.println("<td><font color='"+color+"'>" + j + "x" + i + "=" + (i*j) + 
	 						"</font></td>");	
		 		}	
		 		out.println("</tr>");
	 		}
	 	%>
	 	</table>
	 </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	var orderSelect = $("#orderSelect");
	
	$("#t1").hide();
	$("#t2").hide();
	
	orderSelect.on("change", function(){
		var value = $(this).val();
		
		if(value == "1"){
			$("#t1").show();
			$("#t2").hide();
		}else{
			$("#t2").show();
			$("#t1").hide();
		}
	});
});
</script>
</html>





