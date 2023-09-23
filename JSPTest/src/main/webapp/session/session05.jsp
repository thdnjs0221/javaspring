<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String sessionId = session.getId();

long last_Time = session.getLastAccessedTime();
long Start_Time = session.getCreationTime();
long used_time = (last_Time - Start_Time) /60000;

out.println("세션 아이디: "+sessionId+"<br/>");
out.println("요청 시작 시간: "+Start_Time+"<br/>");
out.println("요청 마지막 시간: "+last_Time+"<br/>");
out.println("웹사이트에서 경과 시간: "+used_time+"<br/>");



%>