<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    

    
<!--페이지 일부분을 구성하기 위한jsp (모듈jsp) document있으면X -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/bootstrap-5.3.2-dist/css/bootstrap.min.css"/>
    
    
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.7.1.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/jquery.serializejson.js"></script>

<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}")  //웬만하면 alert쓰지 않기..
	</script>
<%-- 	<c:remove var="message" scope="session" /> --%>
	<!--어떤 스코프 써주기  flash attribute 사용하면 c:remove 안써줘도 됨-->
</c:if>
