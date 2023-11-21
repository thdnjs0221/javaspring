<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row mt-3">
	<pre>
		SPA 기반의 페이징  처리 예제 테스트시 브라우저의 backward/forward 기능으로 테스틓할 것.
	</pre>
	<table class="table table-bordered">
	    <thead>
	        <tr>
	            <th>상품명</th>
	            <th>상품분류</th>
	            <th>거래처명</th>
	            <th>소재지</th>
	            <th>구매가</th>
	            <th>판매가</th>
	            <th>마일리지</th>
	        </tr>
	    </thead>
		<tbody class="listBody">
		
		</tbody>	
		<tfoot>
			<tr>
				<td colspan="7">
					<div class="pagingArea"></div>
					<div id="searchUI">
						<input type="text" name="prodLgu" placeholder="분류코드"/>
						<input type="text" name="prodBuyer" placeholder="거래처코드"/>
						<input type="text" name="prodName" placeholder="상품명"/>
						<input class="btn btn-success" type="button" value="검색" id="searchBtn"  />
					</div>
				</td>
			</tr>
		</tfoot>  
	</table>
	
	<h4>Hidden form</h4>
	<form id="searchForm" action="${pageContext.request.contextPath }/paging/spa" 
		class="forajax"
		data-data-type="json"
		data-search="searchUI"
		data-success="fnSuccess">
		<input type="text" name="page" />	
		<input type="text" name="prodLgu" placeholder="분류코드"/>
		<input type="text" name="prodBuyer" placeholder="거래처코드"/>
		<input type="text" name="prodName" placeholder="상품명"/>
	</form>
</div>
<script>
$(document).on("submit", ".forajax", function(event){
	event.preventDefault();
	
	let url = this.action;
	let method = this.method;
	let queryString = $(this).serialize();
	let dataType = this.dataset.dataType;
	let formId = this.id??`form_\${Date.now()}`;
	let successFunc = window[this.dataset.success];
	this.id = formId;
	let settings = {
			url : url,
			method : method,
			data : queryString,
			dataType : dataType,
			targetForm : formId,
			success:successFunc,
			global : true // ajaxSend, ajaxStop, ajaxComplete 사용 여부 
		};
	
	$.ajax(settings);
	
	return false;
});

$(document).on('ajaxSend', function(event, jqXHR, settings){
	console.log("ajax send");
});
$(document).on('ajaxComplete', function(event, jqXHR, settings){
	console.log("ajax complete");
	if(jqXHR.state()=='rejected') return false;
	
	history.pushState({
		response : jqXHR.responseJSON
		, targetForm : settings.targetForm
	}, '', settings.url);
});
$(document).on('ajaxStop', function(event, jqXHR, settings){
	console.log("ajax stop");
});

window.addEventListener('popstate', function(event){
	console.log(event)
	if(event.state && event.state.targetForm){
		let resp = event.state.response;
		let targetForm = document.getElementById(event.state.targetForm);
		let searchUI = document.getElementById(targetForm.dataset.search);
		let successFunc = targetForm.dataset.success;
		let url = new URL(event.target.location);
		for(let name of url.searchParams.keys()){
			let values = url.searchParams.getAll(name);
			let iptsTarget = $(targetForm).find(`[name=\${name}]`);
			let iptsSearch = $(searchUI).find(`[name=\${name}]`);
			for(let idx in values){
				$(iptsTarget[idx]).val(values[0]);
				$(iptsSearch[idx]).val(values[0]);
			}
		}
		window[successFunc](resp);
	}
	return false;
});




let fn_paging = function(page, event){return true;};
let pagingArea = $(".pagingArea").on("click", "a.paging", function(event){
	event.preventDefault();
	let page = $(this).data("page");
	if(!page) return false;
	searchForm.find("[name=page]").val(page);
	searchForm.submit();
	return false;
});

let makeTrTag = function(prod){
	return $("<tr>").append(
				$("<td>").html(prod.prodName)
				, $("<td>").html(prod.lprodGu)
				, $("<td>").html(prod.buyer.buyerName)
				, $("<td>").html(prod.prodAdd1)
				, $("<td>").html(prod.prodCost)
				, $("<td>").html(prod.prodPrice)
				, $("<td>").html(prod.prodMileage)
				
			);
}

var fnSuccess = function(resp, status, jqXHR){
	listBody.empty();
	pagingArea.empty();
	
	searchForm[0].page.value="";
	
	let pagingVO = resp.pagingVO;
	
	let dataList = pagingVO.dataList;
	let trTags = [];
	if(dataList){
		$.each(dataList, function(index, prod){
			trTags.push(makeTrTag(prod));
		});
	}else{
		let tr = $("<tr>").html(
			$("<td>").attr("colspan", "7")
					.html("조건에 맞는 상품 없음.")
		);	
		trTags.push(tr);
	}
	listBody.html(trTags);
	if(resp.pagingHTML)
		pagingArea.html(resp.pagingHTML);
}

let listBody = $(".listBody");

let searchForm = $("#searchForm").submit();

let searchUI = $("#searchUI").on("click", "#searchBtn", function(){
	let inputs = searchUI.find(":input[name]");
	$.each(inputs, function(index, input){
		let name = this.name;
		let value = $(this).val();
		searchForm[0][name].value = value;
	});
	searchForm.submit();
});

</script>