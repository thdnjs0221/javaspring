<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<a href="<c:url value='/prod/prodInsert.do'/>" > 신규상품 등록</a>
<table class="table table-border">
	<thead>
		<tr>
			<th>일련번호</th>
			<th>상품명</th>
			<th>상품분류명</th>
			<th>제조사명</th>
			<th>판매가</th>
			<th>세일가</th>
			<th>마일리지</th>
			<th>구매자수</th>
		</tr>
	</thead>
<tbody>
		<c:set var="pordList" value="${paging.dataList }"/>
		<c:if test="${empty pordList}">
			<tr>
				<td colspan="7">검색 조건에 맞는 회원 없음.</td>
			</tr>
		</c:if>
		<c:if test="${not empty pordList}">
			<c:forEach items="${pordList }" var="prod">
				<c:url value="/prod/prodView.do" var="prodViewURL">
					<c:param name="what" value="${prod.prodId }"/>
				</c:url>
				<tr>
					<td>${prod.rnum }</td>
					<td><a href="${prodViewURL}">${prod.prodName }</a></td>
					<td>${prod.lprod.lprodNm }</td>
					<td>${prod.buyer.buyerName }</td>
					<td>${prod.prodPrice }</td>
					<td>${prod.prodSale }</td>
					<td>${prod.prodMileage }</td>
					<td>${prod.prodCount }</td>
				</tr>

			</c:forEach>
		</c:if>
	</tbody>
	   <tfoot>
      <tr>
         <td colspan="8">
            ${paging.pagingHTML }
            <div id="searchUI"  class="row g-3 d-flex justify-content-center">
               <div class="col-auto">
                  <form:select path="detailCondition.prodLgu" id="prodLgu" class="form-select">
                     <option value>상품분류</option>
                     <c:forEach items="${lprodList }" var="lprod">
                        <form:option label="${lprod.lprodNm }" value="${lprod.lprodGu }" />
                     </c:forEach>
                  </form:select>
               </div>
               <div class="col-auto">
                  <form:select path="detailCondition.prodBuyer" class="form-select">
                  <option value>제조사</option>
                     <c:forEach items="${buyerList }" var="buyer">
                        <form:option class="${buyer.buyerLgu}" value="${buyer.buyerId }" label="${buyer.buyerName }" />
                     </c:forEach>
                  </form:select>
               </div>
               <div class="col-auto">
                  <input type="text" name="prodName" placeholder="상품명" class="form-control"/>
               </div>
               <div class="col-auto">
                  <input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
                  <a href="<c:url value='/prod/prodInsert.do'/>" class="btn btn-success">신규상품 등록</a>
               </div>
            </div>
         </td>
      </tr>
   </tfoot>
</table>

<!--전송하는 hidden폼 페이지랑 검색 동시에! -->
<form:form modelAttribute="detailCondition" id="searchForm" method="get">
	<form:input path="prodLgu" readonly="readonly" placeholder="prodLgu"/>
	<form:input path="prodBuyer" readonly="readonly" placeholder="prodLgu"/>
	<form:input path="prodName" readonly="readonly" placeholder="prodLgu"/>
	<input type="text" name="page" readonly="readonly" placeholder="page"/>
</form:form>
<script type="text/javascript">

$("select[name=prodLgu]").on("change",function(event){
	let lgu = $(this).val();
	let options = $("select[name=prodBuyer]").find("option");
	$(options).hide();
	$(options).filter((i,e)=>i==0).show();
	if(lgu){
		$(options).filter(`.\${lgu}`).show();
	}else{
		$(options).show();
	}	
}).trigger("change");


//입력 ui 초기값 바인딩
// $(":input[name=prodLgu]").val("${detailCondition.prodLgu}").trigger("change");
// $(":input[name=prodBuyer]").val("${detailCondition.prodBuyer}");
// $(":input[name=prodName]").val("${detailCondition.prodName}");

//페이징도 같이 서버로 보내기(서버+검색조건)
function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();

}

//검색
$(searchUI).on("click","#searchBtn",function(event){    //this->searchBtn버튼
	let inputs = $(this).parents("#searchUI").find(":input[name]");  //name속성들이 있는것만 가져옴?
	$.each(inputs, function(idx,ipt){
		let name = ipt.name;
		let value = $(ipt).val();  //
		$(searchForm).find(`:input[name=\${name}]`).val(value);
		$(searchForm).submit();
		
	});//$.each
});

</script>