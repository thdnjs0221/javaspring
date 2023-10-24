<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/scopeDesc.jsp</title>
</head>
<body>
<h4>scope</h4>
<pre>
: 생명주기가 다른 4개의 기본 객체에 의해 관리되는 저장 공간(Map)
attribute : scope를 통해 저장 및 공유되는 데이터(name-String / value-Object)
page scope - pageContext
request scope - HttpServeltRequest
session scope - HttpSession
application scope - ServletContext
setAttribute , getAttribute, removeAttribute
<%
	pageContext.setAttribute("pageAttr", "페이지 속성 값");
	request.setAttribute("requestAttr", "요청 속성 값"); 
	session.setAttribute("sessionAttr", "세션 속성 값"); //장바구니
	application.setAttribute("applicationAttr", "어플리케이션 속성 값"); //서버  //누적방문자수
	
	RequestDispatcher rd= request.getRequestDispatcher("/11/attrView.jsp");
	//rd.forward(request, response);
	rd.include(request, response);
	//response.sendRedirect(request.getContextPath()+"/11/attrView.jsp");	// Redirect 클라이언트가 사용하는 주소 
	//forward 1.X 페이지가 변경되어서 2. 요청이 뷰가까지 전달됨
	//include 1.X jsp가 쪼개져서 바뀌는 순간 속성이 사라짐
	
%>
</pre>
<!-- <a href="attrView.jsp">attribute 확인하기</a>  -->





</body>
</html>