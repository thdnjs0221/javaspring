/**
 * 
 */
$(function() { //doucument load 작업이 끝났을때 실행  $()doucument).on("load |ready"),function(){});

	let selectValue = $(calForm.operator).data("initValue");//js는 서버사이드 코드를 넣을 수 없음 . 대신 데이터 속성 사용 가능(calForm.jsp)
	$(calForm.operator).val(selectValue);
	
	

	$(calForm).on('submit', function(event) {
		event.preventDefault();

		let url = this.action;
		let method = this.method;

		//파라미터로 말고 json payload
		let settings = {
			url: url,
			method: method,
			
			
			
			
			dataType: "json", //Accept request header : Content-Type response header

			success: function(resp) {
			
				$(resultArea).html(resp.calVO.expression);

			}, error: function(jqxhr, status, error) {
				console.log("jqxhr:", jqxhr);
				console.log("status:", status);
				console.log("error:", error);
			}
		};
		
		let contentType = $("[name = contentType]:checked").data("contentType");
		 if(contentType && contentType.indexOf("json")>=0){ //json이 맞으면
	
         	let data = $(this).serializeJSON(); //js object
			let json = JSON.stringify(data); //마셜링해주기
			settings.contentType = contentType;
			settings.data = json;
			
      	}else{
         // parameter일때 
			let data =  $(this).serialize(); //query String
			settings.data = data;
      	}

		$.ajax(settings);
		return false; //안전하게 한번 더 
	});


});

