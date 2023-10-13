<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
	<tbody id="listBody">

	</tbody>
	<tfoot>
		<tr>
			<td colspan="8">
<span id="pagingArea"></span>
				<div id="searchUI">

					<select name="prodLgu">
						<option value>상품분류</option>
						<c:if test="${not empty lprodList}">
							<c:forEach items="${lprodList }" var="lprod">
								<option label="${lprod.lprodNm }" value="${lprod.lprodGu  }" />
							</c:forEach>
						</c:if>

					</select>
					<select name="prodBuyer">
						<option value>제조사</option>
						<c:if test="${not empty buyerList}">
							<c:forEach items="${buyerList }" var="buyer">
								<option class="${buyer.buyerLgu }" label="${buyer.buyerName }"
									value="${buyer.buyerId  }" />
							</c:forEach>
						</c:if>
					</select> <input type="text" name="prodName" placeholder="상품명" /> <input
						type="button" value="검색" id="searchBtn" /> <a
						href="<c:url value='/prod/prodInsert.do'/>"> 신규상품 등록</a>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<!--전송하는 hidden폼 페이지랑 검색 동시에! -->
<form id="searchForm"
	action='<c:url value="/prod/ajax/prodListData.do"/>'>
	<input type="text" name="prodLgu" readonly="readonly"
		placeholder="prodLgu" /> <input type="text" name="prodBuyer"
		readonly="readonly" placeholder="prodBuyer" /> <input type="text"
		name="prodName" readonly="readonly" placeholder="prodName" /> <input
		type="text" id="currpage" name="page" readonly="readonly"
		placeholder="page" />
</form>
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
});

$(":input[name=prodLgu]").val("${paging.detailCondition.prodLgu}").trigger("change");
$(":input[name=prodBuyer]").val("${paging.detailCondition.prodBuyer}");
$(":input[name=prodName]").val("${paging.detailCondition.prodName}");

function makeTrTag(prod){
	let cPath = document.body.dataset.contextPath;
	let prodViewURL =`\${cPath}/prod/prodView.do?what=\${prod.prodId}`;
	
    let trTags =`
    	<tr>
		<td>\${prod.rnum }</td>
		<td><a href="\${prodViewURL}">\${prod.prodName }</a></td>
		<td>\${prod.lprod.lprodNm }</td>
		<td>\${prod.buyer.buyerName }</td>
		<td>\${prod.prodPrice }</td>
		<td>\${prod.prodSale }</td>
		<td>\${prod.prodMileage }</td>
		<td>\${prod.prodCount }</td>
	</tr>
       `;
    return trTags;
 };



$(searchForm).on("submit",function(event){
	event.preventDefault();  //동기요청 중단-> 비동기
	
	let url = this.action; 
	let method = this.method;  //get
	let data = $(this).serialize();  // 쿼리스트링
	
	$.getJSON(`\${url}?\${data}`) //쿼리 스트링이 있는 url
		.done(function(resp){   //sucess function
			trTags="";
			 let prodList = resp.paging.dataList;
			 if(prodList.length>0){ // 서버에서 받은 응답데이터의 success 속성을 확인하여 응답이 성공적인지 여부를 판단
	             $.each(prodList,function(idx,prod){
	            	 trTags+=makeTrTag(prod);
	            	});
				 $(pagingArea).html(resp.paging.pagingHTML)		 
	         }else{
	            	
	            	trTags+=`
	               <tr>
	                 <td colspan='8'>상품 정보 없음</td>
	               </tr>
	   
	               `
	            } $(listBody).html(trTags);
	         });
	      
	      return false;
		
}).submit();
//페이징도 같이 서버로 보내기(서버+검색조건)
function fn_paging(page){
	searchForm.page.value = page;
	searchForm.requestSubmit();
	searchForm.page.value ="";
}

//검색
$(searchUI).on("click","#searchBtn",function(event){    //this->searchBtn버튼
	let inputs = $(this).parents("#searchUI").find(":input[name]");  //name속성들이 있는것만 가져옴?
	$.each(inputs, function(idx,ipt){
		let name = ipt.name;
		let value = $(ipt).val();  //
		$(searchForm).find(`:input[name=\${name}]`).val(value);
		
		
	});//$.each
	
	
	$(searchForm).submit();
	
});

</script>