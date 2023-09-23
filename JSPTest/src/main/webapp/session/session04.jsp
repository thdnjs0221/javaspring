<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 int defaultTime = session.getMaxInactiveInterval();
 int time = session.getMaxInactiveInterval() / 60;
 
 out.println("세션 유효시간 기본 : "+defaultTime +"초 <br/>"); //30분
 out.println("세션 유효시간  : "+time +"분 <br/>");

%>

<h4>--------------------------------------------</h4>
<%
session.setMaxInactiveInterval(60*60);
time  = session.getMaxInactiveInterval() /60;
out.println("세션 유효시간 기본 : "+time+"분 <br/>");


%>
