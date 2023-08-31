<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    int minDan=(Integer)request.getAttribute("minDan");
	int maxDan=(Integer)request.getAttribute("MaxDan");
	
	

 
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	table{
		border-collapse: collapse;
	}
	th,td{
	
		border: 1px solid black;
	}

</style>
</head>
<body>
<form id="gugudanForm">
<input type="number" placeholder="min dan" name="minDan"> 
<input type="number" placeholder="max dan" name="maxDan"> 
<button type="submit">전송</button>
</form>



<h4>table태그를 이용한 구구단 출력(<%=minDan %>단-<%=maxDan %>단, 승수 1-9)</h4>
<table>


<%!

private StringBuffer gugudan(int mindan,int maxdan){
	
	StringBuffer trTags = new StringBuffer();

	for(int i=mindan; i<=maxdan; i++){
		trTags.append("<tr>");
		for(int j=1; j<=9; j++){
			trTags.append(String.format("<td> %d*%d=%d </td>", i, j, i*j));
			
		}
		trTags.append("<tr>");
	}
	return trTags;
}
		
%>

<%= gugudan(minDan,maxDan)%>



</table>


<hr/>
<table>
<!--StringBuffer flush전에 안에 내용 수정 가능!  -->
<%
StringBuffer trTags = new StringBuffer();

for(int i=2; i<=9; i++){
	trTags.append("<tr>");
	for(int j=1; j<=9; j++){
		trTags.append(String.format("<td> %d*%d=%d </td>", i, j, i*j));
		
	}
	trTags.append("<tr>");
}

%>
<%=trTags %>
</table>
<hr/>
<table>
<%

	for(int i=minDan; i<=maxDan; i++){
		out.print("<tr>");
		for(int j=1; j<=9; j++){
			out.println(String.format("<td> %d*%d=%d </td>", i, j, i*j));
			
		}
		out.print("<tr>");
	}



%>


</table>


<hr/>
<table>

<%

 for(int i=2; i<=9; i++){
%>
		
	 <tr>
	 
<%
	 
	 for(int j=1; j<=9; j++){
%>
	
		<td><%= String.format("%d*%d=%d", i, j, i*j) %></td>
		 
	<%
		
	 }
%>
</tr>
<% 
 }

%>

 
 

</table>


</body>
</html>