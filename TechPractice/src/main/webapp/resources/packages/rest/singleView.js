	let resultArea = $("#resultArea");
	let headers = {};
	let headerName = $("meta[name='_csrf_header']").attr("content");
	let headerValue = $("meta[name='_csrf']").attr("content");
	headers[headerName] = headerValue;
	
	$(".syncBtn").on("click", function(){
		let status = this.dataset.status;
		syncForm.action = `${$.CPATH}/rest/start/param/${status}`;
		syncForm.requestSubmit();
	});
	
	$("#syncForm").on("submit", function(event){
		let url = this.action;
		let method = this.method;
		let data = {}
		$(this).find(":input[name]").each(function(idx, i){
			data[i.name] = $(this).val();
		});
		
		resultArea.html(`requets method[${method}] based form-> redirect request<br />`);
		
		$.ajax({
			url:url,
			method:method,
			headers: headers,
			data:data,
			dataType:"html"
		}).done(function(resp, textStatus, jqXHR){
			console.log(jqXHR);
			resultArea.append(resp??"no content body");
		}).fail(function(jqXHR, status, error){
			console.log(arguments)
			resultArea.html(`status : ${jqXHR.status}, error : ${status}`)
		});
		
		return false;
	});
	
	$("[data-method]").on("click", function(){
		let method = this.dataset.method;
		let status = this.dataset.status;
		let contentType = this.dataset.mime;
		
		resultArea.html(`requets method[${method}], contentType[${contentType??""}] -> redirect request, status[${status}]<br />`);
		let data = {
			param : "파라미터"
		};
		
		let url = null;
		if(contentType?.indexOf('json') >= 0){
			url = `${$.CPATH }/rest/start/json/${status}`;
		}else{
			url = `${$.CPATH }/rest/start/param/${status}`;
		}
		
		if(method!="get" && contentType){
			headers["Content-Type"] = contentType;
			if(contentType.indexOf('json') >= 0){
				data = JSON.stringify(data);
			}
		}
		
		$.ajax({
			url: url
			, method : method
			, headers : headers
			, data: data
		}).done((resp, status, jqXHR)=>{
			console.log(jqXHR);
			resultArea.append(resp??"no content body");
		}).fail((jqXHR, status, error)=>{
			resultArea.html(`status : ${jqXHR.status}, error : ${status}`)
		});
	});
