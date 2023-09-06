<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/responseDesc.jsp</title>
</head>
<body>
<h4>HttpServletResponse</h4>
<pre>
1. Response Line : Status Code(응답 상태 코드)
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
		- 너가 사용하려는 자원이 외부 접근 자원일때..?
		
		406(NOT_ACCEPTABLE)
		- 클라이언트가 요청할수 있는 Mime content를 전송할 수 없음 (응답 데이터를 못 만들어냄)
			request header(Accept), response header(Content-Type)
		415(UNSUPPORTED_MEDIA_TYPE)
		- 클라이언트가 전송한 content를 판독할 수 없음.
			request header(Content-Type)이 잘못 됐을 때

	500~  : 처리 실패 원인이 서버측에 있을 때. 500(Internal Server Error)
	
2. Response Header 
3. Response Body(Content Body, Message Body)



</pre>
</body>
</html>