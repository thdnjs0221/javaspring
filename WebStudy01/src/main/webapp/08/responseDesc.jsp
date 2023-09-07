<%@ page language="java" pageEncoding="UTF-8"%>
<%-- <% 
	response.setContentType("text/plain; charset=utf-8");
	response.setContentLengthLong(100);
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/responseDesc.jsp</title>
</head>
<body>
<h4>HttpServletResponse</h4>
<pre>
1. Response Line : Status Code(응답 상태 코드) - response.sendError(sc[,message]),setStatus(sc)
	
	Status Code: 서버에서 요청 처리 결과의 성공 여부를 표현하는 상태 코드 
		Http: connectLess + stateLess 
	100~ : ING....WebSocket (connectFull)
	200~ : OK
	300~ : 요청 처리가 최종적으로 완료되려면, 클라이언트로부터 추가 액션이 필요함. response body가 없음.
		302, 307 (Moved) + Location(자원의 새로운 주소) 헤더 사용
		304(NOT_MODIFIED): 일반적으로 브라우저는 정적 자원에 대해 캐싱을 해서 사용함.
				한번 캐싱된 자원이 변경된 적 없으므로, 캐시 자원을 그대로 사용하라는 표현.

	**400~  : 처리 실패 원인이 클라이언트측에 있을 때
		404(Not Found)
		- request line(URI)이 잘못됐을때, 모델2구조일때 c->v 주소가 잘못되었을때 
		405(METHOD_NOT_ALLOWED)
		400(BAD_REQUEST, 요청 검증에 주로 활용됨.)
		
		----- 어플리케이션 보호를 위한 접근 제어에서 활용됨 -----
		401(UNAUTHORIZED)
		- 접근하려는 것이 보호 자원일때 인증이 필요할때 쓰임
		403(FORBIDDEN) 
		- 보호자원 인증은 거쳤지만 권한이 없을때 
		
		406(NOT_ACCEPTABLE)
		- 클라이언트가 요청할수 있는 Mime content를 전송할 수 없음 (응답 데이터를 못 만들어냄)
			request header(Accept), response header(Content-Type)
		415(UNSUPPORTED_MEDIA_TYPE)
		- 클라이언트가 전송한 content를 판독할 수 없음.
			request header(Content-Type)이 잘못 됐을 때 (UNSUPPORTED_MIME_TYPE)

	500~  : 처리 실패 원인이 서버측에 있을 때. 500(Internal Server Error)
	
2. Response Header : respose.set[Int|Date]Header(name,value), addHeader...
	1) Content-* : Content-Type, Content-Length - response body 컨텐츠를 수식해줌. 
	2) Cache 제어: Cache-Controller(Http ver 1.1) , Expires(만료기한), Pragma(Http ver 1.0)
	 	응답은 클라이언트 기준으로 !
	 	<%
	 		response.setHeader("Cache-Controller", "no-cache");
	 		response.addHeader("Cache-Controller", "no-store");
	 		response.addHeader("Pragma", "no-cache");
	 		response.addHeader("Pragma", "no-store");
	 		response.setDateHeader("Expires", 0); //캐시 남기지 말아라. 보안 어플리케이션에서 사용

	 	%>
	3)Auto Request: Refresh
	<a href="<%=request.getContextPath() %>/08/autoRequest.jsp">auto Request</a>
	요청이 자동으로 발생하는것 ex) 어떤 데이터가 2초마다 갱신되는 데이터가 있으면 전송버튼 누르지 않아도 전송이 되어야한다
	4) Redirection: Location
3. Response Body(Content Body, Message Body)
	respose.getWriter() : char 기반의 문자를 기록할 출력 스트림
	respose.getOutputStream(): byte 기반의 스트림(binary 일련의 바이트 집합! 연속성을 가지고 있는..) 컨텐츠를 기록할 출력 스트림(통로)
	단방향의 출력스트림이 필요 !
	



</pre>
</body>
</html>