<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%


if (request.isRequestedSessionIdValid() == true) {
	out.println("세션이 유효합니다!");

}
session.invalidate(); //세션 모두 초기화

if (request.isRequestedSessionIdValid() == true) {
	out.println("세션이 유효합니다!");

} else {
	out.println("세션이 유효하지 않습니다!");
}


%>
