/**
 * 
 */
let listBody = $("#listBody");
let viewUrl = listBody.data("viewUrl");
let pagingArea = $(".pagingArea");
let fn_makeTr = function(board){
	let aTag = $("<a>").attr("href", `${viewUrl}?what=${board.boNo}`)
						.html(`${board.boTitle}[${board.atchCount}]`);
	return $("<tr>").append(
		$("<td>").html(board.rnum)		
		, $("<td>").html(aTag)		
		, $("<td>").html(board.boWriter)		
		, $("<td>").html(board.boDate)		
		, $("<td>").html(board.boHit)		
	);
}
let searchForm = $("[name=searchForm]").on("submit", function(event){
	event.preventDefault();
	let url = this.action;
	let method = this.method;
	let data = $(this).serialize();
	$.ajax({
		url : url,
		method : method,
		data : data,
		dataType : "json"
	}).done(function(resp, textStatus, jqXHR) {
		listBody.empty();
		pagingArea.empty();
		
		let trTags = [];
		if(resp.dataList.length > 0){
			$.each(resp.dataList, function(idx, board){
				trTags.push( fn_makeTr(board) );
			});
			pagingArea.html(resp.pagingHTML);
		}else{
			trTags.push($("<tr>").html($("<td colspan='5'>").html("게시글 없음.")));
		}
		listBody.append(trTags);
	});
	return false;
}).submit();
let searchUI = $("#searchUI").on("click", "#searchBtn" , function(){
	$(this).parents("#searchUI").find(":input[name]").each(function(idx, input){
		let iptName = input.name;
		let iptValue = $(input).val();
		searchForm.find(`[name=${iptName}]`).val(iptValue);
	});
	searchForm.submit();
});
let fn_paging = function(page, event){
	searchForm.find("[name=page]").val(page);
	searchForm.submit();
	searchForm.find("[name=page]").val("");
	return false;
}