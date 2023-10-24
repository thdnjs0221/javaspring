<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<table class="table table-bordered">
	<thead>
		<tr>
			<th>행번호</th>
			<th>업체명</th>
			<th>분류</th>
			<th>지역</th>
			<th>연락처</th>
			<th>메일</th>
			<th>담당자</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="buyerList" value="${paging.dataList }"/>
		<c:choose>
			<c:when test="${not empty buyerList}">
				<c:forEach items="${buyerList }" var="buyer">
					<tr>
						<td>${buyer.rnum }</td>
						<td>${buyer.buyerName }</td>
						<td>${buyer.lprod.lprodNm }</td>
						<td>${buyer.buyerAdd1 }</td>
						<td>${buyer.buyerComtel }</td>
						<td>${buyer.buyerMail }</td>
						<td>${buyer.buyerCharger }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7"> 없음. </td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
		<tr>
			<td colspan="8">
				${paging.pagingHTML }
				<div id="searchUI"  class="row g-3 d-flex justify-content-center">
					<div class="col-auto">
						<form:select path="detailCondition.buyerLgu" class="form-select">
							<option value>업체분류</option>
							<c:forEach items="${lprodList }" var="lprod">
								<form:option label="${lprod.lprodNm }" value="${lprod.lprodGu }" />
							</c:forEach>
						</form:select>
					</div>
					<div class="col-auto">
						<input type="text" name="buyerName" placeholder="업체명" class="form-control"/>
					</div>
					<div class="col-auto">
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
					</div>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form:form modelAttribute="detailCondition" id="searchForm" class="border" method="get">
	<h4>전송 UI</h4>
	<form:input path="buyerLgu" readonly="readonly" placeholder="buyerLgu"/>
	<form:input path="buyerName" readonly="readonly" placeholder="buyerName"/>
	<input type="text" name="page" readonly="readonly" placeholder="page"/>
</form:form>
<script>
function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
}
$(searchUI).on("click", "#searchBtn", function(event){
	let inputs = $(this).parents("#searchUI").find(":input[name]");
	$.each(inputs, function(idx, ipt){
		let name = ipt.name;
		let value = $(ipt).val();
		$(searchForm).find(`:input[name=\${name}]`).val(value);
		$(searchForm).submit();
	});
});
</script>