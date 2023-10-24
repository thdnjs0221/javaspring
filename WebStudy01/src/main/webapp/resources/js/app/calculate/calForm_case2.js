/**
 * 
 */
$(function(){ //doucument load 작업이 끝났을때 실행  $()doucument).on("load |ready"),function(){});
	
	let selectValue = $(calForm.operator).data("initValue");//js는 서버사이드 코드를 넣을 수 없음 . 대신 데이터 속성 사용 가능(calForm.jsp)
$(calForm.operator).val(selectValue);
 
	$(calForm).on('submit',function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize(); //quer String

		let settings = {
				url: url,
				method: method,
				data: data, 
				dataType:"html", //Accept request header : Content-Type response header
						
			    success:function(resp){
			    	$(resultArea).html(resp)		
			    	
						},error: function(jqxhr,status,error){
							console.log("jqxhr:",jqxhr);
							console.log("status:",status);
							console.log("error:",error);
						}
		};
		$.ajax(settings);
		return false; //안전하게 한번 더 
	});
	
	
});

