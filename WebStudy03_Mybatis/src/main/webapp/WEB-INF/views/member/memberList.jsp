<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-border">
	<thead>
		<tr>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>생일</th>
			<th>거주지역</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>

		<c:if test="${empty memberList}">
			<tr>
				<td colspan="5">검색 조건에 맞는 회원 없음.</td>
			</tr>
		</c:if>
		<c:if test="${not empty memberList}">
			<c:forEach items="${memberList }" var="member">
				<tr data-mem-id="${member.memId }" data-bs-toggle="modal" data-bs-target="#exampleModal">
					<td>${member.memName }[${member.prodCount}]</a></td>
					<td>${member.memHp }</td>
					<td>${member.memMail }</td>
					<td>${member.memBir }</td>
					<td>${member.memAdd1 }</td>
					<td>${member.memMileage }</td>
				</tr>

			</c:forEach>
		</c:if>
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
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>


<script>
//EDD(event driven development)
	$(exampleModal).on("show.bs.modal", function(event) {
		let $modal =$(this);  //this-> modal
		let trTag = event.relatedTarget; 
		let who = $(trTag).data("memId");
		//location.href="${pageContext.request.contextPath}/member/memberView.do?who="+who  //get방식 요청
		let url="${pageContext.request.contextPath}/member/memberView.do?who="+who;
		
		$.get(url)
			.done(function(resp){
				$modal.find(".modal-body").html(resp);  //this - > .done(function(resp)
			});

	}).on("hidden.bs.modal",function(event){
		$(this).find(".modal-body").empty;
	});
</script>