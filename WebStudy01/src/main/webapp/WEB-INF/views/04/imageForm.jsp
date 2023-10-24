<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <html>
 <body>
 <pre>
 이미지 스트리밍 서비스에서 쿠키 생성 위치 
 -음악을 들을때, BTS 멤버 컨텐츠가 서비스 될때 , 이미지 스트리밍이 될때(ImageStreamServlet)
 재전송된 쿠키를 기반으로 상태를 복원할 위치 
 -음악을 선택할 수 있는 UI 페이지, BTS 멤버 선택 UI페이지, 이미지 선택 UI페이지

 </pre>
 <%
 	String imgCookieValue = (String) request.getAttribute("imgCookieValue");
 
 %>
<form id="imageForm" onsubmit='submitHandler(event);' action='<%=request.getContextPath() %>/image.do'>
 <select name='image' id='image' onchange='this.form.requestSubmit();' required >    
 	 <option value>이미지 선택</option>  
     <%=request.getAttribute("options") %>
     
 </select>
 <input type='submit' value='전송'>
 </form>
<div id="imageArea">
   
</div>
 <script>
    function submitHandler(event) {
       event.preventDefault();
       let imageName = image.value;
       imageArea.innerHTML = `<img src="<%=request.getContextPath() %>/image.do?image=\${imageName}"/>`;
    }
    
    imageForm.image.value = "<%=imgCookieValue%>";
    imageForm.requestSubmit();
 </script>
 </body>
 </html>