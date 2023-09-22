<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/includee/preScript.jsp"/> <!--  -->

</head>

<body>
<h4>웰컴페이지 : <%=request.getAttribute("title") %></h4>
<% 
String authId =(String)session.getAttribute("authId"); 
if(authId!=null){

%>
<form method="post" action="<%=request.getContextPath() %>/login/logout.do" id="logoutForm"> </form>

<h4><%=authId %> <a href="javascript:;" id="logoutBtn">로그아웃</a> </h4>
<h4><a href="<%=request.getContextPath()%>/adrs/view">주소록</a></h4>

<%
}else{
	%>
	<a href="<%=request.getContextPath()%>/login/loginForm.jsp">로그인</a>
	<% 
}
%>

<script type="text/javascript">


$('#logoutBtn').on("click",function(event){
	event.preventDefault(); //폼의 동기 요청 중단
	logoutForm.requestSubmit();  //제이쿼리일때 $(logoutForm).submit();
});

</script>

</body>
</html>