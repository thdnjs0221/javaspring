<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-border">
	<thead>
		<tr>
			
			<th>일련번호</th>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>생일</th>
			<th>거주지역</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="memberList" value="${paging.dataList }"/>
		<c:if test="${empty memberList}">
			<tr>
				<td colspan="7">검색 조건에 맞는 회원 없음.</td>
			</tr>
		</c:if>
		<c:if test="${not empty memberList}">
			<c:forEach items="${memberList }" var="member">
				<tr data-mem-id="${member.memId }" data-bs-toggle="modal" data-bs-target="#exampleModal">
					<td>${member.rnum }</td>
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
	<tfoot>
		<tr>
			<td colspan="7">
				${paging.pagingHTML }
				<div id="searchUI"> <!--입력받을 폼  -->
					<select name="searchType">
						<option value>전체</option>
						<option value="name">이름</option>
						<option value="address">지역</option>
					</select>
						<input type="text" name="searchWord"/>
						<input type="button" value="검색" id="searchBtn"/>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<!--전송하는 hidden폼 페이지랑 검색 동시에! -->
<form id="searchForm">
	<input type="text" name="searchType"/>
	<input type="text" name="searchWord"/>
	<input type="text" name="page"/>
</form>

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

	$(":input[name=searchType]").val("${paging.simpleCondition.searchType}");
	$(":input[name=searchWord]").val("${paging.simpleCondition.searchWord}");
	//페이징도 같이 서버로 보내기(서버+검색조건)
	function fn_paging(page){
		searchForm.page.value = page;
		searchForm.requestSubmit();

	}
	
	//검색
	$(searchUI).on("click","#searchBtn",function(event){  //this->searchBtn버튼
		let inputs = $(this).parents("#searchUI").find(":input[name]");  //name속성들이 있는것만 가져옴?
		$.each(inputs, function(idx,ipt){
			let name = ipt.name;
			let value = $(ipt).val();  //
			$(searchForm).find(`:input[name=\${name}]`).val(value);
			$(searchForm).submit();
			
		});//$.each
	});


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