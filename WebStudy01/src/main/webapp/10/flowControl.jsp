<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/flowControl.jsp</title>
</head>
<body>
<h4>Flow control(페이지 이동 방식, A->B이동)</h4>
<pre>
	Http :ConnectLess, StateLess
	1. Request Dispatch : 원본 요청을 그대로 유지한 채 분기하는 방식.
		- foward(Model 2에서 자주사용) : 이동 후 최종 응답이 바로 전송.
		- include(page modulization) : B에서 생성된 일부 결과 데이터와 A에서 만들어지 일부 데이터가 하나로 합쳐져서 응답이 전송됨.
					(A가 B를 내포함)
 		<% 
 			String path = "/05/standardDesc.jsp"; //절대경로(서버가 사용하는 주소)
 			//RequestDispatcher rd= request.getRequestDispatcher(path);
 			//rd.forward(request, response);  //클라이언트 입장에서는 응답 :a /b의 존재를 모름(b에서 응답이 나옴)
 			//rd.include(request, response); //a+b응답  클라이언트 입장에서는 응답 :a
 			String location = request.getContextPath()+path; //클라이언트가 사용하는 주소(contextpath있어야함)
 			response.sendRedirect(location); //b의 존재를 안다 , 한번의 요청이 끝나고 의도적으로 삭제하고 싶을때 사용(로그인처리, 보안처리) 
		%> 

		<!-- 액션태그 (백앤드) -->
<%-- 		<jsp:forward page="/05/standardDesc.jsp"/> 포워드는 액션태그로 잘 안씀--%>
<%-- 			<jsp:include page="<%=path %>"/> <!--위치를 의도적으로 결정할수있다, 뷰가 주로 사용하는 구조임/ 한페이지에 html태그 두개 생기지 않게 주의하기!!   --> --%>
		
	2. Redirect : reponse body가 없는 응답이 전송되면서, 원본 요청이 제거되고 , 완전히 새로운 요청이 발생하는 방식
					Location 헤더를 통해 클라이언트가 발생시킬 새로운 요청의 주소를 설정함.


</pre>
</body>
</html>