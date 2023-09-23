<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 아이디, 비밀번호 , 이름., 성별, 핸드폰번호, 주소를 입력 받는 폼 양식을 구성하고,
	ch05_test_process.jsp 로 method 방식 post 전송해주세요
	
	작성 완료 했으면 ch05_test_process.jsp 로 넘어가 처리를 마무리해주세요 
	
	성별 - radio button / value 한글 안되고 영어로
	핸드폰 번호 - select input input

 -->
 
 <form method="post" action="<%=request.getContextPath() %>/ch05/ch05_test_process.jsp">
 
 
  아이디:<input type="text" name="id" ><br/>
  
 
  비밀번호:<input type="password" name="pw" ><br/>
  
  이름: <input type="text" name="name"> 
  
  성별: 남<input type="radio" value="m" name="gen"> 
		여<input type="radio" value="f" name="gen"><br/>
  
  
  핸드폰번호:
  <select name=phone>
  	<option>010</option>
  	<option>011</option>
  </select>-
  <input type="text" name=phone1>
  <input type="text" name=phone2><br/>
   
  주소:<input type="text" name="add"><br/>
  
  <input type="submit" value="전송"/> 
  
 </form>

</body>
</html>