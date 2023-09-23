<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>쉽게 배우는 JSP 웹 프로그래밍</title>
</head>
<body>
									
<!-- 
	ch05_test.jsp 에서 전송받은 
	아이디, 비밀번호, 이름, 성별, 핸드폰번호, 주소를
	
	taglib를 이용하여 출력해주세요
	
	[출력 예시]
	아이디 : a001
	비밀번호 : 1234
	이름 : 홍길동
	성별 : 남자
	핸드폰번호 : 010-1234-1234
	주소 : 대전시 서구 오류동 123  
	
	[여기까지 가능한 분은 진행!]
	출력을 완료하였으면, 5초 뒤에 www.naver.com 홈페이지로 이동시켜주세요
 -->
	 <%
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("mem_id");
		String pw = request.getParameter("mem_pw");
		String name = request.getParameter("mem_name");
		String gender = request.getParameter("mem_gender");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		
		String phone = phone1+"-"+phone2+"-"+phone3;
		
		String addr = request.getParameter("mem_addr");
	 %>
	 <c:set value="<%=id %>" var="id"/>
	 <c:set value="<%=pw %>" var="pw"/>
	 <c:set value="<%=name %>" var="name"/>
	 <c:set value="<%=gender %>" var="gender"/>
	 <c:set value="<%=phone %>" var="phone"/>
	 <c:set value="<%=addr %>" var="addr"/>
	 아이디 : <c:out value="${id }"/> <br/>
	 비밀번호 : <c:out value="${pw }"/> <br/>
	 이름 : <c:out value="${name }"/> <br/>
	 성별 : 
	 <c:choose>
	 	<c:when test="${gender eq 'man' }">
	 		<c:out value="남자"/>
	 	</c:when>
	 	<c:otherwise>
	 		<c:out value="여자"/>
	 	</c:otherwise>
	 </c:choose>
	 핸드폰 : <c:out value="${phone }"/> <br/>
	 주소 : <c:out value="${addr }"/> <br/>
	 
	<br/>
	<p id="p1"></p>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	var a = 0;
	var pTag = document.getElementById("p1");
	
	// [방법 1]
	setInterval(() => {
		a++;
		pTag.innerHTML = a + "초 뒤에 이동합니다!";
		if(a == 5)
			location.href = "https://www.naver.com";
	}, 1000);
	
	// [방법 2]
// 	setTimeout(() => {
// 		location.href = "https://www.naver.com";
// 	}, 5000);
</script>
</html>