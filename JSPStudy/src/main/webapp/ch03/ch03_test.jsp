<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	1) 현재 305호 안에서 보강하고 있는 학생들 이름을 Core 태그 set에 담고
	전체를 출력해주세요.
	
	2) 전체 학생들을 4명씩 끊어서 출력해주세요.
	이름 이름 이름 이름
	이름 이름 이름 이름
	이름 이름 이름 이름
	이름 이름 이름 이름
	
	3) 4명씩 끊어서 출력할 때, 본인의 이름은 '본인'으로 출력하되!
	색깔을 녹색, 굵은 글씨로 출력해주세요!
	
	**** 305호 보강 인원 모두를 넣는건 List를 활용하던 Array를 활용하던 문자열을 활용하던 자유롭게!
 -->
<%
	String[] names = {
		"신범종","김원희","전수진","김석호","이수정",
		"김소원","문선영","신수연","김정하","유선영","우정범",
		"김태형","이건창","김재성","오경석","이재혁","송석원",
		"황수빈","조현준"
	};
	List<String> nameList = new ArrayList<String>();
	for(int i = 0; i < names.length; i++){
		nameList.add(names[i]);
	}
%>
<c:set value="<%=nameList %>" var="names"/>
<c:forEach items="${names }" var="name">
	<c:out value="${name }"/>&nbsp;
</c:forEach>
<br/><br/>

<c:forEach items="${names }" var="nm" varStatus="stat">
	<c:if test="${nm eq '조현준' }">
		<font color="green"><b>본인　</b></font>
	</c:if>
	<c:if test="${nm ne '조현준' }">
		<font>${nm }</font>
	</c:if>
	<c:if test="${stat.count % 4 == 0 }">
		<br/>
	</c:if>
</c:forEach>
</body>
</html>




















