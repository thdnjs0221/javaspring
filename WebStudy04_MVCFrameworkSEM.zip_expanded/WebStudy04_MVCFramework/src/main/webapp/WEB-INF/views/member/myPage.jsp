<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty message }">
	<script>
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>    
    
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
			<th>이미지</th>
			<td>	
				<img src="data:image/*;base64,${member.memImgBase64 }"/>
			</td>
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
			<td colspan="2">
				<a href="${pageContext.request.contextPath }/member/memberUpdate.do" class="btn btn-primary">수정</a>
				<a data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn btn-danger">탈퇴</a>
			</td>
		</tr>
	</table>
	<table class="table table-bordered">
		<thead class="table-dark">
			<tr>
				<th>상품명</th>
				<th>상품분류</th>
				<th>제조사이름</th>
				<th>구매일자</th>
				<th>구매수량</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="cartSet" value="${member.cartSet }"/>
			<c:choose>
				<c:when test="${not empty cartSet }">
					<c:forEach items="${cartSet }" var="cart">
						<c:set var="prod" value="${cart.prod }" />
						<c:url value="/prod/prodView.do" var="prodViewURL">
							<c:param name="what" value="${prod.prodId }" />
						</c:url>
						<tr>
							<td><a href="${prodViewURL }">${prod.prodName }</a></td>
							<td>${prod.lprod.lprodNm }</td>
							<td>${prod.buyer.buyerName }</td>
							<td>${cart.cartDate }</td>
							<td>${cart.cartQty }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">구매 정보 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action='<c:url value="/member/memberDelete.do" />' method="post">
	      <div class="modal-body">
	        <input type="password" name="password" class="form-control" />
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	      </div>
      </form>
    </div>
  </div>
</div>
	
<script>
	$(exampleModal).on("hidden.bs.modal", function(){
		$(this).find("form")[0].reset();
	});
</script>	
	
	
	
	
	
	
	
	
	
	
