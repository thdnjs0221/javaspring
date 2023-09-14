<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="8kb" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/implicitObject.jsp</title>
</head>
<body>
<h4>JSP 스펙의 기본 객체</h4>
<pre>
pageContext(Page Scope) : 가장 먼저 생성되는 기본객체로 현재 jsp 페이지와 관련된 모든 정보(ex. 기본객체)를 가진 객체 
pageContext가 하는일은 :
<%=pageContext.getSession() %>, <%=pageContext.getServletContext() %>
<%=pageContext.getRequest() %>

HttpServletRequest request(Request Scope) : http방식에 따라 패키징된 요청에 대한 정보를 가진 객체 
							요청이 전송되면, 서버에 의해 생성되고, 그에 대한 응답이 전송되면 삭제 됨(stateless) (명확한 생명주기를 가지고 있음)

HttpSession session(Session Scope) : 한 클라이언트가 하나의 브라우저를 이용해서 어플리케이션을 사용하기 시작한 순간부터
						사용 종료 이벤트가 발생할때까지의 기간내에 만들어지는 정보들을 가진 객체.

ServletContext application(application Scope) : 서버와 현재 어플리케이션(context)에 대한 정보를 가진 객체로 하나의 컨텍스트에 대한 싱글턴으로 운영됨.
													서버가 꺼지면 attribute도 없어짐

----> scope(저장공간)라고 불리는 map을 내부에 갖고 있는 객체들로 ,
	 해당scope는 관리 객체의 생명주기와 동일한 생명주기를 가짐. scope에 저장된것을 attribute라고부름
	setAttribute(String, Object), getAttribute(String) , removeAttribute(String)
	getAttributeNames()


HttpServletResponse response : http방식에 따라 패키징된 응답에 대한 정보를 가진 객체.

JspWriter out: 응답 컨텐츠를 버퍼에 기록할때 사용하는 출력 스트림, 출력 버퍼 관리자.
				out.getRemaining() ->버퍼 남은 용량 확인
---------------------------------------------------------------------------
- 잘 사용하지 않음
ServletConfig config : 서블릿의 설정 정보를 가진 객체.

Object page : this (jsp의 인스턴스임 쓸상황이 오면 this로 쓰기/나중에.. 커스텀태그 만들때 사용)

Throwable exception : 어플리케이션 내에서 발생한 모든 비정상상황에 대한 정보를 가진 객체.
						에러 처리 목적으로 만들어지 jsp에서만 활성화됨.

<%  


%>


</pre>
</body>
</html>