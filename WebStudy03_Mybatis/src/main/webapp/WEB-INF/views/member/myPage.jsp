<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:if test="${not empty message }" >
	<script type="text/javascript">
		alert("${message}")
		</script>
		<c:remove var="message" scope="session"/>  <!--어떤 스코프 써주기  -->
	</c:if>
	
<!--응답 컨텐츠가 html인 경우엔 el이 더 많이 활용함  -->


<table class="table table-bordered">
      <tr>
         <th>회원아이디</th>
         <td>${member.memId }</td>
      </tr>
      <tr>
         <th>비밀번호</th>
         <td>${member.memPass }</td>
      </tr>
      <tr>
         <th>회원명</th>
         <td>${member.memName }</td>
      </tr>
      <tr>
         <th>주민번호1</th>
         <td>${member.memRegno1 }</td>
      </tr>
      <tr>
         <th>주민번호2</th>
         <td>${member.memRegno2 }</td>
      </tr>
      <tr>
         <th>생일</th>
         <td>${member.memBir }</td>
      </tr>
      <tr>
         <th>우편번호</th>
         <td>${member.memZip }</td>
      </tr>
      <tr>
         <th>주소1</th>
         <td>${member.memAdd1 }</td>
      </tr>
      <tr>
         <th>주소2</th>
         <td>${member.memAdd2 }</td>
      </tr>
      <tr>
         <th>집전번</th>
         <td>${member.memHometel }</td>
      </tr>
      <tr>
         <th>회사전번</th>
         <td>${member.memComtel }</td>
      </tr>
      <tr>
         <th>휴대폰</th>
         <td>${member.memHp }</td>
      </tr>
      <tr>
         <th>이메일</th>
         <td>${member.memMail }</td>
      </tr>
      <tr>
         <th>직업</th>
         <td>${member.memJob }</td>
      </tr>
      <tr>
         <th>취미</th>
         <td>${member.memLike }</td>
      </tr>
      <tr>
         <th>기념일</th>
         <td>${member.memMemorial }</td>
      </tr>
      <tr>
         <th>기념일자</th>
         <td>${member.memMemorialday }</td>
      </tr>
      <tr>
         <th>마일리지</th>
         <td>${member.memMileage }</td>
      </tr>
      <tr>
         <th>탈퇴여부</th>
         <td>${member.memDelete }</td>
      </tr>
      
     <tr>
     	<td colspan="2"></td>
     	<a href="${pageContext.request.contextPath}/member/memberUpdate.do" class="btn btn-primary">정보수정</a>
     	<a data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn btn-danger">탈퇴</a>
     	<!--누르는 순간 모달이 뜸  -->
     </tr>
     
      	
   </table>
   
   <!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action='<c:url value="/member/memberDelete.do"/>' method="post">  <!--contextpath 클라이언트측 경로  -->
      
      <div class="modal-body">
        <input type="password" name="password" class="form-control">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
      </form>
    </div>
  </div>
</div>

<script type="text/javascript">
$(exampleModal).on("hidden.bs.modal",function(){
	$(this).find("form")[0].reset();  //제이쿼리는 reset
});
</script>
   
