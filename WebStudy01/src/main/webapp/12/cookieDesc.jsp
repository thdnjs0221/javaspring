<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/cookieDesc.jsp</title>
</head>
<body>
<h4>Cookie recipe</h4>
<pre>
<%--
Cookie sampleCookie = new Cookie("sampleCookie","SAMPLE_COOKIE_VALUE");
response.addCookie(sampleCookie);

--%>

1. 쿠키를 생성(name/value 필수조건) : value에 특수문자가 포함된 경우 , encoding/decoding 과정이 필요
2. response header(set-cookie)를 통해 클라이언트쪽으로 전송(최대 20개 제한)
--서버(1,2)--


3. 각 브라우저가 소유한 쿠키 저장소에 저장됨.
4. 다음번 request header(cookie)에 서버로 재전송됨

5. request header에 포함된 쿠키를 통해 상태 복원.


쿠키의 속성
1. name/value(required) ->name,value는 문자열
**한글(특수문자) 공백 매체가 읽을수 있게 인코딩해주기 -> 디코딩해주기
2. domain(host) : 다음번 요청을 통해 재전송할지 여부를 결정함(생성할 때 도메인이 기본값)
		ex) .naver.com:naver 소유한 모든 호스트를 대상으로 재전송
3. path : 다음번 요청을 통해 재전송할지 여부를 결정함(생성할 때 경로가 기본값)
4. max-age : 쿠키의 만료 시간 결정(초단위)
		0: max-age를 제외한 나머지 모든 속성이 동일한 쿠키 삭제.
		-1: 브라우저 종료시 쿠키 삭제.
		 
 
<%
// String koreanValue = URLEncoder.encode("한글 쿠키 값", "utf-8");
// Cookie koreanCookie = new Cookie("koreanCookie",koreanValue);
// response.addCookie(koreanCookie);


String samePathValue = URLEncoder.encode("동일 경로에서만 확인 가능한 쿠키","utf-8");
Cookie samePathCookie = new Cookie("samePathCookie",samePathValue);
samePathCookie.setMaxAge(0);  //쿠키 지우는 방법
response.addCookie(samePathCookie);

// String allPathValue = URLEncoder.encode("모든 경로에서만 확인 가능한 쿠키","utf-8");
// Cookie allPathCookie = new Cookie("allPathCookie",allPathValue);
// allPathCookie.setPath(request.getContextPath()+"/10"); // /webstudy01
// response.addCookie(allPathCookie);

Cookie longLiveCookie = new Cookie("longLiveCookie","Long~");
longLiveCookie.setMaxAge(60*60*24*7);
response.addCookie(longLiveCookie);

%>
<a href="cookieView.jsp">동일경로</a>
<a href="<%=request.getContextPath() %>/11/cookieView.jsp">다른경로</a>
<%--
//sampleCookie찾기
//검증
Cookie[] cookies= request.getCookies();
Cookie finderCookie = null;
if(cookies!=null){
	for(Cookie tmp : cookies){
		if("koreanCookie".equals(tmp.getName())){
			finderCookie = tmp;
			break;
		}
	}
}

if(finderCookie!=null){
	String findedValue = URLDecoder.decode(finderCookie.getValue(), "utf-8") ;
	out.println(String.format("찾은 쿠키의 값: %s", findedValue));
	}

--%>
</pre>

</body>
</html>